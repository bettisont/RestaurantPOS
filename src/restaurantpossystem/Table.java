/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import databaseClasses.MenuItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timbettison
 */
class Table {
    
    int tableNumber;
    // the order that is being taken by the waiting staff 
    List<MenuItem> currentOrder; 
    
    // the order that has been sent to the kitchen 
    List<MenuItem> orderInProgress;
    
    // the order than has been completed by the kitchen 
    List<MenuItem> bill;
    
    // has the order been paid for? if so, clear the bill and any other data this table holds 
    Boolean hasPaid;
    
    public Table(int tableNumber){
        this.tableNumber = tableNumber;
        this.currentOrder = new ArrayList<>();
        this.bill = new ArrayList<>();
        hasPaid = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumbber) {
        this.tableNumber = tableNumbber;
    }

    public List<MenuItem> getOrder() {
        return currentOrder;
    }

    public void setOrder(List<MenuItem> order) {
        this.currentOrder = order;
    }

    public List<MenuItem> getBill() {
        return bill;
    }

    public void setBill(List<MenuItem> bill) {
        this.bill = bill;
    }

    public Boolean getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
    
    
    
}
