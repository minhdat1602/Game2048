package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import model.IGameBoard;
import model.Sound;
import view.GameView;
import view.HighScorePane;

public class ButtonController implements ActionListener {
	private IGameBoard gameboard;
	private GameView gameview;

	public ButtonController(IGameBoard gameboard, GameView gameview) {
		super();
		this.gameboard = gameboard;
		this.gameview = gameview;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gameview.getFunctionPane().getBtnQuit()) {
			System.out.println("exit game success...");
			System.exit(0);
		}

		if (e.getSource() == gameview.getFunctionPane().getBtnNewGame()) {
			gameboard.resetGame();
			gameview.repaint();
			System.out.println("new game success...");
		}

		if (e.getSource() == gameview.getFunctionPane().getBtnHighScore()) {
			new HighScorePane(gameboard.getHighScore());
			System.out.println("show score frame...");
		}

		if (e.getSource() == gameview.getFunctionPane().getBtnSound()) {
			if (gameboard.getSoundtrack().isPlaying()) {
				gameboard.getSoundtrack().stopSoundTrack();
				gameview.getFunctionPane().getBtnSound().setIcon(new ImageIcon("src/pictures/mute.png"));

			} else {
				gameboard.getSoundtrack().resumeSoundTrack();
				gameview.getFunctionPane().getBtnSound().setIcon(new ImageIcon("src/pictures/speaker.png"));
			}
		}

	}

}
