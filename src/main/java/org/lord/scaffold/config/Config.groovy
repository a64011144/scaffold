package org.lord.scaffold.config

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *
 * Created by Yuan Chaochao on 2017/8/29
 */
@CompileStatic
@Configuration
@Slf4j
class Config {

    @Bean
    ExecutorService executorService() {
        Executors.newFixedThreadPool(20)
    }

}
