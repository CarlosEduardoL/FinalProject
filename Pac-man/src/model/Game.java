package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import graphTAD.Edge;
import graphTAD.Graph;
import graphTAD.TheGraph;

public class Game {

	public static final int SCREEN_HEIGHT = 530;
	public static final int SCREEN_WEIGHT = 530;
	public static final int BLOCK_SIZE = 25;

	private boolean[][] map;
	private TheGraph<String, Point> graph;
	private Ghost phanton;
	private PacMan[] pacMans;
	private Ranking rank;
	private boolean inGame;

	public Game() {
		rank = new Ranking();
		pacMans = new PacMan[4];
	}

	public Ghost getPhanton() {
		return phanton;
	}
	
	public void printScore() {
		List<Score> s = rank.getScores();
		for (Score score : s) {
			System.out.println(score.getName() + " " + score.getScore());
		}
	}

	public void movePacmans() {
		for (int i = 0; i < pacMans.length; i++) {
			if (pacMans[i].isInMove()) {
				pacMans[i].move();
				Rectangle phantonRectangle = new Rectangle((int)phanton.getPosX(),(int) phanton.getPosY(), 25, 25);
				Rectangle pacmanrectangle = new Rectangle(pacMans[i].getPosX(), pacMans[i].getPosY(), 25, 25);
				
				if (phantonRectangle.intersects(pacmanrectangle)) {
					phanton.kill();
					if (phanton.getLifes() == 0) {
						inGame = false;
						String name = null;
						while(name==null) {
							name = JOptionPane.showInputDialog("put your name");
						}
						rank.addNewScore(name, phanton.getScore());
					}
				}
			}else {
				List<Point> list = graph.shortesPath(pacMans[i].getLastPoint().getIdentificador(), phanton.getMark().getIdentificador());
				pacMans[i].move(list);
			}
		}
	}

	public int[] posPacman(int i){
		int[] a = {pacMans[i].getPosX(),pacMans[i].getPosY()};
		return a;
	}

	public void movePhantom(double e, double d) {
		phanton.move(e, d);
		phanton.putPoints();
	}

	public boolean checkMove(double e, double d) {
		double x = (e/BLOCK_SIZE);
		double y = (d/BLOCK_SIZE);
		return x>-1 && x+0.9 <SCREEN_HEIGHT/BLOCK_SIZE && y>-1 && y+0.9 <SCREEN_HEIGHT/BLOCK_SIZE &&
				map[(int) x][(int) y] && map[(int) (x+0.80)][(int) (y+0.9)]
						&& map[(int) (x+0.80)][(int) y] && map[(int) x][(int) (y+0.9)];
	}
	private int[][] makeMap(){
		graph = new Graph<>(false);
		phanton = null;
		for (int j = 0; j < pacMans.length; j++) {
			pacMans[j]=null;
		}
		int[][] image = new int[SCREEN_HEIGHT][SCREEN_WEIGHT];
		map = new boolean[SCREEN_HEIGHT/BLOCK_SIZE][SCREEN_WEIGHT/BLOCK_SIZE];


		for (int i = 0; i < image.length-BLOCK_SIZE; i+=BLOCK_SIZE) {

			for (int j = 0; j < image[0].length-BLOCK_SIZE; j+=BLOCK_SIZE) {
				int x = i/BLOCK_SIZE;
				int y = j/BLOCK_SIZE;
				boolean wall = Math.random() < 0.15;
				map[x][y] = !wall;

				for (int k = i; k < i + BLOCK_SIZE; k++) {
					for (int k2 = j; k2 < j + BLOCK_SIZE; k2++) {
						image[k][k2] = wall? 233:0;
					}
				}
			}
		}

		makeGraph(image);

		if (graph.isConnected()) {
			graph.shortesPathWeight("", "");
		}else {
			return makeMap();
		}
		return image;
	}

	private void makeGraph(int[][] image) {
		for (int i = 0; i < image.length-BLOCK_SIZE; i+=BLOCK_SIZE) {
			for (int j = 0; j < image[0].length-BLOCK_SIZE; j+=BLOCK_SIZE) {
				int x = i/BLOCK_SIZE;
				int y = j/BLOCK_SIZE;
				if (map[x][y]) {
					Point point = new Point(i,j);
					point.setIdentificador(i+","+j);
					graph.addNode(point.getIdentificador(), point);
					if (i > 0 && map[x-1][y]) {
						Point p2 = new Point(i-BLOCK_SIZE, j);
						p2.setIdentificador((i-BLOCK_SIZE) +","+(j));
						graph.addEdge(p2.getIdentificador(), p2, point.getIdentificador(), point);
					}
					if (j > 0 && map[x][y-1]) {
						Point p2 = new Point(i, j-BLOCK_SIZE);
						p2.setIdentificador((i) +","+(j-BLOCK_SIZE));
						graph.addEdge(p2.getIdentificador(), p2, point.getIdentificador(), point);
					}
					if (i%2==0 && j%3==0 && i!=0 && j !=0) {
						boolean parar =false;
						for (int k = 0; k < pacMans.length && !parar; k++) {
							if (pacMans[k] == null) {
								pacMans[k] = new PacMan(i,j);
								pacMans[k].setLastPoint(point);
								parar = true;
							}
						}
					}else if (phanton == null) {
						phanton = new Ghost(8, "Carlos");
						phanton.setPosY(j);
						phanton.setPosX(i);
						phanton.setMark(point);
					}
				}
			}
		}
	}

	public void makeImage() {

		int color[][] = new int[530][530];
		color= makeMap();
		String path = "data/newMap.png";
		BufferedImage image2 = new BufferedImage(color.length, color[0].length, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < color.length; x++) {
			for (int y = 0; y < color[0].length; y++) {
				image2.setRGB(x, y, color[x][y]);
			}
		}

		File ImageFile = new File(path);
		try {
			ImageIO.write(image2, "png", ImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

}