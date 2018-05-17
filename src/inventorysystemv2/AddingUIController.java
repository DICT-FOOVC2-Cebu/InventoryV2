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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ralph
 */
public class AddingUIController implements Initializable {
   
  
@FXML
private Button saveButton;
@FXML
private TextField textField2;
@FXML
private TextField textField3;
@FXML
private TextField textField4;
@FXML
private TextField textField5;
@FXML
private TextArea textArea1;
@FXML
private void GenerateButton(ActionEvent event) throws IOException {
        
         
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("GenerateUI.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RegviewScene);
        window.show();
    }
    @FXML
    private void SaveButton(ActionEvent event) throws IOException {
        
         if(textField2.getText().equals("")){
             
             textArea1.setText("Go Home You are Drunk!");
         
         }
         else
         {
             Parent RegViewParent = FXMLLoader.load(getClass().getResource("SmallStorageUI.fxml"));
        
                            Scene RegviewScene = new Scene(RegViewParent);
        
     
                            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window.setScene(RegviewScene);
                             window.show();
         }
    }
    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
