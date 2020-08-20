package view;

import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.GameBoard;
import model.IGameBoard;
import model.Sound;

public class GameView extends JFrame implements GameObserver {

	private GamePlay gameplay;
	private IGameBoard gameboard;
	private FunctionPane functionPane;
	private Sound soundtrack = Sound.getInstance();;

	public GameView() {
		soundtrack.playSoundTrack();

		gameboard = new GameBoard();
		gameplay = new GamePlay(gameboard);
		functionPane = new FunctionPane();

		// dang ky observer
		gameboard.registerObserver((GameObserver) this);

		// set focus de bat su kien vao class GameView
		setFocusable(true);

		initUI();
	}

	public void initUI() {
		// add component
		add(gameplay, BorderLayout.WEST);
		add(functionPane, BorderLayout.CENTER);

		// visible
		setTitle("Game 2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(550, 220);
		setSize(520, 380);
		setResizable(false);
	}

	// observer pattern
	@Override
	public void update(IGameBoard bo) {
		
		GameBoard g = (GameBoard) bo;
		
		gameplay.setTiles(g.getTiles());
		
		functionPane.getScorelb().setText("Score:" + g.getScore() + "");
		functionPane.getPresslb().setText("Pressed: " + g.getPressedNumber() + "");
	}

	// them su kien cho cac nut Quit, New Game va High Score va SoundTrack
	public void addButtonListener(ActionListener actionListener) {
		functionPane.getBtnQuit().addActionListener(actionListener);
		functionPane.getBtnNewGame().addActionListener(actionListener);
		functionPane.getBtnHighScore().addActionListener(actionListener);
		functionPane.getBtnSound().addActionListener(actionListener);
	}

	// them su kien cho cac phim di chuyen game, neu su dung keylistener thi sau khi
	// click 1 button no se thay doi tieu diem va khong nhan su kien cho keylistener
	public void addMoveListener(KeyEventDispatcher dispatcher) {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
	}

	public GamePlay getGameplay() {
		return gameplay;
	}

	public IGameBoard getGameboard() {
		return gameboard;
	}

	public FunctionPane getFunctionPane() {
		return functionPane;
	}

	public Sound getSoundtrack() {
		return soundtrack;
	}

}
