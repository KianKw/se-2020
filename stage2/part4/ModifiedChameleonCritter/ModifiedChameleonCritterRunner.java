import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritterRunner {
    public static void main(String[] args) {
        // 默认构造一个有界的 world
        ActorWorld world = new ActorWorld();
        // 添加不同颜色的石头用于观察 ModifiedChameleonCritter 颜色的变化
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        // 将 ModifiedChameleonCritter 添加到 world 中
        world.add(new Location(4, 4), new ModifiedChameleonCritter());
        world.add(new Location(5, 8), new ModifiedChameleonCritter());
        // 显示GUI
        world.show();
    }
}
