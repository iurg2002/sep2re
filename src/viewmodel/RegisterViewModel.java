package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterViewModel {
    private Model model;
    private StringProperty username;
    private StringProperty password;
    private StringProperty email;
    private StringProperty phone;
    private StringProperty error;

    public RegisterViewModel(Model model) throws IOException, SQLException {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        email = new SimpleStringProperty();
        phone = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    public StringProperty getUsernameProperty() {
        return username;
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public StringProperty getEmailProperty() {
        return email;
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    public StringProperty getErrorProperty() {
        return error;
    }

    public boolean validateRegisterForm() {
        if(username.get() == null || password.get() == null || email.get() == null || phone.get() == null) {
            error.set("Please fill in all fields.");
            return false;
        }
        if (username.get().isEmpty() || password.get().isEmpty() || email.get().isEmpty() || phone.get().isEmpty()) {
            error.set("Please fill in all fields.");
            return false;
        }
        // You can add more validation logic here as needed (e.g., check email format)
        return true;
    }

    public void registerUser(String username, String password, String email, String phone) {
        try {
            model.registerUser(username, password, email, phone, 1); // Assuming role is 1 for regular users
            // Optionally, you can handle success and navigate to the login view here
        } catch (Exception e) {
            error.set("Registration failed. " + e.getMessage());
        }
    }
}
