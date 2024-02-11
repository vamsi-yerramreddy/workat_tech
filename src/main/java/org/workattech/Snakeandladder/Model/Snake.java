package org.workattech.Snakeandladder.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Snake {
    //We are using a map to represent the snake.
    // The key is the head of the snake and the value is the tail of the snake.
    private Map<Integer,Integer> snakes;
    private Set<Integer> sets;

    public Snake(){
        this.snakes= new HashMap<>();
        this.sets = new HashSet<>();
    }
    public void addSnake(int head, int tail){
        snakes.put(head,tail);
        sets.add(tail);
    }
    public boolean isTail(int position){
        return sets.contains(position);
    }
    public void addTail(int position){
        sets.add(position);
    }
    public boolean isHead(int position){
        return snakes.containsKey(position);
    }

    public Set<Integer> getSets() {
        return sets;
    }

    public void setSets(Set<Integer> sets) {
        this.sets = sets;
    }

    public void printSnakes(){
        snakes.forEach((head,tail) -> System.out.println("Head: "+head+" Tail: "+tail));
    }

    public int handleSnakeBite(int position, Player player){
        if(snakes.containsKey(position)){
            int tail = snakes.get(position);
            System.out.println(player + "was bitten by snake & moved from" +position+" to "+tail);
            return tail;
        }else{
            return position;
        }
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }
    public void setSnakes(Map<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

}
