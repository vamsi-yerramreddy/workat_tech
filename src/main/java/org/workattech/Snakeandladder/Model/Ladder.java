package org.workattech.Snakeandladder.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ladder {
   private Map<Integer, Integer> ladders;
   private Set<Integer> ladderTails;


   public Ladder(){
       this.ladders= new HashMap<>();
       this.ladderTails= new HashSet<>();
   }
   public boolean isLadderEnd(int position){
       return ladderTails.contains(position);
   }
   public void addLadderEnd(int position){
       ladderTails.add(position);
   }
   public boolean isLadderStart(int position){
       return ladders.containsKey(position);
   }

    public Set<Integer> getLadderTails() {
        return ladderTails;
    }

    public void setLadderTails(Set<Integer> ladderTails) {
        this.ladderTails = ladderTails;
    }

    public void addLadder(int start, int end){
       ladders.put(start,end);
       ladderTails.add(end);
   }

   public int handleLadder(int position, Player player){
       if(ladders.containsKey(position)) {
           int tail = ladders.get(position);
           System.out.println( player.getName() + "climbed ladder from " + position + " to " + tail);
           return tail;
       }else{
           return position;
       }
   }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setLadders(Map<Integer, Integer> ladders) {
        this.ladders = ladders;
    }

    public void printLadders() {
        ladders.forEach((start,end) -> System.out.println("Start ladder: "+start+" End: "+end));
    }
}
