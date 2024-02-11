package org.workattech.Snakeandladder.Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> boardCells;
    private Snake snakes;
    private Ladder ladders;
    private  int size=100;
    public Board(){
        this.boardCells = new ArrayList<>();
    }

    public Board initializeBoard(int size, Snake snakes, Ladder ladders){
        int rows = size/10;
        int cols = size/10;
        for(int i=0;i<rows  ;++i){
            this.boardCells.add(new ArrayList<>());
            for(int j=0;j<cols;++j){
                boardCells.get(i).add(new Cell(i,j));
                boardCells.get(i).get(j).setPosition(i*10+j+1);
            }
        }
          this.snakes = snakes;
          this.ladders = ladders;
          this.size = size;


        return this;
    }



 public void displayBoard(){
        int tail= 0;
        int ladderEnd = 0;
        System.out.println("Status  of the board:");
        for(int i=0;i<boardCells.size();++i){
            for(int j=0;j<boardCells.get(i).size();++j) {
                int position = boardCells.get(i).get(j).getPosition();
                if (snakes.isHead(position)) {
                    System.out.print("SH" + " ");
                } else if (ladders.isLadderStart(position)) {
                    System.out.print("LH" + " ");
                } else if (snakes.isTail(position)) {
                    System.out.print("ST" + " ");
                } else if (ladders.isLadderEnd(position)) {
                    System.out.print("LE" + " ");
                } else {
                    System.out.print(position + " ");
                }
            }
            System.out.println();
        }
    }


    public List<List<Cell>> getBoardCells() {
        return boardCells;
    }

    public void setBoardCells(List<List<Cell>> boardCells) {
        this.boardCells = boardCells;
    }

    public Snake getSnakes() {
        return snakes;
    }

    public void setSnakes(Snake snakes) {
        this.snakes = snakes;
    }

    public Ladder getLadders() {
        return ladders;
    }

    public void setLadders(Ladder ladders) {
        this.ladders = ladders;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
