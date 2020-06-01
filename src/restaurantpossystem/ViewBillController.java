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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilityClasses.DatabaseManager;
import utilityClasses.MenuItem;
import utilityClasses.Order;
import utilityClasses.Table;
import utilityClasses.orderProgressEntry;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class ViewBillController implements Initializable {

    @FXML
    private TextArea orderItemsTextArea;
    @FXML
    private Text totalText;
    @FXML
    private Button paidButton;
    @FXML
    private Label tableBillText;
    private Table table;
    private DatabaseManager dbManager;
    private List<Order> orders;
    FXMLTableViewController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initBillData(Table table, FXMLTableViewController controller) throws SQLException, Exception {
        orders = new ArrayList<Order>();
        dbManager = new DatabaseManager();
        this.table = table;
        tableBillText.setText("Bill for Table " + table.getTableNumber());
        tableBillText.setAlignment(Pos.CENTER);
        float totalPrice = 0;
        for (Order order : dbManager.getOrdersForSpecificTable(table)) {
            orders.add(order);
            MenuItem thisMenuItem = dbManager.getMenuItem(order.getMenuItemID());
            float price = thisMenuItem.getPrice();
            totalPrice += price;
            orderItemsTextArea.appendText("- " + thisMenuItem.getName() + "\n");
        }
        totalText.setText("Total Price: £" + Float.toString(totalPrice));
        this.controller = controller;
    }

    @FXML
    private void handleBillPaidButton() throws Exception {
        // clear table from database
        // clear orders of that table from database
        dbManager.removeTableFromDB(this.table);
        controller.initTableData(this.table);
        controller.sendToKitchen.setDisable(true);
        Stage stage = (Stage) paidButton.getScene().getWindow();
        stage.close();
    }

}
