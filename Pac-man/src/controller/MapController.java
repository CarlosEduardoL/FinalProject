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

public class MapController implements Initializable {
	
	SuperController controller;
	Game game;
	
	@FXML
	private ImageView phanton;

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

	public void move(KeyEvent e) {
		double x = phanton.getLayoutX();
		double y = phanton.getLayoutY();
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.UP) && game.checkMove(x, y - 7)) {
			phanton.setLayoutY(phanton.getLayoutY() - 7);
		}else if(k.equals(KeyCode.DOWN) && game.checkMove(x, y + 7)) {
			phanton.setLayoutY(phanton.getLayoutY()+ 7);
		}else if(k.equals(KeyCode.LEFT) && game.checkMove(x - 7, y)) {
			phanton.setLayoutX(phanton.getLayoutX()- 7);
		}else if(k.equals(KeyCode.RIGHT) && game.checkMove(x + 7, y)) {
			phanton.setLayoutX(phanton.getLayoutX()+ 7);
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
