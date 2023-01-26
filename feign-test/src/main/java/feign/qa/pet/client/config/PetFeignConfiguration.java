package feign.qa.pet.client.config;

import feign.Logger;
import feign.qa.pet.client.exceptions.PetErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetFeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public PetErrorDecoder petErrorDecoder() {
        return new PetErrorDecoder();
    }

    @Bean
    public CustomFeignRequestResponseAllureLogging customFeignRequestLogging() {
        return new CustomFeignRequestResponseAllureLogging();
    }

}
