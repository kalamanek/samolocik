package Plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Plane extends JComponent implements ActionListener, MouseMotionListener, KeyListener {

private int plane_x = 150;
private int plane_y = 30;

private int paddlex = 0; 
private int plane_ySpeed = 20;
private int plane_xSpeed = 20;

public int score = 0;
public int score1 = 0;
private int scorefinal;
public int bestscore;
public int bestscore1;
public boolean gameOver, started;

public static void main(String[] args) {

JFrame window = new JFrame("Samolocik");
Plane p = new Plane();
window.add(p);
window.pack();
window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
window.setLocationRelativeTo(null);
window.setVisible(true);
window.addMouseMotionListener(p);


Timer timer = new Timer(17, p);
timer.start();

}

public void newplane(int plane_x, int plane_y, int plane_xspeed, int plane_yspeed) {


plane_x = 150;
plane_y = 30;
plane_xspeed = 5;
plane_yspeed = 7;

JOptionPane.showMessageDialog(null, "new plane !");

}

@Override
public Dimension getPreferredSize() {

return new Dimension(800, 600);
}

@Override
protected void paintComponent(Graphics g) {

//draw the sky
g.setColor(Color.cyan);
g.fillRect(0, 0, 800, 600);


//draw the paddel
g.setColor(Color.black);
g.fillRect(paddlex, 500, 100, 20);

//draw the ball
g.setColor(Color.RED);
g.fillOval(plane_x, plane_y, 30, 30);

/*//draw the ball_1
if (score >= 5) {
g.setColor(Color.BLACK);
g.fillOval(ballx1, bally1, 30, 30);

}*/
//score
if (score >= 5) {
g.setColor(Color.red);
g.setFont(new Font("Arial", 8, 50));
g.drawString(String.valueOf(score + score1), 30 / 1 - 15, 80);
} else {
g.setColor(Color.white);
g.setFont(new Font("Arial", 8, 50));
g.drawString(String.valueOf(score), 30 / 1 - 15, 80);
}
// start && gameOver
g.setColor(Color.white);
g.setFont(new Font("Arial", 8, 50));

if (gameOver) {

g.drawString(String.valueOf(" Best Score :" + scorefinal), 50 / 1 - 15, 200);

}
}

public void actionPerformed(ActionEvent e) {

plane_x = plane_x + plane_xSpeed;
plane_y = plane_y + plane_ySpeed;

// Window Down
if (plane_x >= paddlex && plane_x <= paddlex + 100 && plane_y >= 475 && plane_y <=500) {


score = 0;
plane_y = 30;
gameOver = true;

}

if (plane_y >= 700 ) {

score++;
plane_y = 30;

}

// Window up
if (plane_y <= 0) {

plane_ySpeed = 20;

}

// Window right
if (plane_x >= 775) {

plane_xSpeed = -15;

}

// Window left
if (plane_x <= 0) {

plane_xSpeed = 15;

}





//**********************************************************************
bestscore = score;
bestscore1 = score1;

if (scorefinal > bestscore) {

scorefinal = scorefinal;

} else {

scorefinal = bestscore1;
scorefinal = score + score1;
}

if (scorefinal > bestscore) {

scorefinal = scorefinal;

} else {

scorefinal = bestscore;
scorefinal = score + score1;
}


repaint();
}

public void mouseMoved(MouseEvent e) {

paddlex = e.getX() - 50;
repaint();
}

public void mouseDragged(MouseEvent e) {
}

public void keyTyped(KeyEvent e) {

}

public void keyPressed(KeyEvent e) {

}

public void keyReleased(KeyEvent e) {

}

}