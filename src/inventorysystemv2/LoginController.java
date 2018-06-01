/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemv2;

import database.DBConnection;
import database.DBProperties;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ralph
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    
    @FXML
    private Button btnLogin;
    
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    
   DBProperties dbProperties = new DBProperties();
    String db = dbProperties.loadPropertiesFile();
    
    @FXML
    public void buttonPressed(KeyEvent event) throws IOException
    {
    if(event.getCode().toString().equals("ENTER"))
    {
        //do something
        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        if(con!=null){
            
                User admin = new User();
                            if(textField1.getText().equals(admin.username)&&passwordField1.getText().equals(admin.password)){



                                        Parent RegViewParent = FXMLLoader.load(getClass().getResource("OptUI.fxml"));

                                        Scene RegviewScene = new Scene(RegViewParent);

                                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                                        window.setScene(RegviewScene);
                                        window.show();

                                }
                            else
                              label1.setText("Wrong Username or Password !");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error : Server Not Found");
                alert.setContentText("Make sure your mysql is Started properly. \n");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            }
        
    }
    }
    
    
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mkDbProperties();
    }
    public void mkDbProperties() {
    Properties properties = new Properties();
    OutputStream output = null;
        try {
            output = new FileOutputStream("database.properties");
            properties.setProperty("host", "localhost");
            properties.setProperty("port", "3306");
            properties.setProperty("db", "inventorysysv2");
            properties.setProperty("user", "root");
            properties.setProperty("password", "");
            properties.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventorySystemV2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InventorySystemV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
