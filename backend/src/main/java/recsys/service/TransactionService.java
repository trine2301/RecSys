package recsys.service;


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


    public TransactionService(AccountingTransactionRepository accountingTransactionRepository, BankTransactionRepository bankTransactionRepository, PeriodRepository repository) {
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.bankTransactionRepository = bankTransactionRepository;
        this.periodRepository = repository;
    }


    /**
     * Populate periodEntities with data from comparison.
     * @param startDate The start date for the comparison
     * @param endDate The end date for comparison
     * @param accTotal The total amount of accounting transactions
     * @param bankTotal The total amount of bank transactions
     * @param totalDiscrepancyAmount Total discrepancy amount between the sets of transactions
     * @return returns a list of saved entities in periodEntities
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
     * Gets total amount for accounttransactions for a given time-period
     *
     * @return returns total amount in accounting transactions for the given time period
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
     * Gets total amount for accounttransactions for a given time-period

     * @return Returns total amount in bank transactions for the given time period
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
     * Gets the total discrepancy between total amount of bank transactions and accounting transactions for a desired time period.
     *
     * @param startDate Start date
     * @param endDate End date
     * @return Returns total discrepancy between bank transactions and accounting transactions.
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
     * Removes duplicates from list that are not of result.MATCH.
     *
     * @param comparedEntities The list to remove duplicates from.
     * @return returns a list of comparedEntities without duplicates where result is a match.
     */
    public List<ComparisonEntity> removeDuplicatesNotMatches(List<ComparisonEntity> comparedEntities) {
        return comparedEntities.stream().filter(CB1 -> {
            if (CB1.getResult().equals(Result.PARTIAL_MATCH)) {
                return comparedEntities.stream().noneMatch(CB2 ->
                    CB2.getResult().equals(Result.MATCH) && CB1.getAccountingTransactionEntity().equals(CB2.getAccountingTransactionEntity())
                );
            } else {
                return true;
            }
        }).toList();
    }

    /**
     * Compares transactions within a desired period of time.
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ComparisonEntity> compareTransactions(LocalDate startDate, LocalDate endDate) {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findByDateBetween(startDate, endDate);
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findByDateBetween(startDate, endDate);
        List<ComparisonEntity> comparedEntities = new ArrayList<>();

        Map<BankTransactionEntity, Boolean> bankTransMatches = new HashMap<>();

        for (AccountingTransactionEntity accEntity : accTransList) {
            handleMatches(accEntity, bankTransList, comparedEntities, bankTransMatches);
        }

        handleUnmatchedBankTransactions(bankTransList, comparedEntities, bankTransMatches);

        comparisonEntities.addAll(removeDuplicatesNotMatches(comparedEntities));
        return comparisonEntities;
    }

    /**
     * Method that handles the matches of a comparison.
     * @param accEntity
     * @param bankTransList
     * @param comparedEntities
     * @param bankTransMatches
     */
    private void handleMatches(AccountingTransactionEntity accEntity, List<BankTransactionEntity> bankTransList, List<ComparisonEntity> comparedEntities, Map<BankTransactionEntity, Boolean> bankTransMatches) {
        for (BankTransactionEntity bankEntity : bankTransList) {
            if (accEntity.getDate().equals(bankEntity.getDate())) {
                ComparisonEntity entity = createComparisonEntity(accEntity, bankEntity);
                comparedEntities.add(entity);
                bankTransMatches.put(bankEntity, true);
            }
        }
    }

    /**
     *
     * @param bankTransList
     * @param comparedEntities
     * @param bankTransMatches
     */
    private void handleUnmatchedBankTransactions(List<BankTransactionEntity> bankTransList, List<ComparisonEntity> comparedEntities, Map<BankTransactionEntity, Boolean> bankTransMatches) {
        for (BankTransactionEntity bankEntity : bankTransList) {
            if (!bankTransMatches.containsKey(bankEntity)) {
                ComparisonEntity entity = createComparisonEntityWithMissingAccTrans(bankEntity);
                comparedEntities.add(entity);
            }
        }
    }

    private ComparisonEntity createComparisonEntity(AccountingTransactionEntity accEntity, BankTransactionEntity bankEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setAccountingTransactionEntity(accEntity);
        entity.setBankTransactionEntity(bankEntity);
        if (accEntity.getAmount() == bankEntity.getAmount()) {
            entity.setResult(Result.MATCH);
        } else {
            entity.setResult(Result.PARTIAL_MATCH);
        }
        return entity;
    }

    private ComparisonEntity createComparisonEntityWithMissingAccTrans(BankTransactionEntity bankEntity) {
        ComparisonEntity entity = new ComparisonEntity();
        entity.setBankTransactionEntity(bankEntity);
        entity.setResult(Result.MISSING_ACC_TRANS);
        return entity;
    }


}








