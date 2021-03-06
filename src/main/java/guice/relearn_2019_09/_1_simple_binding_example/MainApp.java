package guice.relearn_2019_09._1_simple_binding_example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.relearn_2019_09._2_named_annotation_example.TextEditor;
import guice.relearn_2019_09._2_named_annotation_example.TextEditorModule;

/**
 * An injector is the object-graph builder
 * and a Module is its core building block.
 * Thus, the first step is to create an injector and then use the injector to get the objects.
 *
 *
 * In the this example,
 * TextEditor class object graph is constructed by Guice and
 * this graph contains TextEditor object and its dependency as WinWordSpellChecker object.*/
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
        textEditor.makeConnection();

        System.out.println("textEditor is instantiated.");
    }
}
