package gk.potoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class SecurityConfiguration {

    @Bean
    public AuditorAware<Object> auditorProvider() {
        return null;
        //TODO: Add auditor provider
    }

}
