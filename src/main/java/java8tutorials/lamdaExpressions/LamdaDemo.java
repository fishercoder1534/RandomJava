package java8tutorials.lamdaExpressions;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void sortInJava8_use_lamda_expressions_and_stream() {
        List<Person> names = Arrays.asList(new Person("Sophie", 27), new Person("Ada", 1),
                new Person("Steve", 28), new Person("Eason", 26), new Person("Jenny", 31));

        System.out.println("In sortInJava8_use_lamda_expressions_using_stream method.");
        print(names, "Prior to sort: ");

        List<Person> sorted = names.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());

        print(sorted, "After sorting: ");
    }

    public static void sortInJava8_use_lamda_expressions_and_stream_and_filter() {
        List<Person> names = Arrays.asList(new Person("Sophie", 27), new Person("Ada", 1),
                new Person("Steve", 28), new Person("Eason", 26), new Person("Jenny", 31));

        System.out.println("In sortInJava8_use_lamda_expressions_and_stream_and_filter method.");

        names.stream().distinct().forEach(System.out::println);
        names.stream().map(person -> person.name.charAt(0)).distinct().forEach(System.out::println);
    }

    public static void main(String...args) {
        sortInPreJava8();
        sortInJava8_use_lamda_expressions();
        sortInJava8_use_lamda_expressions_shorter();
        sortInJava8_use_lamda_expressions_shorter_even();
        sortInJava8_use_lamda_expressions_and_stream();
        sortInJava8_use_lamda_expressions_and_stream_and_filter();
    }
}

class Person {
    String name;
    @Getter
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
