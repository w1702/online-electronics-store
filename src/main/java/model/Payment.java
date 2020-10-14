package model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Payment {
    protected String name ;
    protected String cardNo;
    protected String date;
    protected int cvv;

    public TextField nameo;
    public TextField cardnum;
    public TextField  expi;
    public TextField cvvn;

    public void confirmPayment() {
        if (nameo.getText().isEmpty() || cardnum.getText().isEmpty() || expi.getText().isEmpty() || cvvn.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("PLEASE FILL ALL FIELDS");
            alert.showAndWait();
        }

      

    }
}