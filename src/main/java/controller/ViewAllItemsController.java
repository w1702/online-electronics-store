/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Item;
import model.OnlineElectronicsStore;
import utils.MVCController;
import utils.UILoader;

/**
 * FXML Controller class
 *
 * @author chakw
 */
public class ViewAllItemsController extends MVCController<OnlineElectronicsStore> {
    @FXML private Label lblName1;
    @FXML private ImageView img1;
    @FXML private Label lblPrice1;
    @FXML private Label lblName2;
    @FXML private ImageView img2;
    @FXML private Label lblPrice2;
    @FXML private Label lblName3;
    @FXML private ImageView img3;
    @FXML private Label lblPrice3;
    @FXML private Label lblName4;
    @FXML private ImageView img4;
    @FXML private Label lblPrice4;
    @FXML private Label lblName5;
    @FXML private ImageView img5;
    @FXML private Label lblPrice5;
    @FXML private Label lblName6;
    @FXML private ImageView img6;
    @FXML private Label lblPrice6;
    @FXML private TextField txtsearchKeyword;
    @FXML private Label hiddenfield;
    
    @FXML private Button btnPrevious;
    @FXML private Button btnNext;
    @FXML private Button btnProduct2;
    @FXML private Button btnProduct3;
    @FXML private Button btnProduct4;
    @FXML private Button btnProduct5;
    @FXML private Button btnProduct6;
    @FXML private SplitPane spProduct2;
    @FXML private SplitPane spProduct3;
    @FXML private SplitPane spProduct4;
    @FXML private SplitPane spProduct5;
    @FXML private SplitPane spProduct6;
    
   
    // public final methods --------------------------------------------------------------------------------------------------------------->
    
    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }
    
    public final Item getItem(){
        return getModel().getItems().get(0);
    }
    
    public final List<Item> getItems(){
        return getModel().getItems();
    }
    
    // @FXML methods --------------------------------------------------------------------------------------------------------------------->
    
    // initialize method for view all items
    @FXML private void initialize(){
        GetData(0); // get first 6 items
        btnPrevious.setDisable(true);
    }
    
     // Load next 6 items 
    @FXML private void loadNextPage(ActionEvent event) throws Exception {
        clearData();
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) + 6;
        hiddenfield.setText(Integer.toString(number)); 
        GetData(number);
        if (number > 0) {
            btnPrevious.setDisable(false);
        }
    }
    
    // Load previous 6 items
    @FXML private void loadPreviousPage(ActionEvent event) throws Exception {
        clearData();
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) - 6;
        hiddenfield.setText(Integer.toString(number));
        GetData(number);
        if (number == 0){
             btnPrevious.setDisable(true);
        }
    }
    
     // Go to view items details page
    @FXML private void handleViewDetails(ActionEvent event) throws Exception {
        //UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/SearchItems.fxml", "Search Items");
    }
    
    // Go to search page
    @FXML private void handleSearchItems(ActionEvent event) throws Exception {
        UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/SearchItems.fxml", "Search Items");
    }
    
    // private methods for @FXML --------------------------------------------------------------------------------------------------------->
    
    private void GetData(int i){
        String name = null;
        String image = null;
        Double price = null;
        // Bind items list
        List<Item> items = getItems();
        for( int j = i; j < i + 6; j++) {
            if (j < items.size()){
                name = items.get(j).getName();
                image = items.get(j).getImage();
                price = items.get(j).getCost();
                DataBind(name, image, price);
                btnNext.setDisable(false);
            } else {
                btnNext.setDisable(true);
                break;
            }
        }
        displayProductBox();
    }
    
    private void DataBind(String name, String image, Double price){
        if (lblName1.getText() == ""){
            lblName1.setText(name);
            lblPrice1.setText("$ " + price.toString());
//            img1.setImage(image);
        } else if (lblName2.getText() == ""){
            lblName2.setText(name);
            lblPrice2.setText("$ " + price.toString());
//            img2.setImage(image);
        } else if (lblName3.getText() == "") {
            lblName3.setText(name);
            lblPrice3.setText("$ " + price.toString());
//            img3.setImage(image);
        } else if (lblName4.getText() == "") {
            lblName4.setText(name);
            lblPrice4.setText("$ " + price.toString());
//            img4.setImage(image);
        } else if (lblName5.getText() == "") {
            lblName5.setText(name);
            lblPrice5.setText("$ " + price.toString());
//            img5.setImage(image);
        } else if (lblName6.getText() == "") {
            lblName6.setText(name);
            lblPrice6.setText("$ " + price.toString());
//            img6.setImage(image);
        }
    }
    
    private void clearData(){
        lblName1.setText("");
        lblName2.setText("");
        lblName3.setText("");
        lblName4.setText("");
        lblName5.setText("");
        lblName6.setText("");
    }
    
    private void displayProductBox(){
        if (lblName2.getText() == ""){
            btnProduct2.setVisible(false);
        } else {
            btnProduct2.setVisible(true);
        }
        if (lblName3.getText() == ""){
            btnProduct3.setVisible(false);
        } else{
            btnProduct3.setVisible(true);
        }
        if (lblName4.getText() == ""){
            btnProduct4.setVisible(false);
        } else{
            btnProduct4.setVisible(true);
        }
        if (lblName5.getText() == ""){
            btnProduct5.setVisible(false);
        } else{
            btnProduct5.setVisible(true);
        }
        if (lblName6.getText() == ""){
            btnProduct6.setVisible(false);
        } else{
            btnProduct6.setVisible(true);
        }
    }
   
   
}
