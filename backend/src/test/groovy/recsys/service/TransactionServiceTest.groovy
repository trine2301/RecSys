package recsys.service

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import recsys.model.AccountingTransactionEntity
import recsys.model.BankTransactionEntity
import recsys.repository.AccountingTransactionRepository
import recsys.repository.BankTransactionRepository
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import jakarta.inject.Inject
import java.time.LocalDate

@MicronautTest
class TransactionServiceTest extends Specification {

    //AccountingTransactionRepository accountingTransactionRepository = Mock()
    //BankTransactionRepository bankTransactionRepository = Mock()
    //TransactionService transactionService = Mock()

//    @Subject
//    TransactionService service = new TransactionService(
//        accountingTransactionRepository, bankTransactionRepository
//    )

    @Shared
    @Inject
    AccountingTransactionRepository accountingTransactionRepository;



    //TODO finsish testing.

    def 'Check for discrepancy'() {
        given:
            def bankTransaction = new BankTransactionEntity(id: 1, date: LocalDate.now(), amount: 1234, description: 'hei')
            def accountingTransaction = new AccountingTransactionEntity(date: LocalDate.now(), amount: 1235)
            accountingTransactionRepository.save(accountingTransaction)
        when:
            def accounting = accountingTransactionRepository.findAll()
        then:
            accounting.size() == 103
    }
}
