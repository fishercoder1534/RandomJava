package guice.relearn_2019_09;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;

/**
 * An injector is the object-graph builder
 * and a Module is its core building block.
 * Thus, the first step is to create an injector and then use the injector to get the objects.*/
public class MainApp {
    public static void main(String[] args) {
        /*
         * Guice.createInjector() takes Modules, and returns a new Injector
         * instance. This method is to be called once during application startup.
         */

        Injector injector = Guice.createInjector(new TextEditorModule());
        /*
         * Build object using injector
         */
        TextEditor textEditor = injector.getInstance(TextEditor.class);

        System.out.println("textEditor: " + textEditor);
    }
}
