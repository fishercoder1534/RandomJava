package guice.relearn_2019_09._5_constructor_binding_example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * Guice provides a way to create bindings with specific constructor of an object using toConstructor() method.
 */
public class GuiceTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }
}

class TextEditor {
    private SpellChecker spellChecker;

    @Inject
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            bind(SpellChecker.class).toConstructor(SpellCheckerImpl.class.getConstructor(String.class));
        } catch (NoSuchMethodException | SecurityException e) {
            System.out.println("Required constructor missing");
        }

        bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/emp");
    }
}

//spell checker interface
interface SpellChecker {
    public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {
    private String dbUrl;

    public SpellCheckerImpl() {
        System.out.println("In default constructor.");
    }

    public SpellCheckerImpl(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
        System.out.println("In constructor with a string param.");
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
        System.out.println(dbUrl);
    }
}
