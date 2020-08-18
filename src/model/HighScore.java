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

	public HighScore() {
		// TODO Auto-generated constructor stub
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

	public ArrayList<Integer> getScore() {
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

	public ArrayList<Integer> getPressed() {
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

}
