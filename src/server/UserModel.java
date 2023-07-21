package server;

import model.Car;
import model.CarList;
import model.Reservation;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface UserModel extends Remote{
    // User Actions

   CarList getAllCars() throws RemoteException, SQLException;
   CarList getAllAvailableCars() throws RemoteException;
   Reservation reserveCar(int userId, int carId, LocalDate startDate, LocalDate endDate, int status) throws RemoteException, SQLException;

   Car getCarById(int id) throws RemoteException, SQLException;

    User login(String username, String pass) throws RemoteException, SQLException;

    User registerUser(String username, String password, String email, String phone, int i)throws RemoteException, SQLException;

    void editReservation(Reservation reservation, LocalDate startDate, LocalDate endDate)throws RemoteException, SQLException;

    void cancelReservation(Reservation reservation)throws RemoteException, SQLException;

    Reservation getReservationByUser(User currentUser)throws RemoteException, SQLException;
}

