# Part 3 GridWorld Classes and Interfaces

## The Location Class

### **Set 3**

Assume the following statements when answering the following questions.

```java
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```

* How would you access the row value for loc1?

    > 访问 row 的值
    >
    > ```java
    > loc1.getRow();
    > ```
    >
    > 源文件的相关代码
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/grid/Location.java
    > // @line: 119~122
    > public int getCol()
    > {
    > return col;
    > }
    > ```

* What is the value of b after the following statement is executed?

    ```java
    boolean b = loc1.equals(loc2);
    ```

    > b 的值为
    >
    > ```java
    > b = false;
    > ```
    >
    > 源文件的相关代码
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/grid/Location.java
    > // @line: 205~212
    > public boolean equals(Object other)
    > {
    > if (!(other instanceof Location))
    >   return false;
    > 
    > Location otherLoc = (Location) other;
    > return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    > }
    > ```

* What is the value of loc3 after the following statement is executed?

    ```java
    Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
    ```

    > (4, 4)

* What is the value of dir after the following statement is executed?

    ```java
    int dir = loc1.getDirectionToward(new Location(6, 5);
    ```

    > 135度   东南 Southeast

* How does the getAdjacentLocation method know which adjacent location to return?

    > getAdjacentLocation 方法中的参数确定要查找的相邻邻居的方向。
    >
    > getAdjacentLocation方法返回最接近参数列表中给出的方向的相邻位置。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/grid/Location.java
    > // @line: 130~169
    > public Location getAdjacentLocation(int direction)
    > {
    >   // reduce mod 360 and round to closest multiple of 45
    >   int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
    >   if (adjustedDirection < 0)
    >       adjustedDirection += FULL_CIRCLE;
    > 
    >   adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
    >   int dc = 0;
    >   int dr = 0;
    >   if (adjustedDirection == EAST)
    >       dc = 1;
    >   // ...
    >   return new Location(getRow() + dr, getCol() + dc);
    > }
    > ```

## The Grid Interface

### **Set 4**

* How can you obtain a count of the objects in a grid? 

    How can you obtain a count of the empty locations in a bounded grid?

    > 假设 gr 是对 Grid 的引用。
    >
    > 获得网格中对象的数目：
    >
    > ```java
    > gr.getOccupiedLocations().size();
    > ```
    >
    > 获得网格中空位置的数目：
    >
    > ```java
    > gr.getNumRows()*gr.getNumCols() - gr.getOccupiedLocations().size();
    > ```
    >
    > 源文件中相关代码
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/grid/Grid.java
    > // @line: 109~118
    > /**
    >  * Gets the valid occupied locations adjacent to a given location in all
    >  * eight compass directions (north, northeast, east, southeast, south,
    >  * southwest, west, and northwest). <br />
    >  * Precondition: <code>loc</code> is valid in this grid
    >  * @param loc a location in this grid
    >  * @return an array list of the valid occupied locations adjacent to
    >  * <code>loc</code> in this grid
    >  */
    > ArrayList<Location> getOccupiedAdjacentLocations(Location loc);
    > ```

* How can you check if location (10,10) is in a grid?

    > 只有当该位置在网格中时，它才会返回 true。
    >
    > ```java
    > gr.isValid(new Location(10,10));
    > ```
    >
    > 源文件中相关代码
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/grid/Grid.java
    > // @line: 43~50
    > /**
    >  * Checks whether a location is valid in this grid. <br />
    >  * Precondition: <code>loc</code> is not <code>null</code>
    >  * @param loc the location to check
    >  * @return <code>true</code> if <code>loc</code> is valid in this grid,
    >  * <code>false</code> otherwise
    >  */
    > boolean isValid(Location loc);
    > ```

* Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

    > Grid 是一个接口。在 Java 中，接口指定了其他类实现的方法。
    >
    > 可以在AbstractGrid、BoundedGrid和UnboundedGrid类中找到这些方法的实现。
    >
    > 由于AbstractGrid只实现了网格接口所需的一些方法，因此它被声明为一个抽象类。
    >
    > BoundedGrid和UnboundedGrid扩展了AbstractGrid类，并实现了Grid接口所需的其余方法。

* All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

    > 在使用方法方面，使用 [] 符号访问元素比使用其他方法更容易。
    >
    > 在实现方法方面，ArrayList不要求用户在填充列表之前调整列表的大小。
    >
    > 由于BoundedGrid不跟踪网格中对象的数量，因此必须首先计算已占用位置的数量来确定数组的大小，然后返回到网格中查找并存储每个位置。如果网格跟踪已占用位置的数量，填充一个数组就像填充一个ArrayList一样简单。

## The Actor Class

### **Set 5**

* Name three properties of every actor.

    > color, direction, location
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Actor.java
    > // @line: 31~34
    > private Grid<Actor> grid;
    > private Location location;
    > private int direction;
    > private Color color;
    > ```

* When an actor is constructed, what is its direction and color?

    > actor 的初始颜色是 blue，初始方向是 North。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Actor.java
    > // @line: 39~45
    > public Actor()
    > {
    > color = Color.BLUE;
    > direction = Location.NORTH;
    > grid = null;
    > location = null;
    > }
    > ```

* Why do you think that the Actor class was created as a class instead of an interface?

    > Actor 具有状态和行为。接口不能声明实例变量或实现方法，但是类可以。

* Can an actor put itself into a grid twice without first removing itself?

    Can an actor remove itself from a grid twice? 

    Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

    > 在移除自己之前，actor 不能把自己两次放入到网格中。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Actor.java
    > // @line: 117~119
    > if (grid != null)
    > throw new IllegalStateException(
    > 	"This actor is already contained in a grid.");
    > ```
    >
    > 下面的代码可以通过编译，但是运行会报错。
    >
    > ```java
    > public class BugRunner
    > {
    > 	public static void main(String[] args)
    > 	{
    > 		ActorWorld world = new ActorWorld();
    > 		Bug b = new Bug();
    > 		world.add(b);
    > 		b.putSelfInGrid(b.getGrid(),b.getLocation());
    > 		world.add(new Rock());
    > 		world.show();
    > 	}
    > }
    > ```
    >
    > actor 不能把自己从网格中移除两次。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Actor.java
    > // @line: 135~137
    > if (grid == null)
    > throw new IllegalStateException(
    >   "This actor is not contained in a grid.");
    > ```
    >
    > 下面的代码可以通过编译，但是运行会报错。
    >
    > ```java
    > public class BugRunner
    > {
    > public static void main(String[] args)
    > {
    > ActorWorld world = new ActorWorld();
    > Bug b = new Bug();
    > world.add(b);
    > world.add(new Rock());
    > world.show();
    > b.removeSelfFromGrid();
    > b.removeSelfFromGrid();
    > }
    > }
    > ```
    >
    > actor 可以先把自己放入网格，再把自己从网格中移除，之后再把自己放入网格。
    >
    > 下面代码可以正常运行。
    >
    > ```java
    > public class ActorRunner
    > {
    > public static void main(String[] args)
    > {
    > ActorWorld world = new ActorWorld();
    > Actor a = new Actor();
    > world.add(a);
    > Grid g = a.getGrid(); //must remember the grid for placement back in the grid
    > world.add(new Rock());
    > world.show();
    > a.removeSelfFromGrid();
    > a.putSelfInGrid(g,new Location(5,5)); //must specify a location here
    > 	}
    > }
    > ```
    >
    > 

* How can an actor turn 90 degrees to the right?

    > 将 actor 向右旋转 90 度，可以调用 actor 类中的 setDirection 方法中来实现效果。
    >
    > ```java
    > setDirection(getDirection() + Location.RIGHT);
    > ```
    >
    > ```java
    > setDirection(getDirection() + 90);
    > ```
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Actor.java
    > // @line: 80~85
    > public void setDirection(int newDirection)
    > {
    > direction = newDirection % Location.FULL_CIRCLE;
    > if (direction < 0)
    >   direction += Location.FULL_CIRCLE;
    > }
    > ```

## Extending the Actor Class

### Set 6

* Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 98~99
    > if(!gr.isValid(next))
    > 	return false;
    > ```
    >
    > 该语句确保下一个位置是有效的。

* Which statement(s) in the canMove method determines that a bug will not walk into a rock?

    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 100~101
    > Actor neighbor = gr.get(next);
    > return (neighbor == null) || (neighbor instanceof Flower);
    > ```
    >
    > 这两个语句确保一个bug只会移动到未被占据或被一朵花占据的位置。

* Which methods of the Grid interface are invoked by the canMove method and why?

    > isValid 和 get.
    >
    > isValid 方法是确保了下一个位置是网格中的一个有效位置。
    >
    > get 方法查看该位置中的对象，以确保它是空的或它包含的 actor 可以被 bug 替换。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 98~100
    > if (!gr.isValid(next))
    > return false;
    > Actor neighbor = gr.get(next);
    > ```

* Which method of the Location class is invoked by the canMove method and why?

    > getAdjacentLocation.
    >
    > 这个方法传入 bug 当前的方向，以查找 bug 接下来可能的位置。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 97~97
    > Location next = loc.getAdjacentLocation(getDirection());
    > ```

* Which methods inherited from the Actor class are invoked in the canMove method?

    > getLocation, getDirection, getGrid.
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 93~97
    > Grid<Actor> gr = getGrid();
    > if (gr == null)
    > return false;
    > Location loc = getLocation();
    > Location next = loc.getAdjacentLocation(getDirection());
    > ```

* What happens in the move method when the location immediately in front of the bug is out of the grid?

    > bug 会把自己从网格中移除。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 78~81
    > if (gr.isValid(next))
    > moveTo(next);
    > else
    > removeSelfFromGrid();
    > ```

* Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

    > loc 变量是需要的。
    >
    > loc 变量在 bug 移动前记录 bug 的位置。
    >
    > loc 变量用于在 bug 移动到新位置后在 bug 的旧位置放置一朵花。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 76~83
    > Location loc = getLocation();
    > Location next = loc.getAdjacentLocation(getDirection());
    > if (gr.isValid(next))
    > moveTo(next);
    > else
    > removeSelfFromGrid();
    > Flower flower = new Flower(getColor());
    > flower.putSelfInGrid(gr, loc);
    > ```

* Why do you think the flowers that are dropped by a bug have the same color as the bug?

    > 这样在花枯萎之前，很容易看出这朵花是哪只虫子掉的，因为虫子和花的颜色是一样的。
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 82~82
    > Flower flower = new Flower(getColor());
    > ```

* When a bug removes itself from the grid, will it place a flower into its previous location?

    > 在 removeSelfFromGrid 方法中，并没有放置花朵。这个方法是从 Actor 类继承的。Actor
    >
    > 不能把花放在原来的地方。
    >
    > 在 Bug 的 Move 方法中，会先调用 removeSelfFromGrid 方法，然后放置一朵花在 bug 原来的位置。代码如下
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 78~83
    > if (gr.isValid(next))
    > 	moveTo(next);
    > else
    > 	removeSelfFromGrid();
    > Flower flower = new Flower(getColor());
    > flower.putSelfInGrid(gr,loc);
    > ```

* Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Bug.java
    > // @line: 82~83
    > Flower flower = new Flower(getColor());
    > flower.putSelfInGrid(gr, loc); //loc is the old location of the bug
    > ```

* If a bug needs to turn 180 degrees, how many times should it call the turn method?

    > 4 次，每次转 45 度。