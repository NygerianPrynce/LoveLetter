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
public class LoveLetterPanel extends JPanel implements MouseListener{
    private BufferedImage Baron, Chancellor, Countess, Guard, Handmaid, King, Priest, Prince, Princess, Spy, ReferenceCard, CardBack, Player1Icon, Player2Icon, Player3Icon;
    private BufferedImage choice;
    public Player p1 = new Player();
    public Player p2 = new Player();
    public Player p3 = new Player();
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
        } catch(Exception E){
            System.out.println("Exception error");
            return;
        }
        choice = Spy;
        addMouseListener(this);
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int[][] playableCardSizes = {{getWidth()/2-getWidth()/5-getWidth()/82, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()/2 - getWidth()/16, getHeight()-getHeight()/3, getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()-getWidth()/6-getWidth()/5-getWidth()/21, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8}};
        System.out.println("loc is ("+x+","+y+")");
        if(e.getButton() == e.BUTTON1){
            if(x >= playableCardSizes[0][0] && y>= playableCardSizes[0][1] && x<= playableCardSizes[0][0] + playableCardSizes[0][2] && y<= playableCardSizes[0][1] + playableCardSizes[0][3]){
                choice = Spy;
            }
            else if(x >= playableCardSizes[1][0] && y>= playableCardSizes[1][1] && x<= playableCardSizes[1][0] + playableCardSizes[1][2] && y<= playableCardSizes[1][1] + playableCardSizes[1][3]){
                choice = Guard;
            }
            else if(x >= playableCardSizes[2][0] && y>= playableCardSizes[2][1] && x<= playableCardSizes[2][0] + playableCardSizes[2][2] && y<= playableCardSizes[2][1] + playableCardSizes[2][3]){
                choice = Priest;
            }
            repaint();
        }
    }
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){   
        //playerIcon switches based on whose playing
        BufferedImage activePlayerIcon = Player1Icon;
        BufferedImage idlePlayerIcon1 = Player2Icon;
        BufferedImage idlePlayerIcon2 = Player3Icon;
        //specific sizes for player icons - 0:as if the one playing & 1:left sideline & 2:right sideline
        int[][] playerIconSizes = {{getWidth()/2 - getWidth()/46, (getHeight()/2 + getHeight()/12), getWidth()/23, getHeight()/15},
                                   {getWidth()/15, getHeight()/9, getWidth()/6, getHeight()/4},
                                   {(getWidth() - getWidth()/9 - getWidth()/8), getHeight()/9, getWidth()/6, getHeight()/4}};
        //specific sizes for stagnant card images - 0:Deck card sizes & 1:left card sizes & 2:right card sizes                        
        int[][] stagnantCardSizes = {{(getWidth()/2)-(getWidth()/7+getWidth()/50)/2, (getHeight()/2) - (getHeight()/5+getHeight()/15)/2 - (getHeight()/15), getWidth()/7+getWidth()/50, getHeight()/5+getHeight()/15},
                                     {getWidth()/15, (getHeight()/7+getHeight()/4), getWidth()/7+getWidth()/50, getHeight()/5+getHeight()/15},
                                     {(getWidth() - getWidth()/9 - getWidth()/8), (getHeight()/7+getHeight()/4), getWidth()/7+getWidth()/50, getHeight()/5+getHeight()/15}};
        //specific sizes for playable card images - 0:left card sizes & 1:middle card sizes & 2:right card sizes                           
        int[][] playableCardSizes = {{getWidth()/2-getWidth()/5-getWidth()/82, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()/2 - getWidth()/16, getHeight()-getHeight()/3, getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8},
                                     {getWidth()-getWidth()/6-getWidth()/5-getWidth()/21, getHeight()-getHeight()/3,getWidth()/8+getWidth()/50, getHeight()/5+getHeight()/8}};
        //reference card
        g.drawImage(ReferenceCard, (getWidth()/2)-(getWidth()/16), getHeight()/90, getWidth()/8, getHeight()/5+getHeight()/15, null);
        //stagnant cards
        g.drawImage(CardBack, stagnantCardSizes[0][0], stagnantCardSizes[0][1], stagnantCardSizes[0][2], stagnantCardSizes[0][3], null);
        g.drawImage(CardBack, stagnantCardSizes[1][0], stagnantCardSizes[1][1], stagnantCardSizes[1][2], stagnantCardSizes[1][3], null);
        g.drawImage(CardBack, stagnantCardSizes[2][0], stagnantCardSizes[2][1], stagnantCardSizes[2][2], stagnantCardSizes[2][3], null);
        //player icons
        g.drawImage(activePlayerIcon, playerIconSizes[0][0], playerIconSizes[0][1], playerIconSizes[0][2], playerIconSizes[0][3], null);
        g.drawImage(idlePlayerIcon1, playerIconSizes[1][0], playerIconSizes[1][1], playerIconSizes[1][2], playerIconSizes[1][3], null);
        g.drawImage(idlePlayerIcon2, playerIconSizes[2][0], playerIconSizes[2][1], playerIconSizes[2][2], playerIconSizes[2][3], null);
        //Card spots
        g.drawImage(Baron, playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
        g.drawImage(Baron, playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
        g.drawImage(Baron, playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3], null);
        
        
        g.drawImage(choice, getWidth()/10, getHeight()/3, null); 
        g.setColor(Color.yellow);
        g.drawRect(playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3]);
        g.setColor(Color.blue);
        g.drawRect(playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3]);
        g.setColor(Color.red);
        g.drawRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
        

        //Initializes important stuff 4 the player
        /* 
        p1.makePlayer();
        p2.makePlayer();
        p3.makePlayer();
        ArrayList<Integer> random = new ArrayList<Integer>();
        random.add(0);
        random.add(1);
        random.add(2);
        int r = (int)(Math.random()*(3));
        p1.addPlayerNumber(random.remove(r));
        r = (int)(Math.random()*(2));
        p2.addPlayerNumber(random.remove(r));
        p3.addPlayerNumber(random.remove(0));     
        */

        //runs the entire game
        /*while(p1.getLoveLetters()<5 && p2.getLoveLetters()<5 && p3.getLoveLetters()<5){
        Deck jit = new Deck();
        jit.makeDeck();
        jit.shuffle();
        jit.burnCard();
        printDeck(jit);
        initialdeal(p1, p2, p3, jit);
        boolean protect1 = false;
        boolean protect2 = false;
        boolean protect3 = false;
        Scanner myObj = new Scanner(System.in);
        boolean roundDone = false;
        ArrayList<Player> spy = new ArrayList<Player>();
        System.out.println("initial deal done");
        System.out.println("full hand - p1");
        System.out.println(p1.getFullHand());
        System.out.println("full hand - p2");
        System.out.println(p2.getFullHand());
        System.out.println("full hand - p3");
        System.out.println(p3.getFullHand());
        //Run round
        while(jit.getSize() > 0){
            //Check if round is over based on 2 players being out
            if(p1.getPlayerActivity() == 0 & p2.getPlayerActivity() == 0 && protect1 == false && protect2 == false){
                p3.addLoveletter();
                roundDone = true;
                break;
            }
            if(p2.getPlayerActivity() == 0 & p3.getPlayerActivity() == 0 && protect2 == false && protect3 == false){
                p1.addLoveletter();
                roundDone = true;
                break;
            }
            if(p3.getPlayerActivity() == 0 & p1.getPlayerActivity() == 0 && protect1 == false && protect3 == false){
                p2.addLoveletter();
                roundDone = true;
                break;
            }
                //player1
                //undo handmaind protections at the beginning of the turn
                if(protect1 == true){
                    p1.setPlayerActivity(1);
                    protect1 = false;
                    System.out.println("PLAYER 1 IS UNPROTECTED");
                }
                //player1 activity
                if(p1.getPlayerActivity() == 1){
                    
                    dealDependent(p1, jit);
                    System.out.println("full hand - p1");
                    System.out.println(p1.getFullHand());
                    //Countess - 8
                    if(p1.getFullHand().contains("8") && (p1.getFullHand().contains("7") || p1.getFullHand().contains("5"))){
                        //replace input with button click or key press
                        p1.discardCard(p1.findCountess());
                        System.out.println("Countess has been discarded");
                    }else{
                    int c1 = myObj.nextInt();
                    //spy
                    if(p1.getAnyCard(c1) == 0){
                        //replace action and guess with button click or key press
                        spy.add(p1);
                        System.out.println("SPY HAS BEEN ADDED");
                        p1.discardCard(c1);
                    } 
                    //guard - 1
                    else if(p1.getAnyCard(c1) == 1){
                        //replace action and guess with button click or key press
                        String action = myObj.next();
                        int guess = myObj.nextInt();
                        if(action.equals("p2") && guess == p2.getAvailableCard() && p2.getPlayerActivity() != 0){
                            System.out.println("Guard guess was correct " + action + " card was " + guess);
                        } else if (action.equals("p3") && guess == p3.getAvailableCard()&& p3.getPlayerActivity() != 0){
                            System.out.println("Guard guess was correct " + action + " card was " + guess);
                        } else{
                            System.out.println("Guard guess was incorrect " + action + " card was not " + guess);  
                        }
                        p1.discardCard(c1);
                    } 
                    //priest - 2
                    else if(p1.getAnyCard(c1) == 2){
                        //replace action and guess with button click or key press
                        String action = myObj.next();
                        if(action.equals("p2") && p2.getPlayerActivity() != 0){
                            System.out.println("PRIEST USED - PLAYER 2 CARD IS: " + p2.getAvailableCard());
                        } else if (p3.getPlayerActivity() != 0){
                            System.out.println("PRIEST USED - PLAYER 3 CARD IS: " + p3.getAvailableCard());
                        }
                        p1.discardCard(c1);
                    }
                    //baron - 3
                    else if(p1.getAnyCard(c1) == 3){
                        //replace action with button click or key press
                        String action = myObj.next();
                        if (action.equals("p2") && p2.getPlayerActivity() != 0){
                            baron(p1, p2);
                            p1.discardCard(c1);
                        } else if(p3.getPlayerActivity() != 0){
                           baron(p1,p3);
                           p1.discardCard(c1);
                        }
                    } 
                    //handmaid - 4
                    else if(p1.getAnyCard(c1) == 4){
                        //replace action and guess with button click or key press
                        p1.setPlayerActivity(0);
                        protect1 = true;
                        System.out.println("PLAYER 1 IS PROTECTED");
                        p1.discardCard(c1);
                    } 
                    //prince - 5
                    else if(p1.getAnyCard(c1) == 5){
                        //replace action and guess with button click or key press
                        p1.discardCard(c1);
                        dealCard(p1, jit, p1.getAvailableCardSpot());
                        System.out.println("PLAYER 1 CARD REDEALT - NOW IT IS" + p1.getAvailableCard());
                    } 
                    //chancellor - 6
                    else if(p1.getAnyCard(c1) == 6){
                        //replace action and guess with button click or key press
                        p1.discardCard(c1);
                        dealCard(p1, jit, p1.getBlankCardSpot());
                        dealCard(p1, jit, 5);
                        System.out.println("FULL HAND INCLUDING THE CHANCELLOR: " + p1.getFullHandChancellor());
                        System.out.println("Choose 1 - first, 2- second, 3 - third");
                        int action = myObj.nextInt();
                        if (action == 1){
                            jit.addCardToDeck(p1.getSecondCard());
                            p1.returnCard2(1);
                            jit.addCardToDeck(p1.getChancellor());
                            p1.returnCard2(5);
                        } else if(action == 2){
                            jit.addCardToDeck(p1.getFirstCard());
                            p1.returnCard2(0);
                            jit.addCardToDeck(p1.getChancellor());
                            p1.returnCard2(5);
                        } else{
                            jit.addCardToDeck(p1.getAnyCard(0));
                            System.out.println("FIRST CARD:" + p1.getAnyCard(0));
                            p1.returnCard2(0);  
                            jit.addCardToDeck(p1.getAnyCard(1));
                            System.out.println("SECOND CARD:" + p1.getAnyCard(1));
                            p1.returnCard2(1);
                            p1.setSpecific(0, p1.getChancellor());
                            p1.ridChancellor();
                        }
                        System.out.print("After whole chancellor ting is done: ");
                        printDeck(jit);
                        System.out.println("PLAYER 1 CARD IS NOW: " + p1.getAvailableCard());
                    } 
                    //king - 7
                    else if(p1.getAnyCard(c1) == 7){
                        //replace input with button click or key press
                        p1.discardCard(c1);
                        int temp;
                        System.out.println("CHOOSE PLAYER TO SWITCH WITH - Player 2=2, PLAYER 3=3");
                        int action = myObj.nextInt();
                        if (action == 2){
                            temp = p1.getAvailableCard();
                            p1.setSpecific(p1.getAvailableCardSpot(), p2.getAvailableCard());
                            p2.setSpecific(p2.getAvailableCardSpot(), temp);
                            System.out.println("PLAYER 1 AND PLAYER 2 HAVE SWITCHED CARDS");
                            System.out.println("Player 1's hand: " + p1.getFullHand() + " Player 2's hand: " + p2.getFullHand());
                        }
                        if (action == 3){
                            temp = p1.getAvailableCard();
                            p1.setSpecific(p1.getAvailableCardSpot(), p3.getAvailableCard());
                            p3.setSpecific(p3.getAvailableCardSpot(), temp);
                            System.out.println("PLAYER 1 AND PLAYER 3 HAVE SWITCHED CARDS");
                            System.out.println("Player 1's hand: " + p1.getFullHand() + " Player 3's hand: " + p3.getFullHand());
                        }
                    } 
                    //princess - 9
                    else if(p1.getAnyCard(c1) == 9){
                        //replace input with button click or key press
                        p1.setPlayerActivity(0);
                        System.out.println("PLAYER 1 IS OUT OF THE ROUND");
                        p1.discardCard(c1);
                    } 
                }
                    System.out.println("P1 Acivity:" + p1.getPlayerActivity() + " " + "P2 Activity:" + p2.getPlayerActivity()+ " " + "P3 Activity:" + p3.getPlayerActivity());
                //Check if round is over based on 2 players being out
                if(p1.getPlayerActivity() == 0 && p2.getPlayerActivity() == 0 && protect1 == false && protect2 == false){
                    p3.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(p2.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0 && protect2 == false && protect3 == false){
                    p1.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(p3.getPlayerActivity() == 0 && p1.getPlayerActivity() == 0 && protect3 == false && protect1 == false){
                    p2.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(jit.getSize() == 0){
                    break;
                }
                }
                //SECOND PLAYER STUFF
                //undo handmaind protections at the beginning of the turn
                if(protect2 == true){
                    p2.setPlayerActivity(1);
                    protect2 = false;
                    System.out.println("PLAYER 2 IS UNPROTECTED");
                }
                //player 2 activity
                if(p2.getPlayerActivity() == 1){
                    dealDependent(p2, jit);
                    System.out.println("full hand - p2");
                    System.out.println(p2.getFullHand());
                    // Countess - 8
                    if (p2.getFullHand().contains("8") && (p2.getFullHand().contains("7") || p2.getFullHand().contains("5"))){
                        //replace input with button click or key press
                        p2.discardCard(p2.findCountess());
                        System.out.println("Countess has been discarded");
                    } else{
                    int c2 = myObj.nextInt();
                    //spy
                    if(p2.getAnyCard(c2) == 0){
                        //replace action and guess with button click or key press
                        spy.add(p2);
                        System.out.println("SPY HAS BEEN ADDED");
                        p2.discardCard(c2);
                    } 
                    //guard -1
                    else if(p2.getAnyCard(c2) == 1){
                        //replace action and guess with button click or key press
                        String action = myObj.next();
                        int guess = myObj.nextInt();
                        if(action.equals("p3") && guess == p3.getAvailableCard() && p3.getPlayerActivity() != 0) {
                            System.out.println("Guard guess was correct " + action + " card was " + guess);
                        } else if (action.equals("p1") && guess == p1.getAvailableCard() && p1.getPlayerActivity() != 0){
                            System.out.println("Guard guess was correct " + action + " card was " + guess);
                        } else{
                            System.out.println("Guard guess was incorrect " + action + " card was not " + guess);  
                        }
                        p2.discardCard(c2);
                    } 
                    //priest - 2
                    else if(p2.getAnyCard(c2) == 2){
                        //replace action and guess with button click or key press
                        String action = myObj.next();
                        if(action.equals("p3") && p3.getPlayerActivity() != 0){
                            System.out.println("PRIEST USED - PLAYER 3 CARD IS: " + p3.getAvailableCard());
                        } else if (p1.getPlayerActivity() != 0){
                            System.out.println("PRIEST USED - PLAYER 1 CARD IS: " + p1.getAvailableCard());
                        }
                        p2.discardCard(c2);
                    }
                    //baron - 3
                    else if(p2.getAnyCard(c2) == 3){
                        //replace action with button click or key press
                        String action = myObj.next();
                        if (action.equals("p3") && p3.getPlayerActivity() != 0){
                            baron(p2, p3);
                            p2.discardCard(c2);
                        } else if(p1.getPlayerActivity() != 0){
                           baron(p2,p1);
                           p2.discardCard(c2);
                        }
                    } 
                    //handmaid - 4
                    else if(p2.getAnyCard(c2) == 4){
                        //replace action and guess with button click or key press
                        p2.setPlayerActivity(0);
                        protect2 = true;
                        System.out.println("PLAYER 2 IS PROTECTED");
                        p2.discardCard(c2);
                    } 
                    //prince - 5
                    else if(p2.getAnyCard(c2) == 5){
                        //replace action and guess with button click or key press
                        p2.discardCard(c2);
                        dealCard(p2, jit, p2.getAvailableCardSpot());
                        System.out.println("PLAYER 1 CARD REDEALT - NOW IT IS" + p2.getAvailableCard());
                    } 
                    //chancellor - 6
                    else if(p2.getAnyCard(c2) == 6){
                        //replace action and guess with button click or key press
                        p2.discardCard(c2);
                        dealCard(p2, jit, p2.getBlankCardSpot());
                        dealCard(p2, jit, 5);
                        System.out.println("FULL HAND INCLUDING THE CHANCELLOR: " + p2.getFullHandChancellor());
                        System.out.println("Choose 1 - first, 2- second, 3 - third");
                        int action = myObj.nextInt();
                        if (action == 1){
                            jit.addCardToDeck(p2.getAnyCard(1));
                            p2.returnCard2(1);
                            jit.addCardToDeck(p2.getAnyCard(5));
                            p2.returnCard2(5);
                        } else if(action == 2){
                            jit.addCardToDeck(p2.getAnyCard(0));
                            p2.returnCard2(0);
                            jit.addCardToDeck(p2.getAnyCard(5));
                            p2.returnCard2(5);
                        } else{
                            jit.addCardToDeck(p2.getAnyCard(0));
                            System.out.println("FIRST CARD:" + p2.getAnyCard(0));
                            p2.returnCard2(0);
                            jit.addCardToDeck(p2.getAnyCard(1));
                            System.out.println("SECOND CARD:" + p2.getAnyCard(1));
                            p2.returnCard2(1);
                            p2.setSpecific(0, p2.getChancellor());
                            p2.ridChancellor();
                        }
                        System.out.print("After whole chancellor ting is done: ");
                        printDeck(jit);
                        System.out.println("PLAYER 1 CARD IS NOW: " + p2.getAvailableCard());
                    } 
                    //king - 7
                    else if(p2.getAnyCard(c2) == 7){
                        //replace input with button click or key press
                        p2.discardCard(c2);
                        int temp;
                        System.out.println("CHOOSE PLAYER TO SWITCH WITH - Player 1=1, PLAYER 3=3");
                        int action = myObj.nextInt();
                        if (action == 1){
                            temp = p2.getAvailableCard();
                            p2.setSpecific(p2.getAvailableCardSpot(), p1.getAvailableCard());
                            p1.setSpecific(p1.getAvailableCardSpot(), temp);
                            System.out.println("PLAYER 1 AND PLAYER 2 HAVE SWITCHED CARDS");
                            System.out.println("Player 1's hand: " + p1.getFullHand() + " Player 2's hand: " + p2.getFullHand());
                        }
                        if (action == 3){
                            temp = p2.getAvailableCard();
                            p2.setSpecific(p2.getAvailableCardSpot(), p3.getAvailableCard());
                            p3.setSpecific(p3.getAvailableCardSpot(), temp);
                            System.out.println("PLAYER 2 AND PLAYER 3 HAVE SWITCHED CARDS");
                            System.out.println("Player 2's hand: " + p2.getFullHand() + " Player 3's hand: " + p3.getFullHand());
                        }
                    } 
                    //princess - 9
                    else if(p2.getAnyCard(c2) == 9){
                        //replace input with button click or key press
                        p2.setPlayerActivity(0);
                        System.out.println("PLAYER 2 IS OUT OF THE ROUND");
                        p2.discardCard(c2);
                    } 
                }
                    System.out.println("P1 Acivity:" + p1.getPlayerActivity() + " " + "P2 Activity:" + p2.getPlayerActivity()+ " " + "P3 Activity:" + p3.getPlayerActivity());
                
                    //Check if round is over based on 2 players being out
                if(p1.getPlayerActivity() == 0 && p2.getPlayerActivity() == 0 && protect1 == false && protect2 == false){
                    p3.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(p2.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0 && protect2 == false && protect3 == false){
                    p1.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(p3.getPlayerActivity() == 0 && p1.getPlayerActivity() == 0 && protect3 == false && protect1 == false){
                    p2.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(jit.getSize() == 0){
                    break;
                }
                }
                //THIRD PLAYER STUFF
                //player3
                //undo handmaind protections at the beginning of the turn
                if(protect3 == true){
                    p3.setPlayerActivity(1);
                    protect3 = false;
                    System.out.println("PLAYER 3 IS UNPROTECTED");
                }
                //player 3 activity
                if(p3.getPlayerActivity() == 1){
                    dealDependent(p3, jit);
                    System.out.println("full hand - p3");
                    System.out.println(p3.getFullHand());
                    // Countess - 8
                    if(p3.getFullHand().contains("8") && (p3.getFullHand().contains("7") || p3.getFullHand().contains("5"))){
                        //replace input with button click or key press
                        p3.discardCard(p3.findCountess());
                        System.out.println("Countess has been discarded");
                    } else{
                    int c3 = myObj.nextInt();
                    //spy - 0
                    if(p3.getAnyCard(c3) == 0){
                        //replace action and guess with button click or key press
                        spy.add(p3);
                        System.out.println("SPY HAS BEEN ADDED");
                        p3.discardCard(c3);
                    } 
                    //guard - 1
                    else if(p3.getAnyCard(c3) == 1){
                        //replace action and guess with button click or key press
                        String action = myObj.next();
                        int guess = myObj.nextInt();
                        if(action.equals("p1") && guess == p1.getAvailableCard() && p1.getPlayerActivity() != 0){
                            System.out.println("Guard guess was correct " + action + " card was " + guess);
                        } else if (action.equals("p2") && guess == p2.getAvailableCard() && p2.getPlayerActivity() != 0){
                            System.out.println("Guard guess was correct " + action + " card was " + guess);
                        } else{
                            System.out.println("Guard guess was incorrect " + action + " card was not " + guess);  
                        }
                        p3.discardCard(c3);
                    } 
                    //priest - 2
                    else if(p3.getAnyCard(c3) == 2){
                        //replace action and guess with button click or key press
                        String action = myObj.next();
                        if(action.equals("p1") && p1.getPlayerActivity() != 0){
                            System.out.println("PRIEST USED - PLAYER 1 CARD IS: " + p1.getAvailableCard());
                        } else if (p2.getPlayerActivity() != 0){
                            System.out.println("PRIEST USED - PLAYER 2 CARD IS: " + p2.getAvailableCard());
                        }
                        p3.discardCard(c3);
                    }
                    //baron - 3
                    else if(p3.getAnyCard(c3) == 3){
                        //replace action with button click or key press
                        String action = myObj.next();
                        if (action.equals("p1") && p1.getPlayerActivity() != 0){
                            baron(p3, p1);
                            p3.discardCard(c3);
                        } else if(p2.getPlayerActivity() != 0){
                           baron(p3,p2);
                           p3.discardCard(c3);
                        }
                    } 
                    //handmaid - 4
                    else if(p3.getAnyCard(c3) == 4){
                        //replace action and guess with button click or key press
                        p3.setPlayerActivity(0);
                        protect3 = true;
                        System.out.println("PLAYER 3 IS PROTECTED");
                        p3.discardCard(c3);
                    } 
                    //prince - 5
                    else if(p3.getAnyCard(c3) == 5){
                        //replace action and guess with button click or key press
                        p3.discardCard(c3);
                        dealCard(p3, jit, p3.getAvailableCardSpot());
                        System.out.println("PLAYER 1 CARD REDEALT - NOW IT IS " + p3.getAvailableCard());
                    } 
                    //chancellor - 6
                    else if(p3.getAnyCard(c3) == 6){
                        //replace action and guess with button click or key press
                        p3.discardCard(c3);                     
                        dealCard(p3, jit, p3.getBlankCardSpot());
                        dealCard(p3, jit, 5);
                        System.out.println("FULL HAND INCLUDING THE CHANCELLOR: " + p3.getFullHandChancellor());
                        System.out.println("Choose 1 - first, 2- second, 3 - third");
                        int action = myObj.nextInt();
                        if (action == 1){
                            jit.addCardToDeck(p3.getAnyCard(1));
                            p3.returnCard2(1);
                            jit.addCardToDeck(p3.getAnyCard(5));
                            p3.returnCard2(5);
                        } else if(action == 2){
                            jit.addCardToDeck(p3.getAnyCard(0));
                            p3.returnCard2(0);
                            jit.addCardToDeck(p3.getAnyCard(5));
                            p3.returnCard2(5);
                        } else{
                            jit.addCardToDeck(p3.getAnyCard(0));
                            System.out.println("FIRST CARD:" + p3.getAnyCard(0));
                            p3.returnCard2(0);
                            jit.addCardToDeck(p3.getAnyCard(1));
                            System.out.println("SECOND CARD:" + p3.getAnyCard(1));
                            p3.returnCard2(1);
                            p3.setSpecific(0, p3.getChancellor());
                            p3.ridChancellor();
                        }
                        System.out.print("After whole chancellor ting is done: ");
                        printDeck(jit);
                        System.out.println("PLAYER 1 CARD IS NOW: " + p3.getAvailableCard());
                    } 
                    //king - 7
                    else if(p3.getAnyCard(c3) == 7){
                        //replace input with button click or key press
                        p3.discardCard(c3);
                        int temp;
                        System.out.println("CHOOSE PLAYER TO SWITCH WITH - Player 2=2, PLAYER 1=1");
                        int action = myObj.nextInt();
                        if (action == 2){
                            temp = p3.getAvailableCard();
                            p3.setSpecific(p3.getAvailableCardSpot(), p2.getAvailableCard());
                            p2.setSpecific(p2.getAvailableCardSpot(), temp);
                            System.out.println("PLAYER 2 AND PLAYER 3 HAVE SWITCHED CARDS");
                            System.out.println("Player 2's hand: " + p2.getFullHand() + " Player 3's hand: " + p3.getFullHand());
                        }
                        if (action == 1){
                            temp = p3.getAvailableCard();
                            p3.setSpecific(p3.getAvailableCardSpot(), p1.getAvailableCard());
                            p1.setSpecific(p1.getAvailableCardSpot(), temp);
                            System.out.println("PLAYER 1 AND PLAYER 3 HAVE SWITCHED CARDS");
                            System.out.println("Player 1's hand: " + p1.getFullHand() + " Player 3's hand: " + p3.getFullHand());
                        }
                    } 
                    //princess - 9
                    else if(p3.getAnyCard(c3) == 9){
                        //replace input with button click or key press
                        p3.setPlayerActivity(0);
                        System.out.println("PLAYER 3 IS OUT OF THE ROUND");
                        p3.discardCard(c3);
                    } 
                }
                    System.out.println("P1 Acivity:" + p1.getPlayerActivity() + " " + "P2 Activity:" + p2.getPlayerActivity()+ " " + "P3 Activity:" + p3.getPlayerActivity());
                //Check if round is over based on 2 players being out
                if(p1.getPlayerActivity() == 0 && p2.getPlayerActivity() == 0 && protect1 == false && protect2 == false){
                    p3.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(p2.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0 && protect2 == false && protect3 == false){
                    p1.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(p3.getPlayerActivity() == 0 && p1.getPlayerActivity() == 0 && protect1 == false && protect3 == false){
                    p2.addLoveletter();
                    roundDone = true;
                    break;
                }
                if(jit.getSize() == 0){
                    break;
                }
                }
            }
        System.out.println("remaining deck"); 
        printDeck(jit);
        //Check who gets loveletter based on 2 players being out
        if(roundDone == false){
            if((spy.size() > 0 && spy.size() <2) || (spy.size()>0 && (spy.get(0) == spy.get(1)))){
                spy.get(0).addLoveletter();
            }
        else if(p1.getPlayerActivity() == 0 & p2.getPlayerActivity() == 0){
            p3.addLoveletter();
        }
        else if(p2.getPlayerActivity() == 0 & p3.getPlayerActivity() == 0){
            p1.addLoveletter();
        }
        else if(p3.getPlayerActivity() == 0 & p1.getPlayerActivity() == 0){
            p2.addLoveletter();
        } 
        //Check who gets loveletter based on power comparison
        else{
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
            } else {
                if(p2.getDiscardedStrength() > p3.getDiscardedStrength()){
                    p2.addLoveletter();
                } else{
                    p3.addLoveletter();
                }
            }
        }
    }
    }
    System.out.println("Player1 Loveletters:" + p1.getLoveLetters() + " Player2 LoveLetters:" + p2.getLoveLetters() + " Player3 LoveLetters:" + p3.getLoveLetters());
     */
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
        if(p.getFullHand().substring(0,1).equals("n")){
            dealCard(p, d, 0);
        } else{
            dealCard(p, d, 1);
        }
    }
    public static void printDeck(Deck d){
        for(int i = 0; i<d.getSize(); i++){
            System.out.print(d.getCard(i));          
        }
        System.out.println();
    }
    public static void baron(Player p1, Player p2){
        int pow1;
        int pow2;
        if(p1.getFirstCard() == 6){
            pow1 = p1.getSecondCard();
        } else{
            pow1 = p1.getFirstCard();
        }
        pow2 = p2.getAvailableCard();
        if(pow1>pow2){
            p2.setPlayerActivity(0);
        } else if(pow2>pow1){
            p1.setPlayerActivity(0);
        } else{}

    }
}

