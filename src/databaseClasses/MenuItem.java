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
    
    private SimpleStringProperty category, name, description, timeToPrepare;
    private float price;
    private Boolean allergen;
    

    public MenuItem(String name, String category, String description, float price, Boolean allergen, String timeToPrepare){
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.description = new SimpleStringProperty(description);
        this.price = price;
        this.allergen = allergen;
        this.timeToPrepare = new SimpleStringProperty(timeToPrepare);
    }

    public SimpleStringProperty getCategory() {
        return category;
    }

    public void setCategory(SimpleStringProperty category) {
        this.category = category;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleStringProperty getDescription() {
        return description;
    }

    public void setDescription(SimpleStringProperty description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public SimpleStringProperty getTimeToPrepare() {
        return timeToPrepare;
    }

    public void setTimeToPrepare(SimpleStringProperty timeToPrepare) {
        this.timeToPrepare = timeToPrepare;
    }

    public Boolean getAllergen() {
        return allergen;
    }

    public void setAllergen(Boolean allergen) {
        this.allergen = allergen;
    }

    @Override
    public String toString(){
        String toReturn = "";
        toReturn.concat("all fields filled...");
        toReturn.concat("...Item to be added...:");
        toReturn.concat("Category: " + "'"+this.category+"'");
        toReturn.concat("Name: " + "'"+this.name+"'");
        toReturn.concat("Description: " + "'"+ this.description + "'");
        toReturn.concat("Price: " + "'"+ this.price +"'");
        toReturn.concat("Allergen: " + "'"+ this.allergen +"'");
        toReturn.concat("Preperation Time: " + "'"+ this.timeToPrepare +"'");
        return toReturn;
    }
    

    
    
}
