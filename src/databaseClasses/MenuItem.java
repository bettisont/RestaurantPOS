/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author timbettison
 */
public class MenuItem {
    
    private SimpleStringProperty category, name, description, price, allergen, timeToPrepare;
    

    public MenuItem(String name, String category, String description, String price, String allergen, String timeToPrepare){
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleStringProperty(price);
        this.allergen = new SimpleStringProperty(allergen);
        this.timeToPrepare = new SimpleStringProperty(timeToPrepare);
    }

    
    

    
    
}
