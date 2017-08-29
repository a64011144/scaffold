package org.lord.scaffold.services.commands

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandKey
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.lord.scaffold.exception.GlobalExceptionHandler

import java.util.concurrent.Callable

@Slf4j
@CompileStatic
abstract class AbstractHystrixCommand<T> extends HystrixCommand<T> {

    private final String groupKey
    private final String commandKey

    protected final Callable<T> work
    protected final Callable<T> fallback

    AbstractHystrixCommand(String groupKey,
                           String commandKey,
                           Callable<T> work,
                           Callable<T> fallback) {
        super(HystrixCommand.Setter.withGroupKey(HystrixFactory.toGroupKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixFactory.createHystrixCommandPropertiesSetter()))
        this.groupKey = groupKey
        this.commandKey = commandKey
        this.work = work
        this.fallback = fallback ?: { null }
    }

    @Override
    protected T run() throws Exception {
        try {
            return work.call()
        } catch (Exception e) {
            throw new GlobalExceptionHandler.InternalServerError('Hystrix command failed.', e)
        }
    }

    protected T getFallback() {
        return fallback.call()
    }

    @Override
    T execute() {
        def result = super.execute() as T
        if (result == null && isResponseFromFallback()) {
            def e = getFailedExecutionException()
            def errorMessage = "Fallback encountered"
            log.error(errorMessage, e)
            throw new GlobalExceptionHandler.InternalServerError("No fallback available (group: '${groupKey}', command: '${commandKey}', exception: '${e?.toString() ?: ""}')", e)
        }

        return result
    }
}
