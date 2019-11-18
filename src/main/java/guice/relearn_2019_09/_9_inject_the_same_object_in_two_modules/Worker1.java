package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import static guice.relearn_2019_09._9_inject_the_same_object_in_two_modules.Worker1Module.WORKER1;

public class Worker1 implements Worker {
    private final CustomerDbDao customerDbDao;

    @Inject
    public Worker1(@Named(WORKER1) CustomerDbDao customerDbDao) {
        this.customerDbDao = customerDbDao;
    }

    @Override
    public void start() {
        System.out.println("Worker1 started working!");
    }

    @Override
    public void shutdown() {
        System.out.println("Worker1 has shut down!");
    }

}
