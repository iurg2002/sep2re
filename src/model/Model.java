package model;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public interface Model
{
    CarList getAllCars() throws IOException, SQLException;
    CarList getAllAvailableCars() throws IOException;
    Reservation reserveCar( int carId, LocalDate startDate, LocalDate endDate, int status) throws IOException, SQLException;

    Car getCarById(int id) throws SQLException, IOException;

    void login(String username, String pass) throws SQLException, IOException;

    void registerUser(String username, String password, String email, String phone, int i) throws SQLException, IOException;

    void editReservation(Reservation reservation, LocalDate startDate, LocalDate endDate) throws SQLException, IOException;

    void cancelReservation(Reservation reservation)throws SQLException, IOException;

    Reservation getReservation()throws SQLException, IOException;
}
