package guice;

/**
 * created by stevesun on 10/11/18/.
 */
public class MockMessageService implements MessageService {
  @Override public boolean sendMessage(String msg, String receipient) {
    return true;
  }
}
