import db.DatabaseReadClient;
import javafx.application.Application;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import utils.UILoader;

public class OnlineElectronicsStoreApplication extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // todo: WHEN DEVELOPING - change fxml file to the one you are working on
        UILoader.render(primaryStage, new OnlineElectronicsStore(new DatabaseReadClient()), "/view/Login.fxml", "Title");
    }
}
