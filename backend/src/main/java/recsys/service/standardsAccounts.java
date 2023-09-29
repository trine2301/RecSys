package recsys.service;

import java.util.List;


public record standardsAccounts(String accountNo, AccountLanguage languages) {
    public record AccountLanguage(AccountLanguageNB NB) {
        public record AccountLanguageNB(String name, String description, List<String> keywords) {}
    }
}
