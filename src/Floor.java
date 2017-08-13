@SuppressWarnings("unused")
//410-660-8290
public class Floor
{
    private final int WIDTH = 17, HEIGHT = 13, NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
    private final int OFFSET = 5;//Don't be stupid and make this bigger than height or width.
    private Space[][] floor;
    
    
    /**
     * Sets up the board with a standard 18x13 size with and offset of 4
     */
    public Floor()
    {
        if(OFFSET >= HEIGHT || OFFSET >= WIDTH)
        {System.out.println("Invalid sizes. Adjust OFFSET and restart."); System.exit(1);}

        char startTop = 'A' - 1, startLeft = ('A' + (WIDTH + HEIGHT) - 4);//-4 because each array must be 2 greater than the number of spaces
        floor = new Space[WIDTH][HEIGHT];
        
        for(int j = 0 ; j < HEIGHT; j++)
            for(int i = 0; i < WIDTH; i++)
            {
                if(j == 0 && i > 0 && i < WIDTH - OFFSET - 1)//Top wall normal
                    floor[i][j] = new Wall(" " + Character.toString(startTop += 1) + " ");
                else if(j == 0 && i >= WIDTH - OFFSET - 1 && i < WIDTH - 1)//Top wall offset
                    floor[i][j] = new Wall(" " + Character.toString(startTop += 2) + " ");
                
                else if(i == WIDTH - 1 && j == 1)//Right wall begin
                {
                    startTop -= (OFFSET * 2);
                    floor[i][j] = new Wall(" " + Character.toString(startTop += 1) + " ");
                }
                else if(i == WIDTH - 1 && j > 0 && j <= OFFSET + 1)//Right wall offset
                    floor[i][j] = new Wall(" " + Character.toString(startTop += 2) + " ");
                else if(i == WIDTH - 1 && j > OFFSET)//Right wall normal
                    floor[i][j] = new Wall(" " + Character.toString(startTop += 1) + " ");
                
                else if(i == 0 && j > 0  && j < HEIGHT - OFFSET - 1)//Left wall normal
                    floor[i][j] = new Wall(" " + Character.toString(startLeft -= 1) + " ");
                else if(i == 0 && j >= HEIGHT - OFFSET - 1 && j < HEIGHT - 1)//Left wall offset
                    floor[i][j] = new Wall(" " + Character.toString(startLeft -= 2) + " ");
                
                else if(i == 0 && j == HEIGHT - 1)//Bottom wall begin
                {
                    startLeft += (OFFSET * 2);
                    floor[i][j] = new Wall(" " + Character.toString(startLeft += 1) + " ");
                }       
                else if(j == HEIGHT - 1 && i > 0 && i <= OFFSET + 1)//Bottom wall offset
                    floor[i][j] = new Wall(" " + Character.toString(startLeft -= 2) + " ");
                else if(j == HEIGHT - 1 && i > OFFSET)//Bottom wall normal
                    floor[i][j] = new Wall(" " + Character.toString(startLeft -= 1) + " ");
                
                else//Everything else
                    floor[i][j] = new Space();
            }
        
        //Creates empty spaces
        int tempOffset = OFFSET;
        for(int j = 1; j < OFFSET + 1; j++)//Top right
        {
            for(int i = WIDTH - tempOffset - 1; i < WIDTH - 1; i++)
                floor[i][j] = new Space(false);
            tempOffset -= 1;
        }
        tempOffset = OFFSET;
        for(int i = 1; i < OFFSET + 1; i++)//Top left
        {
            for(int j = HEIGHT - tempOffset - 1; j < HEIGHT - 1; j++)
                floor[i][j] = new Space(false);
            tempOffset -= 1;
        }   
        
        //Beautify
        floor[0][0] = new Wall(" ! ");
        floor[WIDTH - 1][0] = new Wall(" ! ");
        floor[0][HEIGHT - 1] = new Wall(" ! ");
        floor[WIDTH - 1][HEIGHT - 1] = new Wall(" ! ");
        
        System.out.println("RAWR! A WILD MONSTER HAS APPEARED!");
    }
    
    /**
     * Used to determine if the spot at the coordinates can act as a spot, or if it is an "offset" that should be skipped over. This method does NOT check to see if a space has another object in it (see set() or canMove()).
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return true if you can use the space, false if you cannot
     */
    public boolean isValidSpace(int x, int y)
    {
        if(x + 1 > WIDTH - 2 || y + 1 > HEIGHT - 2 || x < 0 || y < 0)
            return false;
        return floor[x + 1][y + 1].isUsable();
    }
    
    /**
     * Prints the floor.
     */
    public void print()
    {
        System.out.println();
        for(int j = 0 ; j < HEIGHT; j++)
        {
            for(int i = 0; i < WIDTH; i++)
                System.out.print(floor[i][j]);
            System.out.println();
        }
    }
    
    /**
     * Returns the object at a given coordinate
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return The object at the location, or null if it is empty or invalid
     */
    public Object getObjectAt(int x, int y)
    {
        if(x + 1 > WIDTH - 2 || y + 1 > HEIGHT - 2)
            return null;
        Object o = floor[x + 1][y + 1].getObject();
        return o;
    }
    
    /**
     * Determines the the space is available.  Does not determine if it is a valid space (see isValidSpace()).
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return True if the space is open, false if it is not.
     */
    public boolean canMove(int x, int y)
    {
        if(x + 1 > WIDTH - 2 || y + 1 > HEIGHT - 2)
            return false;
        return floor[x + 1][y + 1].isOpen();
    }
    
    /**
     * Sets the object of a space if the space is empty
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param o The object to be added (of class Person, Rock, or Monster)
     * @return True if the object was added, false if it was not (because there was an object already there, it was the wrong class, etc)
     */
    public boolean set(int x, int y, Object o)
    {
        if(x + 1 > WIDTH - 2 || y + 1 > HEIGHT - 2 || x < 0 || y < 0)
            return false;
        return floor[x + 1][y + 1].addObject(o);
    }
    
    /**
     * Removes the object at the given coordinates.
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return The object that was removed. Null if there was no object.
     */
    public Object remove(int x, int y)
    {
        if(x + 1 > WIDTH - 2 || y + 1 > HEIGHT - 2)
            return false;
        return floor[x + 1][y + 1].remove();
    }
    
    /**
     * This method moves a monster object one space
     * @param m The monster to be moved
     * @return True if the move was successful
     */
    public boolean moveMonster(Monster m)
    {
        boolean worked = false;
        switch (m.getDirection())
        {
            case NORTH:
                if(m.getY() - 1 < 0)
                    worked = moveDatMonster(m, (WIDTH - 2) - 1 - m.getX(), (HEIGHT - 2) - 1 - m.getY());
                else
                    worked = moveDatMonster(m, m.getX(), m.getY() - 1);
                break;
            case EAST:
                if(m.getX() + 2 > WIDTH - 2)
                    worked = moveDatMonster(m, (WIDTH - 2) - 1 - m.getX(), (HEIGHT - 2) - 1 - m.getY());
                else
                    worked = moveDatMonster(m, m.getX() + 1, m.getY());
                break;
            case SOUTH:
                if(m.getY() + 2 > HEIGHT - 2)
                    worked = moveDatMonster(m, (WIDTH - 2) - 1 - m.getX(), (HEIGHT - 2) - 1 - m.getY());
                else
                    worked = moveDatMonster(m, m.getX(), m.getY() + 1);
                break;
            case WEST:
                if(m.getX() - 1 < 0)
                    worked = moveDatMonster(m, (WIDTH - 2) - 1 - m.getX(), (HEIGHT - 2) - 1 - m.getY());
                else
                    worked = moveDatMonster(m, m.getX() - 1, m.getY());
                break;
            default:
                worked = false;
        }
        return worked;
    }
    
    //Moves monster, m, to spot (x, y) and performs any necessary actions (killing people, moving rocks, teleporting, etc)
    private boolean moveDatMonster(Monster m, int x, int y)
    {
        boolean worked = true;
        int i = 0;
        while(!isValidSpace(x,y))
        {
            if(i > OFFSET * 2)
                break;
            switch (m.getDirection())
            {
                case NORTH:
                    if(y - 2 <= 1)
                    {
                        x = (WIDTH - 2) - 1 - m.getX();
                        y = (HEIGHT - 2) - 1 - m.getY();
                    }
                    else
                        y -= 1;
                    break;
                case EAST:
                    if(x + 2 > WIDTH - 2)
                    {
                        x = (WIDTH - 2) - 1 - m.getX();
                        y = (HEIGHT - 2) - 1 - m.getY();
                    }
                    else
                        x += 1;
                    break;
                case SOUTH:
                    if(y + 2 > HEIGHT - 2)
                    {
                        x = (WIDTH - 2) - 1 - m.getX();
                        y = (HEIGHT - 2) - 1 - m.getY();
                    }
                    else
                        y += 1;
                    break;
                case WEST:
                    if(x - 1 <= 1)
                    {
                        x = (WIDTH - 2) - 1 - m.getX();
                        y = (HEIGHT - 2) - 1 - m.getY();
                    }
                    else
                        x -= 1;
                    break;
                default:
                    System.out.println("BROKEN");
                    break;
            }
            i += 1;
        }
        
        if(getObjectAt(x, y) != null && getObjectAt(x, y).getClass() == Person.class)
            remove(x, y);
        worked = set(x, y, remove(m.getX(), m.getY()));
        m.setCoordinates(x, y);
        return worked;
    }
    
    /**
     * This moves a rock object one space
     * @param face The direction to move the rock
     * @param r The rock to be moved
     */
    public void moveRock(int face, Rock r)
    {
    	boolean worked = false;
        switch (face)
        {
            case NORTH:
                worked = moveDatRock(r, r.getX(), r.getY() - 1, face);
                break;
            case EAST:
                worked = moveDatRock(r, r.getX() + 1, r.getY(), face);
                break;
            case SOUTH:
                worked = moveDatRock(r, r.getX(), r.getY() + 1, face);
                break;
            case WEST:
                worked = moveDatRock(r, r.getX() - 1, r.getY(), face);
                break;
            default:
                worked = false;
        }
    	
    }
    
    //Moves rock, r to spot (x, y) and performs any necessary actions (killing people, moving other rocks, disappearing, etc)
    public boolean moveDatRock(Rock r, int x, int y, int face)
    {
    	boolean worked = true;
    	if(!isValidSpace(x, y))
    	{
    		remove(r.getX(), r.getY());
    		return false;
    	}
    	if(getObjectAt(x, y) != null && getObjectAt(x, y).getClass() == Person.class)
            remove(x, y);
    	if(getObjectAt(x, y) != null && getObjectAt(x, y).getClass() == Rock.class)
            moveRock(face, (Rock)getObjectAt(x, y));
        worked = set(x, y, remove(r.getX(), r.getY()));
        r.setCoordinates(x, y);
        return worked;
    }
    
    /**
     * Returns the width of the board.
     * @return The width of the board.
     */
    public int getWidth()
    {
        return WIDTH - 2;
    }
    
    /**
     * Returns the height of the board.
     * @return The height of the board.
     */
    public int getHeight()
    {
        return HEIGHT - 2;
    }
}
