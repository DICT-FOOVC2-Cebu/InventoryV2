/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemv2;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ralph
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
    
            if(textField1.getText().equals("admin")&&passwordField1.getText().equals("admin")){
                
                           
     
                        Parent RegViewParent = FXMLLoader.load(getClass().getResource("OptUI.fxml"));
        
                        Scene RegviewScene = new Scene(RegViewParent);
        
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(RegviewScene);
                        window.show();
                    
                             
                
                }
            else
              label1.setText("Wrong Username or Password !");
    
        }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
