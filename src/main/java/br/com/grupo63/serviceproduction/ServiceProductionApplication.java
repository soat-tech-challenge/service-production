package br.com.grupo63.serviceproduction;

import br.com.grupo63.techchallenge.common.config.aws.ecs.ECSTaskIdInfoContributor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(title = "${info.name}", description = "${info.description}", version = "${info.version}"),
        servers ={
                @Server(url = "${server.servlet.context-path:}", description = "Current URL"),
                @Server(url = "localhost:${server.port:8080}${server.servlet.context-path:}", description = "Localhost"),
                @Server(url = "${app.docs-api-url:(no value)}${server.servlet.context-path:}", description = "Custom URL from env")
        })
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication(scanBasePackageClasses = {
        ServiceProductionApplication.class,
        ECSTaskIdInfoContributor.class
})
public class ServiceProductionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductionApplication.class, args);
    }

}
