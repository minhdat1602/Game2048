package controller;

import java.awt.event.KeyAdapter;

import model.IGameBoard;
import view.GameView;

public class GameController extends KeyAdapter implements Controller {
	private IGameBoard gameboard;
	private GameView gameview;

	public GameController(GameView gameview) {
		super();
		gameboard = gameview.getGameboard();
		this.gameview = gameview;

		addListener();
	}

	public void addListener() {
		gameview.addButtonListener(new ButtonController(gameboard, gameview));
		gameview.addMoveListener(new MoveController(gameboard, gameview));
	}

	public void start() {
		gameboard.resetGame();
		gameview.setVisible(true);
	}

}
