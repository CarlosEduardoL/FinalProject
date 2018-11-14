package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;

public class SuperController {

	Game game;

	public SuperController() {
		game = new Game();
	}

	public void launchMainScreen(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/mainPage.fxml"));
		Parent root = loader.load();
		MainController map = loader.getController();
		map.setController(this);
		map.setGame(game);
		Scene scene = new Scene(root,530,530);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void launchMap(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/map.fxml"));
		Parent root = loader.load();
		MapController map = loader.getController();
		map.setController(this);
		map.setGame(game);
		Scene scene = new Scene(root,530,530);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void launchDifficulty(Stage primaryStage) {

	}

}
