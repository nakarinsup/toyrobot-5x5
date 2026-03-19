# Toy Robot

This is a maven project that target java 17. Ensure you have jdk java 17 installed (and maven) to be able to build and running.

You can either open folder with IntelliJ and run it from there (main class is ToyRobotMain).  
Or using console commandline. The below description explain running from command line.  

To build the project from command line:  
`mvn clean package`

To run the (console) application:  
`java -jar .\target\toyrobot-1.0-SNAPSHOT.jar`


You will see the console screen like this:
```
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

the text graphical show table with 5x5 grid where each cell is possible location can be placed on the table (top view).
You can enter command by typing the command. start with place robot first

for example:  
`PLACE 2,3,EAST`  
every place command will reset the robot location and its heading according to the latest command.  

For compass direction, you can also enter `N`, `E`, `W`, `S` for short. For example:  
`PLACE 2,3,E`  

You can also enter (all) commands in lowercase:  
`place 2,3,east`  
`place 2,3,e`

then you can enter command 
- `MOVE` - move forward
- `LEFT` - turn left 90 degree 
- `RIGHT` - turn right 90 degree 
- `REPORT` - to print out the current robot position and its heading direction


To exit program, can type command as follows:\
`q`\
`quit`\
`exit`

# Example of playing around

start with PLACE command to put the robot on the table

`PLACE 2,3,EAST`

Then this screen will be shown up

```
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |  >  |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

The robot position is placed at position(2,3) (where 0,0 is start from bottom left), and facing toward east direction.
The facing direction is represented by using heading of arrow where
- `^` representing the robot is facing north
- `<` representing the robot is facing west
- `>` representing the robot is facing east
- `v` representing the robot is facing south


then let's try move forward with MOVE command
`MOVE`

The robot will moving forward for one block/cell, as shown below:
```
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |  >  |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

We can send command REPORT to see the current robot's position and heading direction:\
`REPORT`\
Console will print out as follows:\
`Robot current position is : 3, 3, EAST`

Let's turn left:
`LEFT`

The robot heading will facing north
```  
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |  ^  |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

let's report again   
`REPORT`   
console will print the new heading direction:   
`Robot current position is : 3, 3, NORTH`

let's moving forward
`MOVE`
console will update the location:   
``` 
-------------------------------
|     |     |     |  ^  |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program 
```

As the robot is at the edge of the table area, with the current heading toward north, it cannot moving forward further, let's try and see:
`MOVE`

Console will print the error and command is discarded/ignored
`MOVE command is ignored because robot will fall from table!!!`

Let's report again
`REPORT`  
Output:  
`Robot current position is : 3, 4, NORTH`

From here, we can turn the robot to another direction, let turn right   
`RIGHT`  
Output:

``` 
-------------------------------
|     |     |     |  >  |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

Then the robot can move forward (to the east now):
`MOVE`   
Output:
```
-------------------------------
|     |     |     |     |  >  |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

`REPORT`   
Output:   
`Robot current position is : 4, 4, EAST`   

Try move forward further, but cannot because we are at the corner of the table
`MOVE`   
Output:  
`MOVE command is ignored because robot will fall from table!!!`

We can turn it heading, let's turn left:   
`LEFT`   
Output:   
```
-------------------------------
|     |     |     |     |  ^  |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program

```

We cannot moving forward yet, let's see:
`MOVE`   
Output:   
`MOVE command is ignored because robot will fall from table!!!`   

Turn left one more time:   
`LEFT`
Output:   
```
-------------------------------
|     |     |     |     |  <  |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

Let's report position and heading:  
`REPORT`  
Output:  
`Robot current position is : 4, 4, WEST`  

Now we can move forward:   
`MOVE`  
Output:   
``` 
-------------------------------
|     |     |     |  <  |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
|     |     |     |     |     |
-------------------------------
Please enter robot command
PLACE x,y,f
MOVE
LEFT
RIGHT
REPORT
q - quit, for exit program
```

That's all for the demo.



