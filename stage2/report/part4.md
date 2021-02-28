# Part4：Interacting Objects

## The Critter Class

### Set 7

**The source code for the Critter class is in the critters directory**

* What methods are implemented in Critter?

    > act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove  
    >
    > ```java
    > // @file: info/gridworld/actor/Critter.java
    > // @line: 38, 56, 71, 88, 104, 125
    > public void act()
    > public ArrayList<Actor> getActors()
    > public void processActors(ArrayList<Actor> actors)
    > public ArrayList<Location> getMoveLocations()
    > public Location selectMoveLocation(ArrayList<Location> locs)
    > public void makeMove(Location loc)
    > ```

* What are the five basic actions common to all critters when they act?

    > getActors, processActors, getMoveLocations, selectMoveLocation, makeMove  
    >
    > ```java
    > // @file: info/gridworld/actor/Critter.java
    > // @line: 56, 71, 88, 104, 125
    > public ArrayList<Actor> getActors()
    > public void processActors(ArrayList<Actor> actors)
    > public ArrayList<Location> getMoveLocations()
    > public Location selectMoveLocation(ArrayList<Location> locs)
    > public void makeMove(Location loc)
    > ```

* Should subclasses of Critter override the getActors method? Explain.

    > 应该重写，如果critter 的子类和critter类分别从不同的位置选择actor，就必须重写此方法。

* Describe the way that a critter could process actors.

    > critter 可以吃掉列表中的所有 actors  
    >
    > critter 可以让列表中的所有 actors 全部改变颜色
    >
    > critter 可以让列表中的所有 actors 全部移动
    >
    > ```java
    > // @file: info/gridworld/actor/Critter.java
    > // @line: 71~78
    > public void processActors(ArrayList<Actor> actors)
    > {
    >  for (Actor a : actors)
    >  {
    >      if (!(a instanceof Rock) && !(a instanceof Critter))
    >          a.removeSelfFromGrid();
    >  }
    > }
    > ```

* What three methods must be invoked to make a critter move? Explain each of these methods.

    > getMoveLocations, selectMoveLocation, makeMove  
    >
    > * 首先，act 方法调用 getMoveLocations 方法。对于基本 critter，此方法返回该 critter周围所有空相邻位置的列表。
    > * 收到可能的空位置列表后，selectMoveLocation 随机选择其中一个位置并返回该位置。如果没有可供选择的空位置，则 selectMoveLocation 将返回该生物的当前位置。
    > * 收到返回的位置后，makeMove 方法将 critter 移动到新的位置。
    >
    > ```java
    > // @file: info/gridworld/actor/Critter.java
    > // @line: 38~47
    > public void act()
    > {
    >     if (getGrid() == null)
    >         return;
    >     ArrayList<Actor> actors = getActors();
    >     processActors(actors);
    >     ArrayList<Location> moveLocs = getMoveLocations();
    >     Location loc = selectMoveLocation(moveLocs);
    >     makeMove(loc);
    > }
    > ```

* Why is there no Critter constructor?

    > Actor 类有一个默认构造函数。
    >
    > 在 Java 中，如果没有在类中创建构造函数，Java会创建一个默认构造函数。
    >
    > Java 提供的 Critter 默认构造函数将调用 super()，super() 将调用 Actor 默认构造函数。Actor默认构造函数将生成一个 north-blue-critter。
    >
    > ```java
    > // @file: info/gridworld/actor/Actor.java
    > // @line: 39~45
    > public Actor()
    > {
    >  color = Color.BLUE;
    >  direction = Location.NORTH;
    >  grid = null;
    >  location = null;
    > }
    > ```

## Extending the Critter Class

### Set 8

**The source code for the ChameleonCritter class is in the critters directory**

* Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

    > act方法调用getActors、processActors、getMoveLocations、selectMoveLocation和makeMove。ChameleonCritter  类覆盖processActors和makeMove方法。因此，变色龙的召唤行为与变色龙的召唤行为会产生不同的行为。
    >
    > critter 通过移除除石头或 critter 以外的任何邻居来处理它的 actor。ChameleonCritter 随机选择它的一个邻居，获得邻居的颜色，然后把自己的颜色变成它邻居的颜色。ChameleonCritter 调用 makeMove 时，它首先面向下一个位置的方向，然后移动，移动时不会改变方向。
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/ChameleonCritter.java
    > //@line: 36~45
    > public void processActors(ArrayList<Actor> actors)
    > {
    >  int n = actors.size();
    >  if (n == 0)
    >      return;
    >  int r = (int) (Math.random() * n);
    > 
    >  Actor other = actors.get(r);
    >  setColor(other.getColor());
    > }
    > ```

* Why does the makeMove method of ChameleonCritter call super.makeMove?

    > ChameleonCritter 的 makeMove 方法首先改变它的方向以面对它的新位置。然后它调用ssuper.makeMove  使 Critter 类移动到新位置。当它改变方向后，它的 makeMove 就和Critter 一样。
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/ChameleonCritter.java
    > //@line: 50~54
    > public void makeMove(Location loc)
    > {
    >  setDirection(getLocation().getDirectionToward(loc));
    >  super.makeMove(loc);
    > }
    > ```

* How would you make the ChameleonCritter drop flowers in its old location when it moves?

    > 修改makeMove方法使得花落在旧位置。
    >
    > 这需要一个变量来跟踪 ChameleonCritter 的当前位置。当小动物移动后，只有当小动物真的移动到一个新的位置时，才把一朵花放在它原来的位置。参见下面修改的makeMove方法
    >
    > ```java
    > public void makeMove(Location loc)
    > {
    > Location oldLoc = getLocation();
    > setDirection(getLocation().getDirectionToward(loc));
    > super.makeMove(loc);
    > if(!oldLoc.equals(loc)) //don't replace yourself if you did not move
    > {
    >   Flower flo = new Flower(getColor());
    >   flo.putSelfInGrid(getGrid(), oldLoc);
    > }
    > }
    > ```
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/ChameleonCritter.java
    > //@line: 50~54
    > public void makeMove(Location loc)
    > {
    > setDirection(getLocation().getDirectionToward(loc));
    >  super.makeMove(loc);
    > }
    > ```

* Why doesn’t ChameleonCritter override the getActors method?

    > 因为它处理的actor列表与它的基类Critter处理的相同。ChameleonCritter 没有为getActors 定义新的行为，所以它不需要重写这个方法。
    >
    > ```java
    > // @file: info/gridworld/actor/Citter.java
    > //@line: 71~78
    > public void processActors(ArrayList<Actor> actors)
    > {
    >  for (Actor a : actors)
    >  {
    >      if (!(a instanceof Rock) && !(a instanceof Critter))
    >          a.removeSelfFromGrid();
    >  }
    > }
    > ```

* Which class contains the getLocation method?

    > Actor类包含getLocation方法。所有Actor子类都继承这个方法
    >
    > ```java
    > // @file: info/gridworld/actor/Actor.java
    > //@line: 102~105
    > public Location getLocation()
    > {
    >  return location;
    > }
    > ```

* How can a Critter access its own grid?

    > critter可以通过调用从Actor类继承的getGrid方法来访问它的网格
    >
    > ```java
    > // @file: info/gridworld/actor/Actor.java
    > //@line: 92~95
    > public Grid<Actor> getGrid()
    > {
    >  return grid;
    > }
    > ```

## Another Critter

### Set 9

**The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.**

* Why doesn’t CrabCritter override the processActors method?

    > CrabCritter通过吃掉调用getActors时返回的所有邻居来处理它的actor。这与它从基类Critter继承的行为相同。没有必要重写此方法
    >
    > ```java
    > // @file: info/gridworld/actor/Critter.java
    > //@line: 71~78
    > public void processActors(ArrayList<Actor> actors)
    > {
    >  for (Actor a : actors)
    >  {
    >      if (!(a instanceof Rock) && !(a instanceof Critter))
    >          a.removeSelfFromGrid();
    >  }
    > }
    > ```

* Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

    > CrabCritter的getActors方法只查找紧靠 CrabCritter 前面的邻居以及它的右前方和左前方位置。调用processActors方法时，在这些位置找到的任何邻居都将被“吃掉”。在其他邻近位置的 Actor 不会受到干扰。
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/CrabCritter.java
    > //@line: 44~57
    > public ArrayList<Actor> getActors()
    > {
    >  ArrayList<Actor> actors = new ArrayList<Actor>();
    >  int[] dirs =
    >  { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    >  for (Location loc : getLocationsInDirections(dirs))
    >  {
    >      Actor a = getGrid().get(loc);
    >      if (a != null)
    >          actors.add(a);
    >  }
    > 
    >  return actors;
    > }
    > ```

* Why is the getLocationsInDirections method used in CrabCritter?

    > 此方法的参数引入了方向数组。对于 CrabCritter，这个数组包含 CrabCritter 可能吃掉的邻居的方向。getLocationsInDirections方法使用这个数组来确定并返回这个生物在数组参数给出的方向上的有效相邻位置。
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/CrabCritter.java
    > //@line: 101~114
    > public ArrayList<Location> getLocationsInDirections(int[] directions)
    > {
    >  ArrayList<Location> locs = new ArrayList<Location>();
    >  Grid gr = getGrid();
    >  Location loc = getLocation();
    > 
    >  for (int d : directions)
    >  {
    >      Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
    >      if (gr.isValid(neighborLoc))
    >          locs.add(neighborLoc);
    >  }
    >  return locs;
    > }    
    > ```

* If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

    > (4,3), (4,4), (4,5)  
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/CrabCritter.java
    > // @line: 47~48
    > int[] dirs = { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    > ```

* What are the similarities and differences between the movements of a CrabCritter and a Critter?

    > 相似点:当小动物和蟹类动物移动时，它们不会转向它们移动的方向。它们都从可能的移动位置列表中随机选择下一个位置。
    >
    > 区别:螃蟹只会向左或向右移动。一个动物可能的移动位置是它的八个相邻位置中的任何一个。当蟹虫不能移动时，它会随机地左右转。当动物不能移动时，它不会转身。
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/CrabCritter.java
    > // @line: 65~71
    > int[] dirs = 
    > 	{ Location.LEFT, Location.RIGHT };
    > for (Location loc : getLocationsInDirections(dirs))
    >  if (getGrid().get(loc) == null)
    >      locs.add(loc);
    > return locs;
    > 
    > // @file: GridWorldCode/framework/info/gridworld/actor/Critter.java
    > // @line: 90
    > return getGrid().getEmptyAdjacentLocations(getLocation());
    > ```

* How does a CrabCritter determine when it turns instead of moving?

    > 如果makeMove中的参数loc等于crab critter的当前位置，那么它就会转向而不是移动。
    >
    > ```java
    > // @file: GridWorldCode/projects/critters/CrabCritter.java
    > //@line: 79~88
    > if (loc.equals(getLocation()))
    > {
    >  double r = Math.random();
    >  int angle;
    >  if (r < 0.5)
    >      angle = Location.LEFT;
    >  else
    >      angle = Location.RIGHT;
    >  setDirection(getDirection() + angle);
    > }
    > ```

* Why don’t the CrabCritter objects eat each other?

    > 螃蟹从critter类继承processActors方法。这个方法只删除既不是岩石也不是怪物的actor。因为螃蟹是一种小动物，所以螃蟹不会吃其他的小动物
    >
    > ```java
    > // @file: GridWorldCode/framework/info/gridworld/actor/Critter.java
    > //@line: 75~76
    > if (!(a instanceof Rock) && !(a instanceof Critter))
    >  a.removeSelfFromGrid();
    > ```