/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
    
    public void addMenuItemToDatabase(MenuItem menuItem) throws SQLException{
        String category = menuItem.getCategory().get();
        String name = menuItem.getName().get();
        String description = menuItem.getDescription().get();
        float price = menuItem.getPrice();
        Boolean allergen = menuItem.getAllergen();
        int allergenForDb = 1;
        if(!(allergen)){
            allergenForDb = 0;
        }
        String prepTime = menuItem.getTimeToPrepare().get();
        String sql = "INSERT INTO `menuItems` (`ID`, `category`, `name`, `description`, `price`, `allergen`, `prepTime`) VALUES (NULL,"+"'"+category+"'"+","+"'"+name+"'"+","+"'"+description+"'"+","+"'"+price+"'"+","+"'"+allergenForDb+"'"+","+"'"+prepTime+"'"+")";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
    
    public ObservableList<MenuItem> getAllMenuItems() throws SQLException{
        ObservableList<String> listOfMenuItems = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `menuItems`";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            listOfMenuItems.add(rs.getString("category") +", "+ rs.getString("name") +", "+ rs.getString("description") +", "+ rs.getString("price") +", "+ rs.getString("allergen") +", "+ rs.getString("prepTime"));
        }
        return convertStringsToMenuItems(listOfMenuItems);
    }
    
    private ObservableList<MenuItem> convertStringsToMenuItems(ObservableList<String> menuItems){
        ObservableList<MenuItem> menuItemObjects = FXCollections.observableArrayList();
        menuItems.stream().map((menuItem) -> menuItem.split(",")).map((split) -> {
            String name = split[1];
            String category = split[0];
            String description = split[2];
            float price = Float.parseFloat(split[3]);
            Boolean allergen = "1".equals(split[4]);
            String timeToPrepare = split[5];
            MenuItem thisMenuItem = new MenuItem(name, category, description, price, allergen, timeToPrepare);
            return thisMenuItem;
        }).forEachOrdered((thisMenuItem) -> {
            menuItemObjects.add(thisMenuItem);
        });
        return menuItemObjects;
    }
}
