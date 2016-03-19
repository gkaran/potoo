package gk.potoo;

import gk.potoo.repositories.AccountRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public AccountRepository todoService() {
        return Mockito.mock(AccountRepository.class);
    }

}
