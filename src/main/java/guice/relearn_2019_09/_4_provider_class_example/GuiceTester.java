package guice.relearn_2019_09._4_provider_class_example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

import javax.inject.Inject;

/**
 * As @provides method becomes more complex, this method can be moved to separate classes using Provider interface.
 * */
public class GuiceTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
        System.out.println("Program ended.");
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
    /**you have to map the provider to type.*/
    @Override
    protected void configure() {
        bind(SpellChecker.class).toProvider(SpellCheckerProvider.class);
    }
}

//spell checker interface
interface SpellChecker {
    public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {

    private String dbUrl;
    private String user;
    private Integer timeout;

    @Inject
    public SpellCheckerImpl(String dbUrl,
                            String user,
                            Integer timeout) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.timeout = timeout;
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
        System.out.println(dbUrl);
        System.out.println(user);
        System.out.println(timeout);
    }
}

class SpellCheckerProvider implements Provider<SpellChecker> {
    @Override
    public SpellChecker get() {
        String dbUrl = "jdbc:mysql://localhost:5326/emp";
        String user = "user";
        int timeout = 100;

        SpellChecker SpellChecker = new SpellCheckerImpl(dbUrl, user, timeout);
        return SpellChecker;
    }
}
