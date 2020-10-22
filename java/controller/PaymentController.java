package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.omg.CORBA.INITIALIZE;




import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.*;
import utils.MVCController;
import utils.UILoader;
import model.*;
import model.Item;
import model.Payment;

import model.OnlineElectronicsStore;
import javafx.scene.*;


public class PaymentController extends MVCController<OnlineElectronicsStore> implements Initializable {
    @FXML
    private Button CardPay;

    @FXML
    private RadioButton radioButtonCard;

    @FXML
    private TextField cardName;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField exp;

    @FXML
    private TextField cvv;

    @FXML
    private Button ApplePay;

    @FXML
    private RadioButton radioButtonApple;

    @FXML
    private TextField appleID;

    @FXML
    private TextField applePassword;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        radioButtonCard.setToggleGroup(group);
        radioButtonApple.setToggleGroup(group);
        radioButtonCard.setSelected(true);
    }


    @FXML
    public void handleCardPay(ActionEvent event) throws IOException {
        if (!radioButtonCard.isSelected()) {
            radioButtonCard.setSelected(true);
        }

        String cardNameValue = cardName.getText();
        String cardNumberValue = cardNumber.getText();
        String expValue = exp.getText();
        String cvvValue = cvv.getText();
        if (cardNameValue.isEmpty()) {
            cardName.requestFocus();
        } else if (cardNumberValue.isEmpty() || !cardNumberValue.matches("\\d{16}")) {
            cardNumber.requestFocus();
        } else if (expValue.isEmpty()) {
            exp.requestFocus();
        } else if (cvvValue.isEmpty() || !cvvValue.matches("\\d+")) {
            cvv.requestFocus();
        } else {

            Order order = new Order();
            order.setPayment(new Payment(cardNameValue, cardNumberValue,
                    expValue, cvvValue, "", ""));
            order.setItems(getModel().getLoggedInUser().getShoppingCart().getItemsAsList());
            getModel().getLoggedInUser().addOrder(order);

            UILoader.render(new Stage(), getModel(), "/view/CardPay.fxml", "Payment");

            // clear shopping card
            getModel().getLoggedInUser().getShoppingCart().clear();

            // close
            Stage stage = (Stage) cvv.getScene().getWindow();
            stage.close();
        }

    }


    public final OnlineElectronicsStore getOnlineElectronicsStore() {
        return getModel();

    }


    @FXML
    public void handleApplePay(ActionEvent event) throws IOException {
        if (!radioButtonApple.isSelected()) {
            radioButtonApple.setSelected(true);
        }

        String appleIDValue = appleID.getText();
        String applePasswordValue = applePassword.getText();
        if (appleIDValue.isEmpty()) {
            appleID.requestFocus();
        } else if (applePasswordValue.isEmpty()) {
            applePassword.requestFocus();
        } else {

            Order order = new Order();
            order.setPayment(new Payment("", "",
                    "", "", appleIDValue, applePasswordValue));
            order.setItems(getModel().getLoggedInUser().getShoppingCart().getItemsAsList());
            getModel().getLoggedInUser().addOrder(order);

            UILoader.render(new Stage(), getModel(), "/view/ApplePay.fxml", "Payment");

            // clear shopping card
            getModel().getLoggedInUser().getShoppingCart().clear();

            // close
            Stage stage = (Stage) cvv.getScene().getWindow();
            stage.close();
        }
    }


}

    	
    	
    	
    	




