package gk.potoo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile({"default", "heroku"})
@Configuration
@EnableMongoAuditing
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new UsernameAuditorAware();
    }

    @Value("${mongo.url}")
    private String url;

    @Value("${mongo.db}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(url);
    }

}
