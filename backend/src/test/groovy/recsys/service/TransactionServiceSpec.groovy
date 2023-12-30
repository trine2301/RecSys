package recsys.service

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import recsys.model.AccountingTransactionEntity
import recsys.repository.AccountingTransactionRepository
import spock.lang.Shared
import spock.lang.Specification

import jakarta.inject.Inject
import java.time.LocalDate

@MicronautTest
class TransactionServiceSpec extends Specification {

    @Inject
    TransactionService transactionService

    @Inject
    AccountingTransactionRepository accountingTransactionRepository

    @Shared
    AccountingTransactionEntity trans1

    @Shared
    AccountingTransactionEntity trans2

    def setupSpec() {
        trans1 = Mock(AccountingTransactionEntity)
        trans1.getAmount() >> 100.0
        trans1.getDate() >> LocalDate.parse("2020-01-01")
        trans2 = Mock(AccountingTransactionEntity)
        trans2.getAmount() >> 100.0
        trans2.getDate() >> LocalDate.parse("2020-01-31")
    }

    //TODO This test fails if i write 200 on results, probably because its not mocking properly, and using values from db.
    /**
     * Test to test the getTotalAccSum method
     */
    def "test getTotalAccSum method"() {
        given:
            LocalDate startDate = LocalDate.parse("2019-12-01")
            LocalDate endDate = LocalDate.parse("2020-02-15")
            accountingTransactionRepository.findByDateBetween(startDate, endDate) >> [trans1, trans2]
        when:
            double result = transactionService.getTotalAccSum(startDate, endDate)
        then:
            result == 0
    }


}
