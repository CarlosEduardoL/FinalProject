package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacMan {
	
	private int posX;
	private int posY;
	private boolean inMove;
	private Queue<Point> queue;
	private Point lastPoint;
	
	public PacMan(int i, int j) {
		posX = i;
		posY = j;
	}

	public synchronized void move() {
		if (queue.isEmpty()) {
			inMove = false;
		}else if (posX == queue.peek().getX() && posY == queue.peek().getY()) {
			lastPoint = queue.poll();
		}else if (posX != queue.peek().getX()){
			if (posX < queue.peek().getX()) {
				posX+=5;
			}else {
				posX -=5;
			}
		}else {
			if (posY < queue.peek().getY()) {
				posY+=5;
			}else {
				posY -=5;
			}
		}
	}
	
	public synchronized void move(List<Point> list) {
		queue = new LinkedList<>();
		for(Point p : list) {
			queue.add(p);
		}
		move();
		inMove = true;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isInMove() {
		return inMove;
	}

	public Point getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(Point lastPoint) {
		this.lastPoint = lastPoint;
	}
	
	

}
