package dao;

import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private static CarDAOImpl instance;

    private CarDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized CarDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new CarDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep2", "postgres", "1234");
    }

    @Override
    public Car create(String model, int year, double rentalPrice, boolean availability) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO car(model, year, rentalPrice, availability) VALUES (?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model);
            statement.setInt(2, year);
            statement.setDouble(3, rentalPrice);
            statement.setBoolean(4, availability);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int carId = generatedKeys.getInt(1);
                return new Car(carId, model, year, rentalPrice, availability);
            } else {
                throw new SQLException("Creating car failed, no ID obtained.");
            }
        }
    }

    @Override
    public Car readById(int carId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM car WHERE carId = ?"
            );
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                double rentalPrice = resultSet.getDouble("rentalPrice");
                boolean availability = resultSet.getBoolean("availability");
                return new Car(carId, model, year, rentalPrice, availability);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Car> readAll() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM car"
            );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Car> result = new ArrayList<>();
            while (resultSet.next()) {
                int carId = resultSet.getInt("carId");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                double rentalPrice = resultSet.getDouble("rentalPrice");
                boolean availability = resultSet.getBoolean("availability");
                Car car = new Car(carId, model, year, rentalPrice, availability);
                result.add(car);
            }
            return result;
        }
    }

    @Override
    public void update(Car car) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE car SET model = ?, year = ?, rentalPrice = ?, availability = ? WHERE carId = ?"
            );
            statement.setString(1, car.getModel());
            statement.setInt(2, car.getYear());
            statement.setDouble(3, car.getRentalPrice());
            statement.setBoolean(4, car.isAvailability());
            statement.setInt(5, car.getCarId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Car car) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM car WHERE carId = ?"
            );
            statement.setInt(1, car.getCarId());
            statement.executeUpdate();
        }
    }
}

