package gk.potoo.repositories;

import gk.potoo.documents.Account;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(exported = false)
public interface AccountRepository extends Repository<Account, String> {

    Account save(Account account);

    @Query("{ $or: [ { username: ?0 }, { email: ?1 } ] }")
    Account findByUsernameOrEmail(String username, String email);

    Account findByUsername(String username);

}
