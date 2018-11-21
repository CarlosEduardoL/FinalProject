package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Game;
import threads.PacmansThread;

public class MapController implements Initializable {
	
	private SuperController controller;
	private Game game;
	
	@FXML
	private ImageView phanton,pac1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setController(SuperController controller) {
		this.controller = controller;
		
	}
	
	public synchronized void print() {
		int[] posPac1 = game.posPacman(0);
		pac1.setLayoutX(posPac1[0]);
		pac1.setLayoutY(posPac1[1]);
	}
	
	public void setGame(Game game) {
		this.game = game;
		phanton.setLayoutX(game.getPhanton().getPosX());
		phanton.setLayoutY(game.getPhanton().getPosY());
		PacmansThread t = new PacmansThread(game, this);
		t.start();
	}

	public synchronized void move(KeyEvent e) {
		double x = phanton.getLayoutX();
		double y = phanton.getLayoutY();
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.UP) && game.checkMove(x, y - 5)) {
			game.movePhantom(x, y - 5);
			phanton.setLayoutY(phanton.getLayoutY() - 5);
		}else if(k.equals(KeyCode.DOWN) && game.checkMove(x, y + 5)) {
			game.movePhantom(x, y + 5);
			phanton.setLayoutY(phanton.getLayoutY()+ 5);
		}else if(k.equals(KeyCode.LEFT) && game.checkMove(x - 5, y)) {
			game.movePhantom(x - 5, y);
			phanton.setLayoutX(phanton.getLayoutX()- 5);
		}else if(k.equals(KeyCode.RIGHT) && game.checkMove(x + 5, y)) {
			game.movePhantom(x + 5, y);
			phanton.setLayoutX(phanton.getLayoutX()+ 5);
		}
	}
	
	public void move2(MouseEvent e) {
		double x = e.getSceneX();
		double y = e.getSceneY();
		if (game.checkMove(x, y)) {
			phanton.setLayoutX(x);
			phanton.setLayoutY(y);
		}
	}
	
	public void test(MouseEvent e) {
		
	}
	
}
