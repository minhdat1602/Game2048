package model;

import view.GameObserver;
import view.GameView;

public interface IGameBoard {

	public void resetGame();

	public void leftMove();

	public void rightMove();

	public void upMove();

	public void downMove();

	public boolean canMove();

	public void saveHighScore();

	public Tile[] getTiles();

	public boolean isWin();

	public boolean isLose();

	public int getScore();

	// observer
	public void registerObserver(GameObserver go);

	public void removeObserver(GameObserver go);

	public void notifyObservers();

	public void setLose(boolean b);

	public HighScore getHighScore();
}
