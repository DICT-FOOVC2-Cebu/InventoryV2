/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemv2;

import database.DBConnection;
import database.DBProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralph
 */
public class AddingUIController implements Initializable {
   
  
@FXML
private Button btnSave;

@FXML
private Button btnGenerate;

@FXML
private TextField tfPARNo;

@FXML
private TextField tfIssuedBy;

@FXML
private TextField tfReceivedBy;

@FXML
private TextField tfSNNo;

@FXML
private TextField tfRemarks;

@FXML
private TextField tfAmount;

@FXML
private TextArea taDesc;

@FXML
private ComboBox cbStatus;

    Items item = new Items();
    DBConnection dbConnection = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();


@FXML
private void GenerateButton(ActionEvent event) throws IOException {
        
         
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("GenerateUI.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RegviewScene);
        window.show();
    }
    @FXML
    private void saveButton(ActionEvent event) throws IOException {
        item.parNo = tfPARNo.getText();
        item.snNo = tfSNNo.getText();
        item.issuedBy = tfIssuedBy.getText();
        item.receivedBy = tfReceivedBy.getText();
        item.description = taDesc.getText();
        item.amount = tfAmount.getText();
        item.remarks = tfRemarks.getText();
        
        con = dbConnection.geConnection();
                    try {
                pst = con.prepareStatement("insert into "+db+".additem values(?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setBlob(2, (Blob) null);
                pst.setString(3, item.snNo);
                pst.setString(4, item.parNo);
                pst.setString(5, item.issuedBy);
                pst.setString(6, item.receivedBy);
                pst.setString(7, item.description);
                pst.setString(8, item.remarks);
                pst.setString(9, item.amount);
                pst.setString(10, null);
                pst.setString(11, null);
//                if (users.imagePath != null) {
//                    InputStream is;
//                    is = new FileInputStream(new File(users.imagePath));
//                    pst.setBlob(10, is);
//                } else {
//                    pst.setBlob(10, (Blob) null);
//                }
//                pst.setString(11, LocalDate.now().toString());
//                pst.setString(12, users.creatorId);
                pst.executeUpdate();
                pst.close();
                con.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess :");
                alert.setHeaderText("Sucess");
                alert.setContentText("Item " + item.parNo + " Added sucessfuly");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
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
