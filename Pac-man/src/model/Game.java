package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Game {
	
	private boolean[][] map;
	
	public boolean checkMove(double e, double d) {
		int x = (int)e;
		int y = (int)d;
		return map[x][y] && map[x + 14][y] && map[x + 14][y + 16] && map[x][y + 16];
	}

	public Game() {
		try {
			File image = new File("data/Map_B&N.png");
			BufferedImage img = ImageIO.read(image);
			int width = img.getWidth();
			int height = img.getHeight();
			map = new boolean[width][height];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					int p = img.getRGB(i,j);
					int a = (p>>24)&0xff;
					int r = (p>>16)&0xff;
					int g = (p>>8)&0xff;
					int b = p&0xff;
					int avg = (r+g+b)/3;
					map[i][j] = avg < 20;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
