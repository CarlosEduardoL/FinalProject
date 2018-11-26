package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Game;

public class MainController implements Initializable {
	
	private SuperController controller;
	private Game game;
	
	@FXML
	private Button boton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void setController(SuperController controller) {
		this.controller = controller;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void launchMap(ActionEvent e) {
		try {
			controller.launchMap((Stage) ((Node) boton).getScene().getWindow());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void launcScore(ActionEvent e) {
		try {
			controller.launchRanking((Stage) ((Node) boton).getScene().getWindow());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
