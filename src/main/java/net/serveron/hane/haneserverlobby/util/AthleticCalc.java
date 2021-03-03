package net.serveron.hane.haneserverlobby.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AthleticCalc {

    static class StopWatch {
        private final String playerName;
        private final int startTime;
        private final String athleticName;
        public StopWatch(String playerName, String athleticName){
            this.playerName = playerName;
            this.startTime = (int)System.currentTimeMillis()/1000;
            this.athleticName = athleticName;
        }
        public String getPlayerName(){
            return playerName;
        }

        public int getStartTime() {
            return startTime;
        }

        public String getAthleticName() {
            return athleticName;
        }
    }

    private final List<StopWatch> calcList = new ArrayList<>();
    public AthleticCalc(){

    }


    public void playerStart(Player player,String athleticName){
        for(StopWatch stopWatch: calcList){
            if(stopWatch.getPlayerName().equals(player.getName())){
                calcList.remove(stopWatch);
                break;
            }
        }
        calcList.add(new StopWatch(player.getName(),athleticName));
    }

    public int getPlayerTime(Player player,String athleticName){
        int finishTime = (int)System.currentTimeMillis()/1000;

        for(StopWatch stopWatch: calcList){
            if(stopWatch.getPlayerName().equals(player.getName()) && stopWatch.getAthleticName().equals(athleticName)){
                int startTime = stopWatch.getStartTime();
                calcList.remove(stopWatch);
                return finishTime - startTime;
            }
        }
        return -1;
    }

}
