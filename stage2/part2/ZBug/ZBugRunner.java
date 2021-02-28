import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ZBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        /*
        * 生成一个 alice  橙色，位置为 (2, 3)
        * 四边形的初始长度为 3
        */
        ZBug alice = new ZBug(3);
        alice.setColor(Color.ORANGE);
        world.add(new Location(2, 3), alice);
        world.show();
    }
}