package server;

import dao.*;
import model.Car;
import model.CarList;
import model.Reservation;
import model.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserModelImplimentation implements UserModel
{
    private CarDAO carDAO;
    private ReservationDAO reservationDAO;
    private UserDAO userDAO;

    public UserModelImplimentation() throws RemoteException, SQLException
    {
        carDAO = CarDAOImpl.getInstance();
        reservationDAO = ReservationDAOImpl.getInstance();
        userDAO = UserDAOImpl.getInstance();
    }

    @Override
    public CarList getAllCars() throws RemoteException, SQLException
    {
        System.out.println("getAllCars");
        return new CarList((ArrayList<Car>) carDAO.readAll());
    }

    @Override
    public CarList getAllAvailableCars() throws RemoteException
    {
        Car car1 = new Car(1, "Toyota Corrola", 2015, 200, true);
        Car car2 = new Car(2, "Toyota Camry", 2016, 300, true);
        Car car3 = new Car(3, "Toyota Prius", 2017, 400, true);
        Car car4 = new Car(4, "Toyota Yaris", 2018, 500, true);
        Car car5 = new Car(5, "Toyota Avalon", 2019, 600, true);
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        return new CarList(cars);
    }

    @Override
    public Reservation reserveCar(int userId, int carId, LocalDate startDate, LocalDate endDate, int status) throws RemoteException, SQLException
    {
        if(carDAO.readById(carId).isAvailability() == false)
        {
            throw new SQLException("Car is not available");
        }
        if(reservationDAO.readByUserId(userId).size() > 0)
        {
            throw new SQLException("User already has a reservation");
        }
        Reservation reservation = reservationDAO.create(userId, carId, startDate, endDate, status);
        Car car = carDAO.readById(carId);
        car.setAvailability(false);
        carDAO.update(car);
        return reservation;
    }

    @Override
    public Car getCarById(int id) throws SQLException
    {
        Car car = carDAO.readById(id);
        return car;
    }

    @Override
    public User login(String username, String pass) throws SQLException
    {
        User user = userDAO.readByUsername(username);
        if (user != null && user.getPassword().equals(pass))
        {
            return user;
        } else {
            throw new SQLException("Invalid username or password");
        }
    }

    @Override
    public User registerUser(String username, String password, String email, String phone, int i) throws RemoteException, SQLException
    {
        User user = userDAO.create(username, password, email, phone, i);
        return  user;
    }

    @Override
    public void editReservation(Reservation reservation, LocalDate startDate, LocalDate endDate) throws RemoteException, SQLException
    {
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservationDAO.update(reservation);
    }

    @Override
    public void cancelReservation(Reservation reservation) throws RemoteException, SQLException
    {
        reservationDAO.delete(reservation);
        Car car = carDAO.readById(reservation.getCarId());
        car.setAvailability(true);
        carDAO.update(car);
    }

    @Override
    public Reservation getReservationByUser(User currentUser) throws RemoteException, SQLException
    {


        try
        {
            List<Reservation> reservations = reservationDAO.readByUserId(currentUser.getId());
            Reservation reservation = reservations.get(reservations.size()-1);
            return reservation;
        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }

    }

    @Override
    public void addCar(String model, int year, double price) throws RemoteException, SQLException
    {
        carDAO.create(model, year, price, true);
    }

    @Override
    public void deleteCar(int carId) throws SQLException
    {
        carDAO.delete(carDAO.readById(carId));
    }
}
