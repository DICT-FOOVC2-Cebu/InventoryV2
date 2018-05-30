/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystemv2;

/**
 *
 * @author Ralph
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QRCodeReader extends Application implements Initializable {

	@FXML private Button start;
	@FXML private Label code;
	@FXML private Label fps;
	@FXML private ImageView imageView;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			VBox page = (VBox) FXMLLoader.load(QRCodeReader.class.getResource("QRCodeReader.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle("QRCodeReader");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
