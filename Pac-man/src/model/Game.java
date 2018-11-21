package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

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

	public Game() {
		
		graph = new Graph<>(false);
		makeImage();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]?"1 ":"0 ");
			}
			System.out.println();
		}
	}
	
	public boolean checkMove(double e, double d) {
		double x = (e/BLOCK_SIZE);
		double y = (d/BLOCK_SIZE);
		return x>0 && x+0.9 <SCREEN_HEIGHT/BLOCK_SIZE && y>0 && y+0.9 <SCREEN_HEIGHT/BLOCK_SIZE &&
				map[(int) x][(int) y] && map[(int) (x+0.80)][(int) (y+0.9)]
						&& map[(int) (x+0.80)][(int) y] && map[(int) x][(int) (y+0.9)];
	}
	private int[][] makeMap(){
		int[][] image = new int[SCREEN_HEIGHT][SCREEN_WEIGHT];
		map = new boolean[SCREEN_HEIGHT/25][SCREEN_HEIGHT/25];
		for (int i = 0; i < image.length-BLOCK_SIZE; i+=BLOCK_SIZE) {
			
			for (int j = 0; j < image[0].length-BLOCK_SIZE; j+=BLOCK_SIZE) {
				boolean wall = Math.random() < 0.2;
				int x = i/BLOCK_SIZE;
				int y = j/BLOCK_SIZE;
				map[x][y] = !wall;
				if (!wall) {
					graph.addNode(""+i+j, new Point(i,j));
					if (i > 0 && map[x-1][y]) {
						graph.addEdge((i-1) +""+(j), new Point(i-1, j), ""+i+j, new Point(i, j));
					}
					if (j > 0 && map[x][y-1]) {
						graph.addEdge((i) +""+(j-1), new Point(i, j-1), ""+i+j, new Point(i, j));
					}
				}
				for (int k = i; k < i + BLOCK_SIZE; k++) {
					for (int k2 = j; k2 < j + BLOCK_SIZE; k2++) {
						image[k][k2] = wall? 233:0;
					}
				}
			}
		}
		
		
		return image;
	}

	private void makeImage() {

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

}
