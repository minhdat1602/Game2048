package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScore {
	private int score;
	private int pressed;

	public HighScore(int score, int pressed) {
		this.score = score;
		this.pressed = pressed;
	}

	public HighScore() {
		this(0, 0);
	}

	public void addScore(int score) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File("src/score/top5score.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(" " + score);
			System.out.println("Success...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void addPressed(int pressed) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File("src/score/top5pressed.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(" " + pressed);
			System.out.println("Success...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ArrayList<Integer> getScoreList() {
		ArrayList<Integer> result = new ArrayList<Integer>(5);
		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			File file = new File("src/score/top5score.txt");
			Scanner sc = new Scanner(file);
			sc.useDelimiter("[\\s]+");

			while (sc.hasNext()) {
				list.add(sc.nextInt());
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}

		});

		// add top 5;
		for (int i = 0; i < 5; i++) {
			result.add(list.get(i));
		}

		return result;
	}

	public ArrayList<Integer> getPressedList() {
		ArrayList<Integer> result = new ArrayList<Integer>(5);
		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			File file = new File("src/score/top5pressed.txt");
			Scanner sc = new Scanner(file);
			sc.useDelimiter("[\\s]+");

			while (sc.hasNext()) {
				list.add(sc.nextInt());
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}

		});

		// add top 5;
		for (int i = 0; i < 5; i++) {
			result.add(list.get(i));
		}

		return result;
	}

	public int getScore() {
		return score;
	}

	public int getPressed() {
		return pressed;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setPressed(int pressed) {
		this.pressed = pressed;
	}

}
