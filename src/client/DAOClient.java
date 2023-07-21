package client;

import model.Car;
import model.CarList;
import model.Reservation;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public interface DAOClient
{
    CarList getAllCars() throws IOException, SQLException;
    CarList getAllAvailableCars() throws IOException;
    Reservation reserveCar(int userId, int carId, LocalDate startDate, LocalDate endDate, int status) throws IOException, SQLException;

    Car getCarById(int id) throws IOException, SQLException;

    User login(String username, String pass) throws IOException, SQLException;

    User registerUser(String username, String password, String email, String phone, int i) throws IOException, SQLException;

    void editReservation(Reservation reservation, LocalDate startDate, LocalDate endDate)throws IOException, SQLException;

    void cancelReservation(Reservation reservation)throws IOException, SQLException;

    Reservation getReservationByUser(User currentUser)throws IOException, SQLException;
}
