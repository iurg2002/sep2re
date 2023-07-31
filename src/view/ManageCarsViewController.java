package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Car;
import viewmodel.ManageCarsViewModel;
import viewmodel.ViewCarsViewModel;

import java.io.IOException;
import java.sql.SQLException;

public class ManageCarsViewController
{
    @FXML
    private TableColumn<Car, Boolean> availabilityColumn;

    @FXML
    private TableColumn<Car, Integer> carIdColumn;

    @FXML
    private TableView<Car> carsTable;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TextField modelField;

    @FXML
    private TextField priceField;

    @FXML
    private TableColumn<Car, Double> rentalPriceColumn;

    @FXML
    private TableColumn<Car, Integer> yearColumn;

    @FXML
    private TextField yearField;

    @FXML private Label errorLabel;

    @FXML private Label successLabel;

    private Region root;
    private ViewHandler viewHandler;
    private ManageCarsViewModel viewModel;

    public ManageCarsViewController()
    {}

    public void init(ViewHandler viewHandler, ManageCarsViewModel viewModel, Region root) throws SQLException, IOException
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
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
        successLabel.textProperty().bindBidirectional(viewModel.successProperty());
    }

    @FXML
    void addCarButton(ActionEvent event) throws SQLException, IOException
    {
        reset();
        viewModel.addCar(modelField.getText(), yearField.getText(), priceField.getText());
        carsTable.setItems(viewModel.getCarsList());
    }

    @FXML
    void deleteCarButton(ActionEvent event) throws SQLException, IOException
    {
        reset();
        Car car = carsTable.getSelectionModel().getSelectedItem();
        if (car == null)
        {
            viewModel.errorProperty().set("Please select a car");
        }
        else
        {
            try
            {
                viewModel.deleteCar(car.getCarId());
                carsTable.setItems(viewModel.getCarsList());
            }
            catch (SQLException e)
            {
                viewModel.errorProperty().set(e.getMessage());
            }
        }

    }

    public void reset() throws SQLException, IOException
    {
        viewModel.clear();

    }


    public Region getRoot()
    {
        return root;
    }
}
