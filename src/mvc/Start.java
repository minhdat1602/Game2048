package mvc;

import controller.Controller;
import controller.GameController;
import view.GameView;

public class Start {
	public static void main(String[] args) {
		GameView gameview = new GameView();
		Controller ctrl = new GameController(gameview);
		ctrl.start();
	}
}
