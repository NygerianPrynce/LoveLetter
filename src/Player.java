import java.util.*;
public class Player {
    ArrayList<Integer> player = new ArrayList<Integer>(2);
    ArrayList<Integer> discardPile = new ArrayList<Integer>(2);
    public Player( Integer a){
        player.add(0,-1);
        player.add(1,-1);
        player.add(2,0);
        player.add(3,1);
        player.add(4,0);
        player.add(5,-1);
        player.add(6,a);
        player.add(7,1);
        //6 is the "player number - essentially fake - what is put on the screen"
        //7 is dead dead or alive
    }
    public boolean getPlayerLife(){
        return player.get(7) == 1;
    }
    public void setPlayerDead(){
        player.set(7,0);
    }
    public ArrayList<Integer> getDiscardPile(){
        return discardPile;
    }
    public ArrayList<Integer> addPlayerNumber(int number){
        player.set(6,number);
        return player;
    }
    public Integer getPlayerNumber(){
        return player.get(6);
    }
    public ArrayList<Integer> refreshPlayer(){
        player.set(0,-1);
        player.set(1,-1);
        player.set(2,getLoveLetters());
        player.set(3,1);
        player.set(4,0);
        player.set(5,-1);
        player.set(7,1);
        discardPile.clear();
        return player; 
    }
    public ArrayList<Integer> addCard(int position, int val){
        player.set(position, val);
        return player;
    }
    public Integer getDiscardedStrength(){
        return player.get(4);
    }
    public Integer getAnyCard(int position){
        return player.get(position);
    }
    public Integer getChancellor(){
        return player.get(5);
    }
    public Integer getFirstCard(){
        return player.get(0);
    }
    public Integer getSecondCard(){
        return player.get(1);
    }
    public Integer getLoveLetters(){
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
    public Integer getPlayerActivity(){
        return player.get(3);
    }
    public ArrayList<Integer> setPlayerActivity(int a){
        player.set(3,a);
        return player;
    }
    public ArrayList<Integer> returnCard2(int position){
        player.set(position, -1);
        return player;
    }
    public ArrayList<Integer> discardCard(int position){
        discardPile.add(getAnyCard(position));
        player.set(position, -1);
        player.set(4,getDiscardedStrength()+1);
        return player;
    }
    public ArrayList<Integer> ridChancellor(){
        player.set(5, -1);
        return player;
    }
    public ArrayList<Integer> setSpecific(int position, int value){
        player.set(position, value);
        return player;
    }
    public Integer getAvailableCard(){
        if(player.get(0) == -1){
            return player.get(1);
        } else{
            return player.get(0);
        }
    }
    public Integer getAvailableCardSpot(){
        if(player.get(0) == -1){
            return 1;
        } else{
            return 0;
        }
    }
    public Integer getBlankCardSpot(){
        if(player.get(0) == -1){
            return 0;
        } else{
            return 1;
        }
    }
    public Integer findCountess(){
        if(player.get(0) == 8){
            return 0;
        } else{
            return 1;
        }
    }
    
    
}
