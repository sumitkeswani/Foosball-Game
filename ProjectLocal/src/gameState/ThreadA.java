package gameState;
import matchState.entities.Team;

//mdm

public class ThreadA implements Runnable{

	Team team1;
	
	public ThreadA(Team team1)
	{
       this.team1 = team1;		
	}

	public void run() {
		
		System.out.println("you are here");
		
		team1.attackLine.moveUp();
		team1.defLine.moveUp();
		team1.midfieldLine.moveUp();
		//team1.keepLine.moveUp();	
		
	}
	
	

}
