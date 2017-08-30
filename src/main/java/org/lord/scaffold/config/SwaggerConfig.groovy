package org.lord.scaffold.config

import com.google.common.base.Predicate
import com.google.common.base.Predicates
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties

/**
 *
 * Created by Yuan Chaochao on 2017/8/28
 */
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.paths.AbstractPathProvider
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
@ConditionalOnProperty('swagger.enabled')
@ConfigurationProperties(
        prefix = 'swagger'
)
class SwaggerConfig {
    String title
    String description
    String contact
    List<String> patterns
    String basePath = ""
    String documentationPath = "/"

    @Bean
    Docket gateApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathProvider(new BasePathProvider(basePath, documentationPath))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .apiInfo(apiInfo())
                .ignoredParameterTypes(groovy.lang.MetaClass.class)// see https://stackoverflow.com/questions/22763820/exclude-metaclass-properties-for-groovy-model-classes-in-swagger
    }

    Predicate<String> paths() {
        def predicates = []
        patterns.each {
            predicates.push(PathSelectors.regex(it))
        }
        Predicates.or(predicates)
    }

    ApiInfo apiInfo() {
        return new ApiInfo(
                title,
                description,
                null,
                null,
                contact,
                null,
                null
        )
    }

    class BasePathProvider extends AbstractPathProvider {
        String basePath
        String documentationPath

        BasePathProvider(String basePath, String documentationPath) {
            this.basePath = basePath
            this.documentationPath = documentationPath
        }

        protected String applicationPath() {
            return this.basePath
        }

        protected String getDocumentationPath() {
            return this.documentationPath
        }
    }
}
