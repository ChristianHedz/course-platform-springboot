package com.chris.courseplatform.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CoursePlatform-API",
                version = "1.0",
                description = "Course Platform API Documentation"
        )
)
public class OpenApiConfig {
}
