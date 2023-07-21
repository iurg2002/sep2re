package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Car;
import model.Model;
import model.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewCarsViewModel
{
    Model model;
    private ObservableList<Car> carsList;
    private StringProperty error;

    public ViewCarsViewModel(Model model) throws IOException, SQLException
    {
        this.model = model;
        carsList = FXCollections.observableArrayList(model.getAllCars().getList());
        this.error = new SimpleStringProperty();
    }

    public Reservation reserveCar(int carId, LocalDate startDate, LocalDate endDate, int status) throws IOException, SQLException
    {
        try
        {
            Reservation reservation =  model.reserveCar(carId, startDate, endDate, status);
            return reservation;
        } catch (SQLException e)
        {
            error.set(e.getMessage());
        }
        return null;

    }

    public void clearError()
    {
        error.set(null);
    }

    public Model getModel()
    {
        return model;
    }

    public String getError()
    {
        return error.get();
    }

    public StringProperty errorProperty()
    {
        return error;
    }

    public ObservableList<Car> getCarsList() throws SQLException, IOException
    {
        carsList = FXCollections.observableArrayList(model.getAllCars().getList());
        return carsList;
    }

}
