package guice;

import javax.inject.Singleton;

/**
 * EmailService is one of the implementation of MessageService. Notice that class is annotated with
 * @Singleton annotation.
 *
 * Since service objects will be created through injector classes, this annotation is provided
 * to let them know that the service classes should be singleton objects.
 *
 * Google Guice 3.0 added the support for JSR-330 and we can use annotations from
 * com.google.inject
 * or
 * javax.inject package.
 */

@Singleton
public class EmailService implements MessageService {
  @Override public boolean sendMessage(String msg, String receipient) {
    //some fancy code to send email
    System.out.println("Email Message sent to " + receipient + " with message=" + msg);
    return true;
  }
}
