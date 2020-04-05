/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import utilityClasses.MenuItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timbettison
 */
public class Table {
    
    int tableNumber;
    // the order that is being taken by the waiting staff 
    List<MenuItem> orderWaiting; 
    
    // the order that has been sent to the kitchen 
    List<MenuItem> orderInProgress;
    
    // the order than has been completed by the kitchen 
    List<MenuItem> bill;
    
    // has the order been paid for? if so, clear the bill and any other data this table holds 
    Boolean hasPaid;
    
    public Table(int tableNumber){
        this.tableNumber = tableNumber;
        this.orderWaiting = new ArrayList<>();
        this.orderInProgress = new ArrayList();
        this.bill = new ArrayList<>();
        hasPaid = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumbber) {
        this.tableNumber = tableNumbber;
    }

    public List<MenuItem> getOrderWaiting() {
        return orderWaiting;
    }

    public void setOrderWaiting(List<MenuItem> orderWaiting) {
        this.orderWaiting = orderWaiting;
    }

    public List<MenuItem> getOrderInProgress() {
        return orderInProgress;
    }

    public void setOrderInProgress(List<MenuItem> orderInProgress) {
        this.orderInProgress = orderInProgress;
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
    
    public void appendOrderInProgress(MenuItem item){
        orderInProgress.add(item);
    }
    
    public void removeOrderInProgress(MenuItem item){
        orderInProgress.remove(item);
    }
    
    public void appendOrderWaiting(MenuItem item){
        orderWaiting.add(item);
    }
    
    public void removeOrderWaiting(MenuItem item){
        orderWaiting.remove(item);
    }
   
}
