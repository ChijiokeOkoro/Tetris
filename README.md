# Tetris
Simulates the popular game of Tetris with 3 Main classes: Tetris_Main, GameRunner, and Pieces.

Tetris_Main: The tester class that displays the game on a JFrame, creates the bounds for the game, and creates a GameRunner object. 

GameRunner: The class that has contains the methods used to keep track of all the pieces on the board and handles the interacts of pieces with the board and each other. 
Performs actions depending on the keys clicked on the board and continually updates the board for the user to see. Updates the board and score depending on the amount of lines
that are cleared. 

Pieces: This class creates the objects needed for the pieces to be used on the tetris board. Each piece created has 4 positions on the board and methods drawShape, rotateZ and 
rotateX. 
    DrawShape -  uses the 4 positions, depending on a specific piece, and draws it on a board by using the function fillRect of the Color class. 
    rotateZ - changes the 4 positions of a piece whenever a user presses the "Z" button
    rotateX - changes the 4 positions of a piece whenever a user presses the "X" button
