package com.kasimkartal866.demoapp.orm;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE email = (:email) and password = (:password)")
    User checkUserPass(String email , String password);
    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Delete
    void deleteCar(Car car);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Insert
    void addCar(Car car);

    @Query("SELECT * FROM Car")
    List<Car> getAllCars();

    @Query("SELECT * FROM Car where userId = :userId")
    List<Car> getMyCars(int userId);
 }
