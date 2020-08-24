package utils;

import javafx.stage.Stage;

public abstract class MVCController<Model>{
    private Model model;
    private Stage stage;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
