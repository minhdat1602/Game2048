package controller;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import model.IGameBoard;
import view.GameView;

public class MoveController implements KeyEventDispatcher {
	private IGameBoard gameboard;
	private GameView gameview;

	public MoveController(IGameBoard gameboard, GameView gameview) {
		super();
		this.gameboard = gameboard;
		this.gameview = gameview;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		switch (e.getID()) {
		case KeyEvent.KEY_PRESSED:
			if (e.getKeyCode() == e.VK_ESCAPE) {
				gameboard.resetGame();
			}
			if (!gameboard.canMove()) {
				gameboard.setLose(true);
				System.out.println("you lose");
			}
			if (!gameboard.isWin() && !gameboard.isLose()) {
				if (e.getKeyCode() == e.VK_LEFT) {
					gameboard.leftMove();
					System.out.println("Left move");
				}
				if (e.getKeyCode() == e.VK_RIGHT) {
					gameboard.rightMove();
					System.out.println("Right move");
				}
				if (e.getKeyCode() == e.VK_DOWN) {
					gameboard.downMove();
					System.out.println("Down move");
				}
				if (e.getKeyCode() == e.VK_UP) {
					gameboard.upMove();
					System.out.println("Up move");
				}
			}
			if (!gameboard.isWin() && !gameboard.canMove()) {
				gameboard.setLose(true);
				gameboard.saveHighScore();
			}
			gameview.repaint();

		case KeyEvent.KEY_RELEASED:

			break;

		case KeyEvent.KEY_TYPED:

			break;
		}
		return false;
	}
}
