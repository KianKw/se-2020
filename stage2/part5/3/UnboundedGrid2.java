import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.*;

public class UnboundedGrid2<E> extends AbstractGrid<E> {
    // 用二维数组存储元素
    private Object[][] occupantArray;
    // 数组的大小
    private int size;

    // 默认构造器，大小默认为 16
    public UnboundedGrid2(){
        size = 16;
        occupantArray = new Object[size][size];
    }

    // 返回 -1 表示是一个无界网格
    public int getNumRows() {
        return -1;
    }
    // 返回 -1 表示是一个无界网格
    public int getNumCols() {
        return -1;
    }
    // 坐标的行数和列数都要大于 0
    public boolean isValid(Location loc){
        return (loc.getRow() >= 0 && loc.getCol() >= 0);
    }

    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> locs = new ArrayList<Location>();
        // 遍历该二维数组
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                // 如果有元素在该位置上
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    locs.add(loc);
                }
            }
        }

        return locs;
    }

    public E get(Location loc){
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if(loc.getRow() >= size || loc.getCol() >= size) {
        	return null;
        }
        // 直接通过数组下标得到
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        
        //extend the grid size
        if(loc.getRow() >= size || loc.getCol() >= size) {
            expand(loc);
        }
        // 将对象添加到网格中
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if(loc.getRow() >= size || loc.getCol() >= size) {
        	return null;
        }
        // 将对象从网格中移除
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    // 数组扩张方法
    public void expand(Location loc) {
        int newSize = size;
        // 数组宽度不停乘以2，直至能够容纳下loc坐标
        while(loc.getCol() >= newSize || loc.getRow() >= newSize) {
            newSize *= 2;
        }
        // 把原来的数据复制到临时的数组上
        Object[][] newArray = new Object[newSize][newSize];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                newArray[i][j] = occupantArray[i][j]; 
            }
        }
        // 更改bounds以及存储数据的数组
        size = newSize;
        occupantArray = newArray;
    }
}
