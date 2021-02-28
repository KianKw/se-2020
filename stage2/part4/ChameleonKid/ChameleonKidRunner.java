import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChameleonKidRunner {
    public static void main(String[] args) {
        // 默认构造一个带有有界grid的world
        ActorWorld world = new ActorWorld();
        // 构造 ChameleonKid 并添加到 world 中
        world.add(new Location(5, 5), new ChameleonKid());
        // 添加不同颜色的石头和花用于观察 ChameleonKid 颜色的变化
        world.add(new Location(6, 6), new Flower(Color.BLUE));
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 6), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.show();
    }
}
