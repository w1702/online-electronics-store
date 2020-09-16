/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    private Object completeImageData;
    
   
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
        String id = null;
        String name = null;
        String image = null;
        Double price = null;
        // Bind items list
        List<Item> items = getItems();
        for( int j = i; j < i + 6; j++) {
            if (j < items.size()){
                id = items.get(j).getID();
                name = items.get(j).getName();
                image = items.get(j).getImage();
                price = items.get(j).getCost();
                DataBind(id, name, image, price);
                btnNext.setDisable(false);
            } else {
                btnNext.setDisable(true);
                break;
            }
        }
        displayProductBox();
    }
    
    private void DataBind(String id, String name, String image, Double price){
        if (lblName1.getText() == ""){
            lblName1.setText(name);
            lblPrice1.setText("$ " + price.toString());
            hfID1.setText(id);
            convertBase64toImage(image, img1);
        } else if (lblName2.getText() == ""){
            lblName2.setText(name);
            lblPrice2.setText("$ " + price.toString());
            hfID2.setText(id);
            convertBase64toImage(image, img2);
        } else if (lblName3.getText() == "") {
            lblName3.setText(name);
            lblPrice3.setText("$ " + price.toString());
            hfID3.setText(id);
            convertBase64toImage(image, img3);
        } else if (lblName4.getText() == "") {
            lblName4.setText(name);
            lblPrice4.setText("$ " + price.toString());
            hfID4.setText(id);
            convertBase64toImage(image, img4);
        } else if (lblName5.getText() == "") {
            lblName5.setText(name);
            lblPrice5.setText("$ " + price.toString());
            hfID5.setText(id);
            convertBase64toImage(image, img5);
        } else if (lblName6.getText() == "") {
            lblName6.setText(name);
            lblPrice6.setText("$ " + price.toString());
            hfID6.setText(id);
            convertBase64toImage(image, img6);
        }
    }
    
    private void convertBase64toImage(String imageDataBytes, ImageView img){
        if (!imageDataBytes.isEmpty()){
            // Convert Base64 to Image
            String base64Image = imageDataBytes.split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            InputStream stream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(stream);
            
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
