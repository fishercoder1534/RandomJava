package guice.relearn_2019_09.simple_binding_example;

public class WinWordSpellChecker implements SpellChecker {
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling implementation." );
    }
}
