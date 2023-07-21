package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CarList implements Serializable
{
    public int size = 0;
    private ArrayList<Car> cars;

    public CarList()
    {
        cars = new ArrayList<>();
    }

    public CarList(ArrayList<Car> cars)
    {
        this.cars = cars;
    }

    public void addCar(Car car)
    {
        cars.add(car);
        size++;
    }

    public Car getCarById(int carId)
    {
        for (Car car : cars)
        {
            if (car.getCarId() == carId)
            {
                return car;
            }
        }
        return null;
    }

    public ArrayList<Car> getAllCars()
    {
        return cars;
    }

    public ArrayList<Car> getAllAvailableCars()
    {
        ArrayList<Car> availableCars = new ArrayList<>();
        for (Car car : cars)
        {
            if (car.isAvailability())
            {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public String toString()
    {
        String result = "";
        for (Car car : cars)
        {
            result += car.toString() + "\n";
        }
        return result;
    }

    public ArrayList<Car> getList()
    {
        return cars;
    }
}
