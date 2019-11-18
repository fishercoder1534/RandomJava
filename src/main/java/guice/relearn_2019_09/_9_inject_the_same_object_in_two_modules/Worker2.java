package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Worker2 implements Worker {
    private final CustomerDbDao customerDbDao;

    @Inject
    public Worker2(@Named("Worker2") CustomerDbDao customerDbDao) {
        this.customerDbDao = customerDbDao;
    }

    @Override
    public void start() {
        System.out.println("Worker2 started working!");
    }

    @Override
    public void shutdown() {
        System.out.println("Worker2 has shut down!");
    }
}
