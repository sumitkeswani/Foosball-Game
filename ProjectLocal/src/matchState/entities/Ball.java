//mdm

package matchState.entities;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;











import matchState.Match;
//import entities.CoOrdinates;
import ObserverSystem.Observable;
import ObserverSystem.Observer;
import ObserverSystem.event.CollisionEvent;
import ObserverSystem.event.Event;
import gui.MyPanel;

public class Ball implements Runnable, Observable{
	
	Map<Class,List<Observer>> eventMap = new HashMap<Class, List<Observer>>();
	private BufferedImage image;
	public CoOrdinates position;
	public Player lastPlayer;
	private int xSpeed;
	private int ySpeed;
	private int resultSpeed;
	private double dx;
	double dy;
	private boolean isMoving;
	
	//new event
	Event newEvent;
	
	//For singleton
	static Ball ball = new Ball();
	
	private Ball(){
		
		this.isMoving = true;
		this.dx = 2;
		this.dy = 2;
		this.position = new CoOrdinates(110, 110);	
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isMoving)
		{
			try
			{
				Thread.sleep(50);	
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//Have to calculate new position
		    double oldx =  this.position.x;
			double oldy =  this.position.y;
			double newx = oldx + dx;
			
			//Radius assumed to be 30
			if(newx + 30 > MyPanel.WIDTH || newx<0)
				dx = -dx;
			double newy = oldy + dy ;
			if(newy + 30 > MyPanel.HEIGHT || newy<0)
				dy = -dy;
			
			if(dx < 5 && dx > 0){
				dx = 5;
			}
			
			//ball.setFrame(position.x,position.y , size, size); 
			this.position.setX(newx);
			this.position.setY(newy);
			
			
		}

	}
	public void draw(Graphics2D g2d , BufferedImage img , MyPanel panel)
	{
		g2d.drawImage(img,(int)position.x - 10,(int)position.y - 20 , 30, 20, null);
	}
	
	public static Ball getInstance(){
		return ball;
	}
	
	public void setCoOrdinates(double x, double y){
		this.position.x = x;
		this.position.y = y;
	}

	public void setDx(int x){
		this.dx = x;
	}
	
	public void setDy(int y){
		this.dy = y;
	}
	public void deflect(CoOrdinates target, Team t){
		
		double beta = Math.atan(((target.y) - this.position.y)/(target.x - this.position.x));
		dx = 12*Math.cos(beta);
		
		if(dx==0.0)
			dx = 10;
		dy = 12*Math.sin(beta);
		
		if(t.getName().equals("Chelsea")){
			dx = -dx;
			dy = -dy;
		}
		
		if(dy==0.0)
			dy = 10;
	}

	@Override
	public void addObserver(Observer observer, Class<?> eventClass) {
		// TODO Auto-generated method stub
		List<Observer> list = eventMap.get(eventClass);
		if(list==null){
			list = new ArrayList<Observer>();
			eventMap.put(eventClass, list);
		}
		list.add(observer);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		List<Observer> list = eventMap.get(newEvent.getClass());
		if(list!=null){
			for(Observer observer : list){
				observer.handleEvent(newEvent);
			}
		}
	}

}