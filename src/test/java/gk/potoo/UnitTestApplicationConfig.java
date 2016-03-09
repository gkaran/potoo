package gk.potoo;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import gk.potoo.repositories.AccountRepository;
import gk.potoo.repositories.WowRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@EnableMongoRepositories
@ComponentScan(basePackageClasses = { AccountRepository.class, WowRepository.class})
public class UnitTestApplicationConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "test-db";
    }

    @Override
    public Mongo mongo() {
        return new Fongo("test-test").getMongo();
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.ourownjava.spring.data.mongo";
    }
}
