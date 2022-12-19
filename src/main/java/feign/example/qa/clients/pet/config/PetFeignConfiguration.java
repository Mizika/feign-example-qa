package feign.example.qa.clients.pet.config;

import feign.Logger;
import feign.example.qa.clients.pet.exceptions.PetErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetFeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public PetErrorDecoder clientErrorDecoder() {
        return new PetErrorDecoder();
    }
}
