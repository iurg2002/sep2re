package viewmodel;

import model.Model;

import java.io.IOException;
import java.sql.SQLException;

public class ViewModelFactory
{
    private ViewCarsViewModel viewCarsViewModel;
    private ViewReservationViewModel viewReservationViewModel;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    private EditReservationViewModel editReservationViewModel;

    public ViewModelFactory(Model model) throws IOException, SQLException
    {
        viewCarsViewModel = new ViewCarsViewModel(model);
        viewReservationViewModel = new ViewReservationViewModel(model);
        loginViewModel = new LoginViewModel(model);
        registerViewModel = new RegisterViewModel(model);
        editReservationViewModel = new EditReservationViewModel(model);
    }

    public EditReservationViewModel getEditReservationViewModel()
    {
        return editReservationViewModel;
    }

    public ViewCarsViewModel getViewCarsViewModel()
    {
        return viewCarsViewModel;
    }

    public ViewReservationViewModel getViewReservationViewModel()
    {
        return viewReservationViewModel;
    }

    public LoginViewModel getLoginViewModel()
    {
        return loginViewModel;
    }

    public RegisterViewModel getRegisterViewModel()
    {
        return registerViewModel;
    }


}
