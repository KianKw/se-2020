# Stage 1 - 1

Answer the following questions.

- Does the bug always move to a new location? Explain.

    > 不。只有当网格为空或者网格中有一朵花的标志时，虫子才会移动到它前面的位置。

- In which direction does the bug move?

    > 虫子会首选向前移动。

- What does the bug do if it does not move?

    > 当虫子不能移动时，它会向右转45度。

- What does a bug leave behind when it moves?

    > 当虫子移动到一个新的网格时，它会在旧的网格里留下一朵和自己颜色相同花。

- What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as whether the bug is facing some other direction when answering this question.)

    > 当一只虫子正在网格边缘时，如果它并被告知 `act`，将会向右转 45 度。当被告知再次行动时，它会再向右转 45 度。
    >
    > 当一只虫子正在网格边缘时，如果它并被告知 `move`，它会被移除，一朵花会留在刚刚虫子的位置。

- What happens when a bug has a rock in the location immediately in front of it?

    > 虫子右转 45 度。

- Does a flower move?

    > 花不会移动的。

- What behavior does a flower have?

    > 随着时间的变化，花会逐渐枯萎，花的颜色会逐渐变深，直到变成深灰色。

- Does a rock move or have any other behavior?

    > 不会。当使用 `step` 或 `run` 按钮时，石头没有任何行为，保持在它的位置不动。

- Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?

    > 不能。在同一时间点，网格中的一个位置只能包含一个参与者。