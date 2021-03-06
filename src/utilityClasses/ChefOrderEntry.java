/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import restaurantpossystem.FXMLChefGUIController;

/**
 *
 * @author timbettison
 */
public class ChefOrderEntry {

    private Text tableNumber;
    private Text mealName;
    private Text preperationTime;
    private Text notes;
    private int isAllergen;
    private Button startButton;
    private Button finishedButton;
    private int id;
    private FXMLChefGUIController chefController;
    private int prepTimeAsInt;
    private String timeRemaining;
    private String completionTime;

    public ChefOrderEntry(int id, int tableNumber, String mealName, String preperationTime, String completionTime, String status, String notes, int isAllergen, FXMLChefGUIController controller) {

        this.chefController = controller;
        Text tableNumberText = new Text();
        Text mealNameText = new Text();
        Text preperationTimeText = new Text();
        Text notesText = new Text();
        Button startBtnButton = new Button();
        Button finishedBtnButton = new Button();
        prepTimeAsInt = Integer.parseInt(preperationTime);

        if (isAllergen == 1) {
            tableNumberText.setText(String.valueOf(tableNumber));
            tableNumberText.setFill(Color.RED);
            mealNameText.setText(mealName);
            mealNameText.setFill(Color.RED);
            preperationTimeText.setText(preperationTime);
            preperationTimeText.setFill(Color.RED);
            notesText.setText(notes);
            // notesText.wrappingWidthProperty()
            notesText.setFill(Color.RED);
            finishedBtnButton.setStyle("-fx-text-fill: rgb(39,174,96);");
            startBtnButton.setStyle("-fx-text-fill: rgb(255,0,0);");
        } else {
            tableNumberText.setText(String.valueOf(tableNumber));
            mealNameText.setText(mealName);
            preperationTimeText.setText(preperationTime);
            notesText.setText(notes);
            finishedBtnButton.setStyle("-fx-text-fill: rgb(39,174,96);");
        }
        this.completionTime = completionTime;
        LocalTime time = LocalTime.parse(completionTime);
        LocalTime timeNow = LocalTime.now();
        int seconds = (int) timeNow.until(time, ChronoUnit.SECONDS);
        if (!(seconds > 0)) {
            this.timeRemaining = "Not Started.";
        } else if (status.contains("complete")) {
            this.timeRemaining = "Ready For Collection";
        } else {
            String myTimeUntil = String.format("%02d:%02d", seconds / 60, seconds % 60);
            this.timeRemaining = myTimeUntil;
        }

        finishedBtnButton.setText("Complete");
        startBtnButton.setText("Start");
        this.tableNumber = tableNumberText;
        this.mealName = mealNameText;
        this.notes = notesText;
        this.preperationTime = preperationTimeText;
        this.finishedButton = finishedBtnButton;
        this.startButton = startBtnButton;
        this.isAllergen = isAllergen;
        this.id = id;

        finishedButton.setOnAction(e -> {
            try {
                finishButtonOnAction(e);
            } catch (Exception ex) {
                Logger.getLogger(ChefOrderEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        startButton.setOnAction(e -> {
            try {
                startButtonOnAction();
            } catch (Exception ex) {
                Logger.getLogger(ChefOrderEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public Text getNotes() {
        return notes;
    }

    public void setNotes(Text notes) {
        this.notes = notes;
    }

    public void InstansiateAllergenObjects() {

    }

    public Text getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Text tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Button getFinishedButton() {
        return finishedButton;
    }

    public void setFinishedButton(Button finishedButton) {
        this.finishedButton = finishedButton;
    }

    public Text getMealName() {
        return mealName;
    }

    public void setMealName(Text mealName) {
        this.mealName = mealName;
    }

    public Text getPreperationTime() {
        return preperationTime;
    }

    public void setPreperationTime(Text preperationTime) {
        this.preperationTime = preperationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAllergen() {
        return isAllergen;
    }

    public void setIsAllergen(int isAllergen) {
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
        // update completion time of the order in the database
        dbManager.setOrderCompletionTime(this.id, this.prepTimeAsInt);
        // start a timer countdown somehow
        chefController.updateIncomingOrders();
    }

    private void finishButtonOnAction(ActionEvent e) throws SQLException, Exception {
        DatabaseManager dbManager = new DatabaseManager();
        // update the status of the row in the order schema to 'inprogress'
        dbManager.setOrderStatusToComplete(this.id);
        // start a timer countdown somehow
        chefController.updateIncomingOrders();
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

}
