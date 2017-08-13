public class TestDriver
{
	public static void main(String[] agrs)
	{		
		Floor floor = new Floor();
		Monster monster = new Monster(0, 3, 2, floor);
		Rock rock = new Rock(11, 3, floor), r = new Rock(10, 3, floor);
		
		
		floor.set(monster.getX(), monster.getY(), monster);
		floor.set(11, 3, rock);
		floor.set(10, 3, r);
		//people to test the conditions
		floor.set(0, 2, new Person());
		floor.set(0, 4, new Person());
		floor.set(1, 3, new Person());
		floor.set(12, 3, new Person());
		floor.set(3, 3, new Person());
		floor.set(14, 5, new Person());
		floor.set(14, 10, new Person());
		floor.set(2, 6, new Person());
		
		floor.print();
		monster.move(36);
		
	}
}