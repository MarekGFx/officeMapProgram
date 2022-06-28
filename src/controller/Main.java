package controller;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		mainWindow();
	
	}
	
	public void mainWindow() {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindowView.fxml"));
		try {
		AnchorPane pane = loader.load();
		primaryStage.setMinWidth(400);
		primaryStage.setMinHeight(300);
		Scene scene = new Scene(pane);
		MainWindowController mainWindowController = loader.getController();
		
		mainWindowController.setMain(this);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
