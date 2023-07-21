package viewmodel;

import javafx.beans.property.*;
import model.Model;
import model.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EditReservationViewModel
{
    Model model;
    private ObjectProperty<LocalDate> startDate;
    private ObjectProperty<LocalDate> endDate;
    private StringProperty error;
    private StringProperty success;

    public EditReservationViewModel(Model model)
    {
        this.model = model;
        error = new SimpleStringProperty();
        success = new SimpleStringProperty();
        startDate = new SimpleObjectProperty<>();
        endDate = new SimpleObjectProperty<>();
    }

    public LocalDate getStartDate()
    {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty()
    {
        return startDate;
    }

    public void setStartDate(LocalDate date)
    {
        startDate.set(date);
    }

    public LocalDate getEndDate()
    {
        return endDate.get();
    }

    public ObjectProperty<LocalDate> endDateProperty()
    {
        return endDate;
    }

    public void setEndDate(LocalDate date)
    {
        endDate.set(date);
    }

    public String getError()
    {
        return error.get();
    }

    public StringProperty errorProperty()
    {
        return error;
    }

    public void reset() throws SQLException, IOException
    {
        // You can use the reservation object to set the initial values for startDate and endDate.
        // For example:
        startDate.set(getReservation().getStartDate());
        endDate.set(getReservation().getEndDate());
    }

    public void saveReservation(Reservation reservation, LocalDate startDate, LocalDate endDate)
    {
        // You can use the model to save the reservation.
        // For example:
        try
        {
            model.editReservation(reservation, startDate, endDate);
            success.set("Reservation saved.");
        } catch (Exception e)
        {
            error.set(e.getMessage());
        }
    }

    public void cancelReservation(Reservation reservation)
    {
        // You can use the model to cancel the reservation.
        // For example:
        try
        {
            model.cancelReservation(reservation);
            success.set("Reservation cancelled.");
        } catch (Exception e)
        {
            error.set(e.getMessage());
        }
    }

    public Property<String> successProperty()
    {
        return success;
    }

    public Reservation getReservation() throws SQLException, IOException
    {
        return model.getReservation();
    }
}
