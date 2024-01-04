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
 * Represents a comparison model. Contains logic to compare two files etc.
 */
@Singleton
public class TransactionService {

    private final AccountingTransactionRepository accountingTransactionRepository;
    private final BankTransactionRepository bankTransactionRepository;


    public final PeriodRepository periodRepository;

    List<ComparisonEntity> comparisonEntities = new ArrayList<>();


    List<ComparisonEntity> comparedEntities = new ArrayList<>();

    public TransactionService(AccountingTransactionRepository accountingTransactionRepository, BankTransactionRepository bankTransactionRepository, PeriodRepository repository) {
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.bankTransactionRepository = bankTransactionRepository;
        this.periodRepository = repository;
    }




    /**
     * Populate periodEntities
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
     * Gets total amount for account transactions for a given time-period

     * @return
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

     * @return Returns total amount for bank transactions for a given time-period.
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
     * @return returns total discrepancy between bank transactions and accounting transactions.
     */
    public double getDiscrepancyAmount(LocalDate startDate, LocalDate endDate) {

        double discrepancyAmount = 0;
        double bankTotal = getTotalBankSum(startDate, endDate);
        double accountingTotal = getTotalAccSum(startDate, endDate);


        //If banktotal is larger than accountingtotal, amount will be bank minus accounting
        if (bankTotal > accountingTotal) {
            discrepancyAmount = bankTotal - accountingTotal;
        }
        //If accountingtotal is larger than bankTotal, amount will be accounting minus bank
        if (bankTotal < accountingTotal) {
            discrepancyAmount = accountingTotal - bankTotal;
        }
        return discrepancyAmount;
    }



    /**
     * Compares bank transactions with accounting transactions, and assigns a result.
     */
    public List<ComparisonEntity> compareTransactions(LocalDate startDate, LocalDate endDate) {

        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findByDateBetween(startDate, endDate);
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findByDateBetween(startDate, endDate);

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
     * Method that give result to comparison
     * @param result The result to be given the comparison
     * @param accEntity
     * @param bankEntity
     */
    public void giveResultsForMatchOrPartial(Result result, AccountingTransactionEntity accEntity, BankTransactionEntity bankEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setAccountingTransactionEntity(accEntity);
        entity.setBankTransactionEntity(bankEntity);
        entity.setResult(result);
        comparedEntities.add(entity);
    }

    /**
     * Method that give result to comparison
     * @param accEntity
     */
    public void resultMissingBank(AccountingTransactionEntity accEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setAccountingTransactionEntity(accEntity);
        entity.setResult(Result.MISSING_BANK_TRANS);
        comparedEntities.add(entity);
    }


    /**
     * Method that give result to comparison
     * @param bankEntity
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
     * @param accTrans the transaction to match
     * @param bankTrans that transaction to match
     * @return Returns true if accoount1 has the same date and amount as bankTrans.
     */
    public boolean isMatching(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {
        return accTrans.getDate().equals(bankTrans.getDate()) && accTrans.getAmount() == bankTrans.getAmount();
    }

    /**
     * HelperMethod
     */
    private boolean isSameDate(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {
        return accTrans.getDate().equals(bankTrans.getDate());
    }

    /**
     * HelperMethod
     */
    private boolean isSameAmount(@NonNull AccountingTransactionEntity accTrans, @NonNull BankTransactionEntity bankTrans) {
        return accTrans.getAmount() == bankTrans.getAmount();
    }

    /**
     * HelperMethod
     */
    private boolean isSameDescription(@NonNull AccountingTransactionEntity transaction1, @NonNull BankTransactionEntity transaction2) {

        if (transaction1.getDescription() == null || transaction2.getDescription() == null) {
            return false;
        } else {
            return transaction1.getDescription().equals(transaction2.getDescription());

        }
    }
}