package model;

import java.io.Serializable;
import java.time.LocalDate;

// Reservation.java - Model Class for representing Reservation data
public class Reservation implements Serializable
{
    private int reservationId;
    private int userId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int status; // 1 for pending, 2 for approved, 3 for denied

    public Reservation(int reservationId, int userId, int carId, LocalDate startDate, LocalDate endDate, int status)
    {
        this.reservationId = reservationId;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getReservationId()
    {
        return reservationId;
    }

    public void setReservationId(int reservationId)
    {
        this.reservationId = reservationId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getCarId()
    {
        return carId;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    // Constructors, getters, setters, and other methods
    // ...

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}

