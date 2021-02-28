# Part 3 Test Report

## 测试代码编写

### 测试 1

Jumper 能否跳出网格外

```java
@Test
public void testCanJumpOut() {
    Jumper jumper = new Jumper();
    world.add(new Location(0, 9), jumper);
    assertEquals(false,  jumper.canJump());
}
```

### 测试 2

Jumper 前方第一个单元格中有花

```java
@Test
public void testCanJumpFlower1() {
    Jumper jumper = new Jumper();
    Flower flower = new Flower();
    world.add(new Location(2, 3), jumper);
    world.add(new Location(1, 3), flower);
    assertEquals(true, jumper.canJump());
}
```

### 测试 3

Jumper 前方第二个单元格中有花

```java
@Test
public void testCanJumpFlower2() {
    Jumper jumper = new Jumper();
    Flower flower = new Flower();
    world.add(new Location(2, 3), jumper);
    world.add(new Location(0, 3), flower);
    assertEquals(true,  jumper.canJump());
}
```

### 测试 4

Jumper 前方第一个单元格中有石头

```java
@Test
public void testCanJumpRock1() {
    Jumper jumper = new Jumper();
    Rock rock = new Rock();
    world.add(new Location(2, 3), jumper);
    world.add(new Location(1, 3), rock);
    assertEquals(true, jumper.canJump());
}
```

### 测试 5

Jumper 前方第二个单元格中有石头

```java
@Test
public void testCanJumpRock2() {
    Jumper jumper = new Jumper();
    Rock rock = new Rock();
    world.add(new Location(2, 3), jumper);
    world.add(new Location(0, 3), rock);
    assertEquals(false,  jumper.canJump());
}
```



## 运行测试

### import 测试头文件

```java
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
```

### 在测试类中添加 main 函数

```java
public static void main(String args[]) {
    junit.textui.TestRunner.run(JumperTest.class);
}
```

### 编译运行

编译

```shell
javac -cp .:../../junit-4.13.jar:../../hamcrest-ore-1.3.jar:../../gridworld.jar JumperTest.java
```

运行

```shell
java -cp .:../../junit-4.13.jar:../../hamcrest-ore-1.3.jar:../../gridworld.jar JumperTest 
```