package dao;

import model.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private static ReservationDAOImpl instance;

    private ReservationDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ReservationDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new ReservationDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep2", "postgres", "1234");
    }

    @Override
    public Reservation create(int userId, int carId, LocalDate startDate, LocalDate endDate, int status) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reservation(userId, carId, startDate, endDate, status) VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setInt(2, carId);
            statement.setDate(3, Date.valueOf(startDate));
            statement.setDate(4, Date.valueOf(endDate));
            statement.setInt(5, status);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int reservationId = generatedKeys.getInt(1);
                return new Reservation(reservationId, userId, carId, startDate, endDate, status);
            } else {
                throw new SQLException("Creating reservation failed, no ID obtained.");
            }
        }
    }

    @Override
    public Reservation readById(int reservationId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE reservationId = ?");
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                int carId = resultSet.getInt("carId");
                LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                int status = resultSet.getInt("status");
                return new Reservation(reservationId, userId, carId, startDate, endDate, status);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Reservation> readByUserId(int userId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE userId = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Reservation> result = new ArrayList<>();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservationId");
                int carId = resultSet.getInt("carId");
                LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                int status = resultSet.getInt("status");
                Reservation reservation = new Reservation(reservationId, userId, carId, startDate, endDate, status);
                result.add(reservation);
            }
            return result;
        }
    }

    @Override
    public List<Reservation> readAll() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Reservation> result = new ArrayList<>();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservationId");
                int userId = resultSet.getInt("userId");
                int carId = resultSet.getInt("carId");
                LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                int status = resultSet.getInt("status");
                Reservation reservation = new Reservation(reservationId, userId, carId, startDate, endDate, status);
                result.add(reservation);
            }
            return result;
        }
    }

    @Override
    public void update(Reservation reservation) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE reservation SET userId = ?, carId = ?, startDate = ?, endDate = ?, status = ? WHERE reservationId = ?"
            );
            statement.setInt(1, reservation.getUserId());
            statement.setInt(2, reservation.getCarId());
            statement.setDate(3, Date.valueOf(reservation.getStartDate()));
            statement.setDate(4, Date.valueOf(reservation.getEndDate()));
            statement.setInt(5, reservation.getStatus());
            statement.setInt(6, reservation.getReservationId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Reservation reservation) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM reservation WHERE reservationId = ?"
            );
            statement.setInt(1, reservation.getReservationId());
            statement.executeUpdate();
        }
    }
}
