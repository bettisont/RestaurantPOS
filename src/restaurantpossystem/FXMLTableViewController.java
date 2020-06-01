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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import utilityClasses.ChefOrderEntry;
import utilityClasses.CurrentOrderEntry;
import utilityClasses.Order;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class FXMLTableViewController implements Initializable {

    @FXML
    private Button orderProgressButton;
    @FXML
    private Button viewBillButton;

    @FXML
    private Label tableLabel;
    @FXML
    private Button backButton;
    @FXML
    private Pane menuButtonsPane;
    @FXML
    private TextArea currentOrderTextArea;
    @FXML
    private Text currentOrderPriceText;
    @FXML
    private Button startersButton;
    @FXML
    private Button mainsButton;
    @FXML
    private Button sidesButton;
    @FXML
    private Button dessertsButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button sendToKitchen;

    @FXML
    TableView currentOrderTable;
    @FXML
    TableColumn menuItemNameColumn;
    @FXML
    TableColumn menuItemNotesColumn;
    @FXML
    TableColumn menuItemAddNotesBtnColumn;

    DatabaseManager dbManager;
    private Dictionary menuItemButtonMappings;

    private List currentOrder;
    private float currentOrderPrice;
    private Table table;
    private List<Button> menuItemCategories;
    private ObservableList<CurrentOrderEntry> currentOrderEntries = FXCollections.observableArrayList();

    // I'm using this dictionary to store the menuItem linked with the notes for that menu item
    // e.g. <steak, "make sure it's medium rare">
    private Dictionary currentOrderMenuItemEntryPairs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dbManager = new DatabaseManager();
        menuItemButtonMappings = new Hashtable();
        currentOrder = new ArrayList();
        menuItemCategories = new ArrayList<Button>();
        menuItemCategories.add(startersButton);
        menuItemCategories.add(mainsButton);
        menuItemCategories.add(sidesButton);
        menuItemCategories.add(dessertsButton);
        currentOrderTable.setEditable(true);
        menuItemNotesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /*
    this method takes in a table string to show informartion about the corresponding data
     */
    public void initTableData(Table table) throws Exception {
        tableLabel.setText("Table " + table.getTableNumber());
        tableLabel.setAlignment(Pos.CENTER);
        this.table = table;
        if (!(dbManager.isTableOccupied(table.getTableNumber()))) {
            orderProgressButton.setDisable(true);
            viewBillButton.setDisable(true);
        }
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        // save state of the screen first?

        Parent parent = FXMLLoader.load(getClass().getResource("FXMLManagerGUI.fxml"));
        Scene scene = new Scene(parent);

        // this line gets the Stage information
        Stage managerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        managerWindow.setScene(scene);
        managerWindow.show();

    }

    @FXML
    private void displayMenuItems(ActionEvent event) throws SQLException {
        //returnButton.setVisible(true);
        Button categoryBtn = (Button) event.getSource();
        String categoryToGet = categoryBtn.getText();
        // create list that will hold buttons for starters
        List<Button> menuItemButtons = new ArrayList<Button>();
        // execute a query to retrieve the rows from the table which are starters
        ObservableList<MenuItem> starters = dbManager.getSpecifiedFoodCategory(categoryToGet);
        // grab string value from each row that I want to make the button text value
        for (MenuItem item : starters) {
            Button thisButton = new Button();
            thisButton.setText(item.getName() + "\n" + "£" + item.getPrice());
            thisButton.setTextAlignment(TextAlignment.CENTER);
            thisButton.setOnAction(this::handleMenuItemButtonClick);
            menuItemButtons.add(thisButton);
            menuItemButtonMappings.put(thisButton, item);
        }
        // clear the container of previous buttons
        menuButtonsPane.getChildren().clear();
        HBox menuItemBtnHbox = new HBox(10);
        menuItemBtnHbox.getChildren().addAll(menuItemButtons);
        // add new buttons to the container
        menuButtonsPane.getChildren().addAll(menuItemBtnHbox);
        menuButtonsPane.getChildren().add(returnButton);
    }

    @FXML
    private void displayMenuCategories(ActionEvent event) {
        // clear the container of previous buttons
        menuButtonsPane.getChildren().clear();
        // add new buttons to the container
        menuButtonsPane.getChildren().addAll(menuItemCategories);
        menuButtonsPane.getChildren().add(returnButton);
    }

    // when a menu item button is clicked, the corresponding menu item object should be added to a list
    // so that it can be added to the current order
    private void handleMenuItemButtonClick(ActionEvent event) {
        Button menuItemBtn = (Button) event.getSource();
        System.out.println(menuItemBtn.getText());
        MenuItem thisMenuItem = (MenuItem) menuItemButtonMappings.get(menuItemBtn);
        currentOrderEntries.add(new CurrentOrderEntry(thisMenuItem.getName(), this));
        currentOrder.add(thisMenuItem);
        UpdateCurrentOrder();
    }

    @FXML
    private void handleSendToKitchenButton(ActionEvent event) throws SQLException, Exception {

        // add the table in the tables schema
        String tableNumber = tableLabel.getText().split(" ")[1];
        dbManager.insertTableToDb(tableNumber);

        // for each item in the current order list
//        for (Object item : currentOrder) {
//            MenuItem thisItem = (MenuItem) item;
//            // create an entry in the order table
//            // the entry must have a table ID of the current table
//            // the entry must have a menuItemID of the menuItem
//            String tableID = dbManager.getTableID(tableNumber);
//            String menuItemID = dbManager.getMenuItemID(thisItem);
//            String orderNotes = "";
//            // need to get the notes column for each menu item in the table to insrt here
//            dbManager.insertOrderToDb(tableID, menuItemID, orderNotes);
//            sendToKitchen.setText("Order Successfully Sent");
//            currentOrderTextArea.clear();
//            orderProgressButton.setDisable(false);
//            viewBillButton.setDisable(false);
//        }
        for (int i = 0; i < currentOrder.size(); i++) {
            MenuItem thisItem = (MenuItem) currentOrder.get(i);
            String tableID = dbManager.getTableID(tableNumber);
            String menuItemID = dbManager.getMenuItemID(thisItem);
            String orderNotes = (String) menuItemNotesColumn.getCellObservableValue(i).getValue();
            dbManager.insertOrderToDb(tableID, menuItemID, orderNotes);
            orderProgressButton.setDisable(false);
            viewBillButton.setDisable(false);
        }
    }

    @FXML
    private void handleViewBillButton() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewBill.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        ViewBillController controller = loader.getController();
        controller.initBillData(this.table);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(backButton.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void handleOrderProgress(ActionEvent event) throws SQLException, Exception {
        if (dbManager.isTableOccupied(this.table.getTableNumber())) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("orderProgress.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            OrderProgressController controller = loader.getController();
            controller.initOrderData(this.table);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(backButton.getScene().getWindow());
            stage.showAndWait();
        }
    }

    public void UpdateCurrentOrder() {
        menuItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));
        menuItemNotesColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemExtraNotes"));
        currentOrderTable.setItems(currentOrderEntries);
    }

    // this method will allow the user to double click on a cell and update the notes
    public void changeNotesCellEvent(CellEditEvent edditedCell) {
        CurrentOrderEntry thisOrderEntry = (CurrentOrderEntry) currentOrderTable.getSelectionModel().getSelectedItem();
        thisOrderEntry.setMenuItemExtraNotes(edditedCell.getNewValue().toString());
    }
}
