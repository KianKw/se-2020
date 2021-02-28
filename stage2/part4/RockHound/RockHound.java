import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

public class RockHound extends Critter {
    // 重写Critter的processActors方法，使其清除actors里的非Critter元素
    @Override
	public void processActors(ArrayList<Actor> actors) {
		for (Actor a : actors) {
            // remove the rock
		    if (a instanceof Rock) {
		        a.removeSelfFromGrid();
            }
		}
    }
}
