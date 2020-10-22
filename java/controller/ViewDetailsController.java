package controller;

import db.DatabaseWriteClient;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Item;
import model.OnlineElectronicsStore;
import utils.MVCController;
import javafx.scene.control.TextField;


public class ViewDetailsController extends MVCController<OnlineElectronicsStore> {
    
    @FXML
    private ImageView itemImageView;

    @FXML
    private Text idText;

    @FXML
    private Text nameText;

    @FXML
    private Text costText;

    @FXML
    private Text descriptionText;

    @FXML
    private Text addToCartStatusText;

    @FXML
    private TextField reviewCommentTextField;

    @FXML
    private Text addReviewCommentStatusText;

    @FXML
    private Text quantityInCartText;


    @FXML private void initialize(){
        Item item = getCurrentItem();
        itemImageView.setImage(item.getConvertBase64toImage());
        idText.setText("ID: " + item.getId());
        nameText.setText("name: " + item.getName());
        costText.setText("cost: " + item.getCost());
        descriptionText.setText("description: " + item.getDescription());
        setQuantityInCartText();
    }
    
    @FXML
    public void handleAddToCart() {
        getModel()
                .getLoggedInUser()
                .getShoppingCart()
                .addItem(getCurrentItem().getId(), 1);
        addToCartStatusText.setText("Added item to cart");
        setQuantityInCartText();
    }

    @FXML
    public void handleAddReview(){
        if(reviewCommentTextField.getText().isEmpty()){
            addReviewCommentStatusText.setText("Please enter a review comment");
        }
        else{
            addReviewCommentStatusText.setText("Review comment added");
            getModel()
                    .getItemById(getModel().getCurrentlySelectedItem())
                    .addReview(getModel().getLoggedInUser().getId(), reviewCommentTextField.getText());
        }
    }

    public Item getCurrentItem(){
        return getModel().getItemById(getModel().getCurrentlySelectedItem());
    }

    private void setQuantityInCartText() {
        Integer quantity = getModel().getLoggedInUser().getShoppingCart().getItemQuantity().get(getCurrentItem().getId());
        if(quantity == null){
            quantity = 0;
        }
        quantityInCartText.setText("quantity in shopping cart: " + quantity);
    }
}
