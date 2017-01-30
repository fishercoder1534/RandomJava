package java_generics;

/**
 * Created by stevesun on 12/7/16.
 */
public class MainApp {
	public static void main(String[] args) {
		GenericClass<Integer> integerBox = new GenericClass<Integer>();
		GenericClass<String> stringBox = new GenericClass<String>();

		integerBox.add(new Integer(10));
		stringBox.add(new String("Hello World"));

		System.out.printf("Integer Value :%d\n\n", integerBox.get());
		System.out.printf("String Value :%s\n", stringBox.get());
	}
}
