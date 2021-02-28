import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritter extends Critter {
    // 定义颜色衰减的快慢
    private static final double DARKENING_FACTOR = 0.01;
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.    
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n == 0) {
            darken();
        } else {
            // 随机决定变成哪个 Actor 的颜色
            int r = (int) (Math.random() * n);
            Actor other = actors.get(r);
            setColor(other.getColor());
        }
    }

    // 颜色变暗函数
    public void darken() {
        Color c = getColor();
        // 通过衰减RGB分量上的值让颜色暗淡
        int red   = (int) (c.getRed()   - 1 <= 0 ? 0 : (int) (c.getRed()   - 1));
        int green = (int) (c.getGreen() - 1 <= 0 ? 0 : (int) (c.getGreen() - 1));
        int blue  = (int) (c.getBlue()  - 1 <= 0 ? 0 : (int) (c.getBlue()  - 1));
        setColor(new Color(red, green, blue));
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc) {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}
