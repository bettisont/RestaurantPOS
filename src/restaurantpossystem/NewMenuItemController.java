/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class NewMenuItemController implements Initializable {

    ObservableList<String> categoryChoiceBoxList = FXCollections.observableArrayList("Category", "Side", "Starter", "Main", "Dessert");

    // FXML fields 
    @FXML
    private ChoiceBox categoryChoiceBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categoryChoiceBox.setValue("Category");
        categoryChoiceBox.setItems(categoryChoiceBoxList);
    }

    

}
