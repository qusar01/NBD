package dao;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Rent;

import java.util.List;
import java.util.UUID;

@Dao
public interface RentDao {
    @Insert
    void createRent(Rent rent);

    @GetEntity
    Rent readRent(ResultSet resultSet);

    @Update
    void updateRent(Rent rent);

    @Delete
    void deleteRent(Rent rent);

    @Select
    Rent getById(UUID id);

    @Query("SELECT * FROM rents")
    List<Rent> getAll();
}