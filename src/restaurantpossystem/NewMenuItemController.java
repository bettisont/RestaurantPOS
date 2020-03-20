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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class NewMenuItemController implements Initializable {

    ObservableList<String> categoryChoiceBoxList = FXCollections.observableArrayList("Side", "Starter", "Main", "Dessert");

    // FXML fields 
    // Indicator Labels
    @FXML 
    private Label nameRequiredIndicator;
    @FXML 
    private Label descRequiredIndicator;
    @FXML 
    private Label priceRequiredIndicator;
    @FXML 
    private Label preperationRequiredIndicator;
    @FXML 
    private Label categoryRequiredIndicator;
    ///////////////////
    // Fields
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
        
        //field validator
       
    }

    public void handleConfirmButtonAction(){

        // Verify Data meets the constraints 
        // TextFields
       Boolean isNameEmpty = isTextFieldEmpty(name, nameRequiredIndicator, "Field Required!");
       Boolean isDescriptionEmpty = isTextFieldEmpty(description, descRequiredIndicator, "Field Required!");
       Boolean isPriceEmpty = isTextFieldEmpty(price, priceRequiredIndicator, "Field Required!");
       Boolean isPreperationTimeEmpty = isTextFieldEmpty(preperationTime, preperationRequiredIndicator, "Field Required!");
       Boolean isCategoryEmpty = isComboBoxFieldEmpty(categoryChoiceBox, categoryRequiredIndicator, "Field Required!");
       

        
        if(!(isNameEmpty | isDescriptionEmpty | isPriceEmpty | isPreperationTimeEmpty | isCategoryEmpty)){
            System.out.println("all fields filled...");
            System.out.println("...Item to be added...:");
            System.out.println("Category: " + "'"+categoryChoiceBox.getValue()+"'");
            System.out.println("Name: " + "'"+name.getText()+"'");
            System.out.println("Description: " + "'"+ description.getText() + "'");
            System.out.println("Price: " + "'"+price.getText()+"'");
            System.out.println("Allergen: " + "'"+allergen.isSelected()+"'");
            System.out.println("Preperation Time: " + "'"+preperationTime.getText()+"'");
        }
        
        // if none of the fields are empty, then do stuff , else do nothing 
        
       
    }
    
    private Boolean isInteger(String input){
        return true;
    }
    
    private Boolean isTextFieldEmpty(TextField field, Label label, String labelMessage){
        // check if field is empty
        // if it is empty then display the error message and return true
        if("".equals(field.getText())){
            label.setText(labelMessage);
            return true;
        }
        else{
            label.setText("");
            return false;
        }
    }

    private Boolean isComboBoxFieldEmpty(ComboBox field, Label label, String labelMessage){
        // check if field is empty
        // if it is empty then display the error message and return true
        if(field.getValue() == null){
            label.setText(labelMessage);
            return true;
        }
        else{
            label.setText("");
            return false;
        }
    }
}
