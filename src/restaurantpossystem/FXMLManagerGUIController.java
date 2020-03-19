/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import databaseClasses.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    
    private static final String userName = "root";
    private static final String password = "root";
    private static final String conn = "jdbc:mysql://localhost:8889/restaurantManagementSystem";
    
    private Connection connection; 
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
    
        
        try {
            /**
             * setup the db connection
             */
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/restaurantManagementSystem",userName,password);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
    /**
     * 
     * @return an ObservableList of MenuItems
     * this will eventually be linked to a database
     * an ObservableList is very similar to an ArrayList
     */
    private ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList(); 
        
        return menuItems;
    }
    
    @FXML
    private void handleAddItemButtonAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("newMenuItem.fxml"));
        Scene scene = new Scene(parent);
        
        // this line gets the Stage information 
        Stage chefWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        chefWindow.setScene(scene);
        chefWindow.show();
    }

    
}
