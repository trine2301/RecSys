package recsys.service;

import groovy.lang.Singleton;

import java.util.List;

/**
 * TODO
 * Should contain logic related to accounts.
 */
@Singleton
public class AccountService {

    private final List<StandardsAccounts> Accounts;

    /**
     * Constructor
     * @param accounts
     */
    public AccountService(List<StandardsAccounts> accounts) {
        Accounts = accounts;
    }

    /**
     * Gets all accounts from list of standarsAccounts
     * @return All accounts from standardAccounts
     */
    public List<StandardsAccounts> getAllAccounts() {
        return Accounts;
    }
}
