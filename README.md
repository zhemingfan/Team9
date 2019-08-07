# Team9
FIREFORCE NO9
FireForce No.9 is a Tower Defense game in which you play as water defending against fire enemies. Enemies spawn at one end of the path
and march towards the other end of the path, ultimately dealing damage to the player's Health Points. The player's objective is to
prevent the waves of enemies from reaching the end by purchasing and placing towers that automatically attack the enemies as they enter
the tower's range. If too many enemies reach the end of the path, it's game over.

PRE-REQUISITES
To best experience FireForce No.9 we recommend using Eclipse to run the project. The Eclipse project will need to have the JUNIT 4 
library.

The Final GUI version is located in this master branch and the final Text-Based Console version is in the branch TextBasedVersion-final.

GETTING STARTED
1. Make a new project in Eclipse and add the JUNIT 4 library. 
2. Download the repo from https://github.com/zhemingfan/Team9 as a zip file and extract the contents to a new folder. 
3. Copy the entire contents of the "src" folder of our repo into the "src" folder of your newly created Eclipse project. 
4. Finally, to play the game, run the file "GameInterface.java" in the "game" package.

OUR JUNIT TEST
To run our JUNIT test ("PlayerTestGUIVERSION.java" in the "game" package) simply right click on the test file and run as a JUNIT test.
The JUNIT test was created for the sake of testing the player class' methods to ensure that the player's stats were properly being
modified by valid sources and weren't dropping into the negative or allowing invalid purchases. We also wanted to validate that the
player's stats would initialize to their default values properly incase someone tried to modify the starting stats to be unbalanced
in terms of gameplay.

TROUBLESHOOTING
A common problem on some devices are several of our import statements being marked as invalid by Eclipse. To fix this, simply go to
"build path" in the Eclipse project's properties and then remove the JRE library. Afterwards, re-add the library (default settings).

If the JUNIT test file is not working, double-check that the JUNIT 4 library is added in the project's build path.

HOW TO PLAY
When the game is first launched you'll be greeted by a start menu with 2 map icons and 2 start buttons. The buttons will send you into
2 different game modes: Survival for endless random waves of enemies and Story for 5 structured waves and a final boss enemy.

1. Click on the map you want to play on.
2. Click on the start button of the mode you wish to play.

The game screen consists of 2 sections: The left section is the game board where the enemies and towers fight are displayed and rage
their conflict. The right section is the player's menu bar which shows, from top to bottom:
1. Player stats (HP and Money)
2. Tower's placement buttons and their stats (Name, price, damage, range)
3. Spell's cast buttons and their stats (Name, price, damage)
4. Pause/Resume button
5. Quit Game button (results in a game over)

Once the game starts, enemies will already be spawning. If you want, you can PAUSE the game and place towers still to have an added
strategic edge. Towers can only be placed on a grass tile. Invalid tower placement attempts will make the clicked tile flash red. If
you can't afford the tower or spell you want, your money stat will flash red.

Place towers strategically to withstand the ever-increasing waves of survival mode or plan ahead to overcome the structured armies
of the winged fire monster.

BUILT WITH
JDK 1.8 - Java development kit
JavaFX - GUI components
JUNIT - Automated testing
Pro-Create - Graphic Design
Eclipse - IDE
Atom - Text Editor
Sublime - Text Editor

AUTHORS
Team9 - Aries Dimaranan, Linh Phan Le Khan, Jeremy Fan, Se Yeon Sim, Jonathan Delos Santos

Graphic Designer - Linh
Sound - Se Yeon, Jeremy, Aries
Programming - Team 9
UML Diagrams - Se Yeon, Linh

ACKNOWLEDGEMENTS
GitHub user PurpleBooth - Thanks for the ReadMe template
Our TA Lorans Alabood - Thanks for pushing us when our initial project idea was too easy
