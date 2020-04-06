/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utilityClasses.ChefOrderEntry;
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
    @FXML
    Pane incomingOrdersPane;

    @FXML
    TableView incomingOrdersTable;
    @FXML
    TableColumn incomingTableNumColumn;
    @FXML
    TableColumn incomingMealColumn;
    @FXML
    TableColumn incomingPreperationTimeColumn;

    @FXML
    TableView inprogressOrdersTable;
    @FXML
    TableColumn inprogressTableNumColumn;
    @FXML
    TableColumn inprogressMealColumn;
    @FXML
    TableColumn inprogressPreperationTimeColumn;
    @FXML
    Button backButton;

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
    public void updateIncomingOrders() throws SQLException, Exception {

        // get all the orders from the database
        List<Order> allOrders = dbManager.getAllOrders();
        List<String> activeTableNumbers = new ArrayList();
        List<Table> activeTables = new ArrayList();

        // for each order
        for (Order order : allOrders) {
            // IF the order has a status of 'waiting'
            String orderStatus = order.getStatus();
            String tableNumber = dbManager.getTableNumber(order.getTableID());
            Table thisTable = null;
            MenuItem thisMenuItem = dbManager.getMenuItem(order.getMenuItemID());
            // IF this table does not yet exist (not got any orders on it yet)
            if (!activeTableNumbers.contains(tableNumber)) {
                //create this table
                thisTable = new Table(Integer.parseInt(tableNumber));
                activeTables.add(thisTable);
                activeTableNumbers.add(tableNumber);
            } else if (activeTableNumbers.contains(tableNumber)) {
                for (Table table : activeTables) {
                    if (table.getTableNumber() == Integer.parseInt(tableNumber)) {
                        thisTable = table;
                    }
                }
            }
            if (orderStatus.equals("waiting")) {
                // append this menuItem to the tables waitingOrder List
                thisTable.appendOrderWaiting(thisMenuItem);
            } else if (orderStatus.equals("in progress")) {
                thisTable.appendOrderInProgress(thisMenuItem);
            }
        }
        // display them on the gui
        VBox tableButtonsVBox = new VBox(10);
        ObservableList<ChefOrderEntry> waitingChefOrderEntries = FXCollections.observableArrayList();
        ObservableList<ChefOrderEntry> inProgressChefOrderEntries = FXCollections.observableArrayList();
        for (Table thisTable : activeTables) {

            // for each menuItem that is waiting
            for (MenuItem item : thisTable.getOrderWaiting()) {
                ChefOrderEntry thisOrderEntry = new ChefOrderEntry(thisTable.getTableNumber(), item.getName(), item.getTimeToPrepare(), item.getAllergen());
                waitingChefOrderEntries.add(thisOrderEntry);
            }
            for (MenuItem item : thisTable.getOrderInProgress()) {
                ChefOrderEntry thisOrderEntry = new ChefOrderEntry(thisTable.getTableNumber(), item.getName(), item.getTimeToPrepare(), item.getAllergen());
                inProgressChefOrderEntries.add(thisOrderEntry);
            }
        }

        incomingTableNumColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        incomingMealColumn.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        incomingPreperationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("preperationTime"));
        incomingOrdersTable.setItems(waitingChefOrderEntries);

        inprogressTableNumColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        inprogressMealColumn.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        inprogressPreperationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("preperationTime"));
        inprogressOrdersTable.setItems(inProgressChefOrderEntries);

    }

    @FXML
    private void backButtonOnAction(ActionEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLLoginScreen.fxml"));
        Scene scene = new Scene(parent);
        // this line gets the Stage information
        Stage loginWindow = (Stage) ((Node) e.getSource()).getScene().getWindow();
        loginWindow.setScene(scene);
        loginWindow.show();
    }

}
