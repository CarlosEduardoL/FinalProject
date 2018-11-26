package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
	
	private String ruta;
	
	public Ranking() {
		ruta = "rank.sc";
	}  
	
	public Ranking(String ruta) {
		super();
		this.ruta = ruta;
	}



	public ArrayList<Score> getScores(){
		ArrayList<Score> scores = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ruta)));
			String storage = "";
			String buffer;
			while ((buffer = reader.readLine()) != null) {
				storage += buffer;
			}
			for (int i = 0; i < 5; i++) {
				storage = decrypt(storage, storage.length());
			}
			String[] data = storage.split("leon"); 
			for (int i = 0; i < data.length; i++) {
				String[] subData = data[i].split(" ");
				scores.add(new Score(subData[0],Integer.parseInt(subData[1])));
			}
			reader.close();
		}catch (Exception e) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(ruta)));
				String crypt = "Carlos "+0+"leon";
				for (int i = 0; i < 5; i++) {
					crypt = encrypt(crypt, crypt.length());
				}
				writer.write(crypt);
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return getScores();
		}
		
		return scores;
	}
	
	
	public void addNewScore(String name, int Score) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ruta)));
			String storage = "";
			String buffer;
			while ((buffer = reader.readLine()) != null) {
				storage += buffer;
			}
			for (int i = 0; i < 5; i++) {
				storage = decrypt(storage, storage.length());
			}
			String[] data = storage.split("leon"); 
			ArrayList<Score> scores = new ArrayList<>();
			for (int i = 0; i < data.length; i++) {
				String[] subData = data[i].split(" ");
				scores.add(new Score(subData[0],Integer.parseInt(subData[1])));
			}
			scores.add (new Score(name, Score));
			Collections.sort(scores);
			String toSave ="";
			int hasta = scores.size() < 11? scores.size():11;
			for (int i = 0; i < hasta; i++) {
				toSave += scores.get(i).getName() + " " + scores.get(i).getScore() + "leon";
			}
			for (int i = 0; i < 5; i++) {
				toSave = encrypt(toSave, toSave.length());
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(ruta)));
			writer.write(toSave);
			writer.close();
			reader.close();
		} catch (Exception e) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(ruta)));
				String crypt = name+Score+"leon";
				for (int i = 0; i < 5; i++) {
					crypt = encrypt(crypt, crypt.length());
				}
				writer.write(crypt);
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private String encrypt(String data,int pasword) {
		pasword /= 1000;
		pasword +=1;
		char[] string = data.toCharArray();
		for (int i = 0; i < string.length; i++) {
			string[i] += pasword;
		}
		return new String(string);
	}
	
	private String decrypt(String data,int pasword) {
		pasword /= 1000;
		pasword +=1;
		char[] string = data.toCharArray();
		for (int i = 0; i < string.length; i++) {
			string[i] -= pasword;
		}
		return new String(string);
	}

}
