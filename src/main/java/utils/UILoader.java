package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class UILoader {
    public static <ModelType> void render(Stage stage, ModelType modelType, String path, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MVCController.class.getResource(path));
        Callback<Class<?>, Object> controllerFactory = controllerType -> {
            try{
                MVCController<ModelType> mvcController = (MVCController<ModelType>)controllerType.newInstance();
                mvcController.setModel(modelType);
                mvcController.setStage(stage);
                return mvcController;
            }
            catch(Exception e){
                throw new RuntimeException(e + e.getMessage());
            }
        };
        fxmlLoader.setControllerFactory(controllerFactory);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.show();
    }
}
