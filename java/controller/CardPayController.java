package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.*;
import utils.MVCController;
import utils.UILoader;
import model.*;
import model.Payment;
import javafx.scene.*;

public class CardPayController extends MVCController<OnlineElectronicsStore> {

    @FXML
    private Button QA;

    @FXML
    private Button view;

    @FXML
    private Button back;

    @FXML
    private TableView<Item> items = new TableView<>();

    @FXML private TableColumn<Item, String> idClm;
    @FXML private TableColumn<Item, String> nameClm;
    @FXML private TableColumn<Item, String> costClm;
    @FXML private TableColumn<Item, String> desClm;

    @FXML
    private Text cardName;

    @FXML
    private Text cardNumber;

    // @FXML
    // private Text exp;
    //
    // @FXML
    // private Text cvv;


    @FXML private void initialize(){

        idClm.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        costClm.setCellValueFactory(cellData -> cellData.getValue().costProperty().asString("$%.2f"));
        desClm.setCellValueFactory(cellData -> cellData.getValue().desProperty());

        // setTotalCostText();
    }

    @FXML
    public void handleQuestions(ActionEvent event) throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/Q&A.fxml", "Frequently Asked Questions");
    }

    public final Order getLastOrder() {
        return getModel().getLoggedInUser().getLastOrder();
    }

    public final Payment getPayment() {
        Order lastOrder = getModel().getLoggedInUser().getLastOrder();
        if (lastOrder != null) {
            return lastOrder.getPayment();
        } else {
            return new Payment("", "", "", "", "", "");
        }
    }
}

    
   	
    	
    	
    	




