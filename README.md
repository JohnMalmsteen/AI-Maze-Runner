# Harry Potter and the Triwizard Maze!

Java based Maze game demonstrating AI techniques

![Game](http://i.imgur.com/Rmheada.png)


Contents:
---------
1. About
2. How to Play
3. Tools & Environment used
4. Features
5. Structure
6. How to Deploy

1 - About
---
This game has been developed for my Artificial Intelligence module (Dr. John Healy), the aim of the project is to demonstrate fuzzy logic applications and informed&uninformed graph search algorithms.

2 - How to Play
---
You play the part of Harry Potter in the maze at the end of the Goblet of Fire, you must reach the Triwizard Cup which is located in the center of the maze.

###Key Controls
* Arrow Keys: Move around the maze
* Z: Zoom in/out to see the overview of the maze
* A: Use your Avada Kedavra spell if you have one (this will kill all creatures within ten spaces of you

Beware, there are many enemies within the maze but there are also many aids in your quest:


##### The Sword of Godric Gryffindor

![Sword](http://i.imgur.com/Dw5e4pU.png)
If you can get your hands on the sword of godric gryffindor you will be invincible in your next combat, but beware, you will have to find it again after your fight

##### Mana

![Mana](http://i.imgur.com/swhnJ5c.png)
If you can't find the sword then you may have to fight with your magic, this is always risky business but you can increase your odds by stocking up on mana

You will lose some each time you fight so be sure to stock up, and even if you have brimming supplies, nothing is ever certain, be careful out there. Mana level is a linguistic variable in my fuzzy logic.

##### Avada Kedavra

![Spell](http://i.imgur.com/EoJtPPd.png)
Join the dark side harry! The power is undeniable, this spell will kill all creatures within ten spaces of travel of you, this spell uses a depth limited depth first search.

3 - Tools and Environment Used
---
The only external library that this project uses is the Jfuzzy Logic library. It does require Java 
8 as I have used lambdas and some other features.

4 - Features
---
There are three kinds of enemy in the maze:

##### Blast Ended Skrewt
![Skrewt](http://i.imgur.com/5zYNZGo.png)
The Blast Ended Skrewt is a relatively feeble creature and it isn't very intelligent, you will find many of these in the maze and they move at random within it.

##### Deatheater

![DeathEater](http://i.imgur.com/aydO3bb.png)
The Deatheaters are a more formidable foe, you will find less of these within the maze but they patrol around with some sense of purpose (they are perfoming a depth first traversal of the maze)

###### Voldemort

![Voldemort](http://i.imgur.com/O89Z4UW.png)
Voldemort is your legendary enemy, most fear to even speak his name, he can sense you location and takes a Best First path towards you. He recalculates his path to you every 5 moves or if he is within 5 meters of you. If he finds you, you better be ready to use the dark side against him, have the sword of gryffindor or be absolutely brimming with vim and vigour.

##### Compass

![compass](http://i.imgur.com/BOwYZWN.png)
If you are lucky enough to find one of these within the maze you will be shown the path to your goal for 5 seconds.

The compass spell uses the A* algorithm to compute the optimal path to the triwizard cup.

##### Maze Generation

![Map](http://i.imgur.com/OSYG0Z8.png)
I did a custom maze generation algorithm for this project, I started out using a backtracking recursive depth first spanning tree maze. This maze type has the advantage of making every node accessible but it also means there is only one path to everything so I also added in a function that randomly removes walls between cells.

The composition of my maze is Cell objects which contain refrences to their neighbour cells and whether the connector between them is a wall or a passage.

##### Maze Changing
Beware, the walls of this maze are alive and they will change every 20 seconds, it may open up a path for you to the cup, or it may leave you running a long route to it.

5 - Structure
---
I have sorted my source code into 5 main packages:

* ie.gmit.sw.ai
Here I keep most of the Movement strategies for the enemies, although there is more path finding stuff in ie.gmit.sw.gameassets.Navigator and ie.gmit.sw.threads.KillerSpell

In this package there is also the Fuzzy logic FightResolver.

* ie.gmit.sw.game
In this package I have the GameRunner class which is the main entry point class and deals with user input, I also have the GameView class which is charged with displaying the game.

* ie.gmit.sw.gameassets
Here I have Enemy classes, player class, and Item/Spell classes.

* ie.gmit.sw.maze
Here is the maze generator as well as the node/cell classes used by the path finders etc.

* ie.gmit.sw.threads
All of the enemies are threaded and there are also some other threaded activities (turning on and off paths, effects etc, as well as changing the maze) The Runnable implementations and the Runnable Factor are here.

6 - How to Deploy
---
This project require that you have Java 1.8 and the Jfuzzylogic jar which is downloadable [here](http://jfuzzylogic.sourceforge.net/html/index.html) (I have added this jar file to the github project now for your convenience)
the main class is ie.gmit.sw.game.GameRunner

I have now added the built jar file for convenience, it can be run from the main folder (it still needs to be beside libs/ fcl/ and resources/) 

```sh
$ java -cp Maze.jar:libs/jFuzzyLogic.jar ie.gmit.sw.game.GameRunner
```

