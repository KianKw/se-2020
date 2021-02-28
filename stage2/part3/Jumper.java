import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

public class Jumper extends Actor {
    /*
     * Construct a jumper.
    默认构造一个红色 jumper
     * */
    public Jumper() {
		setColor(Color.RED);
    }

    /**
     * Construct a jumper and set the color of jumper.
    可传入颜色参数的构造函数
     */
	public Jumper(Color jumperColor) {
		setColor(jumperColor);
	}
    
    /*
     *  if can jump jump
     *  else turn
     判断能否跳两格
     * 如果可以跳，则跳
     * 否则就转 45 度
     * */
    public void act() {
        if(canJump()) {
            jump();
        } else {
            turn();
        }
    }
    
    /*
     *  change the jump direction of the jumper
     * 每次转身向顺时针转45度
     * */
    public void turn(){
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    
    /*
     *  jump two cell 
     * */
    public void jump(){
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return ;
        }
        /**
        * next 走一步的可选位置
        * nNext 走两步的可选位置
        * 如果 nNext 有效，则移动到nNext
        * 否则，把自己从网格中移除
        * 在各个的位置放一朵花
        */
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nNext = next.getAdjacentLocation(getDirection());
        if (gr.isValid(nNext)) {
            moveTo(nNext);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    
    /*
     * judge whether the jumper can jump
     * 
     * */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        //证明jumper不在网格里，判断jumper不能动
        if (gr == null) {
            return false;
        }
        /*
        * next 走一步的可选位置
        * nNext 走两步的可选位置
        */
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nNext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(nNext)) {
            return false;
        }
        //获取下格子上的actor
        Actor targetDes = gr.get(nNext);
        //如果为空或者是花就可以移动
        return (targetDes == null) || (targetDes instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
