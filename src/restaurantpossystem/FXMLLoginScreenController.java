/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantpossystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author timbettison
 */
public class FXMLLoginScreenController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleChefButtonAction(ActionEvent event) throws IOException {
        Parent chefViewParent = FXMLLoader.load(getClass().getResource("FXMLChefGUI.fxml"));
        Scene chefViewScene = new Scene(chefViewParent);
        
        // this line gets the Stage information 
        Stage chefWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        chefWindow.setScene(chefViewScene);
        chefWindow.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
