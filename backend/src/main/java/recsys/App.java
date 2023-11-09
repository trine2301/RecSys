package recsys;
import io.micronaut.runtime.Micronaut;
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

        boolean checking = transactionService.checkIfDiscrepancyExists();
        double compareTotal = transactionService.getDiscrepancyAmount();
        transactionService.setBankAndAccountingTotalAmount();


        System.out.println("Checking if there is dicrepancies, and it is: " + checking);
        System.out.println("The difference is: " + compareTotal);
    }
}
