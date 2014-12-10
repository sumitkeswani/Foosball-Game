//mdm

package gameState;

import gui.BackGround;
import matchState.entities.Player;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import matchState.MatchBuilder;
import matchState.entities.AutomatedTeam;
import matchState.entities.Ball;
import matchState.entities.Line;
import matchState.entities.Team;
import matchState.entities.UserDefinedTeam;

public class MatchState extends GameState{
	
	//game state manager
	BackGround bg;
	
	//calling teams
	Team team1;
	Team team2;
	
	//Scores
	private Color titleColor;
	private Font titleFont;
	
	//threads for players
	Thread[] playerThreads = new Thread[22];
	Thread ballThread;
	
	private GameStateManager gsm;
	BufferedImage playerBlueImage;
	BufferedImage playerRedImage;
	Line2D.Double[] l;
	BufferedImage ballImage;
	Ball ball;
	
	
	ArrayList<Player> playerList;

	
	public MatchState(GameStateManager gsm){
		this.gsm = gsm;
		bg = new BackGround("/Foosball.png");
		this.playerList = new ArrayList<Player>();
	};

	@Override
	public void init() {
		// TODO Auto-generated method stub		
		team1 = this.gsm.getBuilder().getMatch().teamA;
		team2 = this.gsm.getBuilder().getMatch().teamB;

		try {
			this.ballImage = ImageIO.read(new File("Resources/ball.png"));
			this.playerBlueImage = ImageIO.read(new File("Resources/BluePlayer.png"));
			this.playerRedImage = ImageIO.read(new File("Resources/RedPlayer.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ball = Ball.getInstance();
		
		//start ball
		ballThread = new Thread(ball);
		ballThread.start();
		
		//adding team1 in playerList
		playerList.addAll(team1.keepLine.getPlayers());
		playerList.addAll(team1.defLine.getPlayers());
		playerList.addAll(team1.midfieldLine.getPlayers());
		playerList.addAll(team1.attackLine.getPlayers());
	
		
		//adding team2 players in arraylist
		playerList.addAll(team2.keepLine.getPlayers());
		playerList.addAll(team2.defLine.getPlayers());
		playerList.addAll(team2.midfieldLine.getPlayers());
		playerList.addAll(team2.attackLine.getPlayers());
				
		//players started
		for(int i=0;i<22;i++){
			playerThreads[i] = new Thread(playerList.get(i));
		}		
		for(int i=0;i<22;i++){
			playerThreads[i].start();
		}
		
		titleColor = new Color(255, 150, 150);
		titleFont = new Font("Monotype Corsiva", Font.PLAIN, 15);
		
		
	}
	
	@Override
	public void draw(Graphics2D g) {

		
		//System.out.println("you are here");
		// TODO Auto-generated method stub
		
		bg.draw(g);
		
		g.setStroke(new BasicStroke(5));

		this.gsm.getBuilder().getMatch().ball.draw(g, ballImage, gsm.getMyPanel());

		team1.keepLine.drawLine(g);
		team1.defLine.drawLine(g);
		team1.midfieldLine.drawLine(g);
		team1.attackLine.drawLine(g);
		
		team2.keepLine.drawLine(g);
		team2.defLine.drawLine(g);
		team2.midfieldLine.drawLine(g);
		team2.attackLine.drawLine(g);
		
		team1.keepLine.drawPlayer(g, playerRedImage, this.gsm.getMyPanel());
		team1.defLine.drawPlayer(g, playerRedImage, this.gsm.getMyPanel());
		team1.midfieldLine.drawPlayer(g, playerRedImage, this.gsm.getMyPanel());
		team1.attackLine.drawPlayer(g, playerRedImage, this.gsm.getMyPanel());
		
		team2.keepLine.drawPlayer(g, playerBlueImage, this.gsm.getMyPanel());
		team2.defLine.drawPlayer(g, playerBlueImage, this.gsm.getMyPanel());
		team2.midfieldLine.drawPlayer(g, playerBlueImage, this.gsm.getMyPanel());
		team2.attackLine.drawPlayer(g, playerBlueImage, this.gsm.getMyPanel());
		
		
		g.drawString("ManU : "+ Integer.toString(gsm.getBuilder().getMatch().teamA.score) , 400, 40);
		g.drawString("Chelsea : "+ Integer.toString(gsm.getBuilder().getMatch().teamB.score) , 500, 40);
	
		if(ball.getInstance().position.x + 15 > 990 && (ball.getInstance().position.y >=240 && ball.getInstance().position.y <= 440)){	
			gsm.getBuilder().getMatch().teamA.score += 1;
			ball.position.x = 950;
			ball.position.y = 335;
			ball.setDx(-12);
			ball.setDy(0);
		}
		if(ball.getInstance().position.x - 15 < 10 && (ball.getInstance().position.y >=240 && ball.getInstance().position.y <= 440)){
			gsm.getBuilder().getMatch().teamB.score += 1;
			ball.position.x = 50;
			ball.position.y = 335;
			ball.setDx(12);
			ball.setDy(0);
		}
		
		/*if(gsm.getBuilder().getMatch().teamA.score >= 5 || gsm.getBuilder().getMatch().teamB.score >= 5){
			g.drawString("Game OVER!!", 400, 300);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}*/
	}
	
	

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
		if(k == KeyEvent.VK_UP)
		{
			team1.attackLine.moveUp();
			team1.defLine.moveUp();
			team1.midfieldLine.moveUp();
			team1.keepLine.moveUpKeeper();
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			team1.attackLine.moveDown();
			team1.defLine.moveDown();
			team1.midfieldLine.moveDown();
			team1.keepLine.moveDownKeeper();
		}
		if(k == KeyEvent.VK_W)
		{
			team2.attackLine.moveUp();
			team2.defLine.moveUp();
			team2.midfieldLine.moveUp();
			team2.keepLine.moveUpKeeper();
		}
		if(k == KeyEvent.VK_S)
		{
			team2.attackLine.moveDown();
			team2.defLine.moveDown();
			team2.midfieldLine.moveDown();
			team2.keepLine.moveDownKeeper();
		}
		
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}	
}
