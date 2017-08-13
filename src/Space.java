public class Space
{
	private final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
	private Object obj = null; 
	protected String display = "[ ]";
	protected boolean isUsable = true;
	
	/**
	 * Creates a standard empty space
	 */
	public Space()
	{
		
	}
	
	/**
	 * Creates a standard empty space but defines it as usable or not. If unusable, the spot will not allow objects to be placed, and it will appear as "[X]"
	 * @param isUsable True if it can be used, false otherwise
	 */
	public Space(boolean isUsable)
	{
		this.isUsable = isUsable;
		if(!isUsable)
			display = "[X]";
	}
	
	/**
	 * Creates a space that contains a specific object, and sets the display accordingly
	 * @param obj The object to put in the space
	 */
	public Space(Object obj)
	{
		addObject(obj);
	}
	
	/**
	 * Returns the object that is contained by this space
	 * @return The object
	 */
	public Object getObject()
	{
		return obj;
	}
	
	/**
	 * Removes the object in this space, and resets the display
	 * @return
	 */
	public Object remove()
	{
		Object temp = obj;
		obj = null;
		if(isUsable)
			display = "[ ]";
		else
			display = "[X]";
		return temp;
	}
	
	/**
	 * Adds an object to this space and sets the display accordingly
	 * @param obj The object to be added
	 * @return Returns true if the object was added successfully, or false if there was already an object in the space, of if it could not be added to the space.
	 */
	public boolean addObject(Object obj)
	{
		if(this.obj != null)
			return false;
		else if(!isUsable)
		{
			this.obj = obj;
			return false;
		}
		this.obj = obj;
		if(obj == null)
			display = "[ ]";
		else if(obj.getClass().equals(Person.class))
			display = "[" + "P" + "]";
		else if(obj.getClass().equals(Rock.class))
			display = "[" + "@" + "]";
		else if(obj.getClass().equals(Monster.class))			
			switch(((Monster)obj).getDirection())
			{
				case NORTH:
					display = "[" + "▲" + "]";
					break;
				case EAST:
					display = "[" + "►" + "]";
					break;
				case SOUTH:
					display = "[" + "▼" + "]";
					break;
				case WEST:
					display = "[" + "◄" + "]";
					break;
				default:
					display = "[" + "M" + "]";
			}
		else
			return false;
		return true;
	}
	
	public String toString()
	{
		return display;
	}
	
	/**
	 * Determines if the space is usable (see the constructor Space(boolean isUsable)
	 * @return True if it is, false otherwise
	 */
	public boolean isUsable()
	{
		return isUsable;
	}
	
	/**
	 * Determines whether or not the space is open (already contains an object or not)
	 * @return True if it is, false otherwise
	 */
	public boolean isOpen()
	{
		if(obj != null)
			return false;
		return true;
	}
	
	
}
