package model;

import client.DAOClient;
import client.DAOClientImplimentation;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModelImplimentation implements Model
{
    private DAOClient client;
    private User currentUser;
    public ModelImplimentation() throws NotBoundException, RemoteException
    {
        this.client = new DAOClientImplimentation("localhost", 5099);
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    @Override
    public CarList getAllCars() throws IOException, SQLException
    {
        return client.getAllCars();
    }

    @Override
    public CarList getAllAvailableCars() throws IOException
    {
        return client.getAllAvailableCars();
    }

    @Override
    public Reservation reserveCar( int carId, LocalDate startDate, LocalDate endDate, int status) throws IOException, SQLException
    {
        return client.reserveCar(currentUser.getUserId(), carId, startDate, endDate, status);
    }

    @Override
    public Car getCarById(int id) throws SQLException, IOException
    {
        return client.getCarById(id);
    }

    @Override
    public void login(String username, String pass) throws SQLException, IOException
    {
        currentUser = client.login(username, pass);
    }

    @Override
    public void registerUser(String username, String password, String email, String phone, int i) throws SQLException, IOException
    {
        User user = client.registerUser(username, password, email, phone, i);
        currentUser = user;
    }

    @Override
    public void editReservation(Reservation reservation, LocalDate startDate, LocalDate endDate) throws SQLException, IOException
    {
        client.editReservation(reservation,startDate, endDate);
    }

    @Override
    public void cancelReservation(Reservation reservation) throws SQLException, IOException
    {
        client.cancelReservation(reservation);
    }

    @Override
    public Reservation getReservation() throws SQLException, IOException
    {
        return client.getReservationByUser(currentUser);
    }
}
