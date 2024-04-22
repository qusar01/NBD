package Repositories;

import java.util.List;

public interface pRepository<T> {

    public T save(T element);

    public T findById(Long id);

    public List<T> findAll();

}
