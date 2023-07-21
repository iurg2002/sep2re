package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.RegisterViewModel;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterViewController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private RegisterViewModel viewModel;

    public RegisterViewController() {
    }

    public void init(ViewHandler viewHandler, RegisterViewModel viewModel, Region root) throws SQLException, IOException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = viewModel;

        usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
        emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
        phoneField.textProperty().bindBidirectional(viewModel.getPhoneProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.getErrorProperty());
    }

    @FXML
    private void handleLoginButton(ActionEvent event) throws SQLException, IOException {
        viewHandler.openView("login");
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) throws SQLException, IOException
    {
        if (viewModel.validateRegisterForm()) {
            viewModel.registerUser(usernameField.getText(), passwordField.getText(), emailField.getText(), phoneField.getText());
            // Show success message or navigate to login view
            viewHandler.openView("cars");
            clearFields();
        } else {
            // Show error message or indicate invalid input
            errorLabel.setText("Please fill in all fields with valid data.");
        }
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        emailField.clear();
        phoneField.clear();
    }

    public void reset() {
        clearFields();
    }

    public Region getRoot() {
        return root;
    }
}

