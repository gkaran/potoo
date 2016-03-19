package gk.potoo.config;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MongoConfigurationTest {

    @Test
    public void testAuditorProvider() throws Exception {
        Assertions.assertThat(new MongoConfiguration().auditorProvider())
            .isExactlyInstanceOf(UsernameAuditorAware.class);
    }
}