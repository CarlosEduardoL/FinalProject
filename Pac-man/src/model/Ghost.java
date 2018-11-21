package model;

public class Ghost {
	
	private int lifes;
	private double posX;
	private double posY;
	private String name;
	private Point mark;
	
	public Ghost(int lifes, String name) {
		this.lifes = lifes;
		this.name = name;
	}
	
	public void move(double x, double y) {
		posX = x;
		posY = y;
		if ((int)posX/25 != mark.getX()/25) {
			if ((int)posX/25 < mark.getX()/25) {
				mark.setX(mark.getX()-25);
				mark.setIdentificador(mark.getX()+""+mark.getY());
			}else {
				mark.setX(mark.getX()+25);
				mark.setIdentificador(mark.getX()+""+mark.getY());
			}
		}else {
			if ((int)posY/25 != mark.getY()/25) {
				if ((int)posY/25 < mark.getY()/25) {
					mark.setY(mark.getY()-25);
					mark.setIdentificador(mark.getX()+""+mark.getY());
				}else {
					mark.setY(mark.getY()+25);
					mark.setIdentificador(mark.getX()+""+mark.getY());
				}
			}
		}
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

	public Point getMark() {
		return mark;
	}

	public void setMark(Point mark) {
		this.mark = mark;
	}
	
	
	
}
