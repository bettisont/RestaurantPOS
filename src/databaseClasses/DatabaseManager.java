/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restaurantpossystem.ManagerGUIController;

/**
 *
 * @author timbettison
 */
public class DatabaseManager {
   
    private static final String userName = "root";
    private static final String password = "root";
    private static final String conn = "jdbc:mysql://localhost:8889/restaurantManagementSystem";
    private Connection connection; 
    
    public DatabaseManager(){
        try {
            /**
             * setup the db connection
             */
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/restaurantManagementSystem",userName,password);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
        /**
     * 
     * @return an ObservableList of MenuItems
     * this will eventually be linked to a database
     * an ObservableList is very similar to an ArrayList
     */
    // TODO - alter the functionality of this to retrieve menuItems from the database
    public ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList(); 
        
        return menuItems;
    }
    
}
