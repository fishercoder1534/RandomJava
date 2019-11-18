package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.AbstractModule;

public class WorkersModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new Worker1Module());
//        install(new Worker2Module());
    }
}
