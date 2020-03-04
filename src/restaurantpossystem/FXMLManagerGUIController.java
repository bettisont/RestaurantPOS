/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import databaseClasses.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class FXMLManagerGUIController implements Initializable {
    
    //configure the table 
    @FXML private TableView<MenuItem> tableView; 
    @FXML private TableColumn<MenuItem, String> nameCol;
    @FXML private TableColumn<MenuItem, String> categoryCol;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // set up the columns in the table 
        nameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("category"));
    
        // load dummy data 
        tableView.setItems(getMenuItems());
    
    
    }   
    /**
     * 
     * @return an ObservableList of MenuItems
     * this will eventually be linked to a database
     * an ObservableList is very similar to an ArrayList
     */
    private ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList(); 
        menuItems.add(new MenuItem("burger", "main"));
        menuItems.add(new MenuItem("ice cream", "dessert"));
        menuItems.add(new MenuItem("olives", "side"));
        menuItems.add(new MenuItem("garlic bread", "starter"));
        return menuItems;
    }
    
    

    
}
