# Stage 1 - 2

#### Exploring Actor State and Behavior

Answer the following questions on Matrix By clicking on a cell containing a bug, flower, or rock, do the following.

- Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.

| Degrees | Compass Direction |
| ------- | ----------------- |
| 0       | North             |
| 45      | NorthEast         |
| 90      | East              |
| 135     | SouthEast         |
| 180     | South             |
| 225     | SouthWest         |
| 270     | West              |
| 315     | NorthWest         |
| 360     | North             |

- Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?

    > 使用 `moveTo` 方法可以将虫子移动到网格中的任何有效位置。当使用 `moveTo` 方法移动虫子时，虫子不会改变它原来的方向。如果要改变虫子的方向，需要使用 `setDirection` 方法或 `turn` 方法。
    >
    > 尝试将虫子移到网格之外无效的位置，程序将导致抛出一个异常`IllegalArgumentException`。

- Change the color of a bug, a flower, and a rock. Which method did you use?

    > `setColor` 方法。

- Move a rock on top of a bug and then move the rock again. What happened to the bug?

    > 当一块石头被移动到虫子的身上时，虫子就会死亡消失。
    >
    > 即使石头之后再移动到其他位置，虫子也不会复活出现。
    >
    > 当新参与者移动到旧参与者占据的网格位置时，旧参与者将从网格中移除。

#### GUI Summary

|                       **Mouse Action**                       |                    **Keyboard Shortcut**                     |                          **Result**                          |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                  Click on an empty location                  | Select empty location with cursor keys and press the **Enter** key |                  Shows the constructor menu                  |
|                Click on an occupied location                 | Select occupied location with cursor keys and press the **Enter** key |                    Shows the method menu                     |
|       Select the **Location** -\> **Delete** menu item       |                   Press the **Delete** key                   | Removes the occupant in the currently selected location from the grid |
|                 Click on the **Step** button                 |               Press the **Ctrl**+**N** keys N                |                   Calls act on each actor                    |
|                 Click on the **Run** button                  |               Press the **Ctrl**+**R** keys R                | Starts run mode (in run mode, the action of the **Step** button is carried out repeatedly) |
|                 Click on the **Stop** button                 |               Press the **Ctrl**+**T** keys T                |                        Stops run mode                        |
|             Adjust the **Slow**/**Fast** slider              |        Press the **Ctrl**+**-** / **Ctrl**+**+** keys        |                  Changes speed of run mode                   |
| Select the **Location** -\> **Zoom in**/**Zoom out** menu item |     Press the **Ctrl**+**PgUp** / **Ctrl**+**PgDn** keys     |                 Zooms grid display in or out                 |
|             Adjust the scroll bars next to grid              |            Move the location with the cursor keys            | Scrolls to other parts of the grid (if the grid is too large to fit inside the window) |
|       Select the **World** -\> **Set** grid menu item        |               Press the **Ctrl**+**E** keys E                |         Changes between bounded and unbounded grids          |
|         Select the **World** -\> **Quit** menu item          |               Press the **Ctrl**+**Q** keys Q                |                       Quits GridWorld                        |