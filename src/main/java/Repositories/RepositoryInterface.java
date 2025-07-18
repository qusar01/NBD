package Repositories;

import java.util.UUID;

public interface RepositoryInterface<T> {
    T get(UUID id);
    boolean add(T object);
    void update(T object);
    T delete(UUID id);
}
