package recsys;
import io.micronaut.runtime.Micronaut;
import recsys.model.BankTransactionEntity;
import recsys.model.ComparisonEntity;
import recsys.repository.BankTransactionRepository;
import recsys.service.TransactionService;

/**
 * This class will be the entry point of your application.
 * It is responsible for initializing and starting the application.
 */
public class App {
    public static void main(String[] args) {
        var app = Micronaut
                .build(args)
                .start();

        TransactionService transactionService = app.getBean(TransactionService.class);
        transactionService.checkIfDiscrepancyExists();
        //transactionService.getComparisonEntity();

        boolean checking = transactionService.checkIfDiscrepancyExists();
        double compareTotal = transactionService.getDiscrepancyAmount();
        transactionService.setBankAndAccountingTotalAmount();


        System.out.println("Checking if there is discrepancies, and it is: " + checking);
        System.out.println("The difference is: " + compareTotal);
        System.out.println("difference: " + transactionService.getDiscrepancyAmount());
    }
}
