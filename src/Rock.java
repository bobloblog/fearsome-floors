public class Rock
{
	private int x, y;
	private Floor floor;
	public Rock(int thisX, int thisY, Floor board)
	{
		x = thisX;
		y = thisY;
		floor = board;
		board.set(x, y, this);
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setCoordinates(int thisX, int thisY)
	{
		x = thisX;
		y = thisY;
	}
	
	public Object getObject(int face)
	{
		switch (face) 
		{
			case 1: 
				return getNorth();
			case 2:
				return getEast();
			case 3:
				return getSouth();
			case 4:
				return getWest();
		}
		return null;
	}
	
	private Object getWest()
	{
		return floor.getObjectAt(x - 1, y);
	}
	private Object getNorth()
	{
		return floor.getObjectAt(x, y - 1);
	}
	private Object getSouth()
	{
		return floor.getObjectAt(x, y + 1);
	}
	private Object getEast()
	{
		return floor.getObjectAt(x + 1, y);
		
	}
}
