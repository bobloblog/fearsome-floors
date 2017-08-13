
public class Wall extends Space
{
	/**
	 * Creates a standard wall displayed as " ! " 
	 */
	public Wall()
	{
		super.display = " ! ";
		super.isUsable = false;
	}
	
	/**
	 * Creates a wall with a custom display
	 * @param display
	 */
	public Wall(String display)
	{
		super.display = display;
		super.isUsable = false;
	}
}
