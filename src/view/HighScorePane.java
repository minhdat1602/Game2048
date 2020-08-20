package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.HighScore;

public class HighScorePane extends JFrame {

	/**
	 * 
	 */
	private JLabel[] top;
	private JLabel[] topScore;
	private JLabel[] topPressed;

	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;

	private static final Font FONT = new Font(Font.SERIF, Font.BOLD, 25);

	private HighScore highscore;

	public HighScorePane(HighScore highscore) {
		this.highscore = highscore;

		setTitle("High Score");
		setLayout(new GridLayout(6, 3));

		initUI();
		setLocation(550, 220);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
	}

	public void initUI() {

		top = new JLabel[5];
		topScore = new JLabel[5];
		topPressed = new JLabel[5];

		JLabel col1 = new JLabel("---------", SwingConstants.CENTER);
		col1.setFont(FONT);
		add(col1);

		JLabel col2 = new JLabel("Score", SwingConstants.CENTER);
		col2.setFont(FONT);
		col2.setForeground(Color.RED);
		add(col2);

		JLabel col3 = new JLabel("Pressed", SwingConstants.CENTER);
		col3.setFont(FONT);
		col3.setForeground(Color.RED);
		add(col3);

		for (int i = 0; i < 5; i++) {
			int x = i + 1;
			top[i] = new JLabel("TOP " + x, SwingConstants.CENTER);
			top[i].setFont(FONT);
			add(top[i]);

			topScore[i] = new JLabel(highscore.getScoreList().get(i) + "", SwingConstants.CENTER);
			topScore[i].setFont(FONT);
			topScore[i].setForeground(Color.BLUE);
			add(topScore[i]);

			topPressed[i] = new JLabel(highscore.getPressedList().get(i) + "", SwingConstants.CENTER);
			topPressed[i].setFont(FONT);
			topPressed[i].setForeground(Color.GREEN);
			add(topPressed[i]);
		}
	}

	public HighScore getHighscore() {
		return highscore;
	}
}
