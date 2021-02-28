import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;


public class JumperRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);

        /* 可以跳到花上 */
        world.add(new Location(4, 1), new Flower());

        /* 不可以跳到石头上 */
        world.add(new Location(0, 1), new Rock());

        /* 可以跳过石头上 */
        world.add(new Location(0, 6), new Rock());
        world.add(new Location(8, 1), alice);
        world.show();
    }
}