import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains crab critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabRunner {
    public static void main(String[] args) {
        // 默认构造一个有界的 world
        ActorWorld world = new ActorWorld();
        // 添加各种的 actor 类型
        world.add(new Location(7, 5), new Rock());
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(5, 7), new Rock());
        world.add(new Location(7, 3), new Rock());
        world.add(new Location(7, 8), new Flower());
        world.add(new Location(2, 2), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(3, 8), new Flower());
        // 添加三个不同位置的 kingCrab
        world.add(new Location(4, 5), new KingCrab());
        world.add(new Location(6, 1), new KingCrab());
        world.add(new Location(7, 4), new KingCrab());
        // 现实 GUI
        world.show();
    }
}
