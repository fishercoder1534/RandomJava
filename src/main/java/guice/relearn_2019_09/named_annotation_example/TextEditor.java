package guice.relearn_2019_09.named_annotation_example;

import javax.inject.Inject;
import javax.inject.Named;

public class TextEditor {
    /**
     * Inject using @Named annotation
     *
     * This can be achived using toInstance() method.*/
    private String dbUrl;

    @Inject
    public TextEditor(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void makeConnection() {
        System.out.println(dbUrl);
    }
}
