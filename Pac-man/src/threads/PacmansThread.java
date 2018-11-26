package threads;

import controller.MapController;
import model.Game;

public class PacmansThread extends Thread {

	private Game game;
	private MapController controller;
	
	public PacmansThread(Game game, MapController controller) {
		this.game = game;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while (game.isInGame()) {
			try {
				sleep(120 - 2*game.getPhanton().level());
				game.movePacmans();
				controller.print();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
