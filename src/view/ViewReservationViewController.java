package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Reservation;
import viewmodel.ViewCarsViewModel;
import viewmodel.ViewReservationViewModel;

import java.io.IOException;
import java.sql.SQLException;

public class ViewReservationViewController
{
    @FXML
    private Text carText;

    @FXML
    private Text endDateText;

    @FXML
    private Text startDateText;

    @FXML
    private Text statusText;

    private Region root;
    private ViewHandler viewHandler;
    private ViewReservationViewModel viewModel;

    public ViewReservationViewController()
    {
    }

    public void init(ViewHandler viewHandler, ViewReservationViewModel viewModel, Region root) throws SQLException, IOException
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = viewModel;
        carText.setText("Car: " + viewModel.getCarById(viewModel.getReservation().getCarId()).getModel());
        startDateText.setText("Start Date: " + viewModel.getReservation().getStartDate());
        endDateText.setText("End Date: " + viewModel.getReservation().getEndDate());
        statusText.setText("Status: " + viewModel.getReservation().getStatus());
    }

    public void reset() throws SQLException, IOException
    {
        if(viewModel.getReservation() == null)
        {
            carText.setText("Car: " );
            startDateText.setText("Start Date: " );
            endDateText.setText("End Date: " );
            statusText.setText("Status: " );
        }
        else
        {
            carText.setText("Car: " + viewModel.getCarById(viewModel.getReservation().getCarId()).getModel());
            startDateText.setText("Start Date: " + viewModel.getReservation().getStartDate());
            endDateText.setText("End Date: " + viewModel.getReservation().getEndDate());
            statusText.setText("Status: " + viewModel.getReservation().getStatus());
        }
    }

    @FXML
    private void handleClose(ActionEvent event) throws SQLException, IOException{
        viewHandler.openView("cars");
    }

    @FXML
    private void handleEditReservation(ActionEvent event) throws SQLException, IOException{
        viewHandler.openView("editreservation");
    }

    public Region getRoot() {
        return root;
    }


}
