package br.com.rocketwave.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("api")
public class ConfigurationApi {

//    @Value("${app.urlexternal}")
//    private String bhutApi;

}
