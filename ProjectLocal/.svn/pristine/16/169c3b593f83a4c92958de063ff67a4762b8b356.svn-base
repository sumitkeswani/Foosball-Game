//mdm

package gameState;

import gui.BackGround;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import matchState.entities.AutomatedTeam;
import matchState.entities.Ball;
import matchState.entities.CoOrdinates;
import matchState.entities.UserDefinedTeam;

public class TossState extends GameState{

	CoOrdinates position;
	
	GameStateManager gsm;
	BackGround bg;
	BufferedImage img;
	
	//for random no(toss)
	int x;
	
	public TossState(GameStateManager gsm){
		
		try {
			bg = new BackGround("/Foosball.png");
			img =  ImageIO.read(new File("Resources/unnamed.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.gsm=gsm;
		
		
	}
	Color resultColor;
	Font resultFont;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		x = (int)(Math.random()*2);
		if(x == 0){
			position = new CoOrdinates(111, 335);
		}
		else if(x == 1){
			position = new CoOrdinates(889, 335);
		}
		else
		{
			position = new CoOrdinates(500, 335);
		}
		
		resultColor = new Color(255, 150, 150);
		resultFont = new Font("Monotype Corsiva", Font.PLAIN, 70);
		
		
	}

/*	@Override
	public void update() {
		// TODO Auto-generated method stub
		bg.update();
	}*/

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		//icon.drawImageIcon(icon, 1000, 670, null);
		//icon.paintIcon((Component) icon, g, 1000, 670);
		g.drawImage(img, 240, 235, null);
		g.setColor(resultColor);
		g.setFont(resultFont);
		
		if(x == 0){
			g.drawString("Player1 won the Toss!!", 80, 200);
			
		}
		else{
			if(gsm.getBuilder().getMatch().teamB instanceof UserDefinedTeam){
				g.drawString("Player2 won the Toss!!", 80, 200);
			}
			else if(gsm.getBuilder().getMatch().teamB instanceof AutomatedTeam){
				g.drawString("You lost the Toss!!", 150, 200);
			}
			else{
				g.drawString("Fuck!!", 150, 200);
			}
		}
		
		g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		g.drawString("Press Enter to Play!!", 400, 600);

	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_ENTER){
			gsm.getBuilder().makeBall().getMatch().ball.setCoOrdinates(position.getX(), position.getY());
			gsm.getBuilder().getMatch().level.setdx();
			gsm.getBuilder().getMatch().level.setdy();
			gsm.setState(5);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
}
