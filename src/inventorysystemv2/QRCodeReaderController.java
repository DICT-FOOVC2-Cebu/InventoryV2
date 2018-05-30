/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemv2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ralph
 */
public class QRCodeReaderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    	@FXML private Button start;
	@FXML private Label code;
	@FXML private Label fps;
	@FXML private ImageView imageView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WebCamImageProvider imageProvider = new WebCamImageProvider();
				imageView.imageProperty().bind(imageProvider.imageProperty());
				fps.textProperty().bind(imageProvider.fpsProperty());
				
				imageProvider.setOnSucceeded(event1 -> code.setText(imageProvider.getQrCode()));
				
				Thread t = new Thread(imageProvider);
				t.setDaemon(true);
				t.start();
			}
		});
    }    
    
}
