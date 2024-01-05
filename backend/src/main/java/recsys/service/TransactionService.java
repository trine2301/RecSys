package recsys.service;

import lombok.NonNull;
import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
import recsys.model.ComparisonEntity;
import recsys.model.PeriodEntity;
import recsys.model.Result;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;
import recsys.repository.PeriodRepository;

import jakarta.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class represents a comparison model. Contains logic to compare two transactions.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */

@Singleton
public class TransactionService {

    private final AccountingTransactionRepository accountingTransactionRepository;
    private final BankTransactionRepository bankTransactionRepository;
    public final PeriodRepository periodRepository;
    List<ComparisonEntity> comparisonEntities = new ArrayList<>();
    List<ComparisonEntity> comparedEntities = new ArrayList<>();

    /**
     * Constructor for objects of class TransactionService.
     *
     * @param accountingTransactionRepository the repository of accounting transactions.
     * @param bankTransactionRepository       the repository of bank transactions.
     * @param periodRepository                the repository of period comparisons.
     */
    public TransactionService(AccountingTransactionRepository accountingTransactionRepository, BankTransactionRepository bankTransactionRepository, PeriodRepository periodRepository) {
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.bankTransactionRepository = bankTransactionRepository;
        this.periodRepository = periodRepository;
    }


    /**
     * Populates PeriodEntity with data and saves it.
     *
     * @param startDate              The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate                The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @param accTotal               The total sums of accounting transactions.
     * @param bankTotal              The total sum of banking transactions.
     * @param totalDiscrepancyAmount The total discrepancy between the sum of transactions.
     * @return Returns a list of saved entities of type PeriodEntity.
     */
    public List<PeriodEntity> populatePeriodEntity(LocalDate startDate, LocalDate endDate, double accTotal, double bankTotal, double totalDiscrepancyAmount) {
        PeriodEntity entity = new PeriodEntity();
        List<PeriodEntity> entities = new ArrayList<>();

        entity.setStartDate(startDate);
        entity.setEndDate(endDate);
        entity.setAccTotal(accTotal);
        entity.setBankTotal(bankTotal);
        entity.setTotalDiscrepancyAmount(totalDiscrepancyAmount);
        entities.add(entity);

        return (List<PeriodEntity>) periodRepository.saveAll(entities);
    }


    /**
     * Gets total amount for account transactions for a given time-period.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return Returns total amount for account transactions for the selected period.
     */
    public double getTotalAccSum(LocalDate startDate, LocalDate endDate) {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findByDateBetween(startDate, endDate);
        double total = 0;

        for (AccountingTransactionEntity accountingTransaction : accTransList) {
            total += accountingTransaction.getAmount();
        }

        return total;
    }

    /**
     * Gets total amount for bank transactions for a given time-period.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return Returns total amount for account transactions for the selected period.
     */
    public double getTotalBankSum(LocalDate startDate, LocalDate endDate) {

        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findByDateBetween(startDate, endDate);

        double total = 0;
        for (BankTransactionEntity bankTransaction : bankTransList) {
            total += bankTransaction.getAmount();
        }
        return total;
    }


    /**
     * Gets the total discrepancy between total amount of bank transactions and accounting transactions.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return returns total discrepancy between bank transactions and accounting transactions.
     */
    public double getDiscrepancyAmount(LocalDate startDate, LocalDate endDate) {

        double discrepancyAmount = 0;
        double bankTotal = getTotalBankSum(startDate, endDate);
        double accountingTotal = getTotalAccSum(startDate, endDate);

        if (bankTotal > accountingTotal) {
            discrepancyAmount = bankTotal - accountingTotal;
        }
        if (bankTotal < accountingTotal) {
            discrepancyAmount = accountingTotal - bankTotal;
        }
        return discrepancyAmount;
    }


    /**
     * Compares bank transactions with accounting transactions within a given period.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return A list of ComparisonEntity objects representing the compared transactions. Each entity contains information about the matching or partial matching transactions.
     */
    public List<ComparisonEntity> compareTransactions(LocalDate startDate, LocalDate endDate) {

        List<AccountingTransactionEntity> accTransList;
        List<BankTransactionEntity> bankTransList;

        try {
            accTransList = accountingTransactionRepository.findByDateBetween(startDate, endDate);
            bankTransList = bankTransactionRepository.findByDateBetween(startDate, endDate);
        } catch (Exception e) {
            throw new RuntimeException("Error occured when comparing transactions", e);
        }

        if (accTransList == null || bankTransList == null) {
            throw new IllegalStateException("Failed to retrieve transactions from database");
        }

        Map<BankTransactionEntity, Boolean> bankTransMatches = new HashMap<>();

        for (AccountingTransactionEntity accEntity : accTransList) {
            boolean foundMatch = false;
            for (BankTransactionEntity bankEntity : bankTransList) {
                if (isMatching(accEntity, bankEntity)) {
                    giveResultsForMatchOrPartial(Result.MATCH, accEntity, bankEntity);
                    bankTransMatches.put(bankEntity, true);
                } else if (accEntity.getDate().equals(bankEntity.getDate()) && accEntity.getAmount() != bankEntity.getAmount()) {
                    giveResultsForMatchOrPartial(Result.PARTIAL_MATCH, accEntity, bankEntity);
                    foundMatch = true;
                    bankTransMatches.put(bankEntity, true);
                }
            }
            if (!foundMatch) {
                resultMissingBank(accEntity);
            }
        }
        for (BankTransactionEntity bankEntity : bankTransList) {
            if (!bankTransMatches.containsKey(bankEntity)) {
                resultMissingAcc(bankEntity);
            }
        }
        comparisonEntities.addAll(removeDuplicatesNotMatches(comparedEntities));
        return comparisonEntities;
    }

    /**
     * Method that give result to comparison.
     *
     * @param result     The result to be given the comparison
     * @param accEntity  The entity representing an accounting transaction and one side of the match/partial match.
     * @param bankEntity The entity representing an accounting transaction and one side of the match/partial match.
     */
    public void giveResultsForMatchOrPartial(Result result, AccountingTransactionEntity accEntity, BankTransactionEntity bankEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setAccountingTransactionEntity(accEntity);
        entity.setBankTransactionEntity(bankEntity);
        entity.setResult(result);
        comparedEntities.add(entity);
    }


    /**
     * Method that give the result of missing bank transaction to comparison.
     *
     * @param accEntity The entity representing an accounting transaction missing a correspondent bank transaction.
     */
    public void resultMissingBank(AccountingTransactionEntity accEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setAccountingTransactionEntity(accEntity);
        entity.setResult(Result.MISSING_BANK_TRANS);
        comparedEntities.add(entity);
    }


    /**
     * Method that give the result of missing accounting transaction to comparison.
     *
     * @param bankEntity The entity representing a bank transaction missing a correspondent accounting transaction.
     */
    public void resultMissingAcc(BankTransactionEntity bankEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setBankTransactionEntity(bankEntity);
        entity.setResult(Result.MISSING_ACC_TRANS);
        comparedEntities.add(entity);
    }


    /**
     * Removes duplicates from list that are not of result.MATCH.
     * This method was created in a mob-session.
     *
     * @param comparedEntities The list to remove duplicates from.
     * @return returns a list of comparedEntities without duplicates where result is a match.
     */
    public List<ComparisonEntity> removeDuplicatesNotMatches(List<ComparisonEntity> comparedEntities) {
        return comparedEntities.stream().filter(comparisonEntity1 -> {
            if (comparisonEntity1.getResult().equals(Result.PARTIAL_MATCH) || comparisonEntity1.getResult().equals(Result.MISSING_BANK_TRANS)) {
                return comparedEntities.stream().noneMatch(CB2 ->
                    CB2.getResult().equals(Result.MATCH) && comparisonEntity1.getAccountingTransactionEntity().equals(CB2.getAccountingTransactionEntity())
                );
            } else {
                return true;
            }
        }).toList();
    }


    /**
     * Method to check if a transaction is matching another transaction.
     *
     * @param accTrans  the transaction to match
     * @param bankTrans that transaction to match
     * @return Returns true if accoount1 has the same date and amount as bankTrans.
     */
    public boolean isMatching(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {
        return accTrans.getDate().equals(bankTrans.getDate()) && accTrans.getAmount() == bankTrans.getAmount();
    }


    /**
     * A method to check if two transactions are of same date.
     *
     * @param accTrans  One of the transactions to compare date with.
     * @param bankTrans One of the transactions to compare date with.
     * @return Return true if the two transactions are of same date.
     */
    private boolean isSameDate(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {
        return accTrans.getDate().equals(bankTrans.getDate());
    }

    /**
     * A method to check if two transactions are of same amount.
     *
     * @param accTrans  One of the transactions to compare date amount.
     * @param bankTrans One of the transactions to compare date amount.
     * @return Return true if the two transactions are of same amount.
     */
    private boolean isSameAmount(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {
        return accTrans.getAmount() == bankTrans.getAmount();
    }

    /**
     * A method to check if two transactions are of same description.
     *
     * @param accTrans  One of the transactions to compare date description.
     * @param bankTrans One of the transactions to compare date description.
     * @return Return true if the two transactions are of same description.
     */
    private boolean isSameDescription(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {

        if (accTrans.getDescription() == null || bankTrans.getDescription() == null) {
            return false;
        } else {
            return accTrans.getDescription().equals(bankTrans.getDescription());

        }
    }
}