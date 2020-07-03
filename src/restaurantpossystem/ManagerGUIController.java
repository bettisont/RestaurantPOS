/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import utilityClasses.Table;
import utilityClasses.DatabaseManager;
import utilityClasses.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
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
import utilityClasses.ActiveOrdersEntry;
import utilityClasses.ChefOrderEntry;
import utilityClasses.Order;

class Update extends TimerTask {

    ManagerGUIController controller;

    public Update(ManagerGUIController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            controller.updateActiveOrders();
        } catch (Exception ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class ManagerGUIController implements Initializable {

    private DatabaseManager dbManager;
    private ObservableList<MenuItem> menuItemsInDatabase;
    private List<Table> tables;

    // Tables Tab --------------------------------
    @FXML
    Button tableButton1;
    @FXML
    Button tableButton2;
    @FXML
    Button tableButton3;
    @FXML
    Button tableButton4;
    @FXML
    Button tableButton5;
    @FXML
    Button tableButton6;
    @FXML
    Button tableButton7;
    @FXML
    Button tableButton8;
    @FXML
    Button tableButton9;
    @FXML
    Button tableButton10;
    @FXML
    Button tableButton11;
    @FXML
    Button tableButton12;
    @FXML
    Button tableButton13;
    @FXML
    Button tableButton14;
    @FXML
    Button tableButton15;
    @FXML
    Button tableButton16;
    @FXML
    Button tableButton17;
    @FXML
    Button tableButton18;
    @FXML
    Button tableButton19;
    @FXML
    Button tableButton20;
    @FXML
    Button tableButton21;
    @FXML
    Button tableButton22;
    @FXML
    Button tableButton23;
    @FXML
    Button tableButton24;
    @FXML
    Button tableButton25;
    @FXML
    Button tableButton26;
    @FXML
    Button tableButton27;
    @FXML
    Button tableButton28;
    @FXML
    Button tableButton29;
    @FXML
    Button tableButton30;
    @FXML
    Button backButton;
    // -------------------------------------------------------

    // Active Orders Tab -------------------------------------
    @FXML
    private TableView activeOrdersTable;
    @FXML
    private TableColumn activeOrdersTableNumberColumn;
    @FXML
    private TableColumn activeOrdersTableOrderColumn;
    @FXML
    private TableColumn activeOrdersTableTimeRemainingColumn;
    @FXML
    private TableColumn activeOrdersStatusColumn;

    // -------------------------------------------------------
    // Edit Menu Tab -----------------------------------------
    @FXML
    Button addItemButton;
    @FXML
    Button refreshEditMenuTableButton;
    @FXML
    private TableView<MenuItem> editMenuTable;
    @FXML
    private TableColumn<MenuItem, String> categoryColumn;
    @FXML
    private TableColumn<MenuItem, String> nameColumn;
    @FXML
    private TableColumn<MenuItem, String> descriptionColumn;
    @FXML
    private TableColumn<MenuItem, Float> priceColumn;
    @FXML
    private TableColumn<MenuItem, Boolean> allergenColumn;
    @FXML
    private TableColumn<MenuItem, String> prepTimeColumn;

    // ------------------------------------------------------
    //configure the table
    // @FXML private TableView<MenuItem> tableView;
    // @FXML private TableColumn<MenuItem, String> nameCol;
    // @FXML private TableColumn<MenuItem, String> categoryCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fetchAndUpdateEditMenuTable();

        try {
            updateActiveOrders();
        } catch (Exception ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tables = new ArrayList<Table>();
        Timer timer = new Timer();
        timer.schedule(new Update(this), 0, 1000);

    }

    public void updateActiveOrders() throws SQLException, Exception {
        ObservableList<ActiveOrdersEntry> activeOrdersEntries = FXCollections.observableArrayList();
        for (Order order : dbManager.getAllOrders()) {
            String tableNumber = DatabaseManager.getTableNumber(order.getTableID());
            String orderName = dbManager.getMenuItem(order.getMenuItemID()).getName();
            String status = order.getStatus();
            String completionTime = dbManager.getOrderCompletionTime(order.getId());
            ActiveOrdersEntry thisEntry = new ActiveOrdersEntry(tableNumber, orderName, status, completionTime, this);
            activeOrdersEntries.add(thisEntry);
        }

        activeOrdersTableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        activeOrdersTableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        activeOrdersTableTimeRemainingColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemaining"));
        activeOrdersStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        activeOrdersTable.setItems(activeOrdersEntries);

    }

    @FXML
    private void fetchAndUpdateEditMenuTable() {
        dbManager = new DatabaseManager();
        try {
            menuItemsInDatabase = dbManager.getAllMenuItems();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set up the columns
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        allergenColumn.setCellValueFactory(new PropertyValueFactory<>("allergen"));
        prepTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeToPrepare"));

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

    @FXML
    public void handleTableButtonAction(ActionEvent event) throws IOException, SQLException, Exception {

        Button tableBtn = (Button) event.getSource();
        int tableNum = Integer.parseInt(tableBtn.getText().split(" ")[1]);
        Boolean isTableOccupied = false;
        Table thisTable = new Table(tableNum);
        if (!dbManager.isTableOccupied(tableNum)) {
            tables.add(thisTable);
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLTableView.fxml"));

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        // access the controller and call a method
        FXMLTableViewController controller = loader.getController();
        controller.initTableData(thisTable);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);

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
