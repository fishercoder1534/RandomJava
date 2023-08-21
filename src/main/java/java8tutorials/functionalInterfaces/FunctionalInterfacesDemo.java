package java8tutorials.functionalInterfaces;

import java.util.function.Function;

public class FunctionalInterfacesDemo {
    /**
     * How does lambda expressions fit into Javas type system?
     * Each lambda corresponds to a given type, specified by an interface.
     * A so called functional interface must contain exactly one abstract method declaration.
     * Each lambda expression of that type will be matched to this abstract method.
     * Since default methods are not abstract you're free to add default methods to your functional interface.
     * We can use arbitrary interfaces as lambda expressions as long as the interface only contains one abstract method.
     * To ensure that your interface meet the requirements,
     * you should add the @FunctionalInterface annotation.
     * The compiler is aware of this annotation and throws a compiler error as soon as you try to add a second abstract method declaration to
     * the interface.
     */

    public static void main(String... args) {
        Converter<String, Integer> converter = (from -> Integer.valueOf(from));
        Integer converted = converter.convert("123");
        System.out.println(converted);

        //we can use :: keyword to simplify the above
        //that is how to reference a static method
        converter = Integer::valueOf;
        converted = converter.convert("321");
        System.out.println(converted);

        runAnotherFunctionInterfaceExample();
    }

    private static void runAnotherFunctionInterfaceExample() {
        /**Function interface has a few methods that are often used:
         * apply()
         * andThen()
         * compose()
         * identity()
         * */
        Function<Integer, Integer> addFunction = a -> a + 3;
        System.out.println(addFunction.apply(1));

        Function<Integer, Integer> multipleFunction = (a) -> a * 3;
        System.out.println(multipleFunction.apply(1));

        //a.compose(b) means b will be executed first and then a will execute
        Function<Integer, Integer> compositeFunction = addFunction.compose(multipleFunction);
        System.out.println(compositeFunction.apply(1));

        //a.andThen(b) means a will be executed first, and then function b executes.
        Function<Integer, Integer> andThenFunction = addFunction.andThen(multipleFunction);
        System.out.println(andThenFunction.apply(1));

        //Function.identity() is a static method of Function interface that returns a Function that always returns its input argument. i.e. f(x) = x
        understandFunctionIdentity();
    }

    private static void understandFunctionIdentity() {
        // Using String as Input for Function.identity()
        Function<String, String> stringFunction = Function.identity();
        System.out.println(stringFunction.apply("Alive is Awesome"));

        // Using Integer as input for Function.identity()
        Function<Integer, Integer> integerFunctionUsingFunctionIdentity = Function.identity();
        System.out.println(integerFunctionUsingFunctionIdentity.apply(8));

        // Using lambda expression and String as input
        Function<String, String> stringFunctionUsingLambda = t -> t;
        System.out.println(stringFunctionUsingLambda.apply("Be in present"));

        // Using lambda expression and Integer as input
        Function<Integer, Integer> integerFunctionUsingLambda = t -> t;
        System.out.println(integerFunctionUsingLambda.apply(4));

        Function<Integer, Integer> func1 = Function.identity();
        Function<Integer, Integer> func2 = Function.identity();
        Function<Integer, Integer> func3 = Function.identity();

        Function<Integer, Integer> intFunc1 = t -> t;
        Function<Integer, Integer> intFunc2 = t -> t;
        Function<Integer, Integer> intFunc3 = t -> t;

        System.out.println(func1);
        System.out.println(func2);
        System.out.println(func3);

        System.out.println(intFunc1);
        System.out.println(intFunc2);
        System.out.println(intFunc3);
        /**
         * From the above output, we can conclude that Function.identity()
         * method will always return the same instance
         * whereas each occurrence of (t -> t) or identifier -> identifier
         * will not only create its own instance but even have a distinct implementation class.*/
    }

}
