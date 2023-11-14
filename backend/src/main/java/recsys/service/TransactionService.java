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
import java.util.List;


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
                if (accTransList.get(accTransIndex).getDate().equals(bankTransList.get(bankTransIndex).getDate()) && accTransList.get(accTransIndex).getAmount() == bankTransList.get(bankTransIndex)
                    .getAmount()) {
                    ComparisonEntity comparisonEntity = new ComparisonEntity();
                    comparisonEntity.setBankTransactionEntity(bankTransList.get(bankTransIndex));
                    comparisonEntity.setAccountingTransactionEntity(accTransList.get(accTransIndex));
                    comparisonEntity.setResult(Result.MATCH);
                    comparisonEntity.setStatus(Status.MATCH_COMPLETE);
                    comparisonEntities.add(comparisonEntity);
                    break;
                }
                bankTransIndex++;
            }
            accTransIndex++;
        }
        return comparisonEntities;
    }


    /**
     * When Date doesn't match, but the rest does.
     * @return
     */
    public List<ComparisonEntity> findPartialMatches() {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();

        int accTransIndex = 0;
        int bankTransIndex = 0;

        while (accTransIndex < accTransList.size()) {
            while (bankTransIndex < bankTransList.size()) {
                if (accTransList.get(accTransIndex).getDate().isBefore(bankTransList.get(bankTransIndex).getDate()) || accTransList.get(accTransIndex).getDate().isAfter(bankTransList.get(bankTransIndex).getDate()) && accTransList.get(accTransIndex).getAmount() == bankTransList.get(bankTransIndex)
                    .getAmount()) {
                    ComparisonEntity comparisonEntity = new ComparisonEntity();
                    comparisonEntity.setBankTransactionEntity(bankTransList.get(bankTransIndex));
                    comparisonEntity.setAccountingTransactionEntity(accTransList.get(accTransIndex));
                    comparisonEntity.setResult(Result.PARTIAL_MATCH);
                    comparisonEntity.setStatus(Status.MATCH_COMPLETE);
                    comparisonEntities.add(comparisonEntity);
                    break;
                }
                bankTransIndex++;
            }
            accTransIndex++;
        }
        return comparisonEntities;
    }

    /**
     * When acctrans cant match with any banktrans.
     * @return
     */
    public List<ComparisonEntity> findMissingBankTrans() {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();

        int accTransIndex = 0;
        int bankTransIndex = 0;

        while (accTransIndex < accTransList.size()) {
            while (bankTransIndex < bankTransList.size()) {
                if (1 == 2) {
                    ComparisonEntity comparisonEntity = new ComparisonEntity();
                    comparisonEntity.setBankTransactionEntity(bankTransList.get(bankTransIndex));
                    comparisonEntity.setAccountingTransactionEntity(accTransList.get(accTransIndex));
                    comparisonEntity.setResult(Result.MATCH);
                    comparisonEntity.setStatus(Status.MATCH_COMPLETE);
                    comparisonEntities.add(comparisonEntity);
                    break;
                }
                bankTransIndex++;
            }
            accTransIndex++;
        }
        return comparisonEntities;
    }


    /**
     * When banktrans cant match with any acctrans.
     * @return
     */
    public List<ComparisonEntity> findMissingAccTrans() {
        List<AccountingTransactionEntity> accTransList = accountingTransactionRepository.findAll();
        List<BankTransactionEntity> bankTransList = bankTransactionRepository.findAll();

        int accTransIndex = 0;
        int bankTransIndex = 0;

        while (accTransIndex < accTransList.size()) {
            while (bankTransIndex < bankTransList.size()) {
                if (1 == 2) {
                    ComparisonEntity comparisonEntity = new ComparisonEntity();
                    comparisonEntity.setBankTransactionEntity(bankTransList.get(bankTransIndex));
                    comparisonEntity.setAccountingTransactionEntity(accTransList.get(accTransIndex));
                    comparisonEntity.setResult(Result.MATCH);
                    comparisonEntity.setStatus(Status.MATCH_COMPLETE);
                    comparisonEntities.add(comparisonEntity);
                    break;
                }
                bankTransIndex++;
            }
            accTransIndex++;
        }
        return comparisonEntities;
    }

    /**
     * This is the method sent to frontend.
     * @return
     */
    public List<ComparisonEntity> setComparingResults() {

        findMatches();
        //findPartialMatches();

        return comparisonEntities;
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








    /**
     * For each accounting transaction that can find a matching bank transaction: Status = MATCh, add to List<ComparisonEntity>
     *
     * For each transaction that did not have a match: iterate and give one of the results;
     * PARTIAL_MATCH, MISSING_BANK_TRANS OR MISSING_ACC_TRANS, (and put it in a list of comparisonEntities?)
     */
    //public Status result() {
        //Status status = Status.MATCH_NO_COMPLETE;
        //for each transaction where match == false, iterate and find matching date OR amount = PARTIAL_MATCH, add to List<ComparisonsEntity>
        //for each transaction where match == false, acctrans iterate and find no matches on DATE AND amount = MISSING_BANK_TRANS, add to List<ComparisonsEntity>
        //for each transaction where match == false, bankTrans iterate and find no matches on DATE AND amount = MISSING_ACC_TRANS, add to List<ComparisonsEntity>

        //if match complete -->
        //return Status.MATCH_COMPLETE;

        //else: throw CouldNotMatchException
    //}








