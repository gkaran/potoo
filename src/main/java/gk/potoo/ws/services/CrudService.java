package gk.potoo.ws.services;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

abstract class CrudService<T> {

    private CrudRepository<T, String> repository;

    public CrudService(CrudRepository<T, String> repository) {
        this.repository = repository;
    }

    public T find(String id) {
        return repository.findOne(id);
    }

    public List<T> findAll() {
        return (List<T>) StreamSupport.stream(repository.findAll().spliterator(), false).collect(toList());
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public T update(String id, T data) {
        T found = repository.findOne(id);
        if (found != null) {
            copyUpdatedValues(found, data);
            return repository.save(found);
        }
        return null;
    }

    public T create(T data) {
        return repository.save(data);
    }

    protected abstract void copyUpdatedValues(T found, T data);

}
