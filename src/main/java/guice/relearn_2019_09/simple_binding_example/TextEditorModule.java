package guice.relearn_2019_09.simple_binding_example;

import com.google.inject.AbstractModule;

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
    }
}
