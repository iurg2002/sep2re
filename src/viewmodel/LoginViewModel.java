package viewmodel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewModel
{

    Model model;
    StringProperty username;
    StringProperty password;
    StringProperty error;

    public LoginViewModel(Model model) throws IOException, SQLException
    {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    public StringProperty getUsernameProperty()
    {
        return username;
    }


    public StringProperty getPasswordProperty()
    {
        return password;
    }

    public StringProperty getErrorProperty()
    {
        return error;
    }

    public boolean validateLoginForm() {
        if(username.get() == null || password.get() == null) {
            error.set("Please fill in all fields.");
            return false;
        }
        if (username.get().isEmpty() || password.get().isEmpty()) {
            error.set("Please fill in all fields.");
            return false;
        }
        // You can add more validation logic here as needed (e.g., check email format)
        return true;
    }

    public boolean login(String username, String pass)
    {
        try
        {
            model.login(username, pass);
            return true;
        } catch (Exception e)
        {
            error.set(e.getMessage());
            return false;
        }
    }
}
