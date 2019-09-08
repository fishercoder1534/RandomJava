package guice.relearn_2019_09._7_scope_example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import static org.junit.Assert.assertEquals;

/**
 * Guice returns a new instance every time it supplies a value as its DEFAULT behavior.
 * It is configurable via scopes. The various scopes that Guice supports are −
 *
 * @Singleton − Single instance for lifetime of the application. @Singleton object needs to be threadsafe.
 * @SessionScoped − Single instance for a particular session of the web application. @SessionScoped object needs to be threadsafe.
 * @RequestScoped − Single instance for a particular request of the web application. @RequestScoped object does not need to be threadsafe.
 */
public class GuiceTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        SpellChecker spellChecker = new SpellCheckerImpl();
        injector.injectMembers(spellChecker);

        TextEditor editor = injector.getInstance(TextEditor.class);
        double id = editor.getSpellCheckerId();
        System.out.println(id);

        TextEditor editor1 = injector.getInstance(TextEditor.class);
        double id1 = editor1.getSpellCheckerId();
        System.out.println(id1);

        /**Because it's a singleton, so these two values will be the same.*/
        assertEquals(0, id, id1);
    }
}

class TextEditor {
    private SpellChecker spellChecker;

    @Inject
    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public TextEditor() {
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }

    public double getSpellCheckerId() {
        return spellChecker.getId();
    }
}

//Binding Module
class TextEditorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SpellChecker.class).to(SpellCheckerImpl.class);
    }
}

interface SpellChecker {
    public double getId();

    public void checkSpelling();
}

@Singleton
class SpellCheckerImpl implements SpellChecker {
    double id;

    public SpellCheckerImpl() {
        id = Math.random();
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }

    @Override
    public double getId() {
        return id;
    }
}
