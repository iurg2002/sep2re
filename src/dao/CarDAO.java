package dao;

import model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    Car create(String model, int year, double rentalPrice, boolean availability) throws SQLException;
    Car readById(int carId) throws SQLException;
    List<Car> readAll() throws SQLException;
    void update(Car car) throws SQLException;
    void delete(Car car) throws SQLException;
}
