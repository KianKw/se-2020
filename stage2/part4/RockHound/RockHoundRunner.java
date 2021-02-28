import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

public class RockHoundRunner {
    public static void main(String[] args) {
        // 默认构造一个带有有界的 world
        ActorWorld world = new ActorWorld();
        // 添加石头，观察石头是否会被吃掉
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        // 将 RockHound 添加到 world 中
        world.add(new Location(4, 4), new RockHound());
        world.add(new Location(1, 1), new RockHound());
        // 显示GUI
        world.show();
    }
}