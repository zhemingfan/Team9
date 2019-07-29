# Team9

The final version for our second demo can be found in the "DEMO2-FINAL" branch.

The files which are NOT needed/are yet to be implemented are the following:
  Wave.java
  Fire.java
  Lava.java
  Spirit.java

COMPILING THE CODE
Place all the folders into a single folder. Navigate to this folder in console and then navigate to the
"src" folder

  Example: cd C:/Admin/Documents/NEWFOLDER/src

Compile all files with the following command: javac *.java

RUNNING THE DEMO
After compiling as described above; While in the same folder in the console, type in the following command: 
  java GameInterface

This will launch the demo

THE DEMO
Seven enemies will automatically start moving from the start of the path to the end. When enemies reach the end,
the players HP will go down. This can be seen at the top of the side menu and in the console (for debugging).

On the side menu are 2 buttons representing the available towers. The user can place a tower on any grass tile
by clicking on a tower button and then clicking on the desired grass tile. The console will also show the 
tower's new existence in the 2D array.
  Currently, only the cannon tower is placeable. This is intentional since the main purpose of this function
  for the demo is to showcase that we are able to place towers at all. Since the towers currently cannot 
  attack enemies, it was decided that we only needed one tower to be placeable for this demo and our time
  was better spent working on making towers and enemies successfully interact.
