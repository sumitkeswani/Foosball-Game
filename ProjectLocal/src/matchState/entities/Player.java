package matchState.entities;



import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.text.Position;

import matchState.entities.Interfaces.AttackerLine;
import matchState.entities.Interfaces.DefenderLine;
import matchState.entities.Interfaces.GoalKeeperLine;
import matchState.entities.Interfaces.MidFielderLine;
import ObserverSystem.Observable;
import ObserverSystem.Observer;
import ObserverSystem.event.CollisionEvent;
import ObserverSystem.event.Event;



public class Player implements Runnable, Observer{

	private Event newEvent;
	public String name;
	private Team team;
	CoOrdinates position;
	private Line line;
	private double minKickSpeed;
	private double maxKickSpeed;
	private BufferedImage playerImage = null;
	private Ball ball;
	
	public void setPosition(double x, double y){
		position.setX(x);
		position.setY(y);
	}
	

	public Player(String name,CoOrdinates position ,Line line, Team team)
	{
		this.name = name;
		this.position = new CoOrdinates(position.x,position.y);
		this.line = line;
		this.minKickSpeed =(int)Math.random()*40+1;
		this.maxKickSpeed = (int)Math.random()*40+50;
		this.team = team;
		
		this.ball = Ball.getInstance();
	}


	@Override
	public void run() {
		startObserving(ball);
		
		while(true){
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if(checkForCollision()){
				this.handleEvent(ball.newEvent);
			}
			
			if(this.team instanceof AutomatedTeam){
				if(ball.dy > 0){
					this.line.moveUp();
				}
				else
					this.line.moveDown();
			}
		}
		
	}


	private boolean checkForCollision() {
		// TODO Auto-generated method stub
		if((ball.position.x <= this.position.x + 20) && (ball.position.x >= this.position.x - 20) && (ball.position.y <= this.position.y + 20) && (ball.position.y >= this.position.y - 20)){
			ball.lastPlayer = this;
			ball.newEvent = new CollisionEvent(ball);
			return true;
		
		}
		return false;
	}


	@Override
	public void startObserving(Observable observable) {
		// TODO Auto-generated method stub
		observable.addObserver(this, CollisionEvent.class);
	}


	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	//	System.out.println("I am handling events");
		kickBall();
		
	}


	private void kickBall() {
		// TODO Auto-generated method stub
		if(this.line instanceof GoalKeeperLine){
			CoOrdinates p = this.team.defLine.getPlayers().get((int)(Math.random()*this.team.defLine.getNoOfPlayers())).position;
			ball.deflect(p, this.team);
		
		}
		else if(this.line instanceof DefenderLine){
			CoOrdinates p = this.team.midfieldLine.getPlayers().get((int)(Math.random()*this.team.midfieldLine.getNoOfPlayers())).position;
			ball.deflect(p, this.team);
		}
		else if(this.line instanceof MidFielderLine){
			int n = (int)(Math.random()*2);
			if(n == 0){			//while passing
				CoOrdinates p = this.team.attackLine.getPlayers().get((int)(Math.random()*this.team.attackLine.getNoOfPlayers())).position;
				ball.deflect(p, this.team);
			}
			else{
				if(this.team.getName().equals("ManU")){
					CoOrdinates p = new CoOrdinates(1000, (int)(240 + Math.random()*190));
					ball.deflect(p, this.team);
				}
				else{
					CoOrdinates p = new CoOrdinates(0, (int)(240 + Math.random()*190));
					ball.deflect(p, this.team);
				}
			}
		}
		else if(this.line instanceof AttackerLine){
			if(this.team.getName().equals("ManU")){
				CoOrdinates p = new CoOrdinates(1000, (int)(240 + Math.random()*190));
				ball.deflect(p, this.team);
			}
			else{
				CoOrdinates p = new CoOrdinates(0, (int)(240 + Math.random()*190));
				ball.deflect(p, this.team);
			}
		}		
	}
}

	