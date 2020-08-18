package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FunctionPane extends JPanel {

	private JLabel scorelb, presslb;
	private JButton btnNewGame, btnHighScore, btnQuit, btnSound;

	private static final Font FONT_BTN = new Font("jbutton", Font.BOLD, 16);
	private static final Font FONT_LB = new Font("jlabel", Font.PLAIN, 18);

	public FunctionPane() {
		setPreferredSize(new Dimension(100, 350));
		initUI();
	}

	public void initUI() {
		setLayout(null);

		scorelb = new JLabel("Score: 0000", SwingConstants.CENTER);
		scorelb.setFont(FONT_LB);
		scorelb.setBounds(20, 20, 130, 30);
		add(scorelb);

		presslb = new JLabel("Pressed: 0000", SwingConstants.CENTER);
		presslb.setFont(FONT_LB);
		presslb.setBounds(20, 50, 130, 30);
		add(presslb);

		btnSound = new JButton();
		btnSound.setIcon(new ImageIcon("src/pictures/speaker.png"));
		btnSound.setBorder(BorderFactory.createEmptyBorder());
		btnSound.setContentAreaFilled(false);
		btnSound.setBounds(25, 90, 40, 40);
		add(btnSound);

		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(FONT_BTN);
		btnNewGame.setBounds(10, 150, 130, 35);
		add(btnNewGame);

		btnHighScore = new JButton("High Score");
		btnHighScore.setFont(FONT_BTN);
		btnHighScore.setBounds(10, 200, 130, 35);
		add(btnHighScore);

		btnQuit = new JButton("Quit Game");
		btnQuit.setFont(FONT_BTN);
		btnQuit.setBounds(10, 250, 130, 35);
		add(btnQuit);

	}

	public JLabel getPresslb() {
		return presslb;
	}

	public JLabel getScorelb() {
		return scorelb;
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JButton getBtnHighScore() {
		return btnHighScore;
	}

	public JButton getBtnQuit() {
		return btnQuit;
	}

	public JButton getBtnSound() {
		return btnSound;
	}

}
