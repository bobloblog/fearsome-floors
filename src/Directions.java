public class Directions {
	
	final private int leftAndRight = 2;
	final private int possibleDirections = 4;
	private int[][] directions;
	
	public Directions()
	{
		directions = new int [possibleDirections][leftAndRight];
		directions[0][0] = 4;
		directions[0][1] = 2;
		
		directions[2][0] = 2;
		directions[2][1] = 4;
		
		directions[3][0] = 3;
		directions[3][1] = 1;
		
		directions[1][0] = 1;
		directions[1][1] = 3;
	}
	public int getLeft(int straight)
	{
		return directions[straight - 1][0];
	}
	public int getRight(int straight)
	{
		return directions[straight - 1][1];
	}
	

}
