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
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralph
 */
public class AddingUIController implements Initializable {
    
    ObservableList<String> cbStatusList = FXCollections.observableArrayList("Pending","Resolved");
    
@FXML
private AnchorPane genPane;
    
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
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        
        
    }
    
    
@FXML
private void GenerateButton(ActionEvent event) throws IOException {
        dbQuery();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("GenerateUI.fxml"));
        
        try {
            Loader.load();
        }catch(IOException ex) {
            Logger.getLogger(AddingUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        GenerateUIController generate = Loader.getController();
        generate.setText(tfPARNo.getText(), tfSNNo.getText(), tfRemarks.getText());
        generate.showQRCode(tfPARNo.getText());
        
        Parent RegViewParent = Loader.getRoot();
        
        Scene RegviewScene = new Scene(RegViewParent);
        Stage window = new Stage();
        window.setScene(RegviewScene);
        window.show();
        generate.print();

    }
    @FXML
    private void saveButton(ActionEvent event) throws IOException {
        dbQuery();

//                if (users.imagePath != null) {
//                    InputStream is;
//                    is = new FileInputStream(new File(users.imagePath));
//                    pst.setBlob(10, is);
//                } else {
//                    pst.setBlob(10, (Blob) null);
//                }
//                pst.setString(11, LocalDate.now().toString());
//                pst.setString(12, users.creatorId);

         
    }
    public void alertSuccessBox(){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess :");
                alert.setHeaderText("Sucess");
                alert.setContentText("Item " + item.parNo + " Added sucessfuly");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
    }
    /**
     * Initializes the controller class.
     */
private void dbQuery(){
        item.parNo = tfPARNo.getText();
        item.snNo = tfSNNo.getText();
        item.issuedBy = tfIssuedBy.getText();
        item.receivedBy = tfReceivedBy.getText();
        item.description = taDesc.getText();
        item.amount = tfAmount.getText();
        item.remarks = tfRemarks.getText();
        
        con = dbConnection.geConnection();
                    try {
                pst = con.prepareStatement("insert into "+db+".AddItem values(?,?,?,?,?,?,?,?,?,?,?)");
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
                
                pst.executeUpdate();
                pst.close();
                con.close();
                if(btnSave.isArmed()) alertSuccessBox();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error : Server Not Found");
                alert.setContentText("Make sure your mysql is Started properly. \n");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbStatus.setValue("Pending");
        cbStatus.setItems(cbStatusList);
    }    
    
}
