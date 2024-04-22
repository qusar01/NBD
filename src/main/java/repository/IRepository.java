package repository;


import java.util.List;
import java.util.UUID;

public interface IRepository <T> {

    T get(UUID id);

    void add(T elements);

    void remove(T  elements);

    void update(T elements);

    List<T> find(Object elements);

    List<T> getAll();

}