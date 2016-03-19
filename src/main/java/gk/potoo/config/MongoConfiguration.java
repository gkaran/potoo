package gk.potoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new UsernameAuditorAware();
    }

}
