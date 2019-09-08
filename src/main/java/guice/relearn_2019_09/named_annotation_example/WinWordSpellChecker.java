package guice.relearn_2019_09.named_annotation_example;

public class WinWordSpellChecker implements SpellChecker {
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling implementation." );
    }
}
