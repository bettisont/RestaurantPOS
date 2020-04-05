/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

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

    public String getCategory() {
        return category.get();
    }

    public void setCategory(SimpleStringProperty category) {
        this.category = category;
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public String getDescription() {
        return description.get();
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

    public String getTimeToPrepare() {
        return timeToPrepare.get();
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



    
    
}
