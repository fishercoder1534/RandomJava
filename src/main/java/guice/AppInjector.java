package guice;

import com.google.inject.AbstractModule;

/**
 * Obviously google guice will not know which service to use,
 * we have to configure it by extending AbstractModule abstract class
 * and provide implementation for configure() method.
 */
public class AppInjector extends AbstractModule {
  @Override protected void configure() {
    //bind the service to implementation class
//    bind(MessageService.class).to(EmailService.class);

    //bind MessageService to Facebook Message implementation
    bind(MessageService.class).to(FacebookService.class);
  }
}
