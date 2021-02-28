# Part 3 Design Report

Inception: clarify the details of the problem:

* a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

    > 如果前方第二个单元格里是花，Jumper 会继续向前跳，跳到花上；
    >
    > 如果前方第二格单元格时石头，Jumper 会顺时针转向 45°。
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 44~46
    > public void turn(){
    >  setDirection(getDirection() + Location.HALF_RIGHT);
    > }
    > ```
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 97~99
    > Actor targetDes = gr.get(nNext);
    > 
    > return (targetDes == null) || (targetDes instanceof Flower);
    > ```

* b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

    > 如果前方第二个单元格在网格之外，Jumper 会顺时针转向 45°。
    >
    > 转后继续判断前方的单元格。
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 44~46
    > public void turn(){
    >  setDirection(getDirection() + Location.HALF_RIGHT);
    > }
    > ```
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 92~94
    > if (!gr.isValid(nNext)) {
    >  return false;
    > }
    > ```

* c. What will a jumper do if it is facing an edge of the grid?

    > 如果 Jumper 正对着网格边缘，Jumper 会转向，如果转 45° 后仍无法跳的话，会继续旋转，通常需要旋转两次即旋转 90°。
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 44~46
    > public void turn(){
    >  setDirection(getDirection() + Location.HALF_RIGHT);
    > }
    > ```
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 92~94
    > if (!gr.isValid(nNext)) {
    >  return false;
    > }
    > ```

* d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

    > 如果 jumper 的前方第二个单元格中有其他 actor，Jumper 会转向。
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 97~99
    > Actor targetDes = gr.get(nNext);
    > 
    > return (targetDes == null) || (targetDes instanceof Flower);
    > ```

* e. What will a jumper do if it encounters another jumper in its path?

    > 如果 jumper 的跳跃路径上有其他 jumper，Jumper 会转向。
    >
    > ```java
    > // @file: Part3/Jumper.java
    > // @line: 97~99
    > Actor targetDes = gr.get(nNext);
    > 
    > return (targetDes == null) || (targetDes instanceof Flower);
    > ```

* f. Are there any other tests the jumper needs to make?

    > 其他测试： Jumper 周围的网格全都被占据，Jumper 会把自己从网格中移除还是会不断转向。