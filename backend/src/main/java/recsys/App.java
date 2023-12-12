package recsys;
import io.micronaut.runtime.Micronaut;
import recsys.service.TransactionService;

import java.time.LocalDate;

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



        //boolean checking = transactionService.checkIfDiscrepancyOnTotalAmountExists();
        //double compareTotal = transactionService.getDiscrepancyAmount(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
        //transactionService.setBankAndAccountingTotalAmount();
        //double accTotal = transactionService.getTotalAccSum(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
        //double bankTotal = transactionService.getTotalBankSum(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));


        //System.out.println("Total bank: " + bankTotal + ", total acc: " + accTotal);


    }
}
