/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import utilityClasses.DatabaseManager;
import utilityClasses.MenuItem;
import java.io.IOException;
import static java.lang.System.console;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class NewMenuItemController implements Initializable {

    ObservableList<String> categoryChoiceBoxList = FXCollections.observableArrayList("Side", "Starter", "Main", "Dessert");

    DatabaseManager dbManager;
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
        
        dbManager = new DatabaseManager();
       
    }

    public void handleConfirmButtonAction() throws IOException{

        // Verify Data meets the constraints 
        // TextFields
       Boolean isNameEmpty = isTextFieldEmpty(name, nameRequiredIndicator, "Field Required!");
       Boolean isDescriptionEmpty = isTextFieldEmpty(description, descRequiredIndicator, "Field Required!");
       Boolean isPriceEmpty = isTextFieldEmpty(price, priceRequiredIndicator, "Field Required!");
       Boolean isPreperationTimeEmpty = isTextFieldEmpty(preperationTime, preperationRequiredIndicator, "Field Required!");
       Boolean isCategoryEmpty = isComboBoxFieldEmpty(categoryChoiceBox, categoryRequiredIndicator, "Field Required!");
       
        if(!(isNameEmpty | isDescriptionEmpty | isPriceEmpty | isPreperationTimeEmpty | isCategoryEmpty)){

            MenuItem thisMenuItem = new MenuItem(name.getText(), categoryChoiceBox.getValue().toString(), description.getText(), Float.valueOf(price.getText()), allergen.isSelected(), preperationTime.getText());
            //System.out.println(thisMenuItem.getCategory());
            //System.out.println(thisMenuItem.getName());
            //System.out.println(thisMenuItem.getDescription());
            //System.out.println(thisMenuItem.getAllergen());
            //System.out.println(thisMenuItem.getTimeToPrepare());
            //System.out.println(thisMenuItem.getPrice());
            
           try {            
               dbManager.addMenuItemToDatabase(thisMenuItem);
           } catch (SQLException ex) {
               Logger.getLogger(NewMenuItemController.class.getName()).log(Level.SEVERE, null, ex);
           }
           Stage stage;
           stage = (Stage) preperationTime.getScene().getWindow();
           stage.close();
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
