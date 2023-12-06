package recsys;
import io.micronaut.runtime.Micronaut;
//import recsys.model.ComparisonEntity;
import recsys.model.ComparisonEntity;
import recsys.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

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
        transactionService.checkIfDiscrepancyOnTotalAmountExists();



        boolean checking = transactionService.checkIfDiscrepancyOnTotalAmountExists();
        double compareTotal = transactionService.getDiscrepancyAmount(LocalDate.of(2023, 6, 9), LocalDate.of(2023, 6, 26));
        //transactionService.setBankAndAccountingTotalAmount();
        //List<ComparisonEntity> trans = transactionService.findMatches();



        //System.out.println("Checking if there is discrepancies, and it is: " + checking);
        //System.out.println("The difference is: " + compareTotal);
        //System.out.println("DAMN: " + trans);
        System.out.println("Total is: " + compareTotal);

    }
}
