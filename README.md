# ChainReaction

This game has been developed as a part of a course project for CSE201 under the guidance of Prof. Vivek Kumar.
It includes the source code (a JavaFX application) along with a Desktop Application made primarily for MacOSX (You can find it as the .app file)

# Game Rules 

1. The gameplay takes place in an  board.
1. For each cell in the board, we define a critical mass. The critical mass is equal to the number of orthogonally adjacent cells. That would be 4 for usual cells, 3 for cells in the edge and 2 for cells in the corner.
1. All cells are initially empty. The Red and the Green player take turns to place "orbs" of their corresponding colors. The Red player can only place an (red) orb in an empty cell or a cell which already contains one or more red orbs. When two or more orbs are placed in the same cell, they stack up.
1. When a cell is loaded with a number of orbs equal to its critical mass, the stack immediately explodes. As a result of the explosion, to each of the orthogonally adjacent cells, an orb is added and the initial cell looses as many orbs as its critical mass. The explosions might result in overloading of an adjacent cell and the chain reaction of explosion continues until every cell is stable.
1. When a red cell explodes and there are green cells around, the green cells are converted to red and the other rules of explosions still follow. The same rule is applicable for other colors.
1. The winner is the one who eliminates every other player's orbs.


## Features to be added soon

- AI for singleplayer mode


