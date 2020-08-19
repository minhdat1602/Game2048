package model;

public class Normal implements Level {

	@Override
	public int scoreWin() {
		return 2048 * 4;
	}

}
