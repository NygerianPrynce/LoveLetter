import java.util.*;
public class Deck{
    ArrayList<Integer> deck = new ArrayList<Integer>();
    public ArrayList<Integer> makeDeck(){
        deck.clear();
        deck.add(0);
        deck.add(0);
        
        deck.add(1);
        deck.add(1);
        deck.add(1);
        deck.add(1);
        deck.add(1);
        deck.add(1);
        
        deck.add(2);
        deck.add(2);
        deck.add(3);
        deck.add(3); 
        
        deck.add(4);
        deck.add(4); 
        
        deck.add(5);
        deck.add(5);
        
        deck.add(6);
        deck.add(6);
        deck.add(7);
        deck.add(8);
        deck.add(9);
        return deck;
    }
    public ArrayList<Integer> shuffle(){
        Collections.shuffle(deck);
        return deck;
    }
    public int getCard(int position){
        return deck.get(position);
    }
    public int removeCard(){
        return deck.remove(0);
    }
    public ArrayList<Integer> addCardToDeck(Integer x){
        deck.add(x);
        return deck;
    }
    public int getSize(){
        return deck.size();
    }
    public int burnCard(){
        return deck.remove(0);
    }
}
