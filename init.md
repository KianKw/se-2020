# 命令行过程



在实验室提供虚拟机的基础上

```shell
vim ~/.profile

```

在文件最后插入

```
CLASSPATH=.:$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
```

加载该文件

```shell
source ~/.profile
cd se-2020/dotfiles
cp .bashrc  ~/
cp .vimrc ~/
source ~/.bashrc
source ~/.vimrc
cd $SONAR_HOME
./sonar.sh start
```



编译运行

```shell
cd ./se-2020/stage2/Part2/CircleBug/
javac -classpath .:./../../gridworld.jar CircleBugRunner.java
java -classpath .:./../../gridworld.jar CircleBugRunner

cd ../DancingBug
javac -classpath .:./../../gridworld.jar DancingBugRunner.java
java -classpath .:./../../gridworld.jar DancingBugRunner

cd ../SpiralBug
javac -classpath .:./../../gridworld.jar SpiralBugRunner.java
java -classpath .:./../../gridworld.jar SpiralBugRunner

cd ../ZBug
javac -classpath .:./../../gridworld.jar ZBugRunner.java
java -classpath .:./../../gridworld.jar ZBugRunner

cd ../../Part3/src
javac -classpath .:./../../gridworld.jar JumperRunner.java
java -classpath .:./../../gridworld.jar JumperRunner
```



junit

```shell
javac -cp .:../../junit-4.13.jar:../../hamcrest-ore-1.3.jar:../../gridworld.jar JumperTest.java
java -cp .:../../junit-4.13.jar:../../hamcrest-ore-1.3.jar:../../gridworld.jar org.junit.runner.JUnitCore JumperTest 

javac -classpath .:./../ImageReader/ImageReader.jar ImageReaderRunner.java
java -classpath .:./../ImageReader/ImageReader.jar ImageReaderRunner

javac -cp .:./../../junit-4.13.jar:./../../hamcrest-ore-1.3.jar:./../ImageReader/ImageReader.jar MyImageProcessorTest.java


java -cp .:./../../junit-4.13.jar:./../../hamcrest-ore-1.3.jar:./../ImageReader/ImageReader.jar MyImageProcessorTest

javac -cp .:./../../gridworld.jar MazeBugRunner.java 
```