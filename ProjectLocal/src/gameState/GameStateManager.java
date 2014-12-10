//mdm

package gameState;

//import gameState.MatchState.TestA;
//import gameState.MatchState.TestB;
import gui.MyPanel;

import java.util.ArrayList;

import matchState.MatchBuilder;

public class GameStateManager {
	private MatchBuilder builder;
	Thread T2;
	Thread T1;
	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int CONFIGSTATE = 1;
	public static final int FORMATION1 = 2;
	public static final int FORMATION2 = 3;
	public static final int TOSSSTATE = 4;
	public static final int MATCHSTATE = 5;
	
	
	private MyPanel panel;
	
	
	public GameStateManager(MyPanel myPanel){
		gameStates = new ArrayList<GameState>();
		//currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new ConfigState(this));
		gameStates.add(new SelectFormation1(this));
		gameStates.add(new SelectFormation2(this));
		gameStates.add(new TossState(this));
		gameStates.add(new MatchState(this));
		
		this.panel = myPanel;
		this.builder = MatchBuilder.getBuilderInstance();
		//currentState = 0;
		setState(0);
	}
	
	public MatchBuilder getBuilder(){
		return this.builder;
	}
	
	public void setState(int state){
		this.currentState = state;
		gameStates.get(currentState).init();
		/*if(currentState==4)
		{
			thread1 = new Thread1(this);
			thread2 = new Thread2(this);
			T1 = new Thread(thread1);
			T2 = new Thread(thread2);
			T1.start();
			T2.start();
			
		}*/
	}
	
/*	public void update(){
		gameStates.get(currentState).update();
	}*/
	
	public void draw(java.awt.Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	
	
	public void keyReleased(int k) {
		//System.out.println("here"+k);
		//if(currentState!=4){
		gameStates.get(currentState).keyReleased(k);
	    /*}
		else
		{
			thread1.keyReleased(k);
			thread2.keyReleased(k);
		}*/
	}
	public void keyPressed(int k) {
		//System.out.println("here"+k);
		// TODO Auto-generated method stub
		//if(currentState!=4){
		gameStates.get(currentState).keyPressed(k);
		/*}
		else
		{
			thread1.keyPressed(k);
			thread2.keyPressed(k);
		}*/
 	}
	
	public MyPanel getMyPanel(){
		return this.panel;
	}
}
