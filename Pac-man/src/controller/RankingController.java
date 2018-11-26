package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Game;
import model.Score;

public class RankingController implements Initializable {
	
	@FXML
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	
	private Label[] labels;
	
	private Game game;
	private SuperController controller;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labels = new Label[10];
		labels[0] = l1;
		labels[1] = l2;
		labels[2] = l3;
		labels[3] = l4;
		labels[4] = l5;
		labels[5] = l6;
		labels[6] = l7;
		labels[7] = l8;
		labels[8] = l9;
		labels[9] = l10;
	}

	public void setController(SuperController controller) {
		this.controller = controller;
	}
	
	public void setGame(Game game) {
		this.game = game;
		List<Score> l= game.printScore(); 
		for (int i = 0; i < l.size(); i++) {
			labels[i].setText(l.get(i).getName() + " " + l.get(i).getScore());
		}
	}
	
	public void goBack() {
		try {
			controller.launchMainScreen((Stage) ((Node) l1).getScene().getWindow());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
