package org.lord.scaffold.services

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.async.DeferredResult

import java.util.concurrent.ExecutorService

/**
 *
 * Created by Yuan Chaochao on 2017/8/29
 */
@CompileStatic
@Component
@Slf4j
class HelloService {

    @Autowired
    ExecutorService executorService

    void sleepForAWhile(long time, DeferredResult deferredResult) {
        executorService.execute(new Runnable() {
            @Override
            void run() {
                log.info('Thread name: {}', Thread.currentThread().name)
                Thread.sleep(time)
                deferredResult.setResult('Finished')
            }
        })

    }


}
