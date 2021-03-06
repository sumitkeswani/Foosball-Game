//mdm

package matchState.entities;

import gui.MyPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import matchState.entities.Interfaces.AttackerLine;
import matchState.entities.Interfaces.DefenderLine;
import matchState.entities.Interfaces.GoalKeeperLine;
import matchState.entities.Interfaces.MidFielderLine;



public class Line implements GoalKeeperLine, DefenderLine, AttackerLine, MidFielderLine {
	
	private static int scaleSpeed = 4;
	private static final int upperLimit = 0;
	private static final int lowerLimit = 1000;
	ArrayList<Player> list;
	private double verticalSpeed;
	private int noOfPlayers;
	private double dy;
	public CoOrdinates start;
	public CoOrdinates end;
	private Line2D.Double line; 
	
	
	private Team team;
	
	
	public Line(int noOfPlayers, CoOrdinates starting ,CoOrdinates ending, Team team){
		this.noOfPlayers = noOfPlayers;
		this.list = new ArrayList<Player>(noOfPlayers);
		this.start = new CoOrdinates(starting.x, starting.y);
		this.end = new CoOrdinates(ending.x, ending.y);
		line = new Line2D.Double(start.x, start.y, end.x, end.y);
		this.team = team;
		this.addPlayers();
		
	}
	
	public void drawLine(Graphics2D g2d)
	{
		if(line!=null)
		{
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.draw(line);
		}
	}
	
	public void addPlayers()
	{
		for(int i =0 ;i <noOfPlayers ; i++)
		{
			double ordinate;
			ordinate = this.start.y+((this.end.y-this.start.y)/(noOfPlayers+1))*(i+1);
			CoOrdinates position = new CoOrdinates(this.start.x,ordinate);
			Player player = new Player("Player"+i, position, this, team);
			
			list.add(player);
			//player.run();
		}
		
	}

	
	public void drawPlayer(Graphics g, BufferedImage img , MyPanel panel)
	{
		if(this!=null)
		{
			//System.out.println(img.getTileHeight() + " " + img.getTileWidth());
			
			
				for(int j= 0; j < list.size() ;j++)
				{
					
					g.drawImage(img,(int)list.get(j).position.x - 10,(int)list.get(j).position.y - 20 , panel);
				}
			
			
		}
	}
	
	public void moveUp() {
		// TODO Auto-generated method stub
		
		//	double min = list.get(0).position.y;
		
			for(int j=0; j< list.size() ; j++){
				
				if(list.get(j).position.y - dy*scaleSpeed > 0){
					list.get(j).position.y -= dy*scaleSpeed;
				}
					
				else{
					list.get(j).position.y = 0;
					break;
				}
			}
	}

	public void moveUpKeeper() {
		// TODO Auto-generated method stub
		
			//double min = list.get(0).position.y;
		
			
				if(list.get(0).position.y - dy*scaleSpeed > 155){
					list.get(0).position.y -= dy*scaleSpeed;
				}
					
				else{
					list.get(0).position.y = 155;
				}
			
	}
	
	public void moveDownKeeper() {
		// TODO Auto-generated method stub
		
			//double min = list.get(0).position.y;
		
			
				if(list.get(0).position.y + dy*scaleSpeed < 520){
					list.get(0).position.y += dy*scaleSpeed;
				}
					
				else{
					list.get(0).position.y = 520;
				}
			
	}
	
	public void moveDown() {
		// TODO Auto-generated method stub
		
		//	double min = list.get(0).position.y;
		
			for(int j=list.size()-1; j >= 0 ; j--){
				
				if(list.get(j).position.y + dy*scaleSpeed < 670){
					list.get(j).position.y += dy*scaleSpeed;
				}
					
				else{
					list.get(j).position.y = 670;
					break;
				}
			}
	}

	
	public int getNoOfPlayers(){
		return this.noOfPlayers;
	}
	
/*	private void setLinePositions(){
		if(this.team instanceof )
		if(this instanceof GoalKeeperLine){
			
		}
		else if(this instanceof DefenderLine){
			
		}
		else if(this instanceof AttackerLine){
			
		}
		else if(this instanceof MidFielderLine){
	
		}
	}*/
	
	
	public void setDy(double d){
		this.dy = d;
	}

	public CoOrdinates getStart(){
		return this.start;
	}
	
	public ArrayList<Player> getPlayers(){
		return this.list;
	}
	
}
