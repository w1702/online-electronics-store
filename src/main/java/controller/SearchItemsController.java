package controller;

import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import model.Item;
import utils.MVCController;
import utils.UILoader;


public class SearchItemsController extends MVCController<OnlineElectronicsStore> {
    
    private int totalItemsNumber;
    private double min;
    private double max;
    private String keywordDefault = "keyword";
    
    @FXML private TextField txtsearchKeyword;
    @FXML private ComboBox cbSortBy;
    
    @FXML private Label lblName1;
    @FXML private Label lblDescription1;
    @FXML private ImageView img1;
    @FXML private Label lblPrice1;
    @FXML private Label hfID1;
    @FXML private Label lblName2;
    @FXML private Label lblDescription2;
    @FXML private ImageView img2;
    @FXML private Label lblPrice2;
    @FXML private Label hfID2;
    @FXML private Label lblName3;
    @FXML private Label lblDescription3;
    @FXML private ImageView img3;
    @FXML private Label lblPrice3;
    @FXML private Label hfID3;
    @FXML private Label hiddenfield;
    @FXML private Label lblKeyword;
    @FXML private Label lblShowing;
    
    @FXML private Button btnPrevious;
    @FXML private Button btnNext;
    @FXML private Button btnFirst;
    @FXML private Button btnLast;
    @FXML private TextField txtPriceMin;
    @FXML private TextField txtPriceMax;
    @FXML private Button btnGo;
    @FXML private Button btnProduct1;
    @FXML private Button btnProduct2;
    @FXML private Button btnProduct3;
    @FXML private SplitPane spProduct1;
    @FXML private SplitPane spProduct2;
    @FXML private SplitPane spProduct3;

    // public final methods (Get methods from model) --------------------------------------------------------------------------------------------------------------->
    
    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }

    
    public final List<Item> getItems(){
        return getModel().getItems();
    }
    
    public final List<Item> getSortedItems(String sortBy, String keyword, double min, double max) {
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
        return getModel().getSortedItems(sortBy, order, keyword, min, max);
    }

    public final List<Item> getItemByKeywords(String keywords, double min, double max){
        return getModel().getItemByKeywords(keywords, min, max);
    }
    
    public final String getCurrentlySelectedItem(){
        return getModel().getCurrentlySelectedItem();
    }
    
    // public methods for @FXML --------------------------------------------------------------------------------------------------------->
    
    public void Search (int i, String keyword, double min, double max){
        clearData();
        String id = null;
        String name = null;
        Image image = null;
        Double price = null;
        String description = null;
        // Bind items list
        List<Item> items = getItemByKeywords(keyword, min, max);
        totalItemsNumber = items.size();
        for( int j = i; j < i + 3; j++) {
            if (j < items.size()){
                id = items.get(j).getId();
                name = items.get(j).getName();
                image = items.get(j).getConvertBase64toImage();
                price = items.get(j).getCost();
                description = items.get(j).getDescription();
                dataBind(id, name, image, price, description);
                if (j == totalItemsNumber -1 && totalItemsNumber % 3 == 0){
                    btnNext.setDisable(true);
                    btnLast.setDisable(true);
                } else {
                    btnNext.setDisable(false);
                    btnLast.setDisable(false);
                }
            } else {
                btnNext.setDisable(true);
                btnLast.setDisable(true);
                break;
            }
        }
        displayProductBox();
        showSearchData(keyword, totalItemsNumber);
    }
     
    public void getSortedData(String sortBy, int i, String keyword, double min, double max){
        clearData();
        String id = null;
        String name = null;
        Image image = null;
        Double price = null;
        String description = null;
        // Bind items list
        List<Item> items = getSortedItems(sortBy, keyword, min, max);
        totalItemsNumber = items.size();
        for( int j = i; j < i + 3; j++) {
            if (j < items.size()){
                id = items.get(j).getId();
                name = items.get(j).getName();
                image = items.get(j).getConvertBase64toImage();
                price = items.get(j).getCost();
                description = items.get(j).getDescription();
                dataBind(id, name, image, price, description);
                if (j == totalItemsNumber -1 && totalItemsNumber % 3 == 0){
                    btnNext.setDisable(true);
                    btnLast.setDisable(true);
                } else {
                    btnNext.setDisable(false);
                    btnLast.setDisable(false);
                }
            } else {
                btnNext.setDisable(true);
                btnLast.setDisable(true);
                break;
            }
        }
        displayProductBox();
        showSearchData(keyword, totalItemsNumber);
    }
     
    public void dataBind(String id, String name, Image image, Double price, String description){
        if (lblName1.getText() == ""){
            lblName1.setText(name);
            lblPrice1.setText("$ " + price.toString());
            lblDescription1.setText(description);
            hfID1.setText(id);
            convertBase64toImage(image, img1);
        } else if (lblName2.getText() == ""){
            lblName2.setText(name);
            lblPrice2.setText("$ " + price.toString());
            lblDescription2.setText(description);
            hfID2.setText(id);
            convertBase64toImage(image, img2);
        } else if (lblName3.getText() == "") {
            lblName3.setText(name);
            lblPrice3.setText("$ " + price.toString());
            lblDescription3.setText(description);
            hfID3.setText(id);
            convertBase64toImage(image, img3);
        }
    }
     
    public void convertBase64toImage(Image image, ImageView img){
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
        }
    }
     
    public void showSearchData(String keyword, int totalItems){
        int firstItemNum;
        int lastItemNum = Integer.parseInt(hiddenfield.getText());
        lastItemNum += 3;
        if (lastItemNum > totalItems){ // if larger than total item
            lastItemNum = totalItems; 
        }
        if (!(lastItemNum % 3 == 0) && lastItemNum % 2 == 0){ // cannot divided by 3 and is even
            firstItemNum = lastItemNum - 1;
        } else if (!(lastItemNum % 3 == 0) && !(lastItemNum % 2 == 0)){ // cannot divided by 3 and is even
            firstItemNum = lastItemNum;
        } else if (lastItemNum == 0){ // if no result
            firstItemNum = 0;
        } else { // can divided by 3
            firstItemNum = lastItemNum - 2;
        }
        lblKeyword.setText(keyword);
        lblShowing.setText("Showing " + firstItemNum + "- " + lastItemNum + " of " + totalItems);
    }
     
    public void clearData(){
        lblName1.setText("");
        lblName2.setText("");
        lblName3.setText("");
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
    }
    
    // To check the search required the sort by function or not
    public void searchSortBy(int number){
        if (!cbSortBy.getSelectionModel().isEmpty()){
            String sortBy = cbSortBy.getSelectionModel().getSelectedItem().toString();
            if (sortBy != "Sort By"){
                getSortedData(sortBy, number, lblKeyword.getText(), min, max); // get sorted order
            } else {
                Search(number, lblKeyword.getText(), min, max); // get original order
            }
        } else {
            Search(number, lblKeyword.getText(), min, max); // get original order
        }
        if (number == 0){ // set btnPrevious and btnFirst disable
            btnPrevious.setDisable(true);
            btnFirst.setDisable(true);
        } else{
            btnPrevious.setDisable(false);
            btnFirst.setDisable(false);
        }
    }
    
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?") || str.isEmpty();  //match a number with optional '-' and decimal and null
    }
    
    public void setToolTip(){
        // Set tooltip
        Tooltip.install(btnNext, new Tooltip("Next"));
        Tooltip.install(btnPrevious, new Tooltip("Previous"));
        Tooltip.install(btnLast, new Tooltip("Last"));
        Tooltip.install(btnFirst, new Tooltip("First"));
        Tooltip.install(txtPriceMin, new Tooltip("Minimum Price"));
        Tooltip.install(txtPriceMax, new Tooltip("Maximum Price"));
    }
    
    // @FXML methods --------------------------------------------------------------------------------------------------------------------->
    
    // initialize method
    @FXML private void initialize(){
        min = 0;
        max = 999999999;
        Search(0, getCurrentlySelectedItem(), min, max);
        btnPrevious.setDisable(true);
        btnFirst.setDisable(true);
        cbSortBy.getItems().addAll("Sort By", "Name: A to Z", "Name: Z to A", "Price: Lowest", "Price: Highest", "Time: Newest", "Time: Oldest"); // bind combobox
        setToolTip();
    }
    
    // Go to search page
    @FXML private void handleSearchItems(Event event) throws Exception {
        getModel().setCurrentlySelectedItem(txtsearchKeyword.getText());
        UILoader.render(new Stage(), getModel(), "/view/SearchItems.fxml", "Search Items");
    }
    
    // Sort by search
    @FXML private void cbSortByOnSelectedIndexChanged(ActionEvent event) throws Exception {
        hiddenfield.setText("0"); // reset hfNumber
        searchSortBy(0);
    }
    
    // Price range search 
    @FXML private void btnGo_Click(ActionEvent event) throws Exception {
        if (isNumeric(txtPriceMin.getText()) && isNumeric(txtPriceMax.getText())) {
            if (txtPriceMin.getText().isEmpty()){ // null, set min = 0
                min = 0;
            } else {
                min = Double.parseDouble(txtPriceMin.getText());
            }
            if (txtPriceMax.getText().isEmpty()){ // null, set max = 999999999
                max = 999999999;
            } else {
                max = Double.parseDouble(txtPriceMax.getText());
            }
            cbSortByOnSelectedIndexChanged(event);
        } else {
            // Validation: only numeric accepted!
            Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Validation");
            alert.setHeaderText("Price Range Validation");
            alert.setContentText("Please enter number only!");
            alert.showAndWait();
        }
    }
    
    // Load next 3 items
    @FXML private void loadNextPage(ActionEvent event) throws Exception {
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) + 3;
        hiddenfield.setText(Integer.toString(number));
        searchSortBy(number);
    }

    // Load previous 3 items
    @FXML private void loadPreviousPage(ActionEvent event) throws Exception {
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) - 3;
        hiddenfield.setText(Integer.toString(number));
        searchSortBy(number);
    }
    
    // Load first page item (first 3 items)
    @FXML private void loadFirstPage(ActionEvent event) throws Exception {
        hiddenfield.setText("0"); // reset hfNumber
        searchSortBy(0);
    }

    // Load last page items (last 3 items)
    @FXML private void loadLastPage(ActionEvent event) throws Exception {
        int i = totalItemsNumber;
        for (i = totalItemsNumber; i < totalItemsNumber + 3; i ++){ // find the nearst and greater number can divided by 3
            if (i % 3 == 0) {
                hiddenfield.setText(Integer.toString(i)); // reset hfNumber
                break;
            }
        }
        String hfNumber = hiddenfield.getText();
        int number = Integer.parseInt(hfNumber) - 3;
        hiddenfield.setText(Integer.toString(number));
        searchSortBy(number);
    }

    @FXML public void handleCheckout() throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/Checkout.fxml", "Shopping Cart");
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
            default:
                break;
        }
        getModel().setCurrentlySelectedItem(currentlySelectedItem);
        UILoader.render(new Stage(), getOnlineElectronicsStore(), "/view/ViewDetails.fxml", "View Item Details");
    }

}
