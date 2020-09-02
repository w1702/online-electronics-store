package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.OnlineElectronicsStore;
import model.Item;
import utils.MVCController;


public class SearchItemsController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private TableView itemTableView;

    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }

    public final Item getItem(){
        // todo: this gets the first user as of right now, should be getting the currently logged in user
        return getModel().getItems().get(0);
    }
    
    public void Search (){
    }
}
