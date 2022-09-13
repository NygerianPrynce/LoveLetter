import java.awt.*;
import javax.swing.*;
public class LoveLetterGraphic extends JFrame{
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 960;
    public LoveLetterGraphic(String framename){
        super(framename);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        add(new LoveLetterPanel());
        setVisible(true);
        
    }
}

