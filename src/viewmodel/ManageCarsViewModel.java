package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Car;
import model.Model;

import java.io.IOException;
import java.sql.SQLException;

public class ManageCarsViewModel
{
    Model model;
    private ObservableList<Car> carsList;
    private StringProperty error;
    private StringProperty success;

    public String getError()
    {
        return error.get();
    }

    public StringProperty errorProperty()
    {
        return error;
    }

    public String getSuccess()
    {
        return success.get();
    }

    public StringProperty successProperty()
    {
        return success;
    }

    public ManageCarsViewModel(Model model) throws SQLException, IOException
    {
        this.model = model;
        carsList = FXCollections.observableArrayList(model.getAllCars().getList());
        this.error = new SimpleStringProperty();
        this.success = new SimpleStringProperty();
    }


    public ObservableList<Car> getCarsList() throws SQLException, IOException
    {
        carsList = FXCollections.observableArrayList(model.getAllCars().getList());
        return carsList;
    }

    public void addCar(String carModel, String year, String price) {
        Thread thread = new Thread(() -> {
            try {
                int yearInt = Integer.parseInt(year);
                double priceDouble = Double.parseDouble(price);
                model.addCar(carModel, yearInt, priceDouble);
                Platform.runLater(() -> {
                    try {
                        carsList.setAll(model.getAllCars().getList());
                        success.setValue("Car added successfully");
                    } catch (SQLException | IOException e) {
                        error.setValue(e.getMessage());
                    }
                });
            } catch (SQLException | IOException e) {
                Platform.runLater(() -> error.setValue(e.getMessage()));
            } catch (NumberFormatException e) {
                Platform.runLater(() -> error.setValue("Year and price must be numbers"));
            }
        });
        thread.start();
    }

    public void clear()
    {
        error.setValue("");
        success.setValue("");
    }

    public void deleteCar(int carId) {
        Thread thread = new Thread(() -> {
            try {
                Car car = model.getCarById(carId);
                model.deleteCar(carId);
                Platform.runLater(() -> {
                    try {
                        carsList.setAll(model.getAllCars().getList());
                        success.setValue("Car deleted successfully");
                    } catch (SQLException | IOException e) {
                        error.setValue(e.getMessage());
                    }
                });
                Platform.runLater(() -> success.setValue("Car deleted successfully"));
            } catch (SQLException | IOException e) {
                Platform.runLater(() -> error.setValue(e.getMessage()));
            }
        });
        thread.start();
    }
}
