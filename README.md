# XPlusGame
In this game two players (a human and the computer) take turns putting tiles on a board having n rows and n columns, and to win the game a player needs to put at least k tiles on board positions forming an X-shape or
a + shape. When running the program to play the game we will specify the value of n and the value of k.

An X-shape is formed as follows:

- There is a center tile and four tiles in adjacent board positions along the four corners of the center tile.
- There might be additional tiles in adjacent positions to the four corners of the center tile along its diagonals.

A + shape is formed as follows:
- There is a center tile and four tiles in adjacent positions above, below, to the right, and to the left of the center tile.
- There might be additional tiles in adjacent positions in the same row or in the same column of the center tile.

The human player always starts the game and uses blue tiles; the computer uses red tiles. In each turn the computer examines all free board positions and selects the best one to place a tile there; to do this, each possible play is assigned a score. 
