package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import model.Item;
import utils.MVCController;
import utils.UILoader;


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
    
    // Go to search page
    @FXML private void handleSearchItems(ActionEvent event) throws Exception {
        UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/SearchItems.fxml", "Search Items");
    }
    
    @FXML private void cbSortByOnSelectedIndexChanged(ActionEvent event) throws Exception {
//        hiddenfield.setText("0"); // reset hfNumber
//        String sortBy = cbSortBy.getSelectionModel().getSelectedItem().toString();
//        if (sortBy != "Sort By"){
//            getSortedData(sortBy, 0); // get sorted order
//        } else {
//            getData(0); // get original order
//        }
    }
}
