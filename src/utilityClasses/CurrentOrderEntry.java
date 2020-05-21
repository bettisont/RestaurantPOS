/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import restaurantpossystem.FXMLChefGUIController;
import restaurantpossystem.FXMLTableViewController;

/**
 *
 * @author timbettison
 */
public class CurrentOrderEntry {

    private SimpleStringProperty menuItemName;
    private SimpleStringProperty menuItemExtraNotes;
    FXMLTableViewController controller;

    public CurrentOrderEntry(String menuItemNameString, FXMLTableViewController controller) {

        this.menuItemName = new SimpleStringProperty(menuItemNameString);
        menuItemExtraNotes = new SimpleStringProperty();
        this.controller = controller;
    }

    public String getMenuItemName() {
        return menuItemName.get();
    }

    public void setMenuItemName(SimpleStringProperty menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemExtraNotes() {
        return menuItemExtraNotes.get();
    }

    public void setMenuItemExtraNotes(String menuItemExtraNotes) {
        this.menuItemExtraNotes = new SimpleStringProperty(menuItemExtraNotes);
    }

    public FXMLTableViewController getController() {
        return controller;
    }

    public void setController(FXMLTableViewController controller) {
        this.controller = controller;
    }
}
