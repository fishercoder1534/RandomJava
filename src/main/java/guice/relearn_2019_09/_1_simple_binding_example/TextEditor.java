package guice.relearn_2019_09._1_simple_binding_example;

import javax.inject.Inject;

public class TextEditor {
    private SpellChecker spellChecker;

    /**Consider you have an application which has a text editor component and you want to provide a spell check. Your standard code would look something like this.
     * Note that here we have created a dependency between the TextEditor and the SpellChecker.*/
//    public TextEditor() {
//        spellChecker = new SpellChecker();
//    }

    /**
     * In an inversion of control scenario, we would instead do something like this:
     * <p>
     * Here, the TextEditor should not worry about SpellChecker implementation.
     * The SpellChecker will be implemented independently and will be provided to the TextEditor at the time of TextEditor instantiation.
     */
    @Inject
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}