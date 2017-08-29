package org.lord.scaffold.services.commands

import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixCommandProperties

import java.util.concurrent.Callable

class HystrixFactory {
    static HystrixCommandProperties.Setter createHystrixCommandPropertiesSetter() {
        HystrixCommandProperties.invokeMethod("Setter", null)
    }

    static HystrixCommandGroupKey toGroupKey(String name) {
        HystrixCommandGroupKey.Factory.asKey(name)
    }

    static <T> ListCommand newListCommand(String groupKey,
                                          String commandKey,
                                          Callable<List<T>> work,
                                          Callable<List<T>> fallback = { null }) {
        new ListCommand(groupKey, commandKey, work, fallback)
    }

    static StringCommand newStringCommand(String groupKey,
                                          String commandKey,
                                          Callable<String> work,
                                          Callable<String> fallback = { null }) {
        new StringCommand(groupKey, commandKey, work, fallback)
    }

    static VoidCommand newVoidCommand(String groupKey,
                                      String commandKey,
                                      Callable<Void> work,
                                      Callable<Void> fallback = { null }) {

        new VoidCommand(groupKey, commandKey, work, fallback)
    }

    static class ListCommand<T> extends AbstractHystrixCommand<List<T>> {
        ListCommand(String groupKey, String commandKey, Callable<List<T>> work, Callable<List<T>> fallback) {
            super(groupKey, commandKey, work, fallback)
        }
    }

    static class StringCommand extends AbstractHystrixCommand<String> {
        StringCommand(String groupKey, String commandKey, Callable<String> work, Callable<String> fallback) {
            super(groupKey, commandKey, work, fallback)
        }
    }

    static class VoidCommand extends AbstractHystrixCommand<Void> {
        VoidCommand(String groupKey, String commandKey, Callable<Void> work, Callable<Void> fallback) {
            super(groupKey, commandKey, work, fallback)
        }
    }

}
