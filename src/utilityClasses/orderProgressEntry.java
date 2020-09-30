/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author timbettison
 */
public class orderProgressEntry {

    private String itemName;
    private String status;
    private String completionTime;
    private String timeRemaining;

    public orderProgressEntry(String itemName, String status, String completionTime, String preperationTime) {
        this.itemName = itemName;
        this.status = status;
        this.completionTime = completionTime;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

}
