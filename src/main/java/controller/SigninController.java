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

public class SigninController extends MVCController<OnlineElectronicsStore> {

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;



}
