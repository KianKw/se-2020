import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class KingCrab extends CrabCritter {
    //重写 Critter 的 processActors 方法，用来移动或清除得到的 actors 列表
    @Override
	public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
        	if(!(a instanceof KingCrab))
        		moveAway(a);
        }
    }

	private void moveAway(Actor actor) {
		ArrayList<Location> moveLocation = getGrid().getEmptyAdjacentLocations(actor.getLocation());
        // 如果不能移动或者 actor 是花、石头
		if(moveLocation.size() == 0 || actor instanceof Flower || actor instanceof Rock) {
			actor.removeSelfFromGrid();
		} else {
			int n = moveLocation.size();
	        int r = (int) (Math.random() * n);
	        actor.moveTo(moveLocation.get(r));
		}
	}
}
