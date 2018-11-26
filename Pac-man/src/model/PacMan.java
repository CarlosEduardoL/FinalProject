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
	
	private int disX;
	private int disY;
	
	public PacMan(int i, int j) {
		posX = i;
		posY = j;
	}

	public synchronized void move() {
		
		if (queue.isEmpty()) {
			inMove = false;
		}else if(disX == 0 && disY == 0 && queue.size()==1) {
			lastPoint = queue.poll();
		}else if(disX == 0 && disY == 0 && queue.size() > 1) {
			lastPoint = queue.poll();
			disX = (posX) - (queue.peek().getX());
			disY = (posY) - (queue.peek().getY());
//			System.out.println(disX + "----" + disY);
		}else if(disX != 0) {
			posX += disX>0? -5: disX<0? 5 : 0;
			disX = (posX) - (queue.peek().getX());
//			System.out.println(disX + "----" + disY);
		
		}else if(disY != 0) {
			posY += disY>0? -5: disY<0? 5 : 0;
			disY = (posY) - (queue.peek().getY());			
		}
		
		
		
//		if (queue.isEmpty()) {
//			inMove = false;
//		}else if (posX == queue.peek().getX() && posY == queue.peek().getY()) {
//			lastPoint = queue.poll();
//		}else if (posX != queue.peek().getX()){
//			/*if (!map[(posX+5)][posY] || !map[(posX-5)][posY]) {
//				lastPoint = queue.poll();
//			}else */if (posX < queue.peek().getX()) {
//				posX+=5;
//			}else {
//				posX -=5;
//			}
//		}else {
//			/*if (!map[(posX)][(posY+5)] || !map[(posX)][(posY-5)]) {
//				lastPoint = queue.poll();
//			}else*/ if (posY < queue.peek().getY()) {
//				posY+=5;
//			}else {
//				posY -=5;
//			}
//		}
////		System.out.println(posX+","+posY);
	}
	
	public synchronized void move(List<Point> list) {
		queue = new LinkedList<>();
		if (list != null) {
			for(Point p : list) {
				queue.add(p);
			}
			move();
			inMove = true;
		}
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
