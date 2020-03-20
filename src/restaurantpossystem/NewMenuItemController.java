/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import static java.lang.System.console;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class NewMenuItemController implements Initializable {

    ObservableList<String> categoryChoiceBoxList = FXCollections.observableArrayList("Side", "Starter", "Main", "Dessert");

    // FXML fields 
    @FXML
    private ComboBox categoryChoiceBox;
    
    @FXML 
    private TextField name;
    
    @FXML
    private TextField description;
    
    @FXML 
    private TextField price;
    
    @FXML 
    private CheckBox allergen;
    
    @FXML 
    private TextField preperationTime; 
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categoryChoiceBox.setItems(categoryChoiceBoxList);
    }

    public void handleConfirmButtonAction(){

        System.out.println("Item to be added...");
        System.out.println("Category: " + categoryChoiceBox.getValue());
        System.out.println("Name: " + name.getText());
        System.out.println("Description: " + description.getText());
        System.out.println("Price: " + price.getText());
        System.out.println("Allergen: " + allergen.isSelected());
        System.out.println("Preperation Time: " + preperationTime.getText());
        
    }

}
