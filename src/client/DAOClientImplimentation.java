package client;

import model.Car;
import model.CarList;
import model.Reservation;
import model.User;
import server.UserModel;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAOClientImplimentation implements DAOClient
{
    private UserModel model;
    public DAOClientImplimentation(String host, int port) throws RemoteException, NotBoundException
    {
        Registry registry = LocateRegistry.getRegistry(host, port);
        System.out.println("want it");
        model = (UserModel) registry.lookup("userModel");
        System.out.println("took it");
    }
    @Override
    public CarList getAllCars() throws IOException, SQLException
    {
        return  model.getAllCars();
    }

    @Override
    public CarList getAllAvailableCars() throws IOException
    {
        return  model.getAllAvailableCars();
    }

    @Override
    public Reservation reserveCar(int userId, int carId, LocalDate startDate, LocalDate endDate, int status) throws IOException, SQLException
    {
        return model.reserveCar(userId, carId, startDate, endDate, status);
    }

    @Override
    public Car getCarById(int id) throws SQLException, RemoteException
    {
        return model.getCarById(id);
    }

    @Override
    public User login(String username, String pass) throws SQLException, RemoteException
    {
        return model.login(username, pass);
    }

    @Override
    public User registerUser(String username, String password, String email, String phone, int i) throws IOException, SQLException
    {
        User user  = model.registerUser(username, password, email, phone, i);
        return user;
    }

    @Override
    public void editReservation(Reservation reservation, LocalDate startDate, LocalDate endDate) throws IOException, SQLException
    {
        model.editReservation(reservation, startDate, endDate);
    }

    @Override
    public void cancelReservation(Reservation reservation) throws IOException, SQLException
    {
        model.cancelReservation(reservation);
    }

    @Override
    public Reservation getReservationByUser(User currentUser) throws IOException, SQLException
    {
        return model.getReservationByUser(currentUser);
    }

    @Override
    public void addCar(String carModel, int year, double price) throws SQLException, RemoteException
    {
        model.addCar(carModel, year, price);
    }

    @Override
    public void deleteCar(int carId) throws SQLException, RemoteException
    {
        model.deleteCar(carId);
    }
}
