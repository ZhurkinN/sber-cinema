package ru.zhurkin.sbercinema.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /*
    Адрес интерфейса - http://localhost:8080/swagger-ui/index.html#
     */
    @Bean
    public OpenAPI getOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Сервис фильмотеки")
                        .description("Сервис, позволяющий купить или взять в аренду фильм")
                        .contact(new Contact()
                                .name("Nikita")
                                .email("zhurkin236@gmail.com")));
    }
}
