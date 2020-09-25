/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @FXML private Label hfID1;
    @FXML private Label lblName2;
    @FXML private ImageView img2;
    @FXML private Label lblPrice2;
    @FXML private Label hfID2;
    @FXML private Label lblName3;
    @FXML private ImageView img3;
    @FXML private Label lblPrice3;
    @FXML private Label hfID3;
    @FXML private Label lblName4;
    @FXML private ImageView img4;
    @FXML private Label lblPrice4;
    @FXML private Label hfID4;
    @FXML private Label lblName5;
    @FXML private ImageView img5;
    @FXML private Label lblPrice5;
    @FXML private Label hfID5;
    @FXML private Label lblName6;
    @FXML private ImageView img6;
    @FXML private Label lblPrice6;
    @FXML private Label hfID6;
    @FXML private TextField txtsearchKeyword;
    @FXML private Label hiddenfield;
    @FXML private ComboBox cbSortBy;
    
    @FXML private Button btnPrevious;
    @FXML private Button btnNext;
    @FXML private Button btnProduct1;
    @FXML private Button btnProduct2;
    @FXML private Button btnProduct3;
    @FXML private Button btnProduct4;
    @FXML private Button btnProduct5;
    @FXML private Button btnProduct6;
    @FXML private SplitPane spProduct1;
    @FXML private SplitPane spProduct2;
    @FXML private SplitPane spProduct3;
    @FXML private SplitPane spProduct4;
    @FXML private SplitPane spProduct5;
    @FXML private SplitPane spProduct6;
    
   
    // public final methods (Get methods from model) --------------------------------------------------------------------------------------------------------------->
    
    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }
    
    public final Item getItem(){
        return getModel().getItems().get(0);
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
    
    
    // @FXML methods --------------------------------------------------------------------------------------------------------------------->
    
    // initialize method for view all items
    @FXML private void initialize(){
        getData(0); // get first 6 items
        btnPrevious.setDisable(true);
        cbSortBy.getItems().addAll("Sort By", "Name: A to Z", "Name: Z to A", "Price: Lowest", "Price: Highest", "Time: Newest", "Time: Oldest"); // bind combobox
    }
    
    @FXML private void cbSortByOnSelectedIndexChanged(ActionEvent event) throws Exception {
        hiddenfield.setText("0"); // reset hfNumber
        String sortBy = cbSortBy.getSelectionModel().getSelectedItem().toString();
        if (sortBy != "Sort By"){
            getSortedData(sortBy, 0); // get sorted order
        } else {
            getData(0); // get original order
        }
    }
    
    // Go to view items details page
    @FXML private void handleViewDetails() throws Exception {

   // FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDetailsController.java"));
  //  Parent root = (Parent)loader.load();
  //  ViewDetailsController controller = loader.getController();
   // controller.initData(hiddenfield.getText());


      UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/ViewDetails.fxml", "View Details");

    }

    // public methods for @FXML --------------------------------------------------------------------------------------------------------->
    
    public void getData(int i){
        clearData();
        String id = null;
        String name = null;
        Image image = null;
        Double price = null;
        String description = null;
        // Bind items list
        List<Item> items = getItems();
        for( int j = i; j < i + 6; j++) {
            if (j < items.size()){
                id = items.get(j).getID();
                name = items.get(j).getName();
                image = items.get(j).getConvertBase64toImage();
                price = items.get(j).getCost();
                description = items.get(j).getDescription();
                dataBind(id, name, image, price, description);
                btnNext.setDisable(false);
            } else {
                btnNext.setDisable(true);
                break;
            }
        }
        displayProductBox();
    }
    
    public void getSortedData(String sortBy, int i){
        clearData();
        String id = null;
        String name = null;
        Image image = null;
        Double price = null;
        String description = null;
        // Bind items list
        List<Item> items = getSortedItems(sortBy);
        for( int j = i; j < i + 6; j++) {
            if (j < items.size()){
                id = items.get(j).getID();
                name = items.get(j).getName();
                image = items.get(j).getConvertBase64toImage();
                price = items.get(j).getCost();
                description = items.get(j).getDescription();
                dataBind(id, name, image, price, description);
                btnNext.setDisable(false);
            } else {
                btnNext.setDisable(true);
                break;
            }
        }
        displayProductBox();
    }
    
    public void dataBind(String id, String name, Image image, Double price, String description){
        if (lblName1.getText() == ""){
            lblName1.setText(name);
            lblPrice1.setText("$ " + price.toString());
            hfID1.setText(id);
            convertBase64toImage(image, img1, description);
        } else if (lblName2.getText() == ""){
            lblName2.setText(name);
            lblPrice2.setText("$ " + price.toString());
            hfID2.setText(id);
            convertBase64toImage(image, img2, description);
        } else if (lblName3.getText() == "") {
            lblName3.setText(name);
            lblPrice3.setText("$ " + price.toString());
            hfID3.setText(id);
            convertBase64toImage(image, img3, description);
        } else if (lblName4.getText() == "") {
            lblName4.setText(name);
            lblPrice4.setText("$ " + price.toString());
            hfID4.setText(id);
            convertBase64toImage(image, img4, description);
        } else if (lblName5.getText() == "") {
            lblName5.setText(name);
            lblPrice5.setText("$ " + price.toString());
            hfID5.setText(id);
            convertBase64toImage(image, img5, description);
        } else if (lblName6.getText() == "") {
            lblName6.setText(name);
            lblPrice6.setText("$ " + price.toString());
            hfID6.setText(id);
            convertBase64toImage(image, img6, description);
        }
    }
    
    public void convertBase64toImage(Image image, ImageView img, String description){
        if (!image.toString().isEmpty()){
            // Centeralize the image in imageview
            double w = 0;
            double h = 0;
            double ratioX = img.getFitWidth() / image.getWidth();
            double ratioY = img.getFitHeight() / image.getHeight();
            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }
            w = image.getWidth() * reducCoeff;
            h = image.getHeight() * reducCoeff;
            img.setX((img.getFitWidth() - w) / 2);
            img.setY((img.getFitHeight() - h) / 2);
            
            // Set image in imageview
            img.setImage(image);
            // Set description as the imageview's tooltip
            Tooltip.install(img, new Tooltip(description));
        }
    }
    
    public void clearData(){
        lblName1.setText("");
        lblName2.setText("");
        lblName3.setText("");
        lblName4.setText("");
        lblName5.setText("");
        lblName6.setText("");
    }
    
    public void displayProductBox(){
        if (lblName1.getText() == ""){
            btnProduct1.setVisible(false);
        } else {
            btnProduct1.setVisible(true);
        }
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

    // @FXML methods --------------------------------------------------------------------------------------------------------------------->

     // Load next 6 items
    @FXML private void loadNextPage(ActionEvent event) throws Exception {
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) + 6;
        hiddenfield.setText(Integer.toString(number));
        if (!cbSortBy.getSelectionModel().isEmpty()){
            String sortBy = cbSortBy.getSelectionModel().getSelectedItem().toString();
            if (sortBy != "Sort By"){
                getSortedData(sortBy, number); // get sorted order
            } else {
                getData(number); // get original order
            }
        } else {
            getData(number); // get original order
        }
        if (number > 0) {
            btnPrevious.setDisable(false);
        }
    }

    // Load previous 6 items
    @FXML private void loadPreviousPage(ActionEvent event) throws Exception {
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) - 6;
        hiddenfield.setText(Integer.toString(number));
        if (!cbSortBy.getSelectionModel().isEmpty()){
            String sortBy = cbSortBy.getSelectionModel().getSelectedItem().toString();
            if (sortBy != "Sort By"){
                getSortedData(sortBy, number); // get sorted order
            } else {
                getData(number); // get original order
            }
        } else {
            getData(number); // get original order
        }
        if (number == 0){
             btnPrevious.setDisable(true);
        }
    }

    // Go to search page
    @FXML private void handleSearchItems(ActionEvent event) throws Exception {
        UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/SearchItems.fxml", "Search Items");
    }

    @FXML
    public void handleCheckout() throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/Checkout.fxml", "Checkout");
    }

    // Go to view items details page
    @FXML private void handleViewDetails(Event event) throws Exception {
        String currentlySelectedItem = null;
        String sourceString = event.getSource().toString();
        sourceString = sourceString.replaceAll(", ", "=");
        String[] source = sourceString.split("=");
        String controlId = source[1].substring(source[1].length() - 1);
        switch (controlId){
            case "1":
                currentlySelectedItem = hfID1.getText();
                break;
            case "2":
                currentlySelectedItem = hfID2.getText();
                break;
            case "3":
                currentlySelectedItem = hfID3.getText();
                break;
            case "4":
                currentlySelectedItem = hfID4.getText();
                break;
            case "5":
                currentlySelectedItem = hfID5.getText();
                break;
            case "6":
                currentlySelectedItem = hfID6.getText();
                break;
            default:
                break;
        }

        UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/ViewDetails.fxml", "Title");
    }

}
