/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import utilityClasses.DatabaseManager;
import utilityClasses.MenuItem;
import utilityClasses.Order;
import utilityClasses.Table;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class FXMLChefGUIController implements Initializable {
    
    private static DatabaseManager dbManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbManager = new DatabaseManager();

        try {
            updateIncomingOrders();
        } catch (Exception ex) {
            Logger.getLogger(FXMLChefGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }    
    
    // this is static so it can be called from other classes when a new order has been placed
    // for example 
    public static void updateIncomingOrders() throws SQLException, Exception{
        
        // get all the orders from the database 
        List<Order> allOrders = dbManager.getAllOrders();
        List<String> activeTableNumbers = new ArrayList();
        List<Table> activeTables = new ArrayList();
        
        // for each order
        for(Order order : allOrders){
        // IF the order has a status of 'waiting'
        String orderStatus = order.getStatus();
        String tableNumber = dbManager.getTableNumber(order.getTableID());
        Table thisTable = null;
        // IF this table does not yet exist (not got any orders on it yet)
        if(!activeTableNumbers.contains(tableNumber)){
            //create this table 
            thisTable = new Table(Integer.parseInt(tableNumber));
            activeTables.add(thisTable);
            activeTableNumbers.add(tableNumber);
        }
        else if(activeTableNumbers.contains(tableNumber)){
            for(Table table : activeTables){
                if(table.getTableNumber() == Integer.parseInt(tableNumber)){
                    thisTable = table;
                }
            }
        }
        if(orderStatus.equals("waiting")){
            // get the table number that this order belongs to
            // get the menuItem that the order belongs to
            MenuItem thisMenuItem = dbManager.getMenuItem(order.getMenuItemID());
            // append this menuItem to the tables waitingOrder List 
            thisTable.appendOrderWaiting(thisMenuItem);
        }

        
            
        // IF the order has a status of 'inProgress'
            // get the table number that this order belongs to
            // IF this table does not yet exist (not got any orders on it yet)
                //create this table 
            // get the menuItem that the order belongs to
            // append this menuItem to the tables inProgress List
       
        }
        System.out.println(":P");
        
        

    }
    
}
