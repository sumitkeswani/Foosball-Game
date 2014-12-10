package matchState;

import matchState.entities.Ball;
import matchState.entities.CoOrdinates;
import matchState.entities.AutomatedTeam;
import matchState.entities.Line;
import matchState.entities.Team;
import matchState.entities.UserDefinedTeam;
import matchState.entities.Interfaces.DefenderLine;

public class MatchBuilder {
	private static MatchBuilder instance = new MatchBuilder();
	private Match match;
	private int constructedTeams = 0;
	
	private MatchBuilder(){
	
		this.match = new Match();
	}
	
	public static MatchBuilder getBuilderInstance(){
		return instance;
	}
	
	public Match getMatch(){
		return this.match;
	}
	
	public MatchBuilder makeMultiPlayerTeams(String nameA, String nameB){
		match.teamA = new UserDefinedTeam(nameA);
		match.teamB = new UserDefinedTeam(nameB);
		return this;
	}
	
	public MatchBuilder makeSinglePlayerTeams(String nameA, String nameB){
		match.teamA = new UserDefinedTeam(nameA);
		match.teamB = new AutomatedTeam(nameB);
		return this;
	}
	
	public MatchBuilder Team1makeLines(Team team, int def, int mid, int attack){
		CoOrdinates s0 = new CoOrdinates(75,0);
		CoOrdinates s1 = new CoOrdinates(75+121.4*2,0);
		CoOrdinates s2 = new CoOrdinates(75+121.4*4,0);
		CoOrdinates s3 = new CoOrdinates(75+121.4*6,0);
		CoOrdinates e0 = new CoOrdinates(75,670);
		CoOrdinates e1 = new CoOrdinates(75+121.4*2,670);
		CoOrdinates e2 = new CoOrdinates(75+121.4*4,670);
		CoOrdinates e3 = new CoOrdinates(75+121.4*6,670);
		
		team.setLines(new Line(1, s0, e0, team), new Line(def, s1, e1, team), new Line(mid, s2, e2, team), new Line(attack, s3, e3, team));
		return this;
		
	}
	
	public MatchBuilder Team2makeLines(Team team, int def, int mid, int attack){
		CoOrdinates s0 = new CoOrdinates(925,0);
		CoOrdinates s1 = new CoOrdinates(75+121.4*5,0);
		CoOrdinates s2 = new CoOrdinates(75+121.4*3,0);
		CoOrdinates s3 = new CoOrdinates(75+121.4*1,0);
		CoOrdinates e0 = new CoOrdinates(925,670);
		CoOrdinates e1 = new CoOrdinates(75+121.4*5,670);
		CoOrdinates e2 = new CoOrdinates(75+121.4*3,670);
		CoOrdinates e3 = new CoOrdinates(75+121.4*1,670);
		
		team.setLines(new Line(1, s0, e0, team), new Line(def, s1, e1, team), new Line(mid, s2, e2, team), new Line(attack, s3, e3, team));
		return this;
		
	}
	
	public MatchBuilder addDifficultyLevel(DifficultyLevel level){
		match.level = level;
		return this;
	}
	
	public MatchBuilder makeBall(){
		
		this.match.ball = Ball.getInstance();
		return this;
		
	}
	
	
}
