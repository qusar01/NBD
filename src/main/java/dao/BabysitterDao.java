package dao;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Babysitter;

import java.util.UUID;

@Dao
public interface BabysitterDao {
    @Insert
    void createBabysitter(Babysitter babysitter);

    @GetEntity
    Babysitter readBabysitter(ResultSet resultSet);

    @Update
    void updateBabysitter(Babysitter babysitter);

    @Delete
    void deleteBabysitter(Babysitter babysitter);

    @Select
    Babysitter getById(UUID id);

    @Query("SELECT * FROM babysitters")
    ResultSet getAll();
}