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
public class Order {
    
    private String status;
    private int tableID;
    private int menuItemID;
    
    public Order(String status, int tableID, int menuItemID){
        this.status = status;
        this.tableID = tableID;
        this.menuItemID = menuItemID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(int menuItemID) {
        this.menuItemID = menuItemID;
    }
    
    
    
}
