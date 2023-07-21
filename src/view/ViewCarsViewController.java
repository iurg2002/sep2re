package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Car;
import model.Reservation;
import viewmodel.ViewCarsViewModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewCarsViewController {
    @FXML
    private TableView<Car> carsTable;
    @FXML
    private TableColumn<Car, Integer> carIdColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, Integer> yearColumn;
    @FXML
    private TableColumn<Car, Double> rentalPriceColumn;
    @FXML
    private TableColumn<Car, Boolean> availabilityColumn;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private ViewCarsViewModel viewModel;

    public ViewCarsViewController() {
    }

    public void init(ViewHandler viewHandler, ViewCarsViewModel viewModel, Region root) throws SQLException, IOException
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = viewModel;

        carsTable.setItems(viewModel.getCarsList());

        // Set up the cell value factory for each column
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        rentalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        availabilityColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAvailability()));
        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
    }

    @FXML
    private void handleReserveCar(ActionEvent event) throws SQLException, IOException {
        // Get the selected car from the TableView
        Car selectedCar = carsTable.getSelectionModel().getSelectedItem();
        Reservation reservation = null;


        LocalDate selectedStartDate = startDate.getValue();
        LocalDate selectedEndDate = endDate.getValue();

          if (selectedStartDate == null || selectedEndDate == null) {
            System.out.println("Please select both start date and end date.");
            errorLabel.setText("Please select both start date and end date.");
        } else if (selectedStartDate.isAfter(selectedEndDate)) {
            System.out.println("Start date cannot be after end date.");
            errorLabel.setText("Start date cannot be after end date.");
        } else {
              if (selectedCar == null) {
                  System.out.println("No car selected.");
                  errorLabel.setText("No car selected.");
              } else {
                  reservation = viewModel.reserveCar( selectedCar.getCarId(), selectedStartDate, selectedEndDate, 1);
                  if(reservation != null)  viewHandler.openView("reservation");
              }
        }
    }
    @FXML
    private void handleShowMyReservation(ActionEvent event) throws SQLException, IOException {
        viewHandler.openView("reservation");
    }

    public void reset() throws SQLException, IOException
    {
        errorLabel.setText("");
        carsTable.getSelectionModel().clearSelection();
        startDate.setValue(null);
        endDate.setValue(null);
        carsTable.setItems(viewModel.getCarsList());
    }

    public Region getRoot() {
        return root;
    }
}
