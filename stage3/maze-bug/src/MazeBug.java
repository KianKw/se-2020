import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    public Location next;
    public Location last  = new Location(0, 0);
    public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    public boolean isEnd = false;
    public ArrayList<Location> branch = new ArrayList<Location>();
    boolean hasArrived = false;// 是否到达目的地
    public Integer step = 0;
    int north = 1;
    int east  = 1;
    int south = 1;
    int west  = 1;

    // MazeBug 的构造函数
    public MazeBug() {
        setColor(Color.GREEN);
    }

    // 移动到下一个位置
    public void act() {
        Location locBefore = getLocation();

        // 获取第一个位置，使栈不为空
        if (step == 0) {
            last = locBefore;
            ArrayList<Location> currentPath = new ArrayList<Location>();
            currentPath.add(last);
            crossLocation.add(currentPath);
        }

        boolean willMove = canMove();
        if (isEnd == true) {

            showPath();
            // 输出到达目的地后的步数
            if (hasArrived == false) {
                String msg = step.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasArrived = true;
            }
        } else if (willMove) {
            // 如果最后一个位置是一个交叉点，则添加一个新路径
            if (getValid(locBefore).size() > 1) {
                ArrayList<Location> newBranch = new ArrayList<Location>();
                crossLocation.push(newBranch);
            }
            move();

            // 将当前位置压入栈
            ArrayList<Location> currentBranch = crossLocation.pop();
            currentBranch.add(getLocation());
            crossLocation.push(currentBranch);
            // 创建了新路径后，改变方向
            if (currentBranch.size() == 1) {
                int dir = (int) ((locBefore.getDirectionToward(getLocation())) / 90);
                switch (dir) {
                    case 0:
                        north++; break;
                    case 1:
                        east ++; break;
                    case 2:
                        south++; break;
                    case 3:
                        west ++; break;
                }
            }
            step++;
        } else {
            // 如果当前路径是不通，则删除该路径，标记该位置已经被访问
            ArrayList<Location> currentBranch = crossLocation.pop();
            currentBranch.remove(currentBranch.size() - 1);
            // 如果当前路径分支不为空，则压入栈
            if (currentBranch.size() > 0) {
                crossLocation.push(currentBranch);
            }
            goBack();

            // 路径已经改变，转换方向
            if (currentBranch.size() == 0) {
                int dir = (int) ((getLocation().getDirectionToward(locBefore)) / 90);
                switch (dir) {
                    case 2:
                        south--; break;
                    case 3:
                        west --; break;
                }
            }
            step++;
        }
    }

    private void showPath() {
        Grid gr = getGrid();
        while (!crossLocation.isEmpty()) {
            ArrayList<Location> currentBranch = crossLocation.pop();
            if (!currentBranch.isEmpty()) {
                for (Location loc : currentBranch) {
                    branch.add(loc);
                }
            }
        }

        for (Location loc : branch) {
            Actor act = (Actor) gr.get(loc);
            act.setColor(Color.GREEN);
        }
    }

    // 找到可以移动到的位置
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return null;
        ArrayList<Location> valid = new ArrayList<Location>();
        
        return valid;
    }

    // 判断能否移动到前面的位置
    public boolean canMove() {
        ArrayList<Location> nexts = getValid(getLocation());
        if (nexts.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private int getRandDir(ArrayList<Location> nexts) {
        Random rand = new Random();
        int sum0 = north;
        int sum1 = sum0 + east;
        int sum2 = sum1 + south;
        int sum3 = sum2 + west;

        int ans = rand.nextInt(sum3);
        if (ans < sum0) {
            ans = 0;
        } else if (ans < sum1) {
            ans = 1;
        } else if (ans < sum2) {
            ans = 2;
        } else {
            ans = 3;
        }
        while (ans > 0 && ans >= nexts.size()) {
            ans--;
        }
        return ans;
    }

    // bug 前进一步，并在刚刚的位置放一朵花
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }

        // 下一个方向随机获取
        Location loc = getLocation();
        ArrayList<Location> nexts = getValid(loc);
        int nextNum = getRandDir(nexts);
        next = nexts.get(nextNum);

        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            Actor locActor = getGrid().get(next);
            if (locActor instanceof Rock && locActor.getColor().equals(Color.RED)) {
                isEnd = true;
            }
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    private void goBack() {
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        ArrayList<Location> currentBranch = crossLocation.peek();
        Location lastLoc = currentBranch.get(currentBranch.size() - 1);

        setDirection(getLocation().getDirectionToward(lastLoc));
        moveTo(lastLoc);

        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}

