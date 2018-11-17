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
	
	private int[][] makeMap(){
		int[][] map = new int[530][530];
		
		for (int i = 0; i < map.length-20; i+=20) {
			for (int j = 0; j < map[0].length-22; j+=22) {
				boolean pared = Math.random() < 0.2;
				for (int k = i; k < i + 20; k++) {
					for (int k2 = j; k2 < j + 22; k2++) {
						map[k][k2] = pared? 233:0;
					}
				}
			}
		}
		
		
		return map;
	}

	public Game() {
		
		int color[][] = new int[530][530];
		color= makeMap();
		String path = "data/newMap.png";
		BufferedImage image2 = new BufferedImage(color.length, color[0].length, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < color.length; x++) {
			for (int y = 0; y < color[0].length; y++) {
				image2.setRGB(x, y, color[x][y]);
				System.out.println(color[x][y]);
			}
		}

		File ImageFile = new File(path);
		try {
			ImageIO.write(image2, "png", ImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			File image = new File("data/newMap.png");
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
					color[i][j] = avg;
				}
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
