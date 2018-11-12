package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Game {
	
	private boolean[][] array;
	
	public boolean checkMove(double e, double d) {
		int x = (int)e;
		int y = (int)d;
		System.out.println(array[x][y] && array[x + 16][y] && array[x + 16][y + 18] && array[x][y + 18]);
		return array[x][y] && array[x + 16][y] && array[x + 16][y + 18] && array[x][y + 18];
	}

	public Game() {
		try {
			File image = new File("data/Map_B&N.png");
			BufferedImage img = ImageIO.read(image);
			int width = img.getWidth();
			int height = img.getHeight();
			array = new boolean[width][height];
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					int p = img.getRGB(i,j);
					int a = (p>>24)&0xff;
					int r = (p>>16)&0xff;
					int g = (p>>8)&0xff;
					int b = p&0xff;
					int avg = (r+g+b)/3;
					array[i][j] = avg < 20;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
