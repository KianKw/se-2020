import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
	// 利用map存储位置和物体的对应
    private Map<Location, E> occupantMap;
	// 有界网格的列数和列数
    private int rows;
    private int cols;

    // 默认构造器，传入行数和列数
	public SparseBoundedGrid2(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		occupantMap = new HashMap<Location, E>();
	}
	
    // 返回行数
	public int getNumRows() {
        return rows;
    }
    // 返回列数
    public int getNumCols() {
        return cols;
    }

    // 判断给定坐标是否在网格内
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        // 利用 hashmap 的 keySet 可以得到所有的键值
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    public E get(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        } else if (!isValid(loc)) {
           throw new IllegalArgumentException("Location " + loc
                   + " is not valid");
        }
        // 当位置合理后，直接利用 hashmap 的 get 方法
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj) {
    	if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        // 当位置有效后，直接利用 hashmap 的 put 方法
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc) {
    	if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // 当位置有效后，直接利用 hashmap 的 remove 方法
        return occupantMap.remove(loc);
    }
}
