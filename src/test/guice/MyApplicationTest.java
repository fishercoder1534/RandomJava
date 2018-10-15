package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyApplicationTest {

  private Injector injector;

  @Before
  public void setUp() {
    injector = Guice.createInjector(new AbstractModule() {

      @Override
      protected void configure() {
        bind(MessageService.class).to(MockMessageService.class);
      }
    });
  }

  @After
  public void tearDown() {
    injector = null;
  }

  @Test
  public void test() {
    MyApplication appTest = injector.getInstance(MyApplication.class);
    Assert.assertEquals(true, appTest.sendMessage("Hi Steve", "fishercoder@gmail.com"));
    ;
  }
}
