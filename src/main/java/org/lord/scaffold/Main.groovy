package org.lord.scaffold

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.ComponentScan

/**
 *
 * Created by Yuan Chaochao on 2017/8/26
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan([
        "org.lord.scaffold",
        "com.netflix.spinnaker.config"
])
class Main {

    static void main(String[] args) {
        SpringApplication.run(Main.class, args)
    }

}
