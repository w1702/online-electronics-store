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

public class PController extends MVCController<OnlineElectronicsStore>{

	    @FXML
	    private Button QA;

	    @FXML
	    private Button view;

	    @FXML
	    private Button back;



    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
       
    }
    
    

    @FXML
     public void handleQuestions(ActionEvent event) throws IOException {
    	UILoader.render(new Stage(), getModel(), "/view/Q&A.fxml", "Frequently Asked Questions");
    }
    }

    
   	
    	
    	
    	




