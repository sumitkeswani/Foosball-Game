package matchState.entities;

public class CoOrdinates 
{
	public double x;
	public double y;
	
	public CoOrdinates(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getY(){
		return y;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
}
