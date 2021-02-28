# 阶段四 实训总结报告

## 整体体会

本次实训中

* 学习了 Java 语言，学会了配置 Java 环境，java 的打包；
* 学会了 sonar 等工具的简单使用；
* 理解了 GridWorld 的设计；
* 进一步熟悉了 BFS 和 DFS 算法；
* 分析了启发式算法的过程。

自己的不足之处

* 实训前没有学过 Java，上手有点慢；
* 对 GridWorld 的框架设计理解不够深入。



## 细节体会

#### 学习工具

* vim

    自己在之前经常使用 vim，并且为 vim 安装了各种插件如 `YouCompleteMe`。自己的浏览器也按照了 `Vimium` 插件。到现在，已经比较熟悉 vim 的键位了。

* ant

    ant 是 java 的 makefile。用起来很方便。

* junit

    Junit 是个 单元测试框架，可以利用 junit 来进行`测试驱动开发`。

* sonar

    sonar 可以对项目中的代码规范、类、设计等进行全面的分析。

#### 环境配置

* `.profile`

    添加以下内容

    ```shell
    CLASSPATH=.:$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    ```

* `.bashrc`

    添加以下内容

    ```shell
    export ANT_HOME=~/Desktop/ant 
    export PATH=${ANT_HOME}/bin:$PATH
    
    export JUNIT_HOME=~/Desktop/junit
    export CLASSPATH=$CLASSPATH:$JUNIT_HOME/junit.jar:$JUNIT_HOME
    
    export SONAR_HOME=~/Desktop/sonar-3.7.4/bin/linux-x86-64
    export SONAR_RUNNER_HOME=~/Desktop/sonar-runner-2.4
    export PATH=${SONAR_RUNNER_HOME}/bin:$PATH
    ```

* 编译和运行

    ```shell
    # java
    javac -classpath .:./../ImageReader/ImageReader.jar ImageReaderRunner.java
    java -classpath .:./../ImageReader/ImageReader.jar ImageReaderRunner
    
    # junit
    javac -cp .:./../../junit-4.13.jar:./../../hamcrest-ore-1.3.jar:./../ImageReader/ImageReader.jar MyImageProcessorTest.java
    java -cp .:./../../junit-4.13.jar:./../../hamcrest-ore-1.3.jar:./../ImageReader/ImageReader.jar MyImageProcessorTest
    
    # sonar
    sonar-runner
    ```

#### GridWorld

该过程内容是完成一个GridWorld。GridWorld案例提供了一个图形化环境用于可视化对象在二维网格中的交互。

实际上我们改变和控制各种 Actor 的行为，如让 Actor 按特定路径走、让 Actor 一次前进两格、让 Actor 吃掉另外一种 Actor 等。

#### 图片处理

* 学习到了 Bitmap 的读取和改变方式

    * Bitmap 文件组成

        * Head 14 个字节
        * Info 40 个字节
            * 从 Info 的第 4 个字节开始，读取 4 个字节为宽度
            * 从 Info 的第 8 个字节开始，读取 4 个字节为高度
            * 从 Info 的第 14 个字节开始，读取 2 个字节为像素位数
            * 从 Info 的第 20 个字节开始，读取 4 个字节为图像的大小

    * 在读取字节时需要注意 Endian 端序问题

    * 将像素转整数时需要进行移位操作

        ```java
        (255 & 0xff) << 24  
        | (((int)originalRGB[index + 2] & 0xff) << 16)  
        | (((int)originalRGB[index + 1] & 0xff) << 8)  
        | (int)originalRGB[index] & 0xff;
        ```

* 学习到了图片的基本信息

    * 构成图片的三原色可以分别提取出来
    * 根据三原色的比例可以计算出灰色图像

#### 迷宫

* 学习了 DFS

    DFS 的主要是利用了 Stack，DFS 的主要思想为

    ```c++
    int dfs() {
        Type begin;
        stack<Type> q;
        q.push(begin);
        while (!q.empty()) {
            Type t;
            t = q.top();
            q.pop();
            if (conditionA) {
                Type temp;
                q.push(temp);
            }
            ...
            if (conditionEnd) {
                break;
            }
        }
        return 0;
    }
    ```

    在该项目中，Bug 每移动一次，先判断该位置是否是终点。如果是终点，则打印步数和路径信息并结束；如果不是终点则判断能否移动。如果可以移动，进一步判断是否到达了交叉路口，如果到达了交叉路口，则创建新的分支并将分支压入栈中，之后将该位置添加到分支中。如果不能移动，则从分支中弹出当前位置，Bug 转身返回。

* 关于方向预测

    利用轮盘赌算法，用四个整数分别记录向各个方向移动的次数，如果某方向前进则增加某方向对应数值，否则就减少。最后生成一个在 0 到四个整数之和的范围内的随机数，根据随机数落于区间的范围决定向哪个方向前进。



#### 拼图

* 学习了 BFS

    BFS 的主要是利用了 Queue，BFS 的主要思想为

    ```c++
    int bfs() {
        Type begin;
        queue<Type> q;
        q.push(begin);
        while (!q.empty()) {
            Type t;
            t = q.front();
            q.pop();
            if (conditionA) {
                Type temp;
                q.push(temp);
            }
            ...
            if (conditionEnd) {
                break;
            }
        }
        return 0;
    }
    ```

    在该项目中，需要另外创建一个 Hash 表来存储已经访问过的节点。

    ```java
    Set<JigsawNode> hashJigsaw = new HashSet<>(1000);
    // ...
    if (!hashJigsaw.contains(nextNodes[i])) {
        hashJigsaw.add(nextNodes[i]);
    }
    // ...
    ```

* 学习了启发式算法

    本次实训中主要是实现启发式算法的评估函数

    在本项目中，设置了后续节点不正确的数码个数、曼哈顿距离、欧几里得距离三个评估属性

    该函数的需要多次实验调整参数。在计算出各个评估属性的值后，需要调整各个属性的参数以调整出最优参数，经过多次实验，调整出的参数如下

    ```java
    53 * s + 71 * manHatten + 117 * geometric + manHatten % s + manHatten + geometric % s
    ```