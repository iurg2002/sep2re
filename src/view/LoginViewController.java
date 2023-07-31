package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;
import viewmodel.ViewCarsViewModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController
{

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private LoginViewModel viewModel;

    public LoginViewController()
    {
    }

    public void init(ViewHandler viewHandler, LoginViewModel viewModel, Region root) throws SQLException, IOException
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = viewModel;

        usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.getErrorProperty());
    }





    @FXML
    private void handleLoginButton(ActionEvent event) throws SQLException, IOException
    {
        System.out.println("Login button pressed");
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Password: " + passwordField.getText());
        if(viewModel.validateLoginForm())
        {
            boolean login = viewModel.login(usernameField.getText(), passwordField.getText());
            if (login)
            {
                if(viewModel.isAdmin())
                    viewHandler.openView("managecars");
                else
                viewHandler.openView("cars");
            }
        }
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) throws SQLException, IOException
    {
        System.out.println("Register button pressed");
        viewHandler.openView("register");
    }

    public void reset()
    {
    }

    public Region getRoot()
    {
        return root;
    }
}
