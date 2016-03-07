package gk.potoo.repositories;

import gk.potoo.documents.Wow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@PreAuthorize("hasRole('ROLE_USER')")
public interface WowRepository extends CrudRepository<Wow, String> {

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Iterable<Wow> findAll();
}
