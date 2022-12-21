package feign.qa;


import com.github.javafaker.Faker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"app", "feign.qa"})
public class AppConfigFeign {
    @Bean(name = "faker")
    Faker faker() {
        return new Faker();
    }

}