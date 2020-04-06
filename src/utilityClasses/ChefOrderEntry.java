/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import restaurantpossystem.FXMLChefGUIController;

/**
 *
 * @author timbettison
 */
public class ChefOrderEntry {

    private int tableNumber;
    private String mealName;
    private String preperationTime;
    private Boolean isAllergen;
    private Button startButton;
    private int id;
    private FXMLChefGUIController controller;

    public ChefOrderEntry(int id, int tableNumber, String mealName, String preperationTime, Boolean isAllergen, FXMLChefGUIController controller) {

        this.tableNumber = tableNumber;
        this.mealName = mealName;
        this.preperationTime = preperationTime;
        this.isAllergen = isAllergen;
        this.controller = controller;
        Button thisButton = new Button("Start");
        thisButton.setStyle("-fx-text-fill: rgb(39,174,96);");
        thisButton.setOnAction(e -> {
            try {
                startButtonOnAction();
            } catch (Exception ex) {
                Logger.getLogger(ChefOrderEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.startButton = thisButton;
        this.id = id;

    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getPreperationTime() {
        return preperationTime;
    }

    public void setPreperationTime(String preperationTime) {
        this.preperationTime = preperationTime;
    }

    public Boolean getIsAllergen() {
        return isAllergen;
    }

    public void setIsAllergen(Boolean isAllergen) {
        this.isAllergen = isAllergen;
    }

    public Button getStartButton() {
        return startButton;
    }

    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }

    private void startButtonOnAction() throws SQLException, Exception {
        DatabaseManager dbManager = new DatabaseManager();
        // update the status of the row in the order schema to 'inprogress'
        dbManager.setOrderStatusToInProgress(this.id);
        // start a timer countdown somehow
        controller.updateIncomingOrders();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
