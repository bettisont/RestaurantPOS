/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilityClasses.DatabaseManager;
import utilityClasses.MenuItem;
import utilityClasses.Order;
import utilityClasses.ChefOrderEntry;
import utilityClasses.Table;
import utilityClasses.orderProgressEntry;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class OrderProgressController implements Initializable {

    @FXML
    private Label tableLabel;
    private Table table;
    private DatabaseManager dbManager;
    @FXML
    TableView ordersProgressTable;
    @FXML
    TableColumn itemColumn;
    @FXML
    TableColumn statusColumn;
    @FXML
    TableColumn timeRemainingColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dbManager = new DatabaseManager();
    }

    public void initOrderData(Table table) throws Exception {
        this.table = table;
        tableLabel.setText("Order Progress for Table " + table.getTableNumber());
        tableLabel.setAlignment(Pos.CENTER);
        ObservableList<orderProgressEntry> ordersToDisplay = FXCollections.observableArrayList();
        for (Order order : dbManager.getOrdersForSpecificTable(table)) {
            String orderStatus = order.getStatus();
            MenuItem thisMenuItem = dbManager.getMenuItem(order.getMenuItemID());
            String mealName = thisMenuItem.getName();
            String prepTime = thisMenuItem.getTimeToPrepare();
            String completionTime = dbManager.getOrderCompletionTime(order.getId());
            ordersToDisplay.add(new orderProgressEntry(mealName, orderStatus, completionTime, prepTime));
        }
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeRemainingColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemaining"));
        ordersProgressTable.setItems(ordersToDisplay);

        Timer timer = new Timer();
        timer.schedule(new UpdateOrderProgress(this), 0, 1000);
    }

    public Table getTable() {
        return table;
    }

    public void updateTableData(Table table) throws Exception {
        this.table = table;
        tableLabel.setText("Order Progress for Table " + table.getTableNumber());
        tableLabel.setAlignment(Pos.CENTER);
        ObservableList<orderProgressEntry> ordersToDisplay = FXCollections.observableArrayList();
        for (Order order : dbManager.getOrdersForSpecificTable(table)) {
            String orderStatus = order.getStatus();
            MenuItem thisMenuItem = dbManager.getMenuItem(order.getMenuItemID());
            String mealName = thisMenuItem.getName();
            String prepTime = thisMenuItem.getTimeToPrepare();
            String completionTime = dbManager.getOrderCompletionTime(order.getId());
            ordersToDisplay.add(new orderProgressEntry(mealName, orderStatus, completionTime, prepTime));
        }
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeRemainingColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemaining"));
        ordersProgressTable.setItems(ordersToDisplay);
    }

}

class UpdateOrderProgress extends TimerTask {

    OrderProgressController controller;

    public UpdateOrderProgress(OrderProgressController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            controller.updateTableData(controller.getTable());
        } catch (Exception ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
