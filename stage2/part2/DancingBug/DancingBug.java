import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
    /**
    index 索引
    danceArray 跳舞数组
    steps 步数
    */
    private int index;
    private int[] danceArray;
    private int steps;

    public DancingBug(int[] array) {
        index = 0;
        danceArray = array;
        steps = 0;
    }

    /**
    dance() 表示转向
    * danceArray[index] 表示转几次，（每次45度）
    * 转之后 index ++
    */
    public void dance() {
        for(int i = 0; i < danceArray[index]; i++) {
            turn();
        }
        index = (index+1) % danceArray.length; 
    }

    /**
    act
    * 如果 step < 1（step 为 0）的话，就移动，步数 ++
    * 否则，调用dance() 开始转向，步数设为0
    */
    public void act() {
        if (steps < 1 && canMove()) {
            move();
            steps++;
        } else {
            dance();
            steps = 0;
        }
    }
}
