# Team9
Our demo(s) can be found in the branches "SimpleDemo-branch" and "Demo1-branch".
Both demos work but have different problems and the one that is resolved best will be the demo we expand upon and eventually build the rest of the game off of.

The Simple Demo is a 5x5 game screen where all features work except for the addition of more than 1 towers. Building more than 1 towers has an issue where building additional towers makes the older towers no longer damage the enemy.

The Demo1 is a 10x10 game screen in which the enemy path has different turns. All features are implemented properly except for the player health. The tower glitch as described above also persists.

For the first demo, only the .java files are needed. (The .class can be created by compiling.)
All files ending in .java are needed for the demo except for Point.java. This class is currently only planned to be implemented in the future but is not essential for the demo.

COMPILING THE CODE
Place all files into a folder together. Using either windows command prompt or the OSX/LINUX console to navigate to this folder using the "cd [PathToDirectory]" command. Once in the appropriate directory, type in "javac *.java" to compile the code.

RUNNING THE GAME
To run the demo, first compile the code as described above. Then, in the console whilst in the directory containing all the game files, type in the command "java Game" and the demo will start. You will be greeted by the game's title, TOWER DEFENSE

THE DEMO
Press the Enter key to progress through the demo. At the start, the user will be asked which type of tower they would like to place first and then prompt the user for an X and Y coordinate. The coordinates must be with in 0 - 4 since the map is an array and the first coordinate would be (0,0)
