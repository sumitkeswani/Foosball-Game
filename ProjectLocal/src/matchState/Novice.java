//mdm

package matchState;

public class Novice implements DifficultyLevel{
	
	Match match;
	
	public Novice(Match match)
	{
		this.match=match;
	}
	public void setdx()
	{
	 match.ball.setDx(5);
	}
	
	public void setdy()
	{
		match.ball.setDy(5);
	}
	@Override
	public void setErrorMargin() {
		// TODO Auto-generated method stub
		match.error = 4;
	}
	
}
