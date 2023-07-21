import client.DAOClient;
import client.DAOClientImplimentation;
import dao.*;
import model.CarList;
import model.Model;
import model.ModelImplimentation;
import model.Reservation;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Test
{
    public static void main(String[] args) throws NotBoundException, IOException, SQLException
    {

//        Model model = new ModelImplimentation();
//
//        CarList carList = model.getAllCars();
//        System.out.println(carList);

        CarDAO carDAO = CarDAOImpl.getInstance();
        UserDAO userDAO = UserDAOImpl.getInstance();
        ReservationDAO reservationDAO = ReservationDAOImpl.getInstance();
//        carDAO.create("model", 2002, 200, true);
//        userDAO.create("user1", "pass1", "email1", "phone1", 1);
        List<Reservation> reservations = reservationDAO.readByUserId(1);
        Reservation reservation = reservations.get(reservations.size()-1);
        System.out.println(reservation);
    }
}
