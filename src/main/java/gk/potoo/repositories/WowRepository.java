package gk.potoo.repositories;

import gk.potoo.documents.Wow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface WowRepository extends CrudRepository<Wow, String> {}
