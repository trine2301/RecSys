package recsys.service;

import groovy.lang.Singleton;

import java.util.List;

/**
 * TODO
 * Should contain logic related to accounts.
 */
@Singleton
public class accountService {

    private final List<standardsAccounts> Accounts;

    /**
     * Constructor
     * @param accounts
     */
    public accountService(List<standardsAccounts> accounts) {
        Accounts = accounts;
    }

    /**
     * Gets all accounts from list of standarsAccounts
     * @return All accounts from standardAccounts
     */
    public List<standardsAccounts> getAllAccounts() {
        return Accounts;
    }
}
