package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import model.Reservation;
import viewmodel.EditReservationViewModel;
import viewmodel.LoginViewModel;

import java.io.IOException;
import java.sql.SQLException;

public class EditReservationViewController
{
    @FXML
    DatePicker startDate;

    @FXML
    DatePicker endDate;

    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    private Region root;
    private ViewHandler viewHandler;
    private EditReservationViewModel viewModel;

    public EditReservationViewController()
    {
    }

    public void init(ViewHandler viewHandler, EditReservationViewModel viewModel, Region root) throws SQLException, IOException
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = viewModel;

        startDate.valueProperty().bindBidirectional(viewModel.startDateProperty());
        endDate.valueProperty().bindBidirectional(viewModel.endDateProperty());

        startDate.setValue(viewModel.getReservation().getStartDate());
        endDate.setValue(viewModel.getReservation().getEndDate());

        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
        successLabel.textProperty().bindBidirectional(viewModel.successProperty());
    }

    @FXML
    private void cancelReservation(ActionEvent event) throws SQLException, IOException
    {
        viewModel.cancelReservation(viewModel.getReservation());
    }

@FXML
    private  void  saveReservation(ActionEvent event) throws SQLException, IOException
{
        viewModel.saveReservation(viewModel.getReservation(), startDate.getValue(), endDate.getValue());
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws SQLException, IOException
    {
        viewHandler.openView("reservation");
    }

    public void reset() throws SQLException, IOException
    {
        viewModel.reset();
    }

    public Region getRoot()
    {
        return root;
    }
}
