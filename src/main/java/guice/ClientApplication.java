package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * created by stevesun on 10/11/18/.
 */
public class ClientApplication {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AppInjector());

    MyApplication app = injector.getInstance(MyApplication.class);

    app.sendMessage("Hi Steve", "fishercoder@gmail.com");
  }
}
