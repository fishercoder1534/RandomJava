package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainApp {

    public static void main(String... args) {
        try {
            Injector injector = Guice.createInjector(new WorkersModule());
            System.out.println("created injector: " + injector);
            WorkersRunner workersRunner = injector.getInstance(WorkersRunner.class);
            System.out.println("got workersRunner: " + workersRunner);
            workersRunner.start();
            workersRunner.shutdown();
        } catch (Exception e) {
            System.out.println("caught exception, e: " + e);
        }
    }
}
