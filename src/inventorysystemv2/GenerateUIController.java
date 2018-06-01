/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemv2;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.deploy.panel.GeneralPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * FXML Controller class
 *
 * @author Ralph
 */
public class GenerateUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView ViewImage;
    @FXML
    private TextField tfPropNo, tfSerialNo, tfLoc, tfRem;
    @FXML
    private AnchorPane genPane;
    
    
    public void setText(String propNo, String serialNo, String remarks){
        tfPropNo.setText(propNo);
        tfSerialNo.setText(serialNo);
        tfRem.setText(remarks);
    }
    public void print(){
        PrinterJob job = PrinterJob.createPrinterJob();
        Node node = genPane;
        
        if(job!=null){
            job.printPage(node);
            job.endJob();
        }
    }
    public void showQRCode(String qrCode){
QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String qrString = qrCode;
        int width = 300;
        int height = 300;
        String fileType = "png";
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(qrString, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
//        PrinterJob job = PrinterJob.createPrinterJob();
//        if(job!=null){
//        job.showPrintDialog(genPane.getScene().getWindow());
//        job.printPage(genPane);
//        }    
        } catch (WriterException ex) {
            Logger.getLogger(GenerateUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ViewImage.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
    @FXML
     private void BackButton(WindowEvent event) throws IOException {
        
        
     
     Parent RegViewParent = FXMLLoader.load(getClass().getResource("AddingUI.fxml"));
        
        Scene RegviewScene = new Scene(RegViewParent);
        
     
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RegviewScene);
        window.show();
     }
    
    
}
