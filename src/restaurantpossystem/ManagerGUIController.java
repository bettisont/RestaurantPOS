/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import databaseClasses.DatabaseManager;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class ManagerGUIController implements Initializable {
    
    private DatabaseManager dbManager; 
    private ObservableList<MenuItem> menuItemsInDatabase;
    

    @FXML Button addItemButton;
    @FXML Button refreshEditMenuTableButton;
    
    @FXML private TableView<MenuItem> editMenuTable; 
    @FXML private TableColumn<MenuItem, String> categoryColumn;
    @FXML private TableColumn<MenuItem, String> nameColumn;
    @FXML private TableColumn<MenuItem, String> descriptionColumn;
    @FXML private TableColumn<MenuItem, Float> priceColumn;
    @FXML private TableColumn<MenuItem, Boolean> allergenColumn;
    @FXML private TableColumn<MenuItem, String> prepTimeColumn;
    
    //configure the table 
   // @FXML private TableView<MenuItem> tableView; 
   // @FXML private TableColumn<MenuItem, String> nameCol;
   // @FXML private TableColumn<MenuItem, String> categoryCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // set up the columns in the table 
     //   nameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
     //   categoryCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("category"));
        

        
        fetchAndUpdateEditMenuTable();
        
    }   
    
    @FXML
    private void fetchAndUpdateEditMenuTable(){
        dbManager = new DatabaseManager();
        try {
            menuItemsInDatabase = dbManager.getAllMenuItems();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set up the columns 
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("description"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Float>("price"));
        allergenColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Boolean>("allergen"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("prepTimeColumn"));
        
        // display the data 
        editMenuTable.setItems(menuItemsInDatabase);
    }

    
    @FXML
    private void handleAddItemButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("newMenuItem.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addItemButton.getScene().getWindow());
        stage.showAndWait();
    }
    
}