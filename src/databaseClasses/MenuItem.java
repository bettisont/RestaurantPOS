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
    private SimpleStringProperty name, category;
    

    public MenuItem(String name, String category){
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
    }

    public MenuItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(SimpleStringProperty category) {
        this.category = category;
    }
    
    
}
