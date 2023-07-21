package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Reservation;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewHandler
{
    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;
    private ViewCarsViewController viewCarsViewController;
    private ViewReservationViewController viewReservationViewController;
    private LoginViewController loginViewController;
    private RegisterViewController registerViewController;
    private EditReservationViewController editReservationViewController;

    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) throws SQLException, IOException
    {
        this.primaryStage = primaryStage;
        openView("login");
    }

    public void openView(String id) throws SQLException, IOException
    {
        Region root = null;
        switch (id)
        {
            case "cars":
                root = loadViewCarsView("ViewCarsView.fxml");
                break;
            case "login":
                root = loadLoginView("LoginView.fxml");
                break;
            case "register":
                root = loadRegisterView("RegisterView.fxml");
                break;
            case "reservation":
                root = loadViewReservationView("ViewReservationView.fxml");
                break;
            case "editreservation":
                root = loadEditReservationView("EditReservationView.fxml");
                break;
        }
        currentScene.setRoot(root);
        String title = "";
        if(root.getUserData() != null)
        {
            title += root.getUserData();
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }


    public void closeView()
    {
        primaryStage.close();
    }

    private Region loadViewCarsView(String fxmlFile) throws SQLException, IOException
    {
        Region root = null;
        if(viewCarsViewController == null)
        {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                viewCarsViewController = loader.getController();
                viewCarsViewController.init(this, viewModelFactory.getViewCarsViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            viewCarsViewController.reset();
        }
        return viewCarsViewController.getRoot();
    }

    private Region loadLoginView(String fxmlFile) throws SQLException, IOException
    {
        Region root = null;
        if(loginViewController == null)
        {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(this, viewModelFactory.getLoginViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            loginViewController.reset();
        }
        return loginViewController.getRoot();
    }
    private Region loadRegisterView(String fxmlFile) throws SQLException, IOException
    {
        Region root = null;
        if(registerViewController == null)
        {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                registerViewController = loader.getController();
                registerViewController.init(this, viewModelFactory.getRegisterViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            registerViewController.reset();
        }
        return registerViewController.getRoot();
    }

    private Region loadViewReservationView(String fxmlFile) throws SQLException, IOException
    {
        Region root = null;
        if(viewReservationViewController == null)
        {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                viewReservationViewController = loader.getController();
                viewReservationViewController.init(this, viewModelFactory.getViewReservationViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            viewReservationViewController.reset();
        }
        return viewReservationViewController.getRoot();
    }

    private Region loadEditReservationView(String fxmlFile) throws SQLException, IOException
    {
        Region root = null;
        if(editReservationViewController == null)
        {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                editReservationViewController = loader.getController();
                editReservationViewController.init(this, viewModelFactory.getEditReservationViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            editReservationViewController.reset();
        }
        return editReservationViewController.getRoot();
    }

}
