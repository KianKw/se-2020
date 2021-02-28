import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest extends TestCase {

    private ActorWorld world = new ActorWorld();

    public static void main(String args[]) {
        junit.textui.TestRunner.run(JumperTest.class);
    }

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < world.getGrid().getNumRows(); i++) {
            for (int j = 0; j < world.getGrid().getNumCols(); j++) {
                world.remove(new Location(i, j));
            }
        }
    }
    
    @Test
    //the first cell have a rock
    public void testCanJumpRock1() {
        //Create a new jumper.
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(1, 3), rock);
        assertEquals(true, jumper.canJump());
    }
    
    @Test
    //the first cell have a flower
    public void testCanJumpFlower1() {
        //Create a new jumper.
        Jumper jumper = new Jumper();
        Flower flower = new Flower();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(1, 3), flower);
        assertEquals(true, jumper.canJump());
    }

    @Test
    //the second cell have a rock
    public void testCanJumpRock2() {
        //Create a new jumper.
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(0, 3), rock);
        assertEquals(false,  jumper.canJump());
    }

    @Test
    //the second cell have a flower
    public void testCanJumpFlower2() {
        //Create a new jumper.
       Jumper jumper = new Jumper();
       Flower flower = new Flower();
       world.add(new Location(2, 3), jumper);
       world.add(new Location(0, 3), flower);
       assertEquals(true,  jumper.canJump());
    }
    
    @Test
    //The target for testing jumpers is on the Web
    public void testCanJumpOut() {
       //Create a new jumper.
       Jumper jumper = new Jumper();
       world.add(new Location(0, 9), jumper);
       assertEquals(false,  jumper.canJump());
    }
}
