package model;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;

// Car.java - Model Class for representing Car data
public class Car implements Serializable
{
    private int carId;
    private String model;
    private int year;
    private double rentalPrice;
    private boolean availability;


    public Car(int carId, String model, int year, double rentalPrice, boolean availability)
    {
        this.carId = carId;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.availability = availability;
    }

    public int getCarId()
    {
        return carId;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public double getRentalPrice()
    {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice)
    {
        this.rentalPrice = rentalPrice;
    }

    public boolean isAvailability()
    {
        return availability;
    }

    public void setAvailability(boolean availability)
    {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", rentalPrice=" + rentalPrice +
                ", availability=" + availability +
                '}';
    }

}
