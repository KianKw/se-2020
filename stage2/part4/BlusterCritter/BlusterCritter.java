import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter {
    //区分颜色是变亮还是变暗的变量
    private final int courage;

    //构造器传入一个初始化courage的int值
    public BlusterCritter(int c) {
        courage = c;
    }

    //重写了Critter的getActors方法，使其能返回周围24个格子里Critter的集合
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid<Actor> grid = getGrid();
        for(int i = getLocation().getRow() - 2; i <= getLocation().getRow() + 2; ++i) {
            for(int j = getLocation().getCol() - 2; j <= getLocation().getCol() + 2; ++j) {
                if(i == getLocation().getRow() && j == getLocation().getCol()) {
                	continue;
                }
                Location location = new Location(i, j);
				if(grid.isValid(location) && grid.get(location) != null) {
					actors.add(grid.get(location));
				}					
			}
		}
		return actors;
	}
	
	//change the color of the BlusterCritter
	public void darken() {
		//通过衰减RGB分量上的值让颜色暗淡
		Color c = getColor();
		int red   = (int) (c.getRed()   - 1 <= 0 ? 0 : (int) (c.getRed()   - 1));
		int green = (int) (c.getGreen() - 1 <= 0 ? 0 : (int) (c.getGreen() - 1));
		int blue  = (int) (c.getBlue()  - 1 <= 0 ? 0 : (int) (c.getBlue()  - 1));

		setColor(new Color(red, green, blue));
	}
	
	public void lighter() {
		//通过增加RGB分量上的值让颜色明亮
		Color c = getColor();
		int red   = (int) (c.getRed()   + 1 >= 255 ? 255 : (int) (c.getRed()   + 1));
		int green = (int) (c.getGreen() + 1 >= 255 ? 255 : (int) (c.getGreen() + 1));
		int blue  = (int) (c.getBlue()  + 1 >= 255 ? 255 : (int) (c.getBlue()  + 1));

		setColor(new Color(red, green, blue));
	}

	//重写了Critter的processActors方法，使其不是吃actor，而是变颜色
    @Override
	public void processActors(ArrayList<Actor> actors) {
		int count = 0;
		if(actors.size() > 0) {
			for(Actor actor : actors) {
				if(actor instanceof Critter) {
					++count;
				}
			}
			if(count >= courage) {
				darken();
			} else {
				lighter();
			}
		}
    }
}
