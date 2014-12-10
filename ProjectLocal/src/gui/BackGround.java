package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround {
	private BufferedImage image;
	private double x;
	private double y;
	
	
	public BackGround(String s){
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = 0;
		y = 0;
	}

	
	public void draw(Graphics2D g){
		g.drawImage(image, (int)x, (int)y, null);
	}
	
}
