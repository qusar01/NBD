package Repositories;

public abstract class RepositoryDecorator<T> implements RepositoryInterface<T>{
    protected RepositoryInterface<T> repository;
}