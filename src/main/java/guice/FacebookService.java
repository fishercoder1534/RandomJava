package guice;

/**
 * created by stevesun on 10/11/18/.
 */
public class FacebookService implements MessageService {
  @Override public boolean sendMessage(String msg, String receipient) {
    //some complex code to send Facebook message
    System.out.println("Message sent to Facebook user "+receipient+" with message="+msg);
    return true;
  }
}
