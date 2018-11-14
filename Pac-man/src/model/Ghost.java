package model;

public class Ghost {
	
	private int lifes;
	private double posX;
	private double posY;
	private String name;
	
	public Ghost(int lifes, String name) {
		this.lifes = lifes;
		this.name = name;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public int getLifes() {
		return lifes;
	}

	public String getName() {
		return name;
	}
	
	
	
}
