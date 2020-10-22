package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import model.User;
import utils.MVCController;
import utils.UILoader;

import java.io.IOException;
import java.util.List;

public class SignupController extends MVCController<OnlineElectronicsStore> {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    public void handleSignup(){
        getModel().addUser(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), passwordField.getText());

    }


}
