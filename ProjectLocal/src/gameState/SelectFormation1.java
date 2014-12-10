//mdm

package gameState;

import gui.BackGround;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

import matchState.entities.UserDefinedTeam;

public class SelectFormation1 extends GameState{
	GameStateManager gsm;
	BackGround bg;
	
	private String[] options1 = {"3", "4", "5","6"};
	private String[] options2 = {"2","3", "4", "5","6"};
	private String[] options3 = {"1", "2", "3","4"};
	
	private int sum;
	private int optionPointer1;
	private int optionPointer2;
	private int optionPointer3;
	private int ptr;
	private int flag;
	
	//For team 1
	private int noOfDefenders;
	private int noOfAttackers;
	private int noOfMidFielders;
	
	//Strings
	Color titleColor;
	Font titleFont;
	Font font;
	Font font1;
	
	public SelectFormation1(GameStateManager gsm){
		this.gsm = gsm;
		bg = new BackGround("/Foosball.png");	
	}
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		titleColor = new Color(255, 150, 150);
		titleFont = new Font("Monotype Corsiva", Font.PLAIN, 50);
		font = new Font("Times New Roman", Font.PLAIN, 40);
		font1 = new Font("Times New Roman", Font.PLAIN, 25);
	    sum=0;	
	    ptr=0;
	    optionPointer1 = 0;
	    optionPointer2 = -1;
	    optionPointer3 = -1;		
	    flag = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
	
		g.setColor(titleColor);
		g.setFont(titleFont);
		if(this.gsm.getBuilder().getMatch().teamA instanceof UserDefinedTeam)
			g.drawString("Select Formation for team1", 200, 200);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options1.length; i++) {
			if(i == optionPointer1) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options1[i], 350, 300 + i * 45);
		}
		
		for(int i = 0; i < options2.length; i++) {
			if(i == optionPointer2) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options2[i], 500, 300 + i * 45);
		}
		for(int i = 0; i < options3.length; i++) {
			if(i == optionPointer3) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options3[i], 650, 300 + i * 45);
		}
		
		if(ptr == 4)
		{
			if( sum == 10)
			{
				flag = 1;
				g.setFont(font1);
				g.setColor(Color.CYAN);
				g.drawString("Press Enter to continue", 370, 550);
			}
			else{
				flag = 2;
				g.setFont(font1);
				g.drawString("Wrong formation!!\nPress Enter to try again", 250, 550);
			}
		}
		
		
	}

	@Override
	public void keyPressed(int k) {
		if(flag == 1){
			if(k == KeyEvent.VK_ENTER){
				gsm.getBuilder().Team1makeLines(gsm.getBuilder().getMatch().teamA, noOfDefenders, noOfMidFielders, noOfAttackers);
				gsm.setState(3);
			}
		}
		
		else if(flag == 2){
			if(k == KeyEvent.VK_ENTER){
				gsm.setState(2);
			}
		}
		
		else if(ptr == 0){
			if(k == KeyEvent.VK_ENTER){
				if(optionPointer1 == 0){
					sum = sum + 3;
					ptr = 1;
					noOfDefenders = 3;
				}
				else if(optionPointer1 == 1){
					sum = sum + 4;
					ptr = 1;
					noOfDefenders = 4;
				}
				else if(optionPointer1 == 2){
					sum = sum + 5;
					ptr = 1;
					noOfDefenders = 5;
				}
				else if(optionPointer1 == 3){
					sum = sum + 6;
					ptr = 1;
					noOfDefenders = 6;
				}
				optionPointer2 = 0;
			}
			if(k == KeyEvent.VK_DOWN){
				if(optionPointer1 == 3){
					optionPointer1 = 0;
				}
				else{
					optionPointer1+=1;
				}
			}
			if(k == KeyEvent.VK_UP){
				if(optionPointer1 == 0){
					optionPointer1 = 3;
				}
				else{
					optionPointer1-=1;
				}
			}
			
		}
		
		else if(ptr == 1){
			if(k == KeyEvent.VK_ENTER){
				if(optionPointer2 == 0){
					sum = sum + 2;
					ptr = 2;
					noOfMidFielders = 2;
				}
				else if(optionPointer2 == 1){
					sum = sum + 3;
					ptr = 2;
					noOfMidFielders = 3;
				}
				else if(optionPointer2 == 2){
					sum = sum + 4;
					ptr = 2;
					noOfMidFielders = 4;
				}
				else if(optionPointer2 == 3){
					sum = sum + 5;
					ptr = 2;	
					noOfMidFielders = 5;
				}
				else if(optionPointer2 == 4){
					sum = sum + 6;
					ptr = 2;
					noOfMidFielders = 6;
				}
				optionPointer3 = 0;
			}
			if(k == KeyEvent.VK_DOWN){
				if(optionPointer2 == 4){
					optionPointer2 = 0;
				}
				else{
					optionPointer2+=1;
				}
			}
			if(k == KeyEvent.VK_UP){
				if(optionPointer2 == 0){
					optionPointer2 = 4;
				}
				else{
					optionPointer2-=1;
				}
			}
			
		}
		
		else if(ptr == 2){
			if(k == KeyEvent.VK_ENTER){
				if(optionPointer3 == 0){
					sum = sum + 1;
					ptr = 4;
					noOfAttackers = 1;
				}
				else if(optionPointer3 == 1){
					sum = sum + 2;
					ptr = 4;
					noOfAttackers = 2;
				}
				else if(optionPointer3 == 2){
					sum = sum + 3;
					ptr = 4;
					noOfAttackers = 3;
				}
				else if(optionPointer3 == 3){
					sum = sum + 4;
					ptr = 4;
					noOfAttackers = 4;
				}
	
			}
			if(k == KeyEvent.VK_DOWN){
				if(optionPointer3 == 3){
					optionPointer3 = 0;
				}
				else{
					optionPointer3+=1;
				}
			}
			if(k == KeyEvent.VK_UP){
				if(optionPointer3 == 0){
					optionPointer3 = 3;
				}
				else{
					optionPointer3-=1;
				}
			
			}
		}
	}		
	

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
}
