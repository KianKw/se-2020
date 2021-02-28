# Part5：Grid Data Structures

## Step1：The AbstractGrid Class

### Set 10

**The source code for the AbstractGrid class is in Appendix D.**

* Where is the isValid method specified? Which classes provide an implementation of this method?

    > isValid 方法是在 Grid 接口中指定的。BoundedGrid 和 UnboundedGrid 类实现了此方法。
    >
    > ```java
    > //@file: info/gridworld/grid/Grid.java
    > //@line: 50
    > boolean isValid(Location loc);
    > 
    > //@file: info/gridworld/grid/UnboundedGrid.java
    > //@line: 53~56
    > public boolean isValid(Location loc)
    > {
    > return true;
    > }
    > 
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 60~64
    > 
    > public boolean isValid(Location loc)
    > {
    > return 0 <= loc.getRow() && loc.getRow() < getNumRows()
    >   && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    > }
    > ```

* Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

    > 方法 getValidAdjacentLocations 调用isValid方法。
    >
    > 方法 getEmptyAdjacentLocations 和 getOccupiedAdjacentLocations 不会直接调用isValid，它们会通过调用 getValidAdjacentLocations 来间接调用 isValid。
    >
    > 方法 getNeighbors 不直接调用isValid。它通过调用getOccupiedAdjacentLocations 来调用 getValidAdjacentLocations 最终调用到 isValid。
    >
    > ```java
    > //@file: info/gridworld/grid/AbstractGrid.java
    > //@line: 44~45
    > if (isValid(neighborLoc))
    > locs.add(neighborLoc);
    > ```

* Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

    > 方法 getNeighbors调用了 Grid 的 get 方法和 getOccupiedAdjacentLocations 方法。
    >
    > AbstractGrid 类实现了 getOccupiedAdjacentLocations 方法。
    >
    > get 方法是在 BoundedGrid 和 UnboundedGrid 类中实现的。 
    >
    > ```java
    > //@file: info/gridworld/grid/AbstractGrid.java
    > //@line: 31~32
    > for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
    > neighbors.add(get(neighborLoc));
    > 
    > //@file: info/gridworld/grid/AbstractGrid.java
    > //@line: 62~71
    > public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    > {
    > ArrayList<Location> locs = new ArrayList<Location>();
    > for (Location neighborLoc : getValidAdjacentLocations(loc))
    > {
    >   if (get(neighborLoc) != null)
    >       locs.add(neighborLoc);
    > }
    > return locs;
    > }
    > 
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 85
    > public E get(Location loc)
    > 
    > //@file: info/gridworld/grid/UnboundedGrid.java
    > //@line: 66
    > public E get(Location loc)
    > ```

* Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

    > get 方法返回对存储在网格中给定位置的对象的引用，如果该位置不存在对象，则 get 方法会返回 null。
    >
    > getEmptyAdjacentLocations 调用 get 方法并判断返回值是否为 null，如果返回null，则该位置为“空”，该位置会被添加到列表中。
    >
    > 调用 get 方法是检测给定位置是否为空或已占用的唯一方法。 
    >
    > ```java
    > //@file: info/gridworld/grid/AbstractGrid.java
    > //@line: 51~60
    > public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    > {
    > ArrayList<Location> locs = new ArrayList<Location>();
    > for (Location neighborLoc : getValidAdjacentLocations(loc))
    > {
    >   if (get(neighborLoc) == null)
    >       locs.add(neighborLoc);
    > }
    > return locs;
    > }
    > ```

* What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

    > 有效相邻位置的数目将从 8 个减少到 4 个。有效的相邻位置会变成给定位置东、西、南、北的位置。
    >
    > ```java
    > //@file: info/gridworld/grid/AbstractGrid.java
    > //@line: 40~47
    > int d = Location.NORTH;
    > for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
    > {
    > Location neighborLoc = loc.getAdjacentLocation(d);
    > if (isValid(neighborLoc))
    >   locs.add(neighborLoc);
    > d = d + Location.HALF_RIGHT;
    > }
    > ```

## Step2：The BoundedGrid Class

### Set 11

**The source code for the BoundedGrid class is in Appendix D.**

* What ensures that a grid has at least one valid location?

    > 如果行数小于等于 0 或者列数小于等于 0，BoundedGrid 的构造函数将抛出一个IllegalArgumentException异常。  
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 39~46
    > public BoundedGrid(int rows, int cols)
    > {
    > if (rows <= 0)
    >   throw new IllegalArgumentException("rows <= 0");
    > if (cols <= 0)
    >   throw new IllegalArgumentException("cols <= 0");
    > occupantArray = new Object[rows][cols];
    > }
    > ```

* How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

    > occupantArray[0].length
    > getNumCols 返回 occupantArray 第0行中的列数。
    >
    > 构造函数确保每个 BoundedGrid 对象至少有一行和一列
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 53~58
    > public int getNumCols()
    > {
    > // Note: according to the constructor precondition, numRows() > 0, so
    > // theGrid[0] is non-null.
    > return occupantArray[0].length;
    > }
    > ```

* What are the requirements for a Location to be valid in a BoundedGrid?

    > 位置的行坐标必须大于或等于0 且 小于 BoundedGrid 的行数。
    >
    > 位置的列坐标必须大于或等于0 且 小于 BoundedGrid 的列数。 
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 60~64
    > public boolean isValid(Location loc)
    > {
    > return 0 <= loc.getRow() && loc.getRow() < getNumRows()
    >   && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    > }
    > ```

**In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.**

* What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

    > getOccupiedLocations 方法返回 ArrayList \<Location>
    >
    > 时间复杂度为 O(r * c)。必须访问 BoundedGrid 的每一个位置，检查该位置是否被占用。已被占用的位置添加到 ArrayList 末尾只需要 O(1) 复杂度。
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 66~83
    > public ArrayList<Location> getOccupiedLocations()
    > {
    > ArrayList<Location> theLocations = new ArrayList<Location>();
    > 
    > // Look at all grid locations.
    > for (int r = 0; r < getNumRows(); r++)
    > {
    >   for (int c = 0; c < getNumCols(); c++)
    >   {
    >       // If there's an object at this location, put it in the array.
    >       Location loc = new Location(r, c);
    >       if (get(loc) != null)
    >           theLocations.add(loc);
    >   }
    > }
    > 
    > return theLocations;
    > }
    > ```

* What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

    > get方法返回的是 E，它可以存储在 occupantArray 中的任何类型。
    >
    > get 方法需要一个 Location 对象。
    >
    > 时间复杂度为 O(1)。有行坐标和列坐标，访问二维数组的复杂度为 O(1)
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 85~91
    > public E get(Location loc)
    > {
    > if (!isValid(loc))
    >   throw new IllegalArgumentException("Location " + loc
    >                                            + " is not valid");
    >     return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    > }
    > ```

* What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

    > 如果要添加的对象的位置不在网格中，则会抛出 IllegalArgumentException。
    >
    > 如果传入到 put 方法的对象为 null，则会抛出 NullPointerException。
    >
    > put 方法的时间复杂度为 O(1)。 
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 93~105
    > public E put(Location loc, E obj)
    > {
    > if (!isValid(loc))
    >   throw new IllegalArgumentException("Location " + loc
    >                                            + " is not valid");
    >     if (obj == null)
    >         throw new NullPointerException("obj == null");
    > 
    >     // Add the object to the grid.
    >     E oldOccupant = get(loc);
    >     occupantArray[loc.getRow()][loc.getCol()] = obj;
    >     return oldOccupant;
    > }
    > ```

* What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

    > remove 方法返回 E，E 可以是 BoundedGrid 对象中实际存储的任何类型。
    >
    > 如果试图从空位置删除项，则在该位置中存储null，并返回null。在空位置上调用Grid类的remove方法不会报错。
    >
    > remove 方法的时间复杂度为O(1)。 
    >
    > ```java
    > //@file: info/gridworld/grid/BoundedGrid.java
    > //@line: 107~117
    > public E remove(Location loc)
    > {
    > if (!isValid(loc))
    >   throw new IllegalArgumentException("Location " + loc
    >                                            + " is not valid");
    > 
    >     // Remove the object from the grid.
    >     E r = get(loc);
    >     occupantArray[loc.getRow()][loc.getCol()] = null;
    >     return r;
    > }
    > ```

* Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

    > 效率不太理想的是 getOccupiedLocations 方法，它的时间复杂度为 O(r * c)，可以使用 HashMap 来降低 getOccupiedLocations 方法的时间复杂度。
    >
    > put、get、remove 的时间复杂度都是 O(1)，自己没有想到更有效率的实现方法。

## Step3：The UnboundedGrid Class

### Set 12

**The source code for the UnboundedGrid class is in Appendix D.**

* Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

    > Location类必须实现 hashCode 和 equals 方法。如果两个位置调用 equals方法测试结果为 true， hashCode方法处理这两个位置必须返回相同的值。
    >
    > Location 类实现了 Comparable 接口。因此，为了使Location类成为非抽象类，必须实现 compareTo 方法。当 equals 方法被调用时，compareTo 方法应该为测试为真的两个位置返回0。 TreeMap 要求映射的键是可比较的。
    >
    > Location类满足所有这些需求。 
    >
    > ```java
    > //@file: info/gridworld/grid/Location.java
    > //@line: 28
    > public class Location implements Comparable
    > 
    > //@line: 218~221
    > public int hashCode()
    > {
    > return getRow() * 3737 + getCol();
    > }
    > 
    > //@line: 205~212
    > public boolean equals(Object other)
    > {
    > if (!(other instanceof Location))
    >   return false;
    > 
    > Location otherLoc = (Location) other;
    > return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    > }
    > ```

* Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

    > UnboundedGrid 使用 HashMap 作为其数据结构来保存网格中的项。所有非空位置在无边界网格中都是有效的。UnBoundedGrid 的 isValid 方法总是返回 true;它不检查空位置。在Map对象中，null是键的合法值。在UnboundedGrid对象中，null不是有效位置。因此，UnboundedGrid方法get、put和remove必须检查位置参数，并在参数为空时抛出NullPointerException。
    >
    > 调用isValid方法是在使用位置访问 BoundedGrid 中的occupantArray之前的。如果isValid方法中的位置参数为null，尝试访问 loc.getRow() 将会抛出 NullPointerException。如果编写的代码在调用 get、put 和 remove 方法之前没有调用 isValid 方法，那么尝试访问这些方法中的  loc.getRow() 也会抛出 NullPointerException。 

* What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

    > get、put 和 remove 的平均时间复杂度为 O(1)。
    >
    > 如果使用 TreeMap 代替 HashMap，平均时间复杂度将是 O(log n)，其中 n 是网格中被占用位置的数量。 

* How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

    > getOccupiedLocations 方法 返回的对象经常以不同的顺序。
    >
    > HashMap 中的键基于索引放置在哈希表中，索引是通过使用键的hashCode和表的大小计算的。遍历键集时访问键的顺序取决于它在散列表中的位置。
    >
    > TreeMap 将它的键存储在平衡二叉搜索树中，并使用有序遍历来遍历这棵树。键集中的键将按照位置的 compareTo 方法定义的升序访问。 

* Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

    > 可以，map 可以用来实现有界网格。
    >
    > 如果使用 HashMap 来实现有界网格，那么 getOccupiedLocations 的平均时间复杂度将是 O(n)，其中 n 是网格中的项数。
    >
    > 如果边界网格几乎满了，映射实现将使用更多内存，因为映射存储元素和元素的位置。二维数组只存储元素。位置是通过组合每个元素的行索引和列索引生成的。