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
    private BufferedImage Baron, Chancellor, Countess, Guard, Handmaid, King, Priest, Prince, Princess, Spy, ReferenceCard, CardBack, Player1Icon, Player2Icon, Player3Icon, Token;
    public Integer choice = null;
    public Integer pChoice = null;
    public Player p1 = new Player();
    public Player p2 = new Player();
    public Player p3 = new Player();
    public BufferedImage[] cardFaces = new BufferedImage[10];
    public BufferedImage[] playerIcons = new BufferedImage[3];
    ArrayList<Integer> playerSpots = new ArrayList<Integer>(3);
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
        } catch(Exception E){
            System.out.println("Exception error");
            return;
        }
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
        int[][] playerIconSizes = {{getWidth()/2 - getWidth()/46, (getHeight()/2 + getHeight()/12), getWidth()/23, getHeight()/15},
                                     {getWidth()/15, getHeight()/15, getWidth()/6, getHeight()/4},
                                     {(getWidth() - getWidth()/9 - getWidth()/8), getHeight()/15, getWidth()/6, getHeight()/4}};
        System.out.println("loc is ("+x+","+y+")");
        if(e.getButton() == e.BUTTON1){
            if(x >= playableCardSizes[0][0] && y>= playableCardSizes[0][1] && x<= playableCardSizes[0][0] + playableCardSizes[0][2] && y<= playableCardSizes[0][1] + playableCardSizes[0][3]){
                choice = 0;
            }
            else if(x >= playableCardSizes[1][0] && y>= playableCardSizes[1][1] && x<= playableCardSizes[1][0] + playableCardSizes[1][2] && y<= playableCardSizes[1][1] + playableCardSizes[1][3]){
                choice = 1;
            }
            else if(x >= playableCardSizes[2][0] && y>= playableCardSizes[2][1] && x<= playableCardSizes[2][0] + playableCardSizes[2][2] && y<= playableCardSizes[2][1] + playableCardSizes[2][3]){
                choice = 2;
            }
            //specific sizes for player icons - 0:as if the one playing & 1:left sideline & 2:right sideline
            else if(x >= playerIconSizes[0][0] && y>= playerIconSizes[0][1] && x<= playerIconSizes[0][0] + playerIconSizes[0][2] && y<= playerIconSizes[0][1] + playerIconSizes[0][3]){
                pChoice = playerSpots.get(0);
            }
            else if(x >= playerIconSizes[1][0] && y>= playerIconSizes[1][1] && x<= playerIconSizes[1][0] + playerIconSizes[1][2] && y<= playerIconSizes[1][1] + playerIconSizes[1][3]){
                pChoice = playerSpots.get(1);
            }
            else if(x >= playerIconSizes[2][0] && y>= playerIconSizes[2][1] && x<= playerIconSizes[2][0] + playerIconSizes[2][2] && y<= playerIconSizes[2][1] + playerIconSizes[2][3]){
                pChoice = playerSpots.get(2);
            }
            repaint();
        }
    }
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){   
        //Initializes important stuff 4 the player
        ArrayList<Integer> random = new ArrayList<Integer>();
        random.add(0);
        random.add(1);
        random.add(2);
        int r = (int)(Math.random()*(3));
        p1.addPlayerNumber(random.remove(r));
        r = (int)(Math.random()*(2));
        p2.addPlayerNumber(random.remove(r));
        p3.addPlayerNumber(random.remove(0));   
        playerSpots.add(p1.getPlayerNumber());
        playerSpots.add(p2.getPlayerNumber());
        playerSpots.add(p3.getPlayerNumber());  
        //playerIcon switches based on whose playing
        BufferedImage activePlayerIcon = playerIcons[playerSpots.get(0)];
        BufferedImage idlePlayerIcon1 = playerIcons[playerSpots.get(1)];
        BufferedImage idlePlayerIcon2 = playerIcons[playerSpots.get(2)];
        int tokenCount = 0;
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
        //LoveLetter Spot
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("Token Count: " + tokenCount, getWidth()/5 - getWidth()/80, getHeight()-(getHeight()/6 + getHeight()/15));
        g.drawImage(Token, getWidth()/5, getHeight() -getHeight()/5, getWidth()/16, getHeight()/10, null);
        //discard pile - use length of arraylist to multiply the stuff in a for loop
        int discardSize = 10;
        for(int i = 0; i<discardSize; i++){
            g.drawImage(Baron, discardPileSizes[0][0], discardPileSizes[0][1] + 20*i, discardPileSizes[0][2], discardPileSizes[0][3], null);
        }
        for(int i = 0; i<discardSize; i++){
            g.drawImage(Baron, discardPileSizes[1][0], discardPileSizes[1][1] + 20*i, discardPileSizes[1][2], discardPileSizes[1][3],null);
        }
        for(int i = 0; i<discardSize; i++){
            g.drawImage(Baron, discardPileSizes[2][0], discardPileSizes[2][1] + 20*i, discardPileSizes[2][2], discardPileSizes[2][3],null);
        }
        //image covers
        g.setColor(Color.yellow);
        g.drawRect(playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3]);
        g.setColor(Color.blue);
        g.drawRect(playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3]);
        g.setColor(Color.red);
        g.drawRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
        //open face card for priest
        //g.drawImage(Baron, (getWidth()/4+getWidth()/10), stagnantCardSizes[0][1] + getHeight()/30, getWidth()/14+getWidth()/50, stagnantCardSizes[0][3] - getWidth()/30, null);

        //runs the entire game
        
        while(p1.getLoveLetters()<5 && p2.getLoveLetters()<5 && p3.getLoveLetters()<5){
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
                    //deals
                    dealDependent(p1, jit);
                    System.out.println("full hand - p1");
                    System.out.println(p1.getFullHand());
                    //draws the dealt cards
                    g.drawImage(cardFaces[p1.getFirstCard()], playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
                    g.drawImage(cardFaces[p1.getSecondCard()], playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
                    //Countess - 8 /* 
                    if(p1.getFullHand().contains("8") && (p1.getFullHand().contains("7") || p1.getFullHand().contains("5"))){
                        //replace input with button click or key press
                        while(p1.getAnyCard(choice) != 8){}
                        if(p1.getAnyCard(choice) == 8){
                            p1.discardCard(p1.findCountess());
                            System.out.println("Countess has been discarded");
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        }
                    } else{
                        while(choice == null){}
                        int c1 = myObj.nextInt();
                        //spy
                        if(p1.getAnyCard(choice) == 0){
                            //replace action and guess with button click or key press
                            spy(spy, p1, choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //guard - 1 - deal with after getting everything elses mouse clicks right
                        /*
                        else if(p1.getAnyCard(choice) == 1){
                            //replace action and guess with button click or key press 
                            //needs a keyboard input - ADD
                            while(choice == null){}
                            String action = myObj.next();
                            int guess = myObj.nextInt();
                            if(action.equals("p2") && guess == p2.getAvailableCard() && p2.getPlayerActivity() != 0){
                                p2.setPlayerActivity(0);
                            } else if (action.equals("p3") && guess == p3.getAvailableCard()&& p3.getPlayerActivity() != 0){
                                p3.setPlayerActivity(0);
                            } else{
                                System.out.println("Guard guess was incorrect " + action + " card was not " + guess);  
                            }
                        } 
                        */
                        //priest - 2
                        else if(p1.getAnyCard(choice) == 2){
                            //replace action and guess with button click or key press
                            if (p2.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0){
                                p1.discardCard(choice);
                            } else{
                                p1.discardCard(choice);
                                while(pChoice == null){}
                                g.drawImage(cardFaces[priest(pChoice, p2, p3).getAvailableCard()], (getWidth()/4+getWidth()/10), stagnantCardSizes[0][1] + getHeight()/30, getWidth()/14+getWidth()/50, stagnantCardSizes[0][3] - getWidth()/30, null);
                            }
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        }
                        //baron - 3 - DONE w loop
                        else if(p1.getAnyCard(choice) == 3){
                            //replace action with button click or key press
                            if (p2.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0){
                                p1.discardCard(choice);
                            } else{
                                p1.discardCard(choice);
                                while(pChoice == null){}
                                baron(pChoice, p1, p2, p3);
                            }
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //handmaid - 4 - DONE w while loop
                        else if(p1.getAnyCard(choice) == 4){
                            //replace action and guess with button click or key press
                            handmaid(p1, protect1,choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //prince - 5 - DONE w loop
                        else if(p1.getAnyCard(choice) == 5){
                            //replace action and guess with button click or key press
                            p1.discardCard(choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                            while(pChoice == null){}
                            prince(p1, p2, p3, pChoice, jit);
                            g.drawImage(cardFaces[p1.getAvailableCard()], playableCardSizes[p1.getAvailableCardSpot()][0], playableCardSizes[p1.getAvailableCardSpot()][1], playableCardSizes[p1.getAvailableCardSpot()][2], playableCardSizes[p1.getAvailableCardSpot()][3], null);
                        } 
                        //chancellor - 6 - DONE w loop
                        else if(p1.getAnyCard(choice) == 6){
                            //replace action and guess with button click or key press
                            int bam = chancellor1(p1, choice, jit);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            g.drawImage(cardFaces[p1.getChancellor()], playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3], null);
                            g.drawImage(cardFaces[p1.getAvailableCard()], playableCardSizes[p1.getAvailableCardSpot()][0], playableCardSizes[p1.getAvailableCardSpot()][1], playableCardSizes[p1.getAvailableCardSpot()][2], playableCardSizes[p1.getAvailableCardSpot()][3], null);
                            choice = null;
                            while(choice == null){}
                            chancellor2(p1, choice, jit, bam);
                            g.setColor(Color.WHITE);
                            if(bam == 0){
                                g.fillRect(playableCardSizes[p1.getBlankCardSpot()][0], playableCardSizes[p1.getBlankCardSpot()][1], playableCardSizes[p1.getBlankCardSpot()][2], playableCardSizes[p1.getBlankCardSpot()][3]);
                            } else{
                                g.fillRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
                                g.fillRect(playableCardSizes[p1.getBlankCardSpot()][0], playableCardSizes[p1.getBlankCardSpot()][1], playableCardSizes[p1.getBlankCardSpot()][2], playableCardSizes[p1.getBlankCardSpot()][3]);
                            }
                            choice = null;
                        } 
                        //king - 7 - done w loop
                        else if(p1.getAnyCard(choice) == 7){
                            //replace input with button click or key press
                            if (p2.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0){
                                p1.discardCard(choice);
                            } else{
                                p1.discardCard(choice);
                                while(pChoice == null){}
                                king(p1, p2, p3, pChoice);
                                g.drawImage(cardFaces[p1.getAvailableCard()], playableCardSizes[p1.getAvailableCardSpot()][0], playableCardSizes[p1.getAvailableCardSpot()][1], playableCardSizes[p1.getAvailableCardSpot()][2], playableCardSizes[p1.getAvailableCardSpot()][3], null);
                                g.fillRect(playableCardSizes[p1.getBlankCardSpot()][0], playableCardSizes[p1.getBlankCardSpot()][1], playableCardSizes[p1.getBlankCardSpot()][2], playableCardSizes[p1.getBlankCardSpot()][3]);
                            }
                            choice = null;
                        } 
                        // countess in case
                        else if(p1.getAnyCard(choice) == 8){
                            p1.discardCard(choice);
                            g.fillRect(playableCardSizes[p1.getBlankCardSpot()][0], playableCardSizes[p1.getBlankCardSpot()][1], playableCardSizes[p1.getBlankCardSpot()][2], playableCardSizes[p1.getBlankCardSpot()][3]);
                            choice = null;
                        }
                        //princess - 9 - DOne
                        else if(p1.getAnyCard(choice) == 9){
                            //replace input with button click or key press
                            princess(p1, choice);
                            g.fillRect(playableCardSizes[p1.getBlankCardSpot()][0], playableCardSizes[p1.getBlankCardSpot()][1], playableCardSizes[p1.getBlankCardSpot()][2], playableCardSizes[p1.getBlankCardSpot()][3]);
                            g.fillRect(playableCardSizes[p1.getAvailableCardSpot()][0], playableCardSizes[p1.getAvailableCardSpot()][1], playableCardSizes[p1.getAvailableCardSpot()][2], playableCardSizes[p1.getAvailableCardSpot()][3]);
                            choice = null;
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
                cyclePlayers(playerSpots);
                //----------------------------------------------------------------------------
                //player2
                //undo handmaind protections at the beginning of the turn
                if(protect2 == true){
                    p2.setPlayerActivity(1);
                    protect2 = false;
                    System.out.println("PLAYER 2 IS UNPROTECTED");
                }
                //player1 activity
                if(p2.getPlayerActivity() == 1){
                    //deals
                    dealDependent(p2, jit);
                    System.out.println("full hand - p2");
                    System.out.println(p2.getFullHand());
                    //draws the dealt cards
                    g.drawImage(cardFaces[p2.getFirstCard()], playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
                    g.drawImage(cardFaces[p2.getSecondCard()], playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
                    //Countess - 8 /* 
                    if(p2.getFullHand().contains("8") && (p2.getFullHand().contains("7") || p2.getFullHand().contains("5"))){
                        //replace input with button click or key press
                        while(p2.getAnyCard(choice) != 8){}
                        if(p2.getAnyCard(choice) == 8){
                            p2.discardCard(p2.findCountess());
                            System.out.println("Countess has been discarded");
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        }
                    } else{
                        while(choice == null){}
                        //spy
                        if(p2.getAnyCard(choice) == 0){
                            //replace action and guess with button click or key press
                            spy(spy, p2, choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //guard - 1 - deal with after getting everything elses mouse clicks right
                        /*
                        else if(p1.getAnyCard(choice) == 1){
                            //replace action and guess with button click or key press 
                            //needs a keyboard input - ADD
                            while(choice == null){}
                            String action = myObj.next();
                            int guess = myObj.nextInt();
                            if(action.equals("p2") && guess == p2.getAvailableCard() && p2.getPlayerActivity() != 0){
                                p2.setPlayerActivity(0);
                            } else if (action.equals("p3") && guess == p3.getAvailableCard()&& p3.getPlayerActivity() != 0){
                                p3.setPlayerActivity(0);
                            } else{
                                System.out.println("Guard guess was incorrect " + action + " card was not " + guess);  
                            }
                        } 
                        */
                        //priest - 2
                        else if(p2.getAnyCard(choice) == 2){
                            //replace action and guess with button click or key press
                            if (p1.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0){
                                p2.discardCard(choice);
                            } else{
                                p2.discardCard(choice);
                                while(pChoice == null){}
                                g.drawImage(cardFaces[priest(pChoice, p1, p3).getAvailableCard()], (getWidth()/4+getWidth()/10), stagnantCardSizes[0][1] + getHeight()/30, getWidth()/14+getWidth()/50, stagnantCardSizes[0][3] - getWidth()/30, null);
                            }
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        }
                        //baron - 3 - DONE w loop
                        else if(p2.getAnyCard(choice) == 3){
                            //replace action with button click or key press
                            if (p1.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0){
                                p2.discardCard(choice);
                            } else{
                                p2.discardCard(choice);
                                while(pChoice == null){}
                                baron(pChoice, p2, p1, p3);
                            }
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //handmaid - 4 - DONE w while loop
                        else if(p2.getAnyCard(choice) == 4){
                            //replace action and guess with button click or key press
                            handmaid(p2, protect1,choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //prince - 5 - DONE w loop
                        else if(p2.getAnyCard(choice) == 5){
                            //replace action and guess with button click or key press
                            p2.discardCard(choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                            while(pChoice == null){}
                            prince(p2, p1, p3, pChoice, jit);
                            g.drawImage(cardFaces[p2.getAvailableCard()], playableCardSizes[p2.getAvailableCardSpot()][0], playableCardSizes[p2.getAvailableCardSpot()][1], playableCardSizes[p2.getAvailableCardSpot()][2], playableCardSizes[p2.getAvailableCardSpot()][3], null);
                        } 
                        //chancellor - 6 - DONE w loop
                        else if(p2.getAnyCard(choice) == 6){
                            //replace action and guess with button click or key press
                            int bam = chancellor1(p2, choice, jit);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            g.drawImage(cardFaces[p2.getChancellor()], playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3], null);
                            g.drawImage(cardFaces[p2.getAvailableCard()], playableCardSizes[p2.getAvailableCardSpot()][0], playableCardSizes[p2.getAvailableCardSpot()][1], playableCardSizes[p2.getAvailableCardSpot()][2], playableCardSizes[p2.getAvailableCardSpot()][3], null);
                            choice = null;
                            while(choice == null){}
                            chancellor2(p2, choice, jit, bam);
                            g.setColor(Color.WHITE);
                            if(bam == 0){
                                g.fillRect(playableCardSizes[p2.getBlankCardSpot()][0], playableCardSizes[p2.getBlankCardSpot()][1], playableCardSizes[p2.getBlankCardSpot()][2], playableCardSizes[p2.getBlankCardSpot()][3]);
                            } else{
                                g.fillRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
                                g.fillRect(playableCardSizes[p2.getBlankCardSpot()][0], playableCardSizes[p2.getBlankCardSpot()][1], playableCardSizes[p2.getBlankCardSpot()][2], playableCardSizes[p2.getBlankCardSpot()][3]);
                            }
                            choice = null;
                        } 
                        //king - 7 - done w loop
                        else if(p2.getAnyCard(choice) == 7){
                            //replace input with button click or key press
                            if (p1.getPlayerActivity() == 0 && p3.getPlayerActivity() == 0){
                                p2.discardCard(choice);
                            } else{
                                p2.discardCard(choice);
                                while(pChoice == null){}
                                king(p2, p1, p3, pChoice);
                                g.drawImage(cardFaces[p2.getAvailableCard()], playableCardSizes[p2.getAvailableCardSpot()][0], playableCardSizes[p2.getAvailableCardSpot()][1], playableCardSizes[p2.getAvailableCardSpot()][2], playableCardSizes[p2.getAvailableCardSpot()][3], null);
                                g.fillRect(playableCardSizes[p2.getBlankCardSpot()][0], playableCardSizes[p2.getBlankCardSpot()][1], playableCardSizes[p2.getBlankCardSpot()][2], playableCardSizes[p2.getBlankCardSpot()][3]);
                            }
                            choice = null;
                        } 
                        // countess in case
                        else if(p2.getAnyCard(choice) == 8){
                            p2.discardCard(choice);
                            g.fillRect(playableCardSizes[p2.getBlankCardSpot()][0], playableCardSizes[p2.getBlankCardSpot()][1], playableCardSizes[p2.getBlankCardSpot()][2], playableCardSizes[p2.getBlankCardSpot()][3]);
                            choice = null;
                        }
                        //princess - 9 - DOne
                        else if(p2.getAnyCard(choice) == 9){
                            //replace input with button click or key press
                            princess(p2, choice);
                            g.fillRect(playableCardSizes[p1.getBlankCardSpot()][0], playableCardSizes[p1.getBlankCardSpot()][1], playableCardSizes[p1.getBlankCardSpot()][2], playableCardSizes[p1.getBlankCardSpot()][3]);
                            g.fillRect(playableCardSizes[p2.getAvailableCardSpot()][0], playableCardSizes[p2.getAvailableCardSpot()][1], playableCardSizes[p2.getAvailableCardSpot()][2], playableCardSizes[p2.getAvailableCardSpot()][3]);
                            choice = null;
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
                cyclePlayers(playerSpots);
                //player3
                //undo handmaind protections at the beginning of the turn
                if(protect3 == true){
                    p3.setPlayerActivity(1);
                    protect3 = false;
                    System.out.println("PLAYER 3 IS UNPROTECTED");
                }
                //player1 activity
                if(p3.getPlayerActivity() == 1){
                    //deals
                    dealDependent(p3, jit);
                    System.out.println("full hand - p3");
                    System.out.println(p3.getFullHand());
                    //draws the dealt cards
                    g.drawImage(cardFaces[p3.getFirstCard()], playableCardSizes[0][0], playableCardSizes[0][1], playableCardSizes[0][2], playableCardSizes[0][3], null);
                    g.drawImage(cardFaces[p3.getSecondCard()], playableCardSizes[1][0], playableCardSizes[1][1], playableCardSizes[1][2], playableCardSizes[1][3], null);
                    //Countess - 8 /* 
                    if(p3.getFullHand().contains("8") && (p3.getFullHand().contains("7") || p3.getFullHand().contains("5"))){
                        //replace input with button click or key press
                        while(p3.getAnyCard(choice) != 8){}
                        if(p3.getAnyCard(choice) == 8){
                            p3.discardCard(p3.findCountess());
                            System.out.println("Countess has been discarded");
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        }
                    } else{
                        while(choice == null){}
                        //spy
                        if(p3.getAnyCard(choice) == 0){
                            //replace action and guess with button click or key press
                            spy(spy, p3, choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //guard - 1 - deal with after getting everything elses mouse clicks right
                        /*
                        else if(p1.getAnyCard(choice) == 1){
                            //replace action and guess with button click or key press 
                            //needs a keyboard input - ADD
                            while(choice == null){}
                            String action = myObj.next();
                            int guess = myObj.nextInt();
                            if(action.equals("p2") && guess == p2.getAvailableCard() && p2.getPlayerActivity() != 0){
                                p2.setPlayerActivity(0);
                            } else if (action.equals("p3") && guess == p3.getAvailableCard()&& p3.getPlayerActivity() != 0){
                                p3.setPlayerActivity(0);
                            } else{
                                System.out.println("Guard guess was incorrect " + action + " card was not " + guess);  
                            }
                        } 
                        */
                        //priest - 2
                        else if(p3.getAnyCard(choice) == 2){
                            //replace action and guess with button click or key press
                            if (p2.getPlayerActivity() == 0 && p1.getPlayerActivity() == 0){
                                p3.discardCard(choice);
                            } else{
                                p3.discardCard(choice);
                                while(pChoice == null){}
                                g.drawImage(cardFaces[priest(pChoice, p2, p1).getAvailableCard()], (getWidth()/4+getWidth()/10), stagnantCardSizes[0][1] + getHeight()/30, getWidth()/14+getWidth()/50, stagnantCardSizes[0][3] - getWidth()/30, null);
                            }
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        }
                        //baron - 3 - DONE w loop
                        else if(p3.getAnyCard(choice) == 3){
                            //replace action with button click or key press
                            if (p2.getPlayerActivity() == 0 && p1.getPlayerActivity() == 0){
                                p3.discardCard(choice);
                            } else{
                                p3.discardCard(choice);
                                while(pChoice == null){}
                                baron(pChoice, p3, p2, p1);
                            }
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //handmaid - 4 - DONE w while loop
                        else if(p3.getAnyCard(choice) == 4){
                            //replace action and guess with button click or key press
                            handmaid(p3, protect1,choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                        } 
                        //prince - 5 - DONE w loop
                        else if(p3.getAnyCard(choice) == 5){
                            //replace action and guess with button click or key press
                            p3.discardCard(choice);
                            g.setColor(Color.WHITE);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            choice = null;
                            while(pChoice == null){}
                            prince(p3, p2, p1, pChoice, jit);
                            g.drawImage(cardFaces[p3.getAvailableCard()], playableCardSizes[p3.getAvailableCardSpot()][0], playableCardSizes[p3.getAvailableCardSpot()][1], playableCardSizes[p3.getAvailableCardSpot()][2], playableCardSizes[p3.getAvailableCardSpot()][3], null);
                        } 
                        //chancellor - 6 - DONE w loop
                        else if(p3.getAnyCard(choice) == 6){
                            //replace action and guess with button click or key press
                            int bam = chancellor1(p3, choice, jit);
                            g.fillRect(playableCardSizes[choice][0], playableCardSizes[choice][1], playableCardSizes[choice][2], playableCardSizes[choice][3]);
                            g.drawImage(cardFaces[p3.getChancellor()], playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3], null);
                            g.drawImage(cardFaces[p3.getAvailableCard()], playableCardSizes[p3.getAvailableCardSpot()][0], playableCardSizes[p3.getAvailableCardSpot()][1], playableCardSizes[p3.getAvailableCardSpot()][2], playableCardSizes[p3.getAvailableCardSpot()][3], null);
                            choice = null;
                            while(choice == null){}
                            chancellor2(p3, choice, jit, bam);
                            g.setColor(Color.WHITE);
                            if(bam == 0){
                                g.fillRect(playableCardSizes[p3.getBlankCardSpot()][0], playableCardSizes[p3.getBlankCardSpot()][1], playableCardSizes[p3.getBlankCardSpot()][2], playableCardSizes[p3.getBlankCardSpot()][3]);
                            } else{
                                g.fillRect(playableCardSizes[2][0], playableCardSizes[2][1], playableCardSizes[2][2], playableCardSizes[2][3]);
                                g.fillRect(playableCardSizes[p3.getBlankCardSpot()][0], playableCardSizes[p3.getBlankCardSpot()][1], playableCardSizes[p3.getBlankCardSpot()][2], playableCardSizes[p3.getBlankCardSpot()][3]);
                            }
                            choice = null;
                        } 
                        //king - 7 - done w loop
                        else if(p3.getAnyCard(choice) == 7){
                            //replace input with button click or key press
                            if (p2.getPlayerActivity() == 0 && p1.getPlayerActivity() == 0){
                                p3.discardCard(choice);
                            } else{
                                p3.discardCard(choice);
                                while(pChoice == null){}
                                king(p3, p2, p1, pChoice);
                                g.drawImage(cardFaces[p3.getAvailableCard()], playableCardSizes[p3.getAvailableCardSpot()][0], playableCardSizes[p3.getAvailableCardSpot()][1], playableCardSizes[p3.getAvailableCardSpot()][2], playableCardSizes[p3.getAvailableCardSpot()][3], null);
                                g.fillRect(playableCardSizes[p3.getBlankCardSpot()][0], playableCardSizes[p3.getBlankCardSpot()][1], playableCardSizes[p3.getBlankCardSpot()][2], playableCardSizes[p3.getBlankCardSpot()][3]);
                            }
                            choice = null;
                        } 
                        // countess in case
                        else if(p3.getAnyCard(choice) == 8){
                            p3.discardCard(choice);
                            g.fillRect(playableCardSizes[p3.getBlankCardSpot()][0], playableCardSizes[p3.getBlankCardSpot()][1], playableCardSizes[p3.getBlankCardSpot()][2], playableCardSizes[p3.getBlankCardSpot()][3]);
                            choice = null;
                        }
                        //princess - 9 - DOne
                        else if(p3.getAnyCard(choice) == 9){
                            //replace input with button click or key press
                            princess(p3, choice);
                            g.fillRect(playableCardSizes[p3.getBlankCardSpot()][0], playableCardSizes[p3.getBlankCardSpot()][1], playableCardSizes[p3.getBlankCardSpot()][2], playableCardSizes[p3.getBlankCardSpot()][3]);
                            g.fillRect(playableCardSizes[p3.getAvailableCardSpot()][0], playableCardSizes[p3.getAvailableCardSpot()][1], playableCardSizes[p3.getAvailableCardSpot()][2], playableCardSizes[p3.getAvailableCardSpot()][3]);
                            choice = null;
                        } 
                    }
                    cyclePlayers(playerSpots);
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
    public static void cyclePlayers(ArrayList<Integer> a){
        a.add(0, a.remove(a.size() - 1));
    }
    public static void spy(ArrayList<Player> s, Player a, Integer choice){
        s.add(a);
        System.out.println("SPY HAS BEEN ADDED");
        a.discardCard(choice);
    }
    public static Player priest(Integer pChoice, Player a, Player b){
        if(pChoice == a.getPlayerNumber() && a.getPlayerActivity() != 0){
            return a;
        } else {
            return b;
        } 
    }
    public static void handmaid(Player a, boolean b, Integer choice){
        a.setPlayerActivity(0);
        b = true;
        System.out.println("PLAYER 1 IS PROTECTED");
        a.discardCard(choice);
    }
    public static void prince(Player a, Player b, Player c, Integer pChoice, Deck d){
        if(b.getPlayerActivity() != 0 && b.getPlayerNumber() == pChoice){
            b.discardCard(b.getAvailableCardSpot());
            dealCard(b, d, b.getAvailableCardSpot());
        } else if (c.getPlayerActivity() != 0 && c.getPlayerNumber() == pChoice){ 
            c.discardCard(c.getAvailableCardSpot());
            dealCard(c, d, c.getAvailableCardSpot());
        } else{
            a.discardCard(a.getAvailableCardSpot());
            dealCard(a, d, a.getAvailableCardSpot());
        }
        //System.out.println("PLAYER 1 CARD REDEALT - NOW IT IS" + a.getAvailableCard());
    }
    public static void baron(Integer pChoice, Player a, Player b, Player c){
        int pow1;
        int pow2;
        pow1 = a.getAvailableCard();
        if(a.getPlayerActivity() != 0 && a.getPlayerNumber() == pChoice){
            pow2 = b.getAvailableCard();
            if(pow1>pow2){
                b.setPlayerActivity(0);
            } else if(pow2>pow1){
                a.setPlayerActivity(0);
            }
        } else{
            pow2 = c.getAvailableCard();
            if(pow1>pow2){
                c.setPlayerActivity(0);
            } else if(pow2>pow1){
                a.setPlayerActivity(0);
            }
        }
    }
    public static Integer chancellor1(Player a, Integer choice, Deck d){
        a.discardCard(choice);
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
    public static void king(Player a, Player b, Player c, Integer pChoice){
        int temp;
        if (b.getPlayerActivity() != 0 && b.getPlayerNumber() == pChoice){
            temp = a.getAvailableCard();
            a.setSpecific(a.getAvailableCardSpot(), b.getAvailableCard());
            b.setSpecific(b.getAvailableCardSpot(), temp);
        } else{
            temp = a.getAvailableCard();
            a.setSpecific(a.getAvailableCardSpot(), c.getAvailableCard());
            c.setSpecific(c.getAvailableCardSpot(), temp);
        }
    }
    public static void princess(Player a, Integer choice){
        a.setPlayerActivity(0);
        System.out.println("PLAYER 1 IS OUT OF THE ROUND");
        a.discardCard(choice);
    }
}

