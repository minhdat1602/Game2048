package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import view.GameObserver;

public class GameBoard implements IGameBoard {

	private Tile[] tiles;
	private boolean win, lose;

	// score
	private int score = 0;
	private int pressedNumber = 0;

	private HighScore highScore;
	private boolean saveScore = false;

	private ArrayList observers;
	private Level level;

	public GameBoard() {
		observers = new ArrayList();

		highScore = new HighScore();
		level = new Easy();

		resetGame();
	}

	

	// notify observer when tiles change
	public void change(Tile[] tiles) {
		this.tiles = tiles;

		notifyObservers();
	}

	public void resetGame() {
		score = 0;
		pressedNumber = 0;
		saveScore = false;
		lose = false;
		tiles = new Tile[4 * 4];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile();
		}

		addTile();
		addTile();
		change(tiles);
	}

	public void leftMove() {
		boolean needAddTile = false;
		for (int i = 0; i < 4; i++) {
			Tile[] line = getLine(i);
			Tile[] merged = mergeLine(moveLine(line));
			setLine(i, merged);
			if (!needAddTile && !compare(line, merged)) {
				needAddTile = true;
			}
		}
		if (needAddTile) {
			addTile();
		}
		pressedNumber++;
		System.out.println(pressedNumber);
		change(tiles);
	}

	public void rightMove() {

		tiles = rotate(180);
		leftMove();
		tiles = rotate(180);
		change(tiles);
	}

	public void upMove() {

		tiles = rotate(270);
		leftMove();
		tiles = rotate(90);
		change(tiles);

	}

	public void downMove() {

		tiles = rotate(90);
		leftMove();
		tiles = rotate(270);
		change(tiles);

	}

	private Tile tileAt(int x, int y) {
		return tiles[x + y * 4];
	}

	private void addTile() {
		List<Tile> list = availableSpace();
		if (!availableSpace().isEmpty()) {
			int index = (int) (Math.random() * list.size()) % list.size();
			Tile emptyTime = list.get(index);
			emptyTime.value = Math.random() < 0.9 ? 2 : 4;
		}
	}

	private List<Tile> availableSpace() {
		final List<Tile> list = new ArrayList<Tile>(16);
		for (Tile t : tiles) {
			if (t.isEmpty()) {
				list.add(t);
			}
		}
		return list;
	}

	private boolean isFull() {
		return availableSpace().size() == 0;
	}

	public boolean canMove() {
		if (!isFull()) {
			return true;
		}
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				Tile t = tileAt(x, y);
				if ((x < 3 && t.value == tileAt(x + 1, y).value) || ((y < 3) && t.value == tileAt(x, y + 1).value)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean compare(Tile[] line1, Tile[] line2) {
		if (line1 == line2) {
			return true;
		} else if (line1.length != line2.length) {
			return false;
		}

		for (int i = 0; i < line1.length; i++) {
			if (line1[i].value != line2[i].value) {
				return false;
			}
		}
		return true;
	}

	private Tile[] rotate(int angle) {
		Tile[] newTiles = new Tile[4 * 4];
		int offsetX = 3, offsetY = 3;
		if (angle == 90) {
			offsetY = 0;
		} else if (angle == 270) {
			offsetX = 0;
		}

		double rad = Math.toRadians(angle);
		int cos = (int) Math.cos(rad);
		int sin = (int) Math.sin(rad);
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				int newX = (x * cos) - (y * sin) + offsetX;
				int newY = (x * sin) + (y * cos) + offsetY;
				newTiles[(newX) + (newY) * 4] = tileAt(x, y);
			}
		}
		return newTiles;
	}

	private Tile[] moveLine(Tile[] oldLine) {
		LinkedList<Tile> l = new LinkedList<Tile>();
		for (int i = 0; i < 4; i++) {
			if (!oldLine[i].isEmpty())
				l.addLast(oldLine[i]);
		}
		if (l.size() == 0) {
			return oldLine;
		} else {
			Tile[] newLine = new Tile[4];
			ensureSize(l, 4);
			for (int i = 0; i < 4; i++) {
				newLine[i] = l.removeFirst();
			}
			return newLine;
		}
	}

	private Tile[] mergeLine(Tile[] oldLine) {
		LinkedList<Tile> list = new LinkedList<Tile>();
		for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
			int num = oldLine[i].value;
			if (i < 3 && oldLine[i].value == oldLine[i + 1].value) {
				num *= 2;
				score += num;
				if (num == level.scoreWin()) {
					win = true;
				}
				i++;
			}
			list.add(new Tile(num));
		}
		if (list.size() == 0) {
			return oldLine;
		} else {
			ensureSize(list, 4);
			return list.toArray(new Tile[4]);
		}
	}

	private static void ensureSize(java.util.List<Tile> l, int s) {
		while (l.size() != s) {
			l.add(new Tile());
		}
	}

	private Tile[] getLine(int index) {
		Tile[] result = new Tile[4];
		for (int i = 0; i < 4; i++) {
			result[i] = tileAt(i, index);
		}
		return result;
	}

	private void setLine(int index, Tile[] re) {
		System.arraycopy(re, 0, tiles, index * 4, 4);
	}

	@Override
	public void removeObserver(GameObserver go) {
		int i = observers.indexOf(go);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			GameObserver go = (GameObserver) observers.get(i);
			go.update(this);
		}
	}

	@Override
	public void registerObserver(GameObserver go) {
		observers.add(go);
	}

//	public void saveHighScore() {
//		if (!saveScore) {
//			if (pressedNumber != 0 && score != 0) {
//				highScore.addPressed(pressedNumber);
//				highScore.addScore(score);
//				saveScore = true;
//			}
//		}
//		System.out.println("save score success...");
//	}
	
	public void saveHighScore() {
		if (!saveScore) {
			if (pressedNumber != 0 && score != 0) {
				highScore.addPressed(pressedNumber);
				highScore.addScore(score);
				saveScore = true;
			}
		}
		System.out.println("save score success...");
	}

	public int getPressedNumber() {
		return pressedNumber;
	}

	public void setPressedNumber(int pressedNumber) {
		this.pressedNumber = pressedNumber;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public HighScore getHighScore() {
		return highScore;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
