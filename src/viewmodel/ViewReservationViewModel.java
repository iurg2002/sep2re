package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Car;
import model.Model;
import model.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewReservationViewModel
{
    Model model;


    public ViewReservationViewModel(Model model) throws IOException, SQLException
    {
        this.model = model;

    }

    public Car getCarById(int id) throws IOException, SQLException
    {
        return model.getCarById(id);
    }

    public Reservation getReservation() throws SQLException, IOException
    {


        return model.getReservation();

    }
}
