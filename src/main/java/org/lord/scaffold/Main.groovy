package org.lord.scaffold

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

/**
 *
 * Created by Yuan Chaochao on 2017/8/26
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan([
        "org.lord.scaffold"
])
class Main {

    static void main(String[] args) {
        SpringApplication.run(Main.class, args)
    }

}
