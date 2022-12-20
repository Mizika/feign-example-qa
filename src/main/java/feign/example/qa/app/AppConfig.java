package feign.example.qa.app;


import com.github.javafaker.Faker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = { "feign"})
public class AppConfig {
    @Bean(name = "faker")
    Faker faker() {
        return new Faker();
    }

}