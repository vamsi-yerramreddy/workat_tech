package org.workattech.Snakeandladder.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game(Board board, List<Player> players, GameStatus gameStatus) {
        this.board = board;
        this.players=players;
        this.gameStatus = gameStatus;
    }

    public Game() {
        this.board = new Board();
        this.players= new ArrayList<>();
        this.gameStatus = GameStatus.NOT_STARTED;
    }


    public Game createGameUsingFile(String fileName,Board board) {
        Snake snakes = board.getSnakes();
        Ladder ladders = board.getLadders();
        List<Player> players = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int numberOfSnakes= scanner.nextInt();
            for(int i=0;i<numberOfSnakes;i++){
                int head = scanner.nextInt();
                int tail = scanner.nextInt();
                snakes.addSnake(head,tail);
            }
            int numberOfLadders= scanner.nextInt();
            for(int i=0;i<numberOfLadders;i++){
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                ladders.addLadder(start,end);
            }
            int numberOfPlayers= scanner.nextInt();
            for (int i=0;i<numberOfPlayers;i++){
                String name = scanner.next();
                players.add(new Player(name));
            }
            scanner.close();
            Game game = new Game(board,players,GameStatus.NOT_STARTED);
            return game;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return  null;
    }

    public void startGame(Game game ) {
        this.board = game.getBoard();
        this.players = game.getPlayers();
        this.gameStatus = game.getGameStatus();

        int currentPlayerIndex = 0;

        while(gameStatus!= GameStatus.FINISHED){
            System.out.println("It's " + players.get(currentPlayerIndex).getName() + "'s turn. " +
                    "Press r to roll the dice , q to quit the game & d to display the board.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if(input.equals("q")){
                System.out.println("Thanks for playing the game. Exiting the game");
                gameStatus = GameStatus.FINISHED;
                break;
            }
            if(input.equals("d")){
                board.displayBoard();
            }
            else if(input.equals("r")) {
                System.out.println("Rolling the dice");
                int getDiceValue = rollDice();
                Player currentPlayer = players.get(currentPlayerIndex);
                int currPosition = currentPlayer.getPosition();
                int newPosition = currPosition + getDiceValue;

                if (board.getSnakes().handleSnakeBite(newPosition, currentPlayer) != newPosition) {
                    newPosition = board.getSnakes().handleSnakeBite(newPosition, currentPlayer);
                    currentPlayer.setPosition(newPosition);
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

                } else if (board.getLadders().handleLadder(newPosition, currentPlayer) != newPosition) {
                    newPosition = board.getLadders().handleLadder(newPosition, currentPlayer);
                    currentPlayer.setPosition(newPosition);
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

                } else if (newPosition == board.getSize()) {
                    System.out.println(currentPlayer.getName() + " wins the game");
                    gameStatus = GameStatus.FINISHED;
                    break;
                } else if (newPosition > board.getSize()) {
                    System.out.println("since its more than size of board. It's not valid position");
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                } else {
                    System.out.println(currentPlayer.getName() + " rolled a " + getDiceValue + " and moved from " + currPosition + " to " + newPosition);
                    currentPlayer.setPosition(newPosition);
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                }
            }

        }
    }

    public int rollDice() {
        return (int) (Math.random() * 6 + 1);
    }

}
