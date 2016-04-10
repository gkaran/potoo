package gk.potoo.repositories;

import gk.potoo.documents.Wow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

@Component
public interface WowRepository extends CrudRepository<Wow, String> {

    @Override
    Wow findOne(String s);

    @Override
    @RestResource(exported = false)
    Iterable<Wow> findAll(Iterable<String> strings);

    @RestResource(path = "creator", rel = "creator")
    Iterable<Wow> findByCreator(@Param("creator") String creator);

    @Override
    @RestResource(exported = false)
    Iterable<Wow> findAll();

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
