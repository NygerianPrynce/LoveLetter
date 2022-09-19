import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.lang.Math;
public class LoveLetterPanel extends JPanel implements MouseListener, KeyListener{
    private BufferedImage Baron, Chancellor, Countess, Guard, Handmaid, King, Priest, Prince, Princess, Spy, ReferenceCard, CardBack, Player1Icon, Player2Icon, Player3Icon, Token, bg;
    public Integer choice;
    public Player pChoice;
    public static Player p1;
    public static Player p2;
    public static Player p3;
    public BufferedImage winner;
    public BufferedImage[] cardFaces;
    public BufferedImage[] playerIcons;
    public ArrayList<Player> playerSpots;
    public ArrayList<Player> playerSpotsPerma;
    public int switcharoo = 0;
    public Deck jit;
    public static boolean protect1;
    public static boolean protect2;
    public static boolean protect3;
    public boolean roundDone;
    public Integer state;
    public ArrayList<Player> spy;
    public Player mainP;
    public Player otherP1;
    public Player otherP2;
    public Integer action;
    public Integer actionG;
    public Integer bam;
    public Boolean next;
    public Integer pressed; 
    public Integer specialChoice;
    public Integer kInput;
    public int gamegame;
    public LoveLetterPanel(){
        try{
            Baron = ImageIO.read(LoveLetterPanel.class.getResource("/images/Baron.png"));
            Chancellor = ImageIO.read(LoveLetterPanel.class.getResource("/images/Chancellor.png"));
            Countess = ImageIO.read(LoveLetterPanel.class.getResource("/images/Countess.png"));
            Guard = ImageIO.read(LoveLetterPanel.class.getResource("/images/Guard.png"));
            Handmaid = ImageIO.read(LoveLetterPanel.class.getResource("/images/Handmaid.png"));
            King = ImageIO.read(LoveLetterPanel.class.getResource("/images/King.png"));
            Priest = ImageIO.read(LoveLetterPanel.class.getResource("/images/Priest.png"));
            Prince = ImageIO.read(LoveLetterPanel.class.getResource("/images/Prince.png"));
            Princess = ImageIO.read(LoveLetterPanel.class.getResource("/images/Princess.png"));
            Spy = ImageIO.read(LoveLetterPanel.class.getResource("/images/Spy.png"));
            ReferenceCard = ImageIO.read(LoveLetterPanel.class.getResource("/images/ReferenceCard.png"));
            CardBack = ImageIO.read(LoveLetterPanel.class.getResource("/images/CardBack.png"));
            Player1Icon = ImageIO.read(LoveLetterPanel.class.getResource("/images/Player1Icon.png"));
            Player2Icon = ImageIO.read(LoveLetterPanel.class.getResource("/images/Player2Icon.png"));
            Player3Icon = ImageIO.read(LoveLetterPanel.class.getResource("/images/Player3Icon.png"));
            Token = ImageIO.read(LoveLetterPanel.class.getResource("/images/Token.png"));
            bg = ImageIO.read(LoveLetterPanel.class.getResource("/images/bg.jpeg"));
        } catch(Exception E){
            System.out.println("Exception error");
            return;
        }
        //Initializes important stuff 4 the player
        p1 = new Player(0);
        p2 = new Player(1);
        p3 = new Player(2);
        ArrayList<Player> random = new ArrayList<Player>();
        random.add(p1);
        random.add(p2);
        random.add(p3);
        playerSpots = new ArrayList<Player>(3);
        playerSpotsPerma = new ArrayList<Player>(3);
        int r = (int)(Math.random()*(3));
        Player z = random.remove(r);
        playerSpots.add(z);
        playerSpotsPerma.add(z);
        r = (int)(Math.random()*(2));
        Player x = random.remove(r);
        playerSpots.add(x);
        playerSpotsPerma.add(x);
        Player y = random.remove(0);
        playerSpots.add(y);
        playerSpotsPerma.add(x);
        //sets up playerSpots 
        pressed = 0;
        //sets up graphic placements in the array for the cardfaces and icons
        cardFaces = new BufferedImage[10];
        playerIcons = new BufferedImage[3];
        cardFaces[0] = Spy;
        cardFaces[1] = Guard;
        cardFaces[2] = Priest;
        cardFaces[3] = Baron;
        cardFaces[4] = Handmaid;
        cardFaces[5] = Prince;
        cardFaces[6] = Chancellor;
        cardFaces[7] = King;
        cardFaces[8] = Countess;
        cardFaces[9] = Princess;
        playerIcons[0] = Player1Icon;
        playerIcons[1] = Player2Icon;
        playerIcons[2] = Player3Icon;
        //Sets choice options to null for clicking 
        choice = 123;
        pChoice = null;
        bam = 0;
        next = false;
        //mouselistener adder
        addMouseListener(this);
        addKeyListener(this);
        //makes new deck
        jit = new Deck();
        jit.makeDeck();
        jit.shuffle();
        jit.burnCard();
        printDeck(jit);
        initialdeal(p1, p2, p3, jit);
        System.out.println("initial deal done");
        System.out.println("full hand - p1");
        System.out.println(p1.getFullHand());
        System.out.println("full hand - p2");
        System.out.println(p2.getFullHand());
        System.out.println("full hand - p3");
        System.out.println(p3.getFullHand());
        //states for the drawer 
        mainP = playerSpots.get(0);
        otherP1 = playerSpots.get(1);
        otherP2 = playerSpots.get(2);
        //sets up for drawer
        //dealDependent(mainP, jit);
        //System.out.println("full hand - mainP");
        //System.out.println(mainP.getFullHand());
        //sets up rounDone to false
        roundDone = false;   
        //sets up stuff for spy
        spy = new ArrayList<Player>();
        //sets up stuff for handmaid
        protect1 = false;
        protect2 = false;
        protect3 = false;
        state = 21;
        repaint();
        action = 0;
        actionG = 0;
        specialChoice = -1;
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){
        kInput = e.getKeyChar() -'0';
        System.out.println(kInput);
        if(actionG == 9 && action == 9 && choice != -1){
            System.out.println("waiting for guess");
            if(kInput == pChoice.getAvailableCard()){
                guard(pChoice);
                state = 2;
                repaint();
                System.out.println("guessed correctly");
            } else{
                state = 3;
                repaint();
            }
            action = 0;
            actionG = 0;
        }

    }

    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
               //new round starter
                 //checks for roundDone
        if(!p1.getPlayerLife() && !p2.getPlayerLife()){
            roundDone = true;
            System.out.println("round done for p3");
        }
        else if(!p2.getPlayerLife() && !p3.getPlayerLife()){
            roundDone = true;
            System.out.println("round done for p1");
        }
        else if(!p1.getPlayerLife() && !p3.getPlayerLife()){
            roundDone = true;
            System.out.println("round done for p2");

        }
      
        if((jit.getSize() == 0 || roundDone) && !loveLetterEnd(p1, p2, p3)){
                System.out.println("Player1 Loveletters:" + p1.getLoveLetters() + " Player2 LoveLetters:" + p2.getLoveLetters() + " Player3 LoveLetters:" + p3.getLoveLetters()); 
                if( !roundDone && ((spy.size() > 0 && spy.size() <2) || (spy.size()>0 && (spy.get(0) == spy.get(1))))){
                    spy.get(0).addLoveletter();
                }
                else if(mustPlay(p1, p2)){
                    p3.addLoveletter();
                }
                else if(mustPlay(p3, p2)){
                    p1.addLoveletter();
                }
                else if(mustPlay(p1, p3)){
                    p2.addLoveletter();
                } 
                //Check who gets loveletter based on power comparison
                else if(!roundDone){
                    int pa = p1.getAvailableCard();
                    int pb = p2.getAvailableCard();
                    int pc = p3.getAvailableCard();
                    if (pa > pb && pa > pc){
                        p1.addLoveletter();
                    } else if( pb > pa && pb > pc){
                        p2.addLoveletter();
                    } else if(pc>pa && pc>pb){
                        p3.addLoveletter();
                    } 
                    //Check who gets loveletter based on discarded power comparison
                    else if(pa == pb){
                        if(p1.getDiscardedStrength() > p2.getDiscardedStrength()){
                            p1.addLoveletter();
                        } else{
                            p2.addLoveletter();
                        }
                    } else if(pb == pc){
                        if(p2.getDiscardedStrength() > p3.getDiscardedStrength()){
                            p2.addLoveletter();
                        } else{
                            p3.addLoveletter();
                        }
                    } else if(pa == pc){
                        if(p1.getDiscardedStrength() > p3.getDiscardedStrength()){
                            p1.addLoveletter();
                        } else{
                            p3.addLoveletter();
                        }
                    } else{}
                }
                roundDone = false;
                //new round set up
                jit.makeDeck();
                jit.shuffle();
                jit.burnCard();
                printDeck(jit);
                p1.refreshPlayer();
                p2.refreshPlayer();
                p3.refreshPlayer();
                initialdeal(p1, p2, p3, jit);
                System.out.println("initial deal done");
                System.out.println("full hand - p1");
                System.out.println(p1.getFullHand());
                System.out.println("full hand - p2");
                System.out.println(p2.getFullHand());
                System.out.println("full hand - p3");
                System.out.println(p3.getFullHand());
                choice = -1;
                state = 15; 
                repaint();
                pressed = 0;
                System.out.println("gamegame");
            }
            //game over
        if(loveLetterEnd(p1, p2, p3)){
                Player dub = findWinner(playerSpots);
                winner = playerIcons[dub.getPlayerNumber()];
                state = 0;
                repaint();
                System.out.println("Player1 Loveletters:" + p1.getLoveLetters() + " Player2 LoveLetters:" + p2.getLoveLetters() + " Player3 LoveLetters:" + p3.getLoveLetters()); 
            }
    
        int[][] playableCardSizes = {{getWidth()/2-getWidth()/5-getWidth()/82, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()/2 - getWidth()/16, getHeight()-getHeight()/3, getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()-getWidth()/6-getWidth()/5-getWidth()/21, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8}};
        int[][] playerIconSizes = {{getWidth()/2 - getWidth()/46, (getHeight()/2 + getHeight()/12), getWidth()/23, getHeight()/15},
                                     {getWidth()/15, getHeight()/15, getWidth()/6, getHeight()/4},
                                     {(getWidth() - getWidth()/9 - getWidth()/8), getHeight()/15, getWidth()/6, getHeight()/4}};
        int[] nextButtonSize = {getWidth() - (getWidth()/4+getWidth()/6), (getHeight()/2) - (getHeight()/5+getHeight()/15)/2 - (getHeight()/15) + getHeight()/12, getWidth()/15, getHeight()/10};
        System.out.println("loc is ("+x+","+y+")");
        if(e.getButton() == e.BUTTON1){
                if(x >= playableCardSizes[0][0] && y>= playableCardSizes[0][1] && x<= playableCardSizes[0][0] + playableCardSizes[0][2] && y<= playableCardSizes[0][1] + playableCardSizes[0][3]){
                    choice = 0; 
                    if(mainP.getPlayerActivity() == 1 && playerSpots.get(0) == mainP && roundDone != true){
                        System.out.print(mainP.getPlayerNumber() + " is playing");
                        //protection stufff for players
                        //Countess - 8 /* 
                        if(mainP.getFullHand().contains("8") && (mainP.getFullHand().contains("7") || mainP.getFullHand().contains("5"))){
                            //replace input with button click or key press
                            //ask stroud how to keep it in the same if
                            if(mainP.getAnyCard(choice) == 8){
                                mainP.discardCard(mainP.findCountess());
                                state = 10;
                                repaint();
                                System.out.println("Countess has been discarded");
                                System.out.println("countess has been played FORCEFULLY - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } else{
                            }
                        } else{
                            //spy
                            if(mainP.getAnyCard(choice) == 0 && action == 0 && actionG == 0){
                                spy(spy, mainP, choice);
                                state = 10;
                                repaint();
                                System.out.println("spy has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            //guard - 1 - deal with after getting everything elses mouse clicks right
                            
                            else if(mainP.getAnyCard(choice) == 1 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press 
                                //needs a keyboard input - ADD
                                mainP.discardCard(choice);
                                state = 10;
                                repaint();
                                action = 9;
                            } 
                            
                            //priest - 2
                            else if(mainP.getAnyCard(choice) == 2 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("priest has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } 
                                else {
                                    action = 2;
                                } 
                            }
                            //baron - 3 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 3 && action == 0&& actionG == 0){
                                //replace action with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("baron has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } else {
                                    action = 3;
                                }
                            } 
                            //handmaid - 4 - DONE w while loop
                            else if(mainP.getAnyCard(choice) == 4 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                handmaid(mainP, choice);
                                state = 10;
                                repaint();
                                System.out.println("handmaid has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            //prince - 5 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 5 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                    action = 4;
                            } 
                            //chancellor - 6 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 6 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                mainP.discardCard(choice);
                                bam = chancellor1(mainP, choice, jit);
                                if(bam == 0){
                                    state = 11;
                                    repaint();
                                } else{
                                    state = 12;
                                    repaint();
                                }
                                action = 5;  
                                System.out.println("chancellor clicked"); 
                                specialChoice = choice;
                                choice = -1;
                            } 
                            //king - 7 - done w loop
                            else if(mainP.getAnyCard(choice) == 7 && action == 0 && actionG == 0){
                                //replace input with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("king has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } else {
                                    action = 6;
                                }
                            } 
                            // countess in case
                            else if(mainP.getAnyCard(choice) == 8 && action == 0 && actionG == 0){
                                mainP.discardCard(choice);
                                state = 10;
                                repaint();
                                System.out.println("Countess has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            }
                            //princess - 9 - DOne
                            else if(mainP.getAnyCard(choice) == 9 && action == 0 && actionG == 0){
                                //replace input with button click or key press
                                princess(mainP, choice);
                                mainP.discardCard(choice);
                                state = 7;
                                repaint();
                                System.out.println("Princess has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            else if(action == 5 && choice != -1){
                                chancellor2(mainP, choice, jit, bam);
                                if(bam == 0){
                                    state = 5;
                                    repaint();
                                } else{
                                    state = 6;
                                    repaint();
                                }
                                action = 0;
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Chancellor has been played - in mouseclick");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            }
                        }
                    }
            
                    
                }
                else if(x >= playableCardSizes[1][0] && y>= playableCardSizes[1][1] && x<= playableCardSizes[1][0] + playableCardSizes[1][2] && y<= playableCardSizes[1][1] + playableCardSizes[1][3]){
                    choice = 1;
                    if(mainP.getPlayerActivity() == 1 && playerSpots.get(0) == mainP && roundDone != true){
                        System.out.print(mainP.getPlayerNumber() + " is playing");
                        //protection stufff for players
                        //Countess - 8 /* 
                        if(mainP.getFullHand().contains("8") && (mainP.getFullHand().contains("7") || mainP.getFullHand().contains("5"))){
                            //replace input with button click or key press
                            //ask stroud how to keep it in the same if
                            if(mainP.getAnyCard(choice) == 8){
                                mainP.discardCard(mainP.findCountess());
                                state = 10;
                                repaint();
                                System.out.println("Countess has been discarded");
                                System.out.println("countess has been played FORCEFULLY - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } else{
                            }
                        } else{
                            //spy
                            if(mainP.getAnyCard(choice) == 0 && action == 0 && actionG == 0){
                                spy(spy, mainP, choice);
                                state = 10;
                                repaint();
                                System.out.println("spy has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            //guard - 1 - deal with after getting everything elses mouse clicks right
                            else if(mainP.getAnyCard(choice) == 1 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press 
                                //needs a keyboard input - ADD
                                mainP.discardCard(choice);
                                state = 10;
                                repaint();
                                action = 9;
                            } 
                            //priest - 2
                            else if(mainP.getAnyCard(choice) == 2 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("priest has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } 
                                else {
                                    action = 2;
                                } 
                            }
                            //baron - 3 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 3 && action == 0 && actionG == 0){
                                //replace action with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("baron has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } else {
                                    action = 3;
                                }
                            } 
                            //handmaid - 4 - DONE w while loop
                            else if(mainP.getAnyCard(choice) == 4 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                handmaid(mainP, choice);
                                state = 10;
                                repaint();
                                System.out.println("handmaid has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            //prince - 5 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 5 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                
                                    action = 4;
                            } 
                            //chancellor - 6 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 6 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                mainP.discardCard(choice);
                                bam = chancellor1(mainP, choice, jit);
                                if(bam == 0){
                                    state = 11;
                                    repaint();
                                } else{
                                    state = 12;
                                    repaint();
                                }
                                action = 5;   
                                System.out.println("chancellor clicked"); 
                                specialChoice = choice;
                                choice = -1;
                            } 
                            //king - 7 - done w loop
                            else if(mainP.getAnyCard(choice) == 7 && action == 0 && actionG == 0){
                                //replace input with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("king has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } else {
                                    action = 6;
                                }
                            } 
                            // countess in case
                            else if(mainP.getAnyCard(choice) == 8 && action == 0 && actionG == 0){
                                mainP.discardCard(choice);
                                state = 10;
                                repaint();
                                System.out.println("Countess has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            }
                            //princess - 9 - DOne
                            else if(mainP.getAnyCard(choice) == 9 && action == 0 && actionG == 0){
                                //replace input with button click or key press
                                princess(mainP, choice);
                                mainP.discardCard(choice);
                                state = 7;
                                repaint();
                                System.out.println("Princess has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            else if(action == 5 && choice != -1){
                                chancellor2(mainP, choice, jit, bam);
                                if(bam == 0){
                                    state = 5;
                                    repaint();
                                } else{
                                    state = 6;
                                    repaint();
                                }
                                action = 0;
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Chancellor has been played - in mouseclick");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            }
                        }
                    }
            
                    
                    
                }
                else if(x >= playableCardSizes[2][0] && y>= playableCardSizes[2][1] && x<= playableCardSizes[2][0] + playableCardSizes[2][2] && y<= playableCardSizes[2][1] + playableCardSizes[2][3]){
                    choice = 2;
                    if(mainP.getPlayerActivity() == 1 && playerSpots.get(0) == mainP && roundDone != true){
                        System.out.print(mainP.getPlayerNumber() + " is playing");
                        //protection stufff for players
                        //Countess - 8 /* 
                        if(mainP.getFullHand().contains("8") && (mainP.getFullHand().contains("7") || mainP.getFullHand().contains("5"))){
                            //replace input with button click or key press
                            //ask stroud how to keep it in the same if
                            if(mainP.getAnyCard(choice) == 8){
                                mainP.discardCard(mainP.findCountess());
                                state = 10;
                                repaint();
                                System.out.println("Countess has been discarded");
                                System.out.println("countess has been played FORCEFULLY - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } else{
                            }
                        } else{
                            //spy
                            if(mainP.getAnyCard(choice) == 0 && action == 0 && actionG == 0){
                                spy(spy, mainP, choice);
                                state = 10;
                                repaint();
                                System.out.println("spy has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            //guard - 1 - deal with after getting everything elses mouse clicks right
                            else if(mainP.getAnyCard(choice) == 1 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press 
                                //needs a keyboard input - ADD
                                mainP.discardCard(choice);
                                state = 10;
                                repaint();
                                action = 9;
                            } 
                            //priest - 2
                            else if(mainP.getAnyCard(choice) == 2 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("priest has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } 
                                else {
                                    action = 2;
                                } 
                            }
                            //baron - 3 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 3 && action == 0 && actionG == 0){
                                //replace action with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("baron has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } else {
                                    action = 3;
                                }
                            } 
                            //handmaid - 4 - DONE w while loop
                            else if(mainP.getAnyCard(choice) == 4 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                handmaid(mainP, choice);
                                state = 10;
                                repaint();
                                System.out.println("handmaid has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            //prince - 5 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 5 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                action = 4;
                            } 
                            //chancellor - 6 - DONE w loop
                            else if(mainP.getAnyCard(choice) == 6 && action == 0 && actionG == 0){
                                //replace action and guess with button click or key press
                                mainP.discardCard(choice);
                                bam = chancellor1(mainP, choice, jit);
                                if(bam == 0){
                                    state = 11;
                                    repaint();
                                } else{
                                    state = 12;
                                    repaint();
                                }
                                action = 5;   
                                System.out.println("chancellor clicked"); 
                                specialChoice = choice;
                                choice = -1;
                            } 
                            //king - 7 - done w loop
                            else if(mainP.getAnyCard(choice) == 7 && action == 0 && actionG == 0){
                                //replace input with button click or key press
                                if (mustPlay(otherP1, otherP2)){
                                    mainP.discardCard(choice);
                                    state = 10;
                                    repaint();
                                    System.out.println("king has been played - in mouseclick");
                                    System.out.println(mainP.getPlayerNumber() + "turn done");
                                    
                                    System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                                } else {
                                    action = 6;
                                }
                            } 
                            // countess in case
                            else if(mainP.getAnyCard(choice) == 8 && action == 0 && actionG == 0){
                                mainP.discardCard(choice);
                                state = 10;
                                repaint();
                                System.out.println("Countess has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            }
                            //princess - 9 - DOne
                            else if(mainP.getAnyCard(choice) == 9 && action == 0 && actionG == 0){
                                //replace input with button click or key press
                                princess(mainP, choice);
                                mainP.discardCard(choice);
                                state = 7;
                                repaint();
                                System.out.println("Princess has been played - in mouseclick");
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            } 
                            else if(action == 5 && choice != -1){
                                chancellor2(mainP, choice, jit, bam);
                                if(bam == 0){
                                    state = 5;
                                    repaint();
                                } else{
                                    state = 6;
                                    repaint();
                                }
                                action = 0;
                                System.out.println(mainP.getPlayerNumber() + "turn done");
                                
                                System.out.println("Chancellor has been played - in mouseclick");
                                System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                            }
                        }
                    }
            
    
                }
                //specific sizes for player icons - 0:as if the one playing & 1:left sideline & 2:right sideline
                else if(x >= playerIconSizes[0][0] && y>= playerIconSizes[0][1] && x<= playerIconSizes[0][0] + playerIconSizes[0][2] && y<= playerIconSizes[0][1] + playerIconSizes[0][3]){
                    pChoice = playerSpots.get(0);
                    System.out.println("main player clicked");
                    //ACTION STUFF
                    if(action == 2 && checks(pChoice) && choice != -1){
                        mainP.discardCard(choice);
                        state = 13;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("priest has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 3 && checks(pChoice) && choice != -1) {
                        mainP.discardCard(choice);
                        baron(mainP, pChoice);
                        //make state for baron
                        state = 13;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("Baron has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 4 && checks(pChoice) && choice != -1){
                        prince(pChoice, jit);
                        mainP.discardCard(choice);
                        //redraws in case the prince switched with himself
                        System.out.println("Swapped");
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        System.out.println("Prince has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                        state = 14;
                        repaint();
                    }
                    else if(action == 6 && checks(pChoice) && choice != -1){
                        mainP.discardCard(choice);
                        king(mainP, pChoice);
                        state = 14;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("King has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 9 && checks(pChoice) && choice != -1){
                        actionG = 9;
                    }
        
                }
                else if(x >= playerIconSizes[1][0] && y>= playerIconSizes[1][1] && x<= playerIconSizes[1][0] + playerIconSizes[1][2] && y<= playerIconSizes[1][1] + playerIconSizes[1][3]){
                    pChoice = playerSpots.get(1);
                    System.out.println("left player clicked");
                    //Action Stuff
                    if(action == 2 && checks(pChoice) && choice != -1){
                        mainP.discardCard(choice);
                        state = 13;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("priest has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 3 && checks(pChoice) && choice != -1) {
                        mainP.discardCard(choice);
                        baron(mainP, pChoice);
                        //make state for baron
                        state = 13;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("Baron has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 4 && checks(pChoice) && choice != -1){
                        prince(pChoice, jit);
                        mainP.discardCard(choice);
                        //redraws in case the prince switched with himself
                        System.out.println("Swapped");
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        System.out.println("Prince has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                        state = 14;
                        repaint();
                    }
                    else if(action == 6 && checks(pChoice) && choice != -1){
                        mainP.discardCard(choice);
                        king(mainP, pChoice);
                        state = 14;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("King has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 9 && checks(pChoice) && choice != -1){
                        actionG = 9;
                    }
        
                }
                else if(x >= playerIconSizes[2][0] && y>= playerIconSizes[2][1] && x<= playerIconSizes[2][0] + playerIconSizes[2][2] && y<= playerIconSizes[2][1] + playerIconSizes[2][3]){
                    pChoice = playerSpots.get(2);
                    System.out.println("right player clicked");
                        //Action Stuff
                        if(action == 2 && checks(pChoice) && choice != -1){
                        mainP.discardCard(choice);
                        state = 13;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("priest has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 3 && checks(pChoice) && choice != -1) {
                        mainP.discardCard(choice);
                        baron(mainP, pChoice);
                        //make state for baron
                        state = 13;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("Baron has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 4 && checks(pChoice) && choice != -1){
                        prince(pChoice, jit);
                        mainP.discardCard(choice);
                        //redraws in case the prince switched with himself
                        System.out.println("Swapped");
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        System.out.println("Prince has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                        state = 14;
                        repaint();
                    }
                    else if(action == 6 && checks(pChoice) && choice != -1){
                        mainP.discardCard(choice);
                        king(mainP, pChoice);
                        state = 14;
                        repaint();
                        action = 0;
                        System.out.println(mainP.getPlayerNumber() + "turn done");
                        
                        System.out.println("King has been played - in mouseclick");
                        System.out.println("Now it is " + mainP.getPlayerNumber() + " player turn");
                    }
                    else if(action == 9 && checks(pChoice) && choice != -1){
                        actionG = 9;
                    }
                    
                }

            
            else if(x >= nextButtonSize[0] && y>= nextButtonSize[1] && x<= nextButtonSize[0] + nextButtonSize[2] && y<= nextButtonSize[1] + nextButtonSize[3]){
                next = true;
                pressed++;
                gamegame = 1;
                if(next == true && pressed>1){
                    System.out.println("doing rest of times");
                    //deals
                    cyclePlayers(playerSpots); 
                    choice = -1;
                    pChoice = null;
                    specialChoice = -1;
                    mainP = playerSpots.get(0);
                    otherP1 = playerSpots.get(1);
                    otherP2 = playerSpots.get(2);
                    if(protect1 == true && mainP == p1){
                        p1.setPlayerActivity(1);
                        protect1 = false;
                        System.out.println("PLAYER 1 IS UNPROTECTED");
                    } else if(protect2 == true && mainP == p2){
                        p2.setPlayerActivity(1);
                        protect2 = false;
                        System.out.println("PLAYER 2 IS UNPROTECTED");
                    } else if(protect3 == true && mainP == p3){
                        p3.setPlayerActivity(1);
                        protect3 = false;
                        System.out.println("PLAYER 3 IS UNPROTECTED");
                    }
                    if(!mainP.getPlayerLife()){
                        cyclePlayers(playerSpots); 
                        System.out.println("Players cycled & skipped");
                        mainP = playerSpots.get(0);
                        otherP1 = playerSpots.get(1);
                        otherP2 = playerSpots.get(2);
                        dealDependent(mainP, jit);
                        if(protect1 == true && mainP == p1){
                            p1.setPlayerActivity(1);
                            protect1 = false;
                            System.out.println("PLAYER 1 IS UNPROTECTED");
                        } else if(protect2 == true && mainP == p2){
                            p2.setPlayerActivity(1);
                            protect2 = false;
                            System.out.println("PLAYER 2 IS UNPROTECTED");
                        } else if(protect3 == true && mainP == p3){
                            p3.setPlayerActivity(1);
                            protect3 = false;
                            System.out.println("PLAYER 3 IS UNPROTECTED");
                        }
                        System.out.println("full hand - mainP");
                        System.out.println(mainP.getFullHand());
                        System.out.println(mainP.getPlayerNumber());
                        //draws the dealt cards
                        state = 1; 
                        repaint();
                        next = false;
                        System.out.println("next button has been clicked");
                    } else{
                        dealDependent(mainP, jit);
                        System.out.println("full hand - mainP");
                        System.out.println(mainP.getFullHand());
                        System.out.println(mainP.getPlayerNumber());
                        //draws the dealt cards
                        state = 1; 
                        repaint();
                        next = false;
                        System.out.println("next button has been clicked");
                    }
                } else{
                    System.out.println("done first time");
                    choice = -1;
                    pChoice = null;
                    specialChoice = -1;
                    mainP = playerSpots.get(0);
                    otherP1 = playerSpots.get(1);
                    otherP2 = playerSpots.get(2); 
                    dealDependent(mainP, jit);
                    System.out.println("full hand - mainP");
                    System.out.println(mainP.getFullHand());
                    System.out.println(mainP.getPlayerNumber());
                    //draws the dealt cards
                    state = 1; 
                    repaint();
                    next = false;
                    System.out.println("next button has been clicked");
                }
                
            }
           
        }
         //
        
       
}

    
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){   
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        //playerIcon switches based on whose playing
        BufferedImage activePlayerIcon = playerIcons[mainP.getPlayerNumber()];
        BufferedImage idlePlayerIcon1 = playerIcons[otherP1.getPlayerNumber()];
        BufferedImage idlePlayerIcon2 = playerIcons[otherP2.getPlayerNumber()];
        int tokenCount = mainP.getLoveLetters();
        //specific sizes for player icons - 0:as if the one playing & 1:left sideline & 2:right sideline
        int[][] playerIconSizes = {{getWidth()/2 - getWidth()/46, (getHeight()/2 + getHeight()/12), getWidth()/23, getHeight()/15},
                                   {getWidth()/15, getHeight()/15, getWidth()/6, getHeight()/4},
                                   {(getWidth() - getWidth()/9 - getWidth()/8), getHeight()/15, getWidth()/6, getHeight()/4}};
        //specific sizes for stagnant card images - 0:Deck card sizes & 1:left card sizes & 2:right card sizes                        
        int[][] stagnantCardSizes = {{(getWidth()/2)-(getWidth()/7+getWidth()/50)/2, (getHeight()/2) - (getHeight()/5+getHeight()/15)/2 - (getHeight()/15), getWidth()/7+getWidth()/50, getHeight()/5+getHeight()/15},
                                     {getWidth()/15, (getHeight()/6+getHeight()/6), getWidth()/7+getWidth()/50, getHeight()/5+getHeight()/15},
                                     {(getWidth() - getWidth()/9 - getWidth()/8), (getHeight()/6+getHeight()/6), getWidth()/7+getWidth()/50, getHeight()/5+getHeight()/15}};
        //specific sizes for playable card images - 0:left card sizes & 1:middle card sizes & 2:right card sizes                           
        int[][] playableCardSizes = {{getWidth()/2-getWidth()/5-getWidth()/82, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()/2 - getWidth()/16, getHeight()-getHeight()/3, getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()-getWidth()/6-getWidth()/5-getWidth()/21, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8}};
        //specific sizes for the discard piles -0:as if the one playing & 1:left sideline & 2:right sideline
        int[][] discardPileSizes = {{getWidth()-getWidth()/4, getHeight() - (getHeight()/4 + getHeight()/9), getWidth()/17, getHeight()/13+getHeight()/15},
                                     {getWidth()/4, (getHeight()/4 - getHeight()/15), getWidth()/17, getHeight()/13+getHeight()/15},
                                     {getWidth() - (getWidth()/4 + getWidth()/17), (getHeight()/4 - getHeight()/15), getWidth()/17, getHeight()/13+getHeight()/15}};
        int[] nextButtonSize = {getWidth() - (getWidth()/4+getWidth()/6), (getHeight()/2) - (getHeight()/5+getHeight()/15)/2 - (getHeight()/15) + getHeight()/12, getWidth()/15, getHeight()/10};

        for(int i = 0; i<otherP1.getDiscardPile().size(); i++){
            g.drawImage(cardFaces[otherP1.getDiscardPile().get(i)], discardPileSizes[1][0], discardPileSizes[1][1] + 20*i, discardPileSizes[1][2], discardPileSizes[1][3], null);
        }
        for(int i = 0; i<otherP2.getDiscardPile().size(); i++){
            System.out.println(otherP2.getDiscardPile().get(i));
            g.drawImage(cardFaces[otherP2.getDiscardPile().get(i)], discardPileSizes[2][0], discardPileSizes[2][1] + 20*i, discardPileSizes[2][2], discardPileSizes[2][3], null);
        }
        //reference card
        g.drawImage(ReferenceCard, (getWidth()/2)-(getWidth()/16), getHeight()/90, getWidth()/8, getHeight()/5+getHeight()/15, null);
        //stagnant cards - 1 = left, 0 = main, 2 = right
        if(otherP1.getPlayerLife()){
            g.drawImage(CardBack, stagnantCardSizes[1][0], stagnantCardSizes[1][1], stagnantCardSizes[1][2], stagnantCardSizes[1][3], null);
        }
        if(otherP2.getPlayerLife()){
            g.drawImage(CardBack, stagnantCardSizes[2][0], stagnantCardSizes[2][1], stagnantCardSizes[2][2], stagnantCardSizes[2][3], null);
        }
        
        //player icons
        g.drawImage(activePlayerIcon, playerIconSizes[0][0], playerIconSizes[0][1], playerIconSizes[0][2], playerIconSizes[0][3], null);
        g.drawImage(idlePlayerIcon1, playerIconSizes[1][0], playerIconSizes[1][1], playerIconSizes[1][2], playerIconSizes[1][3], null);
        g.drawImage(idlePlayerIcon2, playerIconSizes[2][0], playerIconSizes[2][1], playerIconSizes[2][2], playerIconSizes[2][3], null);


        //LoveLetter Spot
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("Token Count: " + tokenCount, getWidth()/5 - getWidth()/80, getHeight()-(getHeight()/6 + getHeight()/15));
        g.drawImage(Token, getWidth()/5, getHeight() -getHeight()/5, getWidth()/16, getHeight()/10, null);
        
        //image SPOTS
        g.setColor(Color.yellow);
        g.drawRect(playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3]);
        g.setColor(Color.blue);
        g.drawRect(playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3]);
        g.setColor(Color.red);
        g.drawRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
        //next button
        g.setColor(Color.red);
        g.fillRect(nextButtonSize[0], nextButtonSize[1], nextButtonSize[2], nextButtonSize[3]);
        //rule text
        g.setColor(Color.red);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString("NEXT", nextButtonSize[0]+getWidth()/150, nextButtonSize[1]-getHeight()/50);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("Click The NEXT Button To Start the ", getWidth()-getWidth()/3-getWidth()/10, getHeight()/10);
        g.drawString("\n Game or Continue To The Next ", getWidth()-getWidth()/3-getWidth()/10, getHeight()/10+30);
        g.drawString("\n Player or Begin A New Round", getWidth()-getWidth()/3-getWidth()/10, getHeight()/10+60);
        
        //Main Discard Pile outline
        g.setColor(Color.BLACK);
        g.drawRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);

        //game over
        if(state == 0){
            g.setColor(Color.BLUE);
            g.setFont(new Font("Serif", Font.PLAIN, 50));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER - CONGRATULATIONS: ", getWidth()/4 - getWidth()/27, getHeight()/4 + getHeight()/9);
            g.drawImage(winner, getWidth()/2 - getWidth()/12, getHeight()/2, playerIconSizes[1][2], playerIconSizes[1][3], null);
        }
        //draws full hand and discard pile
        if(state == 1 && mainP != null){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.setColor(Color.black);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.drawImage(cardFaces[mainP.getFirstCard()], playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
            g.drawImage(cardFaces[mainP.getSecondCard()], playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            System.out.println("discard pile drawn - in paint of: " + mainP.getPlayerNumber());
            System.out.println("original satisifed");
            state = 15;
        }
        if(state == 2){
            g.setColor(Color.GREEN);
            g.setFont(new Font("Serif", Font.PLAIN, 30));
            g.drawString("GUESS WAS", (getWidth()/4) + getWidth()/14, getHeight()/2 - getHeight()/5 - getHeight()/100);
            g.drawString("CORRECT", (getWidth()/4 + getWidth()/90) + getWidth()/14, getHeight()/2 + 30- getHeight()/5 - getHeight()/100);

            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.drawImage(cardFaces[pChoice.getAvailableCard()], (getWidth()/4+getWidth()/10), stagnantCardSizes[0][1] + getHeight()/30, getWidth()/14+getWidth()/50, stagnantCardSizes[0][3] - getWidth()/30, null);
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            state = 15;
        }
        if(state == 3){
            g.setColor(Color.RED);
            g.setFont(new Font("Serif", Font.PLAIN, 30));
            g.drawString("GUESS WAS", (getWidth()/4) + getWidth()/14, getHeight()/2 - getHeight()/5 - getHeight()/100);
            g.drawString("INCORRECT", (getWidth()/4 + getWidth()/90) + getWidth()/16, getHeight()/2 + 30- getHeight()/5 - getHeight()/100);
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            System.out.println("empties card spot based on discard - in paint");
            state = 15;
        }

        //fills blank spot for chancellor
        if(state == 5 && mainP != null){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.fillRect(playableCardSizes[mainP.getBlankCardSpot()][0], playableCardSizes[mainP.getBlankCardSpot()][1], playableCardSizes[mainP.getBlankCardSpot()][2], playableCardSizes[mainP.getBlankCardSpot()][3]);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            System.out.println("blanks out empty spot cus chancellor - in paint");
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            state = 15;
        }
        //empties chancellor spot
        if(state == 6 && mainP != null){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.fillRect(playableCardSizes[mainP.getBlankCardSpot()][0], playableCardSizes[mainP.getBlankCardSpot()][1], playableCardSizes[mainP.getBlankCardSpot()][2], playableCardSizes[mainP.getBlankCardSpot()][3]);
            g.fillRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            System.out.println("blanks out chancellor spot - in paint");
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            state = 15;
        }
        //clears the entire hand for the princess play
        if(state == 7 && mainP != null){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.fillRect(playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3]);
            g.fillRect(playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3]);
            System.out.println("princess played - in paint");
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            
            state = 15;
        }
        //discard cand show pile
        if(state == 10){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            System.out.println("empties card spot based on discard - in paint");
            state = 15;
        }
        if(state == 11){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[specialChoice][0], playableCardSizes[specialChoice][1], playableCardSizes[specialChoice][2], playableCardSizes[specialChoice][3]);
            g.drawImage(cardFaces[mainP.getFirstCard()], playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
            g.drawImage(cardFaces[mainP.getSecondCard()], playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
            System.out.println("DISCARD PILE SIZE: " + mainP.getDiscardPile().size());
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    System.out.println(mainP.getDiscardPile().get(i));
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            System.out.println("empties card spot based on discard - in paint");
            state = 15;
        }
        if(state == 12){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[specialChoice][0], playableCardSizes[specialChoice][1], playableCardSizes[specialChoice][2], playableCardSizes[specialChoice][3]);
            g.drawImage(cardFaces[mainP.getChancellor()], playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3], null);
            g.drawImage(cardFaces[mainP.getFirstCard()], playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
            g.drawImage(cardFaces[mainP.getSecondCard()], playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
            if(mainP.getDiscardPile().size() > 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    System.out.println(mainP.getDiscardPile().get(i));
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            System.out.println("DISCARD PILE SIZE: " + mainP.getDiscardPile().size());
            System.out.println("empties card spot based on discard - in paint");
            state = 15;
        }
        if(state == 13){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.drawImage(cardFaces[pChoice.getAvailableCard()], (getWidth()/4+getWidth()/10), stagnantCardSizes[0][1] + getHeight()/30, getWidth()/14+getWidth()/50, stagnantCardSizes[0][3] - getWidth()/30, null);
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            state = 15;
        }
        if(state == 14){
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.setColor(Color.red);
            g.drawString("BURNT CARD", (getWidth()/2)-(getWidth()/16) -getWidth()/12 + getWidth()/300, getHeight()/30);
            g.drawImage(CardBack, (getWidth()/2)-(getWidth()/16) - getWidth()/8, getHeight()/30, getWidth()/8 + getWidth()/50, getHeight()/5+getHeight()/30, null);
            g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
            g.setColor(Color.red);
            g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
            g.drawImage(cardFaces[mainP.getAvailableCard()], playableCardSizes[mainP.getAvailableCardSpot()][0], playableCardSizes[mainP.getAvailableCardSpot()][1], playableCardSizes[mainP.getAvailableCardSpot()][2], playableCardSizes[mainP.getAvailableCardSpot()][3], null);
            System.out.println(mainP.getAvailableCard());
            g.setColor(Color.BLACK);
            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
            if(mainP.getDiscardPile().size() != 0){
                for(int i = 0; i<mainP.getDiscardPile().size(); i++){
                    g.drawImage(cardFaces[mainP.getDiscardPile().get(i)], discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
                }
            }
            state = 15;
        }
        
    }
    public static void initialdeal(Player a, Player b, Player c, Deck d){
                dealCard(a, d, 0);
                dealCard(b, d, 0);
                dealCard(c, d, 0);
    }
    public static void dealCard(Player p, Deck d, int position){
        p.addCard(position,d.removeCard());
    }
    public static void dealDependent(Player p, Deck d){
        if(p.getFirstCard() == -1){
            dealCard(p, d, 0);
        } else if(p.getSecondCard() == -1){
            dealCard(p, d, 1);
        } else{}
        
    }
    public static void printDeck(Deck d){
        for(int i = 0; i<d.getSize(); i++){
            System.out.print(d.getCard(i));          
        }
        System.out.println();
    }
    public static void cyclePlayers(ArrayList<Player> a){
        a.add(a.size()-1, a.remove(0));
    }
    public static void spy(ArrayList<Player> s, Player a, Integer choice){
        s.add(a);
        System.out.println("SPY HAS BEEN ADDED");
        a.discardCard(choice);
    }
    public static boolean checks(Player a){
        if(a.getPlayerActivity() != 0){
            return true;
        } else{
            return false;
        }
    }
    public static void handmaid(Player a, Integer choice){
        a.setPlayerActivity(0);
        System.out.println("PLAYER 1 IS PROTECTED");
        a.discardCard(choice);
        if(a == p1){
            protect1 = true;
        }
        if(a == p2){
            protect2 = true;
        }
        if(a == p3){
            protect3 = true;
        }
    }
    public static void prince(Player pChoice, Deck d){
        //forces to replace card
        int s = pChoice.getAvailableCardSpot();
        pChoice.discardCard(s);
        dealCard(pChoice, d, s);
        //System.out.println("PLAYER 1 CARD REDEALT - NOW IT IS" + a.getAvailableCard());
    }
    public static void baron(Player a, Player pChoice){
        int pow1 = a.getAvailableCard();
        int pow2 = pChoice.getAvailableCard();
        pow1 = a.getAvailableCard();
        if(pow1>pow2){
            pChoice.setPlayerDead();
            pChoice.setPlayerActivity(0);
        } else if(pow2>pow1){
            a.setPlayerDead();
            a.setPlayerActivity(0);
        } else{
            System.out.println("nums r equal");
        }
    }
    public static Integer chancellor1(Player a, Integer choice, Deck d){
        if(d.getSize() >2){
            dealCard(a, d, a.getBlankCardSpot());
            dealCard(a, d, 5);
            System.out.println("FULL HAND INCLUDING THE CHANCELLOR: " + a.getFullHandChancellor());
            System.out.println("Choose 1 - first, 2- second, 3 - third");
            return 1;
        } else{
            dealCard(a, d, a.getBlankCardSpot());
            return 0;
        }
    }
    public static void chancellor2(Player a, Integer choice, Deck d, Integer option){
        if(option == 0){
            if (choice == 0){
                d.addCardToDeck(a.getSecondCard());
                a.returnCard2(1);
                d.addCardToDeck(a.getChancellor());
                a.returnCard2(5);
            } else if(choice == 1){
                d.addCardToDeck(a.getFirstCard());
                a.returnCard2(0);
                d.addCardToDeck(a.getChancellor());
                a.returnCard2(5);
            }
        }
        if(option == 1){
            if (choice == 0){
                d.addCardToDeck(a.getSecondCard());
                a.returnCard2(1);
                d.addCardToDeck(a.getChancellor());
                a.returnCard2(5);
            } else if(choice == 1){
                d.addCardToDeck(a.getFirstCard());
                a.returnCard2(0);
                d.addCardToDeck(a.getChancellor());
                a.returnCard2(5);
            } else if(choice == 2){
                d.addCardToDeck(a.getAnyCard(0));
                System.out.println("FIRST CARD:" + a.getAnyCard(0));
                a.returnCard2(0);  
                d.addCardToDeck(a.getAnyCard(1));
                System.out.println("SECOND CARD:" + a.getAnyCard(1));
                a.returnCard2(1);
                a.setSpecific(0, a.getChancellor());
                a.ridChancellor();
            }
        }
        System.out.print("After whole chancellor ting is done: ");
        printDeck(d);
    }
    public static void king(Player a, Player pChoice){
        Integer temp = a.getAvailableCard();
        a.setSpecific(a.getAvailableCardSpot(), pChoice.getAvailableCard());
        pChoice.setSpecific(pChoice.getAvailableCardSpot(), temp);
    }
    public static void princess(Player a, Integer choice){
        a.setPlayerDead();
        a.setPlayerActivity(0);
        System.out.println("PLAYER 1 IS OUT OF THE ROUND");
    }
    public static boolean loveLetterEnd(Player a, Player b, Player c){
        return a.getLoveLetters()==5 && b.getLoveLetters()==5 && c.getLoveLetters()==5;
    }
    public static boolean mustPlay(Player b, Player c){
        return (b.getPlayerActivity() == 0 && c.getPlayerActivity() == 0);
    }
    public static boolean checkLife(Player a, Player b, Player c, boolean p1, boolean p2, boolean p3){
        return (a.getPlayerActivity() == 1 || p1 == true) && (b.getPlayerActivity() == 1 || p2 == true) && (c.getPlayerActivity() == 1 || p3 == true);
    }
    public static void guard(Player a){
        a.setPlayerActivity(0);
        a.setPlayerDead();
    }
    public static Player findWinner(ArrayList<Player> a){
        if(a.get(0).getLoveLetters() == 5){
            return a.get(0);
        }
        if(a.get(1).getLoveLetters() == 5){
            return a.get(1);
        }
        else{
            return a.get(2);
        } 
    }

}

