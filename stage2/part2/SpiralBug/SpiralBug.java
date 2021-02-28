import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
    /**
    steps 步数
    sideLength 四边形的长度
    */
    private int steps;
    private int sideLength;

    /**
     * Constructs a spiral bug that traces a circle of a given side length
     * @param length the side length
     */
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
    act
    * 如果 步数 < 四边形的长度 并且可以移动，则移动，步数++
    * 否则，转90度，步数变为0，四边形的长度++
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            //each time a turn, add one to make a bigger square
            sideLength++;
        }
    }
}
