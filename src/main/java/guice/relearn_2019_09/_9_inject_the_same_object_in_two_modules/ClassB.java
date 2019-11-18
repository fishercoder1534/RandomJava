package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class ClassB {
    private final CustomerDbDao customerDbDao;
    private static final String message = "This is from ClassB!";
}
