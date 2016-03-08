package gk.potoo.repositories;

import gk.potoo.documents.Wow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@PreAuthorize("hasRole('ROLE_USER')")
public interface WowRepository extends CrudRepository<Wow, String> {

    @Override
    @PreAuthorize("permitAll")
    Wow findOne(String s);

    @Override
    @PreAuthorize("permitAll")
    Iterable<Wow> findAll(Iterable<String> strings);

    @RestResource(path = "creator", rel = "creator")
    @PreAuthorize("permitAll")
    Iterable<Wow> findByCreator(@Param("creator") String creator);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Iterable<Wow> findAll();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

}
