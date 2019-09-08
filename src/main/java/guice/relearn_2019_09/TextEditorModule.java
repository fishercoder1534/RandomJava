package guice.relearn_2019_09;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Dependency Injection is controlled by the Guice Bindings.
 * Guice uses bindings to map object types to their actual implementations.
 * These bindings are defined a module.
 * A module is a collection of bindings as shown below −
 * */
public class TextEditorModule extends AbstractModule {
    @Override
    protected void configure() {
        /*
         * Bind SpellChecker binding to WinWordSpellChecker implementation
         * whenever spellChecker dependency is used.
         */
        bind(SpellChecker.class).to(WinWordSpellChecker.class);
        bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/emp");
    }
}
