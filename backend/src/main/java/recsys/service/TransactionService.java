package recsys.service;

import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
//import recsys.model.ComparisonEntity;
import recsys.model.ComparisonEntity;
import recsys.model.Result;
import recsys.model.Status;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;

import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Represents a comparison model. Contains logic to compare two files etc.
 *
 */
@Singleton
public class TransactionService {

    private final AccountingTransactionRepository accountingTransactionRepository;
    private final BankTransactionRepository bankTransactionRepository;
    private double bankTotal;
    private double accountingTotal;

    List<ComparisonEntity> comparisonEntities = new ArrayList<>();

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
     * Compares total amount
     * @return returns total discrepancy.
     */
    public double getDiscrepancyAmount() {

        double discrepancyAmount = 0;

        setBankAndAccountingTotalAmount();

        if(checkIfDiscrepancyOnTotalAmountExists() == true) {
            discrepancyAmount = bankTotal - accountingTotal;
        } if(discrepancyAmount < 0) {
            discrepancyAmount = accountingTotal - bankTotal;
        }
        return discrepancyAmount;
    }


    /**
     * Checks if there is any discrepancy on the total amount between bankTransactions and AccountingTransactions.
     * @return Returns True if there is a discrepancy.
     */
    public boolean checkIfDiscrepancyOnTotalAmountExists() {

        boolean isDiscrepancy = false;

        setBankAndAccountingTotalAmount();

        if(bankTotal - accountingTotal != 0) {
            isDiscrepancy = true;
        }
        return isDiscrepancy;
    }

    /**
     * Check if an accounting transaction can find a matching bank transaction. Status = MATCH, add to List<ComparisonEntity>
     */
    public List<ComparisonEntity> findMatches() {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();

        int accTransIndex = 0;
        int bankTransIndex = 0;

        while (accTransIndex < accTransList.size()) {
            while (bankTransIndex < bankTransList.size()) {
                if (isMatching(accTransList.get(accTransIndex), bankTransList.get(bankTransIndex))) {
                    giveResultsForIteration(Result.MATCH, accTransIndex, bankTransIndex);
                    break;
                } else {
                    giveResultsForIteration(Result.PARTIAL_MATCH, accTransIndex, bankTransIndex);
                }
                //if (!isMatching(accTransList.get(accTransIndex), bankTransList.get(bankTransIndex))) {
                //    giveResultsForIteration(Result.MISSING_BANK_TRANS, accTransIndex, bankTransIndex);
                //    break;
                //}
                bankTransIndex++;
            }
            accTransIndex++;
        }

        //for ()
        return comparisonEntities;
    }
    //break?


    /**
     * Method to check if a transaction is matching another transaction.
     * @param transaction1 the transaction to match
     * @param transaction2 that transaction to match
     * @return Returns true if accoount1 has the same date and amount as transaction2.
     */
    public boolean isMatching(AccountingTransactionEntity transaction1, BankTransactionEntity transaction2) {
        return transaction1.getDate().equals(transaction2.getDate()) && transaction1.getAmount() == transaction2.getAmount();
    }


     public boolean isMatched() {
        return false;
     }

    /** TODO write better documentation.
     * Gives a result for the indexes and adds result to ComparisonEntity.
     * @param result the result that is set.
     * @param bankTransIndex the index
     * @param accTransIndex the other index
     */
    public void giveResultsForIteration(Result result, int bankTransIndex, int accTransIndex) {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();

        ComparisonEntity comparisonEntity = new ComparisonEntity();
        comparisonEntity.setBankTransactionEntity(bankTransList.get(bankTransIndex));
        comparisonEntity.setAccountingTransactionEntity(accTransList.get(accTransIndex));
        comparisonEntity.setResult(result);
        comparisonEntity.setStatus(Status.MATCH_COMPLETE);
        comparisonEntities.add(comparisonEntity);
    }


    /**
     * When Date doesn't match, but the rest does.
     * @return
     */
    public List<ComparisonEntity> findPartialMatches() {
        return comparisonEntities;
    }

    /**
     * When acctrans cant match with any banktrans.
     * @return
     */
    public List<ComparisonEntity> findMissingBankTrans() {
        return comparisonEntities;
    }


    /**
     * When banktrans cant match with any acctrans.
     * @return
     */
    public List<ComparisonEntity> findMissingAccTrans() {
        return comparisonEntities;
    }

    /**
     * This is the method sent to frontend.
     * @return
     */
    public Set setComparingResults() {

        findMatches();
        Set<String> s = new LinkedHashSet<>(Collections.singleton(comparisonEntities.toString()));

        return s;
    }

    /**
     * HelperMethod
     */
    private boolean isSameDate() {

        return false;
    }

    /**
     * HelperMethod
     */
    private boolean isSameAmount() {
        return false;
    }

    /**
     * HelperMethod
     */
    private boolean isMissingBank() {
        return false;
    }

    /**
     * HelperMethod
     */
    private boolean isMissingAcc() {
        return false;
    }

}








