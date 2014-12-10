//mdm

package matchState.entities;

import javax.swing.JComponent;

import matchState.DifficultyLevel;
import matchState.entities.Interfaces.AttackerLine;
import matchState.entities.Interfaces.DefenderLine;
import matchState.entities.Interfaces.GoalKeeperLine;
import matchState.entities.Interfaces.MidFielderLine;

public class Team implements Formation{
	
	private String name;
	public int score;
	private boolean wonToss = false;

	

	private double min;
	private double[] arr = new double[4];
	
	public DefenderLine defLine;
	public AttackerLine attackLine;
	public MidFielderLine midfieldLine;
	public GoalKeeperLine keepLine;
	
	
	
	public Team(String name){
		this.setName(name);
		this.score = 0;
	}
	
	public void setLines(GoalKeeperLine keepLine, DefenderLine defline, MidFielderLine midLine, AttackerLine attackLine){
		this.defLine=defline;
		this.attackLine = attackLine;
		this.midfieldLine = midLine;
		this.keepLine = keepLine;
		this.calcRatio();
		
	}
	
	
	public void calcRatio(){
		arr[0] = 500/(this.keepLine.getNoOfPlayers()+1);
		arr[1] = 1000/(this.defLine.getNoOfPlayers()+1);
		arr[2] = 1000/(this.midfieldLine.getNoOfPlayers()+1);
		arr[3] = 1000/(this.attackLine.getNoOfPlayers()+1);
		this.min = arr[0];
		
		for(int i = 1;i<4;i++){
			if(arr[i] < min){
				min = arr[i];
			}
		}
		
		for(int i=0;i<4;i++){
			arr[i] /= min;
			//System.out.println("i : " + arr[i]);
		}
		
		keepLine.setDy(arr[0]);
		defLine.setDy(arr[1]);
		midfieldLine.setDy(arr[2]);
		attackLine.setDy(arr[3]);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
