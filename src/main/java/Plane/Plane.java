package Plane;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Plane{

    public static void main(String[] args) {

        JFrame window = new JFrame("Samolocik");
        PlaneGame p;
        p = new PlaneGame();
        window.add(p);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.addMouseMotionListener(p);
        p.timer = new Timer(17,p);
        p.timer.start();
        
        //Timer timer = new Timer(17, p);
        //timer.start();

    }

}
