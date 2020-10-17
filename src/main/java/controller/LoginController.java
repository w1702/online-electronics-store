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

public class LoginController extends MVCController<OnlineElectronicsStore> {

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label message;


    public void handleLogin() throws IOException {
        boolean userLoggedIn = false;
        message.setTextFill(Color.BLUE);
        message.setText("Validating credentials...");

        List<User> users = getModel().getUsers();
        for (User user : users) {
            if(user.getEmail().equals(emailTextField.getText()) && user.getPassword().equals(passwordField.getText())){
                userLoggedIn = true;
                getModel().setLoggedInUser(user);
                UILoader.render(new Stage(), getModel(), "/view/ViewAllItems.fxml", "View All Items");
            }
        }

        if(!userLoggedIn) {
            message.setTextFill(Color.RED);
            message.setText("Invalid Email / Password");
        }
    }

    public void handleSignup() throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/SignUp.fxml", "Sign Up");
    }

    public void handleReset(){
        emailTextField.setText("");
        passwordField.setText("");
        message.setText("");
    }
}