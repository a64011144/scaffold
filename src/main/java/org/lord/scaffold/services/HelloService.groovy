package org.lord.scaffold.services

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.lord.scaffold.services.commands.HystrixFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.concurrent.*

/**
 *
 * Created by Yuan Chaochao on 2017/8/29
 */
@CompileStatic
@Component
@Slf4j
class HelloService {

    private static final String GROUP = "applications"

    @Autowired
    ExecutorService executorService

    String sleepForAWhile(long time) {
        def sleepTasks = buildSleepTasks(time)
        List<Future<Void>> futures = executorService.invokeAll(sleepTasks)
        try {
            futures.collect { it.get(100, TimeUnit.SECONDS) }
        } catch (ExecutionException ee) {
            throw ee
        }

        'FINISHED'
    }

    private Collection<Callable<Void>> buildSleepTasks(long time) {
        return [
                new SleepTask(time)
        ] as Collection<Callable<Void>>
    }

    static class SleepTask implements Callable<void> {
        private final long millis

        SleepTask(long millis) {
            this.millis = millis
        }

        @Override
        void call() throws Exception {
            Thread.sleep(millis)
        }
    }

}
