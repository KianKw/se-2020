import info.gridworld.actor.Bug;

/**
 * A CircleBug traces out a circle "box"
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class CircleBug extends Bug {
    /*
    step 走了几步
    sideLength 八边形的边长
    */
    private int steps;
    private int sideLength;

    /**
     * Constructs a circle bug that traces a circle of a given side length
     * @param length the side length
     */
    public CircleBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     * 如果 步数 < 八边形的边长 并且 可以移动，就移动，步数增加
     * 否则，就右转 45度，并且把 步数 赋值为0
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            steps = 0;
        }
    }
}
