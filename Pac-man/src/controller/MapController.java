package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MapController implements Initializable {
	
	@FXML
	private ImageView phanton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void move(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.UP)) {
			phanton.setLayoutY(phanton.getLayoutY() - 7);
		}else if(k.equals(KeyCode.DOWN)) {
			phanton.setLayoutY(phanton.getLayoutY()+ 7);
		}else if(k.equals(KeyCode.LEFT)) {
			phanton.setLayoutX(phanton.getLayoutX()- 7);
		}else if(k.equals(KeyCode.RIGHT)) {
			phanton.setLayoutX(phanton.getLayoutX()+ 7);
		}
	}
	
	public void test(MouseEvent e) {
		System.out.print("(x,y) " + e.getSceneX());
		System.out.println("," + e.getSceneY());
	}
	
}
