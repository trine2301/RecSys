/**
 * Unit-tests of the TransactionService class.
 * The following tests are performed:
 * <ul>
 *   <li> hello </li>
 *   <li> aaaahahahah </li>
 *   <li> </li>
 *   Not testing helper-methods in transactionService
 * </ul>
 */

package recsys.service

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import recsys.model.AccountingTransactionEntity
import recsys.model.BankTransactionEntity
import recsys.repository.AccountingTransactionRepository
import recsys.repository.BankTransactionRepository
import spock.lang.Shared
import spock.lang.Specification

import jakarta.inject.Inject
import java.time.LocalDate

@MicronautTest
class TransactionServiceTest extends Specification {

    @Inject
    TransactionService transactionService

    @Inject
    AccountingTransactionRepository accountingTransactionRepository

    @Inject
    BankTransactionRepository bankTransactionRepository

    @Shared
    AccountingTransactionEntity accTrans1

    @Shared
    AccountingTransactionEntity accTrans2

    @Shared
    BankTransactionEntity bankTrans1

    @Shared
    BankTransactionEntity bankTrans2

    @MockBean(AccountingTransactionRepository)
    AccountingTransactionRepository accountingTransactionRepository() {
        Mock(AccountingTransactionRepository)
    }

    @MockBean(BankTransactionRepository)
    BankTransactionRepository bankTransactionRepository() {
        Mock(BankTransactionRepository)
    }


    def setupSpec() {
        accTrans1 = Mock(AccountingTransactionEntity)
        accTrans1.getAmount() >> 100.0
        accTrans1.getDate() >> LocalDate.parse("2020-01-01")
        accTrans2 = Mock(AccountingTransactionEntity)
        accTrans2.getAmount() >> 100.0
        accTrans2.getDate() >> LocalDate.parse("2020-01-31")

        bankTrans2.getDate() >> LocalDate.parse("2020-01-01")
        bankTrans2.getDate() >> LocalDate.parse("2020-01-31")
        bankTrans1 = Mock(BankTransactionEntity)
        bankTrans1.getAmount() >> 100.0
        bankTrans2 = Mock(BankTransactionEntity)
        bankTrans2.getAmount() >> 150.0
    }



    /**
     * Checks that the getTotalAccSum provides correct output.
     */
    def "test getTotalAccSum method"() {
        given:
            LocalDate startDate = LocalDate.parse("2019-12-31")
            LocalDate endDate = LocalDate.parse("2020-12-31")
            accountingTransactionRepository.findByDateBetween(startDate, endDate) >> [accTrans1, accTrans2]
        when:
            double result = transactionService.getTotalAccSum(startDate, endDate)
        then:
            result == accTrans2.getAmount() + accTrans1.getAmount()

    }

    /**
     * Checks that the getTotalBankSum provides correct output.
     */
    def "test getTotalBankSum method"() {
        given:
            LocalDate startDate = LocalDate.parse("2019-12-31")
            LocalDate endDate = LocalDate.parse("2020-12-31")
            bankTransactionRepository.findByDateBetween(startDate, endDate) >> [bankTrans1, bankTrans2]
        when:
            double result = transactionService.getTotalBankSum(startDate, endDate)
        then:
            result == bankTrans2.getAmount() + bankTrans1.getAmount()

    }


    def "test getDiscrepancyAmount method"() {
        given:
            LocalDate startDate = LocalDate.parse("2019-01-01")
            LocalDate endDate = LocalDate.parse("2023-01-31")
            bankTransactionRepository.findByDateBetween(startDate, endDate) >> [bankTrans1, bankTrans2]
            accountingTransactionRepository.findByDateBetween(startDate, endDate) >> [accTrans1, accTrans2]

        when:
            double result = transactionService.getDiscrepancyAmount(startDate, endDate)

        then:
            result == 50

    }

    def "test getDiscrepancyAmount method"() {
        given:
            LocalDate startDate = LocalDate.parse("2019-01-01")
            LocalDate endDate = LocalDate.parse("2023-01-31")
            bankTransactionRepository.findByDateBetween(startDate, endDate) >> [bankTrans1, bankTrans2]
            accountingTransactionRepository.findByDateBetween(startDate, endDate) >> [accTrans1, accTrans2]

        when:
            double result = transactionService.getDiscrepancyAmount(startDate, endDate)

        then:
            result == 50

    }

}
