package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import model.Game;

public class MainController implements Initializable {
	
	private SuperController controller;
	private Game game;

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

}
