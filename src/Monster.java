public class Monster
{
    //keep direction of the string
    public int thisDirection = 3;
    final private int step = 1;
    private Floor floor;
    Directions directions;
    //move is taken in by the driver on how much the monster should move
    int x, y;
    public Monster(int startX, int startY, int startDirection, Floor board)
    {
        x = startX;
        y = startY;
        thisDirection = startDirection;
        directions = new Directions();
        floor = board;
    }
    public void move(int move)
    {
        if(move > 0)
        {
            int left = check(directions.getLeft(thisDirection));
            int right = check(directions.getRight(thisDirection));
            int straight = check(thisDirection);
            
            if(left < straight && left != 0)
            {
                if(left > right && right != 0)
                {
                    thisDirection = directions.getRight(thisDirection);
                    floor.moveMonster(this);
                }
                else
                {
                    thisDirection = directions.getLeft(thisDirection);
                    floor.moveMonster(this);
                }
            }
            if(right < straight && right != 0)
            {
                if(right > left && left != 0)
                {
                    thisDirection = directions.getLeft(thisDirection);
                    floor.moveMonster(this);
                }
                else
                {
                    thisDirection = directions.getRight(thisDirection);
                    floor.moveMonster(this);
                }
            }
            else
            {
                //straight
                if(checkObject(thisDirection) != null && checkObject(thisDirection).getClass() == Rock.class)
                {
                    floor.moveRock(thisDirection, (Rock)checkObject(thisDirection));
                    floor.moveMonster(this);
                }
                else
                {
                    floor.moveMonster(this);
                }
            }
            floor.print();
            move(move - 1);
        }
        else
            return;
    }

    public int getDirection()
    {
        return thisDirection;
    }
    public void setCoordinates(int thisX, int thisY)
    {
        x = thisX;
        y = thisY;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    private int check(int face)
    {
        switch (face) 
        {
            case 1: 
                return checkNorth();
            case 2:
                return checkEast();
            case 3:
                return checkSouth();
            case 4:
                return checkWest();
        }
        
        return -1;
    }
    
    private int checkWest()
    {
        for(int i = 0; i <= x; i++)
        {
            if(floor.getObjectAt(x - i, y)  == null)
                continue;
            else if(floor.getObjectAt(x - i, y).getClass() == Person.class)
                return i;
            else if(floor.getObjectAt(x - i, y).getClass() == Rock.class)
                return floor.getWidth() * floor.getHeight();//Ensures that it is the biggest number.
        }
        
        return floor.getWidth() * floor.getHeight();
    }
    private int checkNorth()
    {
      
        for(int i = 0; i <= y; i++)
        {
            if(floor.getObjectAt(x, y - i)  == null)
                continue;
            else if(floor.getObjectAt(x, y - i).getClass() == Person.class)
                return i;
            else if(floor.getObjectAt(x, y - i).getClass() == Rock.class)
                return floor.getWidth() * floor.getHeight();//Ensures that it is the biggest number.
        }
        
        return floor.getWidth() * floor.getHeight();
    }
    private int checkSouth()
    {
        for(int i = y; i < floor.getHeight(); i++)
        {
            if(floor.getObjectAt(x, i)  == null)
                continue;
            else if(floor.getObjectAt(x, i).getClass() == Person.class)
                return i - y;
            else if(floor.getObjectAt(x, i).getClass() == Rock.class)
                return floor.getWidth() * floor.getHeight();//Ensures that it is the biggest number.
        }
        
        return floor.getWidth() * floor.getHeight();
    }
    private int checkEast()
    {
        for(int i = x; i < floor.getWidth(); i++)
        {
            if(floor.getObjectAt(i, y)  == null)
                continue;
            else if(floor.getObjectAt(i, y).getClass() == Person.class)
                return i - x;
            else if(floor.getObjectAt(i, y).getClass() == Rock.class)
                return floor.getWidth() * floor.getHeight();//Ensures that it is the biggest number.
        }
        
        return floor.getWidth() * floor.getHeight();
    }
    
    
    
    private Object checkObject(int direction)
    {
        switch (direction) 
        {
            case 1: 
                return checkObjectNorth();
            case 2:
                return checkObjectEast();
            case 3:
                return checkObjectSouth();
            case 4:
                return checkObjectWest();
        }
        return null;
    }
    
    private Object checkObjectWest()
    {
        return floor.getObjectAt(x - step, y);
    }
    private Object checkObjectNorth()
    {
        return floor.getObjectAt(x, y - step);
    }
    private Object checkObjectSouth()
    {
        return floor.getObjectAt(x, y + step);
    }
    private Object checkObjectEast()
    {
        return floor.getObjectAt(x + step, y);
        
    }
    
    public String toString()
    {
        return "ICH BIN EIN MONSTER!";
    }
    
    
}
