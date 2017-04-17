package java8tutorials.lamdaExpressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by stevesun on 4/16/17.
 */
public class LamdaDemo {
    public static void sortInPreJava8() {
        List<Person> names = Arrays.asList(new Person("Sophie", 27), new Person("Ada", 1),
                new Person("Steve", 28), new Person("Eason", 26), new Person("Jenny", 31));

        print(names, "Prior to sort: ");

        Collections.sort(names, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });

        print(names, "After sorting: ");
    }

    private static void print(List<Person> persons, String context) {
        System.out.println(context);
        for (Person person : persons) {
            System.out.println(person.toString());
        }
        System.out.println();
    }

    public static void sortInJava8_use_lamda_expressions() {
        List<Person> names = Arrays.asList(new Person("Sophie", 27), new Person("Ada", 1),
                new Person("Steve", 28), new Person("Eason", 26), new Person("Jenny", 31));

        print(names, "Prior to sort: ");

        Collections.sort(names, (Person a, Person b) -> {
            return a.age - b.age;
        });

        print(names, "After sorting: ");
    }

    public static void sortInJava8_use_lamda_expressions_shorter() {
        List<Person> names = Arrays.asList(new Person("Sophie", 27), new Person("Ada", 1),
                new Person("Steve", 28), new Person("Eason", 26), new Person("Jenny", 31));

        print(names, "Prior to sort: ");

        Collections.sort(names, (Person a, Person b) -> a.age - b.age);

        print(names, "After sorting: ");
    }

    public static void sortInJava8_use_lamda_expressions_shorter_even() {
        List<Person> names = Arrays.asList(new Person("Sophie", 27), new Person("Ada", 1),
                new Person("Steve", 28), new Person("Eason", 26), new Person("Jenny", 31));

        print(names, "Prior to sort: ");

        Collections.sort(names, (a, b) -> a.age - b.age);

        print(names, "After sorting: ");
    }

    public static void main(String...args) {
        sortInPreJava8();
        sortInJava8_use_lamda_expressions();
        sortInJava8_use_lamda_expressions_shorter();
        sortInJava8_use_lamda_expressions_shorter_even();
    }
}

class Person {
    String name;
    int age;
    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
