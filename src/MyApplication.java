import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelImplimentation;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Model model = new ModelImplimentation();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }
}
