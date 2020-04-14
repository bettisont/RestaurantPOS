/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import utilityClasses.Table;

/**
 * FXML Controller class
 *
 * @author timbettison
 */
public class OrderProgressController implements Initializable {

    @FXML
    private Label tableLabel;
    private Table table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initOrderData(Table table) {
        this.table = table;
        tableLabel.setText("Order Progress for Table " + table.getTableNumber());
        tableLabel.setAlignment(Pos.CENTER);
    }

}
