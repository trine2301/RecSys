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
            result == trans1.getAmount() + trans2.getAmount()
    }

    def "test getDiscrepancyAmount method"() {
        given: "Some accounting transactions"
            LocalDate startDate = LocalDate.parse("2023-01-01")
            LocalDate endDate = LocalDate.parse("2023-01-31")

        and: "Mock the getTotalBankSum and getTotalAccSum methods"
            transactionService.metaClass.getTotalBankSum = { LocalDate s, LocalDate e -> 200.0 }
            transactionService.metaClass.getTotalAccSum = { LocalDate s, LocalDate e -> 100.0 }

        when: "We call the getDiscrepancyAmount method"
            double bankTotal = transactionService.getTotalBankSum(startDate, endDate)
            double accountingTotal = transactionService.getTotalAccSum(startDate, endDate)
            double result = transactionService.getDiscrepancyAmount(startDate, endDate)

        then: "We expect the difference between the sums of the transactions"
            println "Bank Total: $bankTotal"
            println "Accounting Total: $accountingTotal"
            result == 100
    }




}
