# Requirements So Far

You have a toy robot that roams around a table top. 
It responds to the following series of commands, that for the time being can be methods on a class.

* Place(x, y, direction_facing) - Places the robot at the position X, Y and facing either North, South, East, or West. If the robot has already been placed, another place command can be called to place the robot at the new location
* Move - Moves the robot forward in the direction they are facing.
* Left - Rotates the robot 90 degrees to the left without changing the position of the robot
* Right - Rotates the robot 90 degrees to the right without changing the position of the robot
* Report - Announces the X, Y position and facing direction of the robot (for now this can just be output to the command line or return a string)
* Clean - Removes any  dirt directly in front of the robot

The tabletop has co-ordinates between (0,0) and (x,y), with x and y read from a file (table_size.txt).
The tabletop also has piles of dirt in it, the co-ordinates of which are read from another file (dirt_positions.txt).

The robot will either accept commands from STDIN, or from a file passed as a parameter to the program.
