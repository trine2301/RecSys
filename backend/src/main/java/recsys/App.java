package recsys;
import io.micronaut.runtime.Micronaut;

/**
 * This class will be the entry point of your application.
 * It is responsible for initializing and starting the application.
 */
public class App {
    public static void main(String[] args) {
        Micronaut
                .build(args)
                .start();
    }
}
