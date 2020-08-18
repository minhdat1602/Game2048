package view;

import model.IGameBoard;

public interface GameObserver {
	public void update(IGameBoard gb);
}
