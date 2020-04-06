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
    TableColumn incomingActionColumn;
    @FXML
    TableColumn incomingIdColumn;

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
    private int id;

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
        List<Order> allOrders = dbManager.getAllOrders();

        ObservableList<ChefOrderEntry> waitingChefOrderEntries = FXCollections.observableArrayList();
        ObservableList<ChefOrderEntry> inProgressChefOrderEntries = FXCollections.observableArrayList();

        for (Order order : allOrders) {
            String orderStatus = order.getStatus();
            String tableNumber = dbManager.getTableNumber(order.getTableID());
            MenuItem thisMenuItem = dbManager.getMenuItem(order.getMenuItemID());
            String mealName = thisMenuItem.getName();
            Boolean allergen = thisMenuItem.getAllergen();
            int orderId = order.getId();
            String prepTime = thisMenuItem.getTimeToPrepare();
            if (orderStatus.equals("waiting")) {
                waitingChefOrderEntries.add(new ChefOrderEntry(orderId, Integer.valueOf(tableNumber), mealName, prepTime, allergen, this));
            } else if (orderStatus.equals("in progress")) {
                inProgressChefOrderEntries.add(new ChefOrderEntry(orderId, Integer.valueOf(tableNumber), mealName, prepTime, allergen, this));
            }
        }

        incomingTableNumColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        incomingMealColumn.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        incomingPreperationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("preperationTime"));
        incomingActionColumn.setCellValueFactory(new PropertyValueFactory<>("startButton"));

        incomingActionColumn.setStyle("-fx-alignment: CENTER;");
        incomingIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
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
