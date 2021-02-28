import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    // 表示数组的行数和列数
	private int rows;
	private int cols;

    // 用数组来存储SparseGridNode类
	private SparseGridNode[] occupantArray;

    // 默认构造器
	public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        this.rows = rows;
        this.cols = cols;
        this.occupantArray = new SparseGridNode[rows];
	}

	// 返回网格的列数
	public int getNumCols() {
	    // Note: according to the constructor precondition, numRows() > 0, so
	    return cols;
	}

    // 返回网格的行数
	public int getNumRows() {
        return rows;
    }

    //判断给定的坐标是否在网格规定的范围内
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

	public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // 遍历数组
        for (int r = 0; r < getNumRows(); r++) {
            SparseGridNode node = occupantArray[r];
            // 遍历 SparseGridNode 的链表
            while(node != null) {
            	if(node.getOccupand() != null) {
            		theLocations.add(new Location(r, node.getCol()));
            	}
            	node = node.getNext();
            }
        }
        return theLocations;
    }
	
	public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        E target = null;
        // 遍历 SparseGridNode 的链表
        SparseGridNode node = occupantArray[loc.getRow()];
        while(node != null) {
        	if(loc.getCol() == node.getCol()) {
        		target = (E)node.getOccupand();
        		break;
        	}
        	node = node.getNext();
        }
		return (E)target;
    }
	
	
	public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // 删掉原网格位置的元素之后，把新的元素加到该行链表的头部
        E oldOccupant = get(loc);
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        SparseGridNode newNode = new SparseGridNode(obj, col, node);
        occupantArray[row] = newNode;
        return oldOccupant;
    }
	
	public E remove(Location loc) {
	    if (!isValid(loc)) {
	        throw new IllegalArgumentException("Location " + loc
	                + " is not valid");
        }
	    
	    // Remove the object from the grid.
	    E r = get(loc);
	    int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        if(node.getCol() == col && node != null) {
        	occupantArray[row] = node.getNext();
        } else {
        	SparseGridNode bNode = null;
            while (node != null) {
            	if(node.getCol() == col) {
            		bNode.setNext(node.getNext());
            		break;
            	}
    			bNode = node;
    			node = node.getNext();
    		}
        } 
	    return r;
	}
}