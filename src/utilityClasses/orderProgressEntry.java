/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

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

    public orderProgressEntry(String itemName, String status, String completionTime) {
        this.itemName = itemName;
        this.status = status;
        this.completionTime = completionTime;
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

}
