package gameState;
import gui.BackGround;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{
	private BackGround bg;
	private String[] options = {"Start", "Help", "Quit"};
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private int ptr;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		this.ptr = 0;
		bg = new BackGround("/Foosball.png");
		//this.init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		titleColor = new Color(255, 150, 150);
		titleFont = new Font("Monotype Corsiva", Font.PLAIN, 70);
		font = new Font("Times New Roman", Font.PLAIN, 40);
	}

/*	@Override
	public void update() {
		// TODO Auto-generated method stub
	}*/

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			if(ptr == 0){
				gsm.setState(1);
			}
//			System.out.println("This is a random text");
			else if(ptr == 1){
				//Call Help state
			}
			else if(ptr == 2){
				//Close The window
				System.exit(0);
			}
		}
		else if(k == KeyEvent.VK_UP && ptr == 0){
			ptr = 2;
		}
		else if(k == KeyEvent.VK_UP){
			ptr = ptr -1;
		}
		else if(k == KeyEvent.VK_DOWN && ptr == 2){
			ptr = 0;
		}
		else if(k == KeyEvent.VK_DOWN){
			ptr = ptr + 1;
		}
		
		
	}
	@Override
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Foosball", 375, 200);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == ptr) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 450, 370 + i * 40);
		}
		
	}



	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
}
