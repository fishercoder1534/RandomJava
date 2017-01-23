package sporadic;

/**
 * Created by stevesun on 12/29/16.
 */
public class UnderstandingJavaReference {
    /**This is a good example to understand how references work in Java.*/
    public static void main(String[] args) {
        /**String.replace() method has a return value, this method actually creates a new string
         * to return, these four lines of code assign the newly created string to this reference 'a',
         * that's why a got changed the second time I print it out.*/
        String a = "ABCabc";
        System.out.println(a);
        a = a.replace('A', 'a');
        System.out.println(a);

        /**Here, we didn't assign the newly created string to ss, that's why ss didn't change when
         * I print it out the second time.
         * I can find more details if I open up String.replace() method.*/
        String ss = "123456";
        System.out.println(ss);
        ss.replace('1', '0');
        System.out.println(ss);
    }
}
