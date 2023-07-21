package dao;

import model.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
    Reservation create(int userId, int carId, LocalDate startDate, LocalDate endDate, int status) throws SQLException;
    Reservation readById(int reservationId) throws SQLException;
    List<Reservation> readByUserId(int userId) throws SQLException;
    List<Reservation> readAll() throws SQLException;
    void update(Reservation reservation) throws SQLException;
    void delete(Reservation reservation) throws SQLException;
}
