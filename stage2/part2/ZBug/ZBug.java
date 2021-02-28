import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;


public class ZBug extends Bug {
    /**
     * @param steps the number of steps in the current side
     * @param sideLength the maximum size of each segment
     * @param stage the stage of the Z the ZBug is on
     * steps 步数
     * sideLength 字母z一条边的长度
     * stage 在哪条边上
     */
    private int steps;
    private int sideLength;
    private int stage;
    
    /**
     * Constructs a z bug that traces a circle of a given side length
     * @param length the side length
     * 在第一条边
     * 方向为东
     */
    public ZBug(int length) {
    	stage = 1;
    	setDirection(Location.EAST);
        steps = 0;
        sideLength = length;
    }

    /**
    * Moves to the next location of the square.
    * 如果步数小于边长并且可以移动，则移动，步数++
    * 否则，（步数等于边长）
        * 如果 stage=1，在第一条边
            * stage++ 转向第二条边
            * 步数设为0
            * 方向设为西南
        * 如果 stage = 2，在第二条边
            * stage++ 转向第三条边
            * 步数设为0
            * 方向设为东
    */
    public void act() {
    	//not having finish a z
        if(stage <= 3) {
        	if(canMove() && steps < sideLength) {
        		steps++;
        		move();
        	} else if(stage == 1 && steps == sideLength) {
        		stage++;
        		steps = 0;
        		setDirection(Location.SOUTHWEST);
        	} else if(stage == 2 && steps == sideLength) {
        		stage++;
        		steps = 0;
        		setDirection(Location.EAST);
        	}
        }
    }
}
