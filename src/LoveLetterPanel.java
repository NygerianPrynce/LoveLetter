import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
public class LoveLetterPanel extends JPanel{
    private BufferedImage Baron, Chancellor, Countess, Guard, Handmaid, King, Priest, Prince, Princess, Spy;
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
        } catch(Exception E){
            System.out.println("Exception error");
            return;
        }
    }
    public void paint(Graphics g){
        //has problems - not showin up
        g.drawImage(Baron, 0, 0, 800, 480, null);
        g.drawImage(Prince, 0, 0, 800, 480, null);
    }
}
