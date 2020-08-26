/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Item;
import model.OnlineElectronicsStore;
import utils.MVCController;

/**
 *
 * @author chakw
 */
public class ViewAllItemsController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private TableView itemTableView;

    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }

    public final Item getItem(){
        // todo: this gets the first user as of right now, should be getting the currently logged in user
        return getModel().getItems().get(0);
    }
}

