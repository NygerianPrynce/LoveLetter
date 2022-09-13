import java.util.*;
public class Player {
    ArrayList<Integer> player = new ArrayList<Integer>(2);
    public ArrayList<Integer> makePlayer(){
        player.add(0,null);
        player.add(1,null);
        player.add(2,0);
        player.add(3,1);
        player.add(4,0);
        player.add(5,null);
        return player;
    }
    public ArrayList<Integer> refreshPlayer(){
        player.set(0,null);
        player.set(1,null);
        player.set(2,getLoveLetters());
        player.set(3,1);
        player.set(4,0);
        player.set(5,null);
        return player;
    }
    public ArrayList<Integer> addCard(int position, int val){
        player.set(position, val);
        return player;
    }
    public int getDiscardedStrength(){
        return player.get(4);
    }
    public int getAnyCard(int position){
        return player.get(position);
    }
    public int getChancellor(){
        return player.get(5);
    }
    public int getFirstCard(){
        return player.get(0);
    }
    public int getSecondCard(){
        return player.get(1);
    }
    public int getLoveLetters(){
        return player.get(2);
    }
    public String getFullHand(){
        return player.get(0) + "" + player.get(1);
    }
    public String getFullHandChancellor(){
        return player.get(0) + "" + player.get(1) + "" + player.get(5);
    }
    public ArrayList<Integer> addLoveletter(){
        player.set(2, getLoveLetters()+1);
        return player;
    }
    public int getPlayerActivity(){
        return player.get(3);
    }
    public ArrayList<Integer> setPlayerActivity(int a){
        player.set(3,a);
        return player;
    }
    public ArrayList<Integer> returnCard2(int position){
        player.set(position, null);
        return player;
    }
    public ArrayList<Integer> discardCard(int position){
        player.set(position, null);
        player.set(4,getDiscardedStrength()+1);
        return player;
    }
    public ArrayList<Integer> ridChancellor(){
        player.set(5, null);
        return player;
    }
    public ArrayList<Integer> setSpecific(int position, int value){
        player.set(position, value);
        return player;
    }
    public int getAvailableCard(){
        if(player.get(0) == null){
            return player.get(1);
        } else{
            return player.get(0);
        }
    }
    public int getAvailableCardSpot(){
        if(player.get(0) == null){
            return 1;
        } else{
            return 0;
        }
    }
    public int getBlankCardSpot(){
        if(player.get(0) == null){
            return 0;
        } else{
            return 1;
        }
    }
    public int findCountess(){
        if(player.get(0) == 8){
            return 0;
        } else{
            return 1;
        }
    }
    
    
}
