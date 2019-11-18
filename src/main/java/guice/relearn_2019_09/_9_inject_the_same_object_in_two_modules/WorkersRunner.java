package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class WorkersRunner {

    @Inject
    private Set<Worker> workers;

    public void start() {
        log.info("Starting up Background Workers: " + workers.stream()
                .map((w) -> w.getClass().getSimpleName())
                .collect(Collectors.joining(",")));

        val now = System.currentTimeMillis();

        workers.forEach((w) -> {
            log.info("Starting up " + w.getClass().getName() + "...");
            w.start();
            log.info("Start up took " + (System.currentTimeMillis() - now) + " ms");
        });
    }

    /**
     * Shutdown workers gracefully
     */
    public void shutdown() {
        workers.forEach((w) -> {
            w.shutdown();
        });
    }
}
