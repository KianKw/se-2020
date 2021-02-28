import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CircleBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        /**
        生成一个 alice 
        * 八边形的边长为 2
        * 颜色为 橙色
        * 位置为 6, 2
        */
        CircleBug alice = new CircleBug(2);
        alice.setColor(Color.ORANGE);
        world.add(new Location(6, 2), alice);
        world.show();
    }
}