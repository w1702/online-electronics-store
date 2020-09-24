package controller;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.*;
import utils.MVCController;
import utils.UILoader;
import model.*;
import model.Item;
import model.OnlineElectronicsStore;
import javafx.scene.*;

public class PaymentController extends MVCController<OnlineElectronicsStore>{
    @FXML
    private TextField cvc;

    @FXML
    private TextField cardName;

    @FXML
    private Button ApplePay;

    @FXML
    private Button Pay;

    @FXML
    private TextField cardName1;

    @FXML
    private TextField cardName2;

    @FXML
    private TextField exp;

    @FXML
    private TextField cardNumber;
    

    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
       
    }
    
    @FXML
    public void handleButtonEvent(ActionEvent event) throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/P.fxml", "Online electronics store");
    }
}

    	
    	
    	
    	




