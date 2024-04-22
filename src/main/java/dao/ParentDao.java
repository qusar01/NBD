package dao;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Parent;

import java.util.List;
import java.util.UUID;

@Dao
public interface ParentDao {
    @Insert
    void createParent(Parent parent);

    @GetEntity
    Parent readParent(ResultSet resultSet);

    @Update
    void updateParent(Parent parent);

    @Delete
    void deleteParent(Parent parent);

    @Select
    Parent getById(UUID id);

    @Query("SELECT * FROM parents")
    List<Parent> getAll();
}