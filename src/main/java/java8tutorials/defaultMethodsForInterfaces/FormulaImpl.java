package java8tutorials.defaultMethodsForInterfaces;

/**
 * Created by stevesun on 4/16/17.
 */
public class FormulaImpl implements Formula {
    @Override
    public double calculate(int a) {
        return sqrt(a*100);
    }

    public static void main(String... args) {
        Formula formula = new FormulaImpl();
        System.out.println(formula.sqrt(16));
        System.out.println(formula.calculate(100));
    }
}
