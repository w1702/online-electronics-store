package controller;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import model.Item;
import utils.MVCController;
import utils.UILoader;


public class SearchItemsController extends MVCController<OnlineElectronicsStore> {
    
    @FXML private TextField txtsearchKeyword;
    @FXML private ComboBox cbSortBy;

    // public final methods (Get methods from model) --------------------------------------------------------------------------------------------------------------->
    
    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }

    
    public final List<Item> getItems(){
        return getModel().getItems();
    }
    
    public final List<Item> getSortedItems(String sortBy) {
        String order = new String();
        String[] sort = sortBy.split(": ");
        switch (sort[0]){ // set sortBy
            case "Name":
                sortBy = "name";
                break;
            case "Price":
                sortBy = "cost";
                break;
            case "Time":
                sortBy = "id";
                break;
            default:
                return getItems();
        }
        if (sort[1].equals("A to Z") || sort[1].equals("Lowest") || sort[1].equals("Oldest")){ // set order
            order = "ASC";
        } else {
            order = "DESC";
        }
        return getModel().getSortedItems(sortBy, order);
    }

    
    public final String getCurrentlySelectedItem(){
        return getModel().getCurrentlySelectedItem();
    }
    
    // public methods for @FXML --------------------------------------------------------------------------------------------------------->
    
    
     public void Search (){
    }
    
    
    // @FXML methods --------------------------------------------------------------------------------------------------------------------->
    
    // initialize method
    @FXML private void initialize(){
        String keyword = getCurrentlySelectedItem();
        txtsearchKeyword.setText(keyword);
        cbSortBy.getItems().addAll("Sort By", "Name: A to Z", "Name: Z to A", "Price: Lowest", "Price: Highest", "Time: Newest", "Time: Oldest"); // bind combobox
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
