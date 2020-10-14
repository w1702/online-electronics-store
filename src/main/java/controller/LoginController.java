package controller;
import db.DatabaseClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import utils.MVCController;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginController extends MVCController<OnlineElectronicsStore> {

    @FXML
    private TextField email;

    @FXML
    private PasswordField passWord;

    @FXML
    private Button login;

    @FXML
    private Button reset;

    @FXML
    private Label message;


    public void submitButtonClick(){

        message.setTextFill(Color.BLUE);
        message.setText("Validating credentials...");
        DatabaseClient db = new DatabaseClient();
        String jsonString = db.getAppData().toString();

        boolean dataCollected = false;

        String[] values = jsonString.split(",");
        String userEmail = "", pass = "";
        for (int i = 0; i < values.length; i++) {
            message.setText("Validating credentials...");
            if (!userEmail.equals("") && !pass.equals("")) {
                dataCollected = true;
                break;
            }
            if (values[i].contains("email")) {
                String[] em = values[i].split(":");
                userEmail = em[1].replaceAll("\"", "");
            }
            if (values[i].contains("password")) {
                String[] em = values[i].split(":");
                pass = em[1].replaceAll("\"", "");
            }

        }

        if (userEmail.equals(this.email.getText()) && pass.equals(this.passWord.getText())) {
            message.setText("Logging in");
            Stage stage = (Stage) this.login.getScene().getWindow();

            stage.close();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewAllItems.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setTitle("Sucessfully Logged In");
                stage1.setScene(new Scene(root1));
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            if (dataCollected) {
                message.setTextFill(Color.RED);
                message.setText("Invalid Id/Password");
            }
        }


    }

    public void resetFields(){
        email.setText("");
        passWord.setText("");
        message.setText("");
    }

}