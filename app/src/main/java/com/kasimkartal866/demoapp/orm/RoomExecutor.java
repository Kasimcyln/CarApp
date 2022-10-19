package com.kasimkartal866.demoapp.orm;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoomExecutor {
    private static RoomExecutor instance;
    private final UserDao dao;

    private RoomExecutor(Context context) {
        dao = UserDatabase.getUserDatabase(context).userDao();
    }

    public static RoomExecutor getInstance(Context context) {
        if (instance == null)
            instance = new RoomExecutor(context);
        return instance;
    }

    public User checkUserPass (String username, String password) {
        UserPass userPass  = new UserPass(username, password);
        User user = null;

        try {
            user = new CheckUserPass_Async().execute(userPass).get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser (User user) {
        if(user != null) {
            try {
                new AddUsers_Async().execute(user).get();
            }catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void addCar(Car car) {
        if (car != null) {
            new AddCar_Async(car).execute();
        }
    }

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        try {

            cars = new GetCars_Async().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCarsByUser(int userId) {
        List<Car> cars = new ArrayList<>();
        try {
            if (userId == 1)
                cars = new GetCars_Async().execute().get();
            else
                cars = new GetMyCars_Async().execute(userId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return cars;
    }



    //**********************************************************************************************


    private class CheckUserPass_Async extends AsyncTask<UserPass, Void, User> {
        @Override
        protected User doInBackground(UserPass... users) {
            return dao.checkUserPass(users[0].getUserName(),users[0].getPass());        }
    }

    private class Car_Async extends AsyncTask<Void, Void, List<User>> {
        @Override
        protected List<User> doInBackground(Void... voids) {
            return dao.getAllUsers();
        }
    }

    private class AddUsers_Async extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            dao.addUser(users[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AddCar_Async extends AsyncTask<Car, Void, Void> {
        public AddCar_Async(Car car) {
            this.car = car;
        }
        Car car;
        @Override
        protected Void doInBackground(Car... cars) {
            dao.addCar(car);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetCars_Async extends AsyncTask<Void, Void, List<Car>> {
        @Override
        protected List<Car> doInBackground(Void... voids) {
            return dao.getAllCars();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetMyCars_Async extends AsyncTask<Integer, Void, List<Car>> {
        @Override
        protected List<Car> doInBackground(Integer... userIds) {
            return dao.getMyCars(userIds[0]);
        }
    }
}


