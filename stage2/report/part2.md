# Part2 Bug Variations

Answer the following questions.

- What is the role of the instance variable sideLength?

    > sideLength 实例变量定义了一个 BoxBug 在它的盒子的每个边移动的步数。

    ```java
    // @file: GridWorldCode/project/boxBug/BoxBug.java
    // @line: 34~38
     public BoxBug(int length)
     {
         steps = 0;
         sideLength = length;
     }
    ```

    

- What is the role of the instance variable steps?

    > steps 实例变量记录一个 BoxBug 在它的盒子的当前一侧移动了多少步。

    ```java
    // @file: GridWorldCode/project/boxBug/BoxBug.java
    // @line: 34~38
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
    ```

    

- Why is the turn method called twice when steps becomes equal to sideLength?

    > 当 BoxBug 沿某一侧边移动时，它必须旋转 90 度才能沿着箱子的另一边移动。
    >
    > turn 方法只执行一次只能完成45度旋转，所以它需要调用两次 turn 方法来90度旋转。

    ```java
    // @file: GridWorldCode/projects/boxBug/BoxBug.java
    // @line: 52~54
    turn();
    turn();
    steps = 0;
    ```

    

- Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

    > BoxBug类扩展了Bug类，Bug类有一个公共的move方法。因为BoxBug类是Bug类的子类，所以它从Bug类继承了move方法。

    ```java
    // @file: GridWorldCode/projects/boxBug/BoxBug.java
    // @line:25
    public class BoxBug extends Bug
    ```

    ```java
    // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    // @line:71
    public void move()
    ```

    

- After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

    > 是的。当构造一个 BoxBug 时，它的边长是确定的，不能被更改。

    ```java
    // @file: GridWorldCode/projects/boxBug/BoxBug.java
    // @line:35~38
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
    ```

    

- Can the path a BoxBug travels ever change? Why or why not?

    > 是的。当BoxBug试图移动时，如果另一个Actor(如 Rock 或 Bug)在它前面，BoxBug将转向开始一个新的路径。

    ```java
    // @file: GridWorldCode/project/boxBug/BoxBug.java
    // @line: 45~49
    if (steps < sideLength && canMove())
    {
        move();
        steps++;
    }
    ```

    ```java
    // @file: GridWorldCode/framework/info/gridworld/actor/bug.java
    // @line: 93~95
    Grid<Actor> gr = getGrid();
    if (gr == null)
        return false;
    ```

    

- When will the value of steps be zero?

    > 在构造 BoxBug 时，step的值被设置为 0。

    ```java
    // @file: GridWorldCode/projects/boxBug/BoxBug.java
    // @line: 34~38
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
    ```

    > 当steps等于sideength时，step的值将被设为0，这意味着BoxBug已经完成了它的盒子路径的一条边，或者此时 BoxBug 不能移动，只能转向开始一个新的路径。

    ```java
    // @file: GridWorldCode/projects/boxBug/BoxBug.java
    // @line: 45~55
    if (steps < sideLength && canMove())
    {
        move();
        steps++;
    }
    else
    {
        turn();
        turn();
        steps = 0;
    }
    ```

    