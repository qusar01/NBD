package Repositories;

import Source.BabysitterType;

import java.util.List;

public interface bRepository<T> {

    public T save(T element);

    public T hire(Long id);

    public T changeType(Long id, BabysitterType babysitterType);

    public T findById(Long id);

    public List<T> findAll();

}