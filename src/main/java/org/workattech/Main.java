package org.workattech;

import org.workattech.Snakeandladder.Model.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = "src/main/java/org/workattech/input.txt";
        Game game = new Game();
        Board gameBoard = new Board();
        gameBoard.initializeBoard(100, new Snake(), new Ladder());
        Game snakesandLadders = game.createGameUsingFile(fileName, gameBoard);
        gameBoard.displayBoard();
        snakesandLadders.startGame(snakesandLadders);




    }
}