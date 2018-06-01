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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ralph
 */
public class OptUIController implements Initializable {
    
    
    
    
    @FXML
     private void BackButton(ActionEvent event) throws IOException {
      /*log out button*/  
        
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RegviewScene);
        window.show();
    }
    @FXML
     private void ReadButton(ActionEvent event) throws IOException {
      /*log out button*/  
        
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("QRCodeReader.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
<<<<<<< HEAD
        Stage window = new Stage();
=======
        Stage window =  new Stage();
>>>>>>> 79507f68e77773e41d441d349805ab850097ae25
        window.setScene(RegviewScene);
        window.show();
    }
    @FXML
     private void AddButton(ActionEvent event) throws IOException {
       /*add button*/
        
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("AddingUI.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
        Stage window = new Stage();
        window.setScene(RegviewScene);
        window.show();
        
    }
     @FXML
     private void StorageButton(ActionEvent event) throws IOException {
        
        
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("SmallStorageUI.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RegviewScene);
        window.show();
    }
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   
    
}
