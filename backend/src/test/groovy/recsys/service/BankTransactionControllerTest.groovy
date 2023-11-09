package recsys.service

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import recsys.controller.AccountingTransactionController
import recsys.controller.BankTransactionController
import recsys.repository.AccountingTransactionRepository
import recsys.repository.BankTransactionRepository
import spock.lang.Specification
import spock.lang.Subject

@MicronautTest
class BankTransactionControllerTest extends Specification {

    BankTransactionRepository bankTransactionRepository = Mock()
    AccountingTransactionRepository accountingTransactionRepository = Mock()


    @Subject
    BankTransactionController bankTransactionController = new BankTransactionController(bankTransactionRepository)
    AccountingTransactionController accountingTransactionController = new AccountingTransactionController(accountingTransactionRepository)


    def "get bank transactions from repository"() {
        when:
            bankTransactionController.getBankTransactions()
        then:
            1 * bankTransactionRepository.findAll()
    }

    def "get bank transactions from repository"() {
        when:
            accountingTransactionController.getAccountingTransactions()
        then:
            1 * accountingTransactionRepository.findAll()
    }
}
