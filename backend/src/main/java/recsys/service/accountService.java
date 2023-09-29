package recsys.service;

import groovy.lang.Singleton;

import java.util.List;

/**
 * Should contain logic to
 */
@Singleton
public class accountService {

    private final List<standardsAccounts> Accounts;

    public accountService(List<standardsAccounts> accounts) {
        Accounts = accounts;
    }

    public List<standardsAccounts> getAllAccounts() {
        return Accounts;
    }
}
