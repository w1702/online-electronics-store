import db.DatabaseClient;
import javafx.application.Application;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import utils.UILoader;

import java.io.File;

public class OnlineElectronicsStoreApplication extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // todo: change fxml file
        UILoader.render(primaryStage, new OnlineElectronicsStore(new DatabaseClient()),File.separator+ "view" + File.separator + "Checkout.fxml", "Title");

    }
}
