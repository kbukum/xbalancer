package org.oopdev.xbalancer.service.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public class JpaService<T, ID extends Serializable> extends CrudService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public JpaService(JpaRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return repository.findAll(ids);
    }


    public boolean exists(ID id) {
        return repository.exists(id);
    }

    public void flush() {
        repository.flush();
    }

    public <S extends T> S saveAndFlush(S entity) {
        return repository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<T> entities) {
        repository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    public T getOne(ID id) {
        return repository.getOne(id);
    }

    public <S extends T> S findOne(Example<S> example) {
        return repository.findOne(example);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    public <S extends T> long count(Example<S> example) {
        return repository.count(example);
    }

    public <S extends T> boolean exists(Example<S> example) {
        return repository.exists(example);
    }
}