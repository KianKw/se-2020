import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class DancingBugRunner {
    public static void main(String[] args) {
    	UnboundedGrid<Actor> grid = new UnboundedGrid<Actor>();
        ActorWorld world = new ActorWorld(grid);
        int[] danceArray = {0,0,4,0,0,3,0,0,4,0,0,3};
        /* 生成一个 alice  橙色，位置为 (15,15) */
        DancingBug alice = new DancingBug(danceArray);
        alice.setColor(Color.ORANGE);
        world.add(new Location(15, 15), alice);
        world.show();
    }
}
