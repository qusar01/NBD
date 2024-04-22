package dao;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Child;

import java.util.List;
import java.util.UUID;

@Dao
public interface ChildDao {
    @Insert
    void createChild(Child child);

    @GetEntity
    Child readChild(ResultSet resultSet);

    @Update
    void updateChild(Child child);

    @Delete
    void deleteChild(Child child);

    @Select
    Child getById(UUID id);

    @Query("SELECT * FROM children")
    List<Child> getAll();
}