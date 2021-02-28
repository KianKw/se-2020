import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritterRunner {
    public static void main(String[] args) {
        //默认构造一个带有有界grid的world
        ActorWorld world = new ActorWorld();
        //构造一系列的Critter
        Critter aCritter = new Critter();
        Critter bCritter = new Critter();
        Critter cCritter = new Critter();
        Critter dCritter = new Critter();
        Critter eCritter = new Critter();
        Critter fCritter = new Critter();
        Critter gCritter = new Critter();
        Critter hCritter = new Critter();
        //为了观察到颜色的变化，设置相同颜色的 Critter
        aCritter.setColor(Color.green);
        bCritter.setColor(Color.green);
        cCritter.setColor(Color.green);
        dCritter.setColor(Color.green);
        eCritter.setColor(Color.green);
        fCritter.setColor(Color.green);
        gCritter.setColor(Color.green);
        hCritter.setColor(Color.green);
        //把 Critter 添加到 world 中
        world.add(new Location(3, 3), aCritter);
        world.add(new Location(3, 4), bCritter);
        world.add(new Location(5, 4), cCritter);
        world.add(new Location(2, 8), dCritter);
        world.add(new Location(5, 5), eCritter);
        world.add(new Location(1, 5), fCritter);
        world.add(new Location(7, 2), gCritter);
        world.add(new Location(7, 8), hCritter);
        BlusterCritter temp = new BlusterCritter(10);
        world.add(new Location(4, 4), temp);
        // 显示 GUI
        world.show();
    }
}
