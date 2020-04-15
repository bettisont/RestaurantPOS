/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

/**
 *
 * @author timbettison
 */
public class orderProgressEntry {

    private String itemName;
    private String status;
    private String timeRemaining;

    public orderProgressEntry(String itemName, String status, String timeRemaining) {
        this.itemName = itemName;
        this.status = status;
        this.timeRemaining = timeRemaining;
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

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

}
