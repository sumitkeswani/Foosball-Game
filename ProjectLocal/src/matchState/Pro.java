//mdm

package matchState;

public class Pro implements DifficultyLevel{

	Match match;
	 
	public Pro(Match match)
	{
		this.match = match;
	}
	public void setdx()
	{
	 match.ball.setDx(20);
	}
	
	public void setdy()
	{
		match.ball.setDy(20);
	}
	@Override
	public void setErrorMargin() {
		// TODO Auto-generated method stub
		match.error = 0;
	}
	
}
