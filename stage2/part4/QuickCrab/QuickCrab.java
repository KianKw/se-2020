import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
    /**
     * 重写 getMoveLocations 方法，使得优先选取距离左右距离为两个空格的位置
     * @return 右边和左边空位置的链表
     */
    @Override
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        Location location = getLocation();
        Grid<Actor> grid =  getGrid();
        // 左右方向数组
        int[] dirs = {
            Location.LEFT,
            Location.RIGHT
        };
        for (int dir : dirs) {
            Location one = location.getAdjacentLocation(getDirection() + dir);
            Location two = one.getAdjacentLocation(getDirection() + dir);
            // 判断该方向两个位置是否都有效并且是空的
            if(grid.isValid(one) && grid.isValid(two) && grid.get(one) == null && grid.get(two) == null) {
                locs.add(two);
            }
        }
        if(locs.size() == 0) {
            return super.getMoveLocations();
        }
        return locs;
    }
}
