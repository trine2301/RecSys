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
        transactionService.checkIfDiscrepancyOnTotalAmountExists();
        //transactionService.populatePeriodEntity(LocalDate.of(2020, 01, 01), LocalDate.of(2020, 02, 01), 1234.00, 1236.00, 2.00);
        //List<PeriodEntity> fknPrint2 = transactionService.populatePeriodEntity(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 02, 01), 1234, 123, 1);

        //System.out.println(fknPrint);
    }
}
