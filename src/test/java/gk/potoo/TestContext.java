package gk.potoo;

import gk.potoo.repositories.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestContext {

    @Bean
    public AccountRepository todoService() {
        return mock(AccountRepository.class);
    }

}
