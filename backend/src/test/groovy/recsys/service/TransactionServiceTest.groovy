package recsys.service

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

    //Defined the mock-object.
    def accountingRepository = Mock(AccountingTransactionRepository)
    def bankRepository = Mock(BankTransactionRepository)


    @Shared
    @Inject
    AccountingTransactionRepository accountingTransactionRepository;
    @Shared
    @Inject
    BankTransactionRepository bankTransactionRepository;
    @Shared
    @Inject
    TransactionService service;

    def setup() {
        bankRepository.findAll() >> [
            new BankTransactionEntity(date: LocalDate.now(), amount: 1234),
            new BankTransactionEntity(date: LocalDate.now().minusDays(1), amount: 2345)
        ]
        accountingRepository.findAll() >> [
            new AccountingTransactionEntity(date: LocalDate.now(), amount: 1234),
            new AccountingTransactionEntity(date: LocalDate.now().minusDays(1), amount: 2345)
        ]
    }

    def "findAll in BankRepo should return all bank transactions"() {
        when: "We call findAll"
            def transactions = bankRepository.findAll()

        then: "We get all bank transactions"
            println(bankRepository.findAll())

        and: "The bankRepository was called"
            1 * bankRepository.findAll()
    }


//    @Subject
//    TransactionService service = new TransactionService(
//        accountingTransactionRepository, bankTransactionRepository
//    )



    //TODO this test only checks if one line is added tp accounting transactionEntity.
    def 'Check for discrepancy'() {
        given:
            def bankTransactionEntity = new BankTransactionEntity(id: 1, date: LocalDate.now(), amount: 1234, description: 'hei')
            def accountingTransactionEntity = new AccountingTransactionEntity(date: LocalDate.now(), amount: 1235)
            accountingTransactionRepository.save(accountingTransactionEntity)
        when:
            def accounting = accountingTransactionRepository.findAll()
        then:
            accounting.size() == 1
        println()
    }


    def 'Check for discrepancy'() {
        given:
            def bankTransactionEntity = new BankTransactionEntity(date: LocalDate.now(), amount: 1234)
            def accountingTransaction = new AccountingTransactionEntity(date: LocalDate.now(), amount: 1000)
            accountingTransactionRepository.save(accountingTransaction)
            bankTransactionRepository.save(bankTransactionEntity)
        when:
            service.checkIfDiscrepancyOnTotalAmountExists()
        then:
            assert service.checkIfDiscrepancyOnTotalAmountExists() == true
    }

    /**
     * Positive test of checkIfAccTransHaveAMatchingBankTrans()
     */
    def 'Check if trans is a match'() {
        given:
            def bankTransactionEntity = new BankTransactionEntity(date: LocalDate.now(), amount: 1000)
            def accountingTransactionEntity = new AccountingTransactionEntity(date: LocalDate.now(), amount: 1000)
            accountingTransactionRepository.save(accountingTransactionEntity)
            bankTransactionRepository.save(bankTransactionEntity)
        when:
            service.compareResults()
        then:
            assert service.compareResults() == true
    }

    /**
     * Negative test of checkIfAccTransHaveAMatchingBankTrans()
     */
    def 'Negative test to check if trans is a match'() {
        given:
            def bankTransactionEntity = new BankTransactionEntity(date: LocalDate.now(), amount: 1000)
            def accountingTransactionEntity = new AccountingTransactionEntity(date: LocalDate.now(), amount: 1000)
            accountingTransactionRepository.save(accountingTransactionEntity)
            bankTransactionRepository.save(bankTransactionEntity)
        when:
            service.compareResults()
        then:
            assert service.compareResults() == false
    }

    /**
     * Positive test of checkIfAccTransHaveAMatchingBankTrans()
     */
    def 'Check if trans is not a match'() {
        given:
            def bankTransactionEntity = new BankTransactionEntity(date: LocalDate.now(), amount: 1100)
            def accountingTransactionEntity = new AccountingTransactionEntity(date: LocalDate.now(), amount: 1000)
            accountingTransactionRepository.save(accountingTransactionEntity)
            bankTransactionRepository.save(bankTransactionEntity)
        when:
            service.compareResults()
        then:
            assert service.compareResults() == false
    }
}
