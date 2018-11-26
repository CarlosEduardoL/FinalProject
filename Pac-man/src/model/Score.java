package model;

public class Score implements Comparable<Score>{
	
	private String name;
	private int score;
	
	
	@Override
	public int compareTo(Score o) {
		// TODO Auto-generated method stub
		return -score + o .score;
	}


	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}


	public String getName() {
		return name;
	}


	public int getScore() {
		return score;
	}

}
