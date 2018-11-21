package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
	private ImageView phanton,pac1,pac2,pac3,pac4,background;
	
	private ImageView[] pacs;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		pacs = new ImageView[4];
		pacs[0]=pac1;
		pacs[1]=pac2;
		pacs[2]=pac3;
		pacs[3]=pac4;
	}
	
	public void setController(SuperController controller) {
		this.controller = controller;
		
	}
	
	public void print() {
		for (int i = 0; i < pacs.length; i++) {
			int[] posPac1 = game.posPacman(i);
			pacs[i].setLayoutX(posPac1[0]);
			pacs[i].setLayoutY(posPac1[1]);
		}
		phanton.setLayoutY(game.getPhanton().getPosY());
		phanton.setLayoutX(game.getPhanton().getPosX());
		background.setLayoutX(background.getLayoutX());
	}
	
	public void setGame(Game game) {
		this.game = game;
		phanton.setLayoutX(game.getPhanton().getPosX());
		phanton.setLayoutY(game.getPhanton().getPosY());
		PacmansThread t = new PacmansThread(game, this);
		t.start();
	}

	public void move(KeyEvent e) {
		double x = phanton.getLayoutX();
		double y = phanton.getLayoutY();
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.UP) && game.checkMove(x, y - 5)) {
			game.movePhantom(x, y - 5);
		}else if(k.equals(KeyCode.DOWN) && game.checkMove(x, y + 5)) {
			game.movePhantom(x, y + 5);
		}else if(k.equals(KeyCode.LEFT) && game.checkMove(x - 5, y)) {
			game.movePhantom(x - 5, y);
		}else if(k.equals(KeyCode.RIGHT) && game.checkMove(x + 5, y)) {
			game.movePhantom(x + 5, y);
		}
		print();
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
