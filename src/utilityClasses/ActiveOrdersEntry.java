/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import restaurantpossystem.ManagerGUIController;

/**
 *
 * @author timbettison
 */
public class ActiveOrdersEntry {

    private String tableNumber;
    private String order;
    private String status;
    private String timeRemaining;
    private String completionTime;

    public ActiveOrdersEntry(String tableNumber, String order, String status, String completionTime, ManagerGUIController controller) {
        this.tableNumber = tableNumber;
        this.order = order;
        this.status = status;
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
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeRemaining() {
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
        return this.timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}
