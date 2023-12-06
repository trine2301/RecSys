package recsys.service;

import lombok.NonNull;
import recsys.exceptions.CouldNotCompareTransactionsException;
import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
import recsys.model.ComparisonEntity;
import recsys.model.Result;
import recsys.model.Status;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;

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
    private double bankTotal;
    private double accountingTotal;

    List<ComparisonEntity> comparisonEntities = new ArrayList<>();

    //List<ComparisonEntity> matchedEntities = new ArrayList<>();

    public TransactionService(AccountingTransactionRepository accountingTransactionRepository, BankTransactionRepository bankTransactionRepository) {
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.bankTransactionRepository = bankTransactionRepository;
    }


    /**
     * Sets the total amount for bank and for accounting transactions, based on the amounts in each transaction in BankTransactionEntity and AccountingTransactionEntity.
     */
    public void setBankAndAccountingTotalAmount() {
        List<BankTransactionEntity> bankTransactions = bankTransactionRepository.findAll();
        List<AccountingTransactionEntity> accountingTransactions = accountingTransactionRepository.findAll();

        bankTotal = 0;
        accountingTotal = 0;

        for (BankTransactionEntity bankTransaction : bankTransactions) {
            bankTotal += bankTransaction.getAmount();
        }
        for (AccountingTransactionEntity accountingTransaction : accountingTransactions) {
            bankTotal += accountingTransaction.getAmount();
        }
    }


    /**
     * Gets the total discrepancy between total amount of bank transactions and accounting transactions.
     *
     * @return returns total discrepancy between bank transactions and accounting transactions.
     * TODO this one gives wrong number. I think it adds instead of doing the right thing.
     */
    public double getDiscrepancyAmount(LocalDate startDate, LocalDate endDate) {

        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findByDateBetween(startDate, endDate);
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findByDateBetween(startDate, endDate);
        int discrepancyAmount = 0;

        //setBankAndAccountingTotalAmount();
        bankTotal = 0;
        accountingTotal = 0;

        //Setting the total amount for bank transactions.
        for (BankTransactionEntity bankTransaction : bankTransList) {
            bankTotal += bankTransaction.getAmount();
        }
        //Setting the total amount for bank transactions.
        for (AccountingTransactionEntity accountingTransaction : accTransList) {
            accountingTotal += accountingTransaction.getAmount();
        }

        //If banktotal is larger than accountingtotal, amount will be bank minus accounting
        if (bankTotal > accountingTotal) {
            discrepancyAmount = (int) (bankTotal - accountingTotal);
        }
        //If accountingtotal is larger than bankTotal, amount will be accounting minus bank
        if (bankTotal < accountingTotal) {
            discrepancyAmount = (int) (accountingTotal - bankTotal);
        }
        System.out.println(discrepancyAmount);
        return discrepancyAmount;
    }


    /**
     * Checks if there is any discrepancy on the total amount between bankTransactions and AccountingTransactions.
     *
     * @return Returns True if there is a discrepancy.
     */
    public boolean checkIfDiscrepancyOnTotalAmountExists() {
        return (bankTotal - accountingTotal != 0);
    }

    /**
     * Compares bank transactions with accounting transactions, and assigns a result.
     */
    public List<ComparisonEntity> compareTransactions(LocalDate startDate, LocalDate endDate) {

        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findByDateBetween(startDate, endDate);
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findByDateBetween(startDate, endDate);


        List<ComparisonEntity> comparedEntities = new ArrayList<>();

        Map<BankTransactionEntity, Boolean> bankTransMatches = new HashMap<>();

        for (AccountingTransactionEntity accEntity : accTransList) {
            boolean foundMatch = false;
            for (BankTransactionEntity bankEntity : bankTransList) {
                if (accEntity.getDate().equals(bankEntity.getDate()) && accEntity.getAmount() == bankEntity.getAmount()) {
                    ComparisonEntity entity = new ComparisonEntity();
                    entity.setAccountingTransactionEntity(accEntity);
                    entity.setBankTransactionEntity(bankEntity);
                    entity.setResult(Result.MATCH);
                    comparedEntities.add(entity);
                    foundMatch = true;
                    bankTransMatches.put(bankEntity, true);
                } else if (accEntity.getDate().equals(bankEntity.getDate()) && accEntity.getAmount() != bankEntity.getAmount()) {
                    ComparisonEntity entity = new ComparisonEntity();
                    entity.setAccountingTransactionEntity(accEntity);
                    entity.setBankTransactionEntity(bankEntity);
                    entity.setResult(Result.PARTIAL_MATCH);
                    comparedEntities.add(entity);
                    foundMatch = true;
                    bankTransMatches.put(bankEntity, true);
                }
            }
            if (!foundMatch) {
                ComparisonEntity entity = new ComparisonEntity();
                entity.setAccountingTransactionEntity(accEntity);
                entity.setResult(Result.MISSING_BANK_TRANS);
                comparedEntities.add(entity);
            }
        }
        for (BankTransactionEntity bankent : bankTransList) {
            if (!bankTransMatches.containsKey(bankent)) {
                ComparisonEntity entity = new ComparisonEntity();
                entity.setBankTransactionEntity(bankent);
                entity.setResult(Result.MISSING_ACC_TRANS);
                //comparisonEntities.add(entity);
                comparedEntities.add(entity);
            }
        }

        //Add all comparedEntities into comparisonEntities, after removing duplicates that are not Result.Match.
        comparisonEntities.addAll(removeDuplicatesNotMatches(comparedEntities));
        return comparisonEntities;

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
     * Compares bank transactions with accounting transactions, and assigns a result.
     * @return Returns a list of compared entities.
     * @throws CouldNotCompareTransactionsException
     */
    public List<ComparisonEntity> compareResults() throws CouldNotCompareTransactionsException {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();
        //ComparisonEntity matchedEntity = new ComparisonEntity();

        int accTransIndex = 0;
        int bankTransIndex = 0;

        while (accTransIndex < accTransList.size()) {
            while (bankTransIndex < bankTransList.size()) {
                AccountingTransactionEntity accTrans = accTransList.get(accTransIndex);
                BankTransactionEntity bankTrans = bankTransList.get(bankTransIndex);

                //Matches
                if (comparisonEntities.contains(accTrans)) {
                    giveResultsForIteration(Result.MATCH, accTransIndex, bankTransIndex);
                    break;
                    //partial match
                } else if (isSameDate(accTrans, bankTrans) || !isSameAmount(accTrans, bankTrans)) {
                    if (comparisonEntities.contains(accTrans)) {
                        break;
                    } else {
                        giveResultsForIteration(Result.PARTIAL_MATCH, bankTransIndex, accTransIndex);
                    }
                }
                bankTransIndex++;
            }
            accTransIndex++;
        }
        return comparisonEntities;
    }


    /**
     * TODO write better documentation.
     * Gives a result for the indexes and adds result to ComparisonEntity.
     *
     * @param result         the result that is set.
     * @param bankTransIndex the index
     * @param accTransIndex  the other index
     */
    public void giveResultsForIteration(Result result, int bankTransIndex, int accTransIndex) {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();

        ComparisonEntity comparisonEntity = new ComparisonEntity();

        comparisonEntity.setAccountingTransactionEntity(accTransList.get(accTransIndex));
        comparisonEntity.setBankTransactionEntity(bankTransList.get(bankTransIndex));
        comparisonEntity.setResult(result);
        comparisonEntity.setStatus(Status.MATCH_COMPLETE);
        comparisonEntities.add(comparisonEntity);
    }


    /**
     * This is the method sent to frontend.
     */
    public List<ComparisonEntity> setComparingResults() throws CouldNotCompareTransactionsException {

        //compareTransactions();
        compareResults();
        //Set<String> s = new LinkedHashSet<>(Collections.singleton(comparisonEntities.toString()));

        return comparisonEntities;
    }

    /**
     * Method to check if a transaction is matching another transaction.
     *
     * @param transaction1 the transaction to match
     * @param transaction2 that transaction to match
     * @return Returns true if accoount1 has the same date and amount as transaction2.
     */
    public boolean isMatching(@NonNull AccountingTransactionEntity transaction1, @NonNull BankTransactionEntity transaction2) {
        return transaction1.getDate().equals(transaction2.getDate()) && transaction1.getAmount() == transaction2.getAmount();
    }

    /**
     * HelperMethod
     */
    private boolean isSameDate(@NonNull AccountingTransactionEntity transaction1, @NonNull BankTransactionEntity transaction2) {
        return transaction1.getDate().equals(transaction2.getDate());
    }

    /**
     * HelperMethod
     */
    private boolean isSameAmount(@NonNull AccountingTransactionEntity transaction1, @NonNull BankTransactionEntity transaction2) {
        return transaction1.getAmount() == transaction2.getAmount();
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








