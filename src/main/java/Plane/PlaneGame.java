/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plane;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Student
 */
public class PlaneGame extends JComponent implements ActionListener, MouseMotionListener, KeyListener {
	public class CustomPair {
	    public int x;
	    public int y;
	    CustomPair(){
	    	this.x =0;
	    	this.y =0;
	    }
	 
	}
	int[] tablica = new int[10];
	CustomPair[] shots = new CustomPair[100];


	int shots_amount = 0;
	
    public String log;
    private int plane_x;
    private int plane_y;

    private int paddlex;
    private int plane_ySpeed;
    private int plane_xSpeed;
    
    private int enemy_x;
    private final int enemy_y;

    public int score;
    public int score1;
    private int scorefinal;
    public int bestscore;
    public int bestscore1;
    public boolean gameOver, started;
    public Timer timer;


    public PlaneGame() {
        for(int i = 0 ; i < 100 ; i++){
        	shots[i] = new CustomPair();
        }
        
        this.plane_x = 150;
        this.plane_y = 30;
        this.plane_xSpeed = 20;
        this.plane_ySpeed = 20;
        this.enemy_y=30;
        //this.timer = new Timer(); // jeszcze nie dziala odpalanie timera w konstruktorze 
        //this.timer.start();
        //timerStart();
        JOptionPane.showMessageDialog(null, "new plane !");
    }

    public void timerPause() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    public void timerStop() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    public void timerStart() {
        if (!timer.isRunning()) {
            timer.start();
        }
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
        
//draw shots
        for(int i = 0 ; i < shots_amount ; i++)
            g.fillOval(shots[i].x, shots[i].y, 15, 30);
    		
//draw the enemy
        
        
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
            this.timerStop();
        }
    }

    public void actionPerformed(ActionEvent e) {
    	
    	for(int i = 0 ; i < shots_amount ; i++){
    		shots[i].y-=5;
    	}

        plane_x = plane_x + plane_xSpeed;
        plane_y = plane_y + plane_ySpeed;

// Window Down
        if (plane_x >= paddlex && plane_x <= paddlex + 100 && plane_y >= 475 && plane_y <= 500) {

            score = 0;
            plane_y = 30;
            gameOver = true;

        }

        if (plane_y >= 700) {

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
// nic nie dzia³a na klikniêcie, log pokazuje ci¹gle null przy zakoñczeniu gry. 
// p- obs³uga pauzy
// n- nowa gra po przegranej, póŸniej siê na case przerobi

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
//reset jest bez sensu bo powinien na nowo ³adowaæ poziom i generowaæ wszystko od nowa
        if (key == 'n') {
            this.timer.start();
            score = 0;
        }
        
        if (e.getKeyCode()==KeyEvent.VK_P) {
            this.timerPause();
        }
        if (key == KeyEvent.VK_SPACE) // strzelanie
        {
        	shots[shots_amount].x = paddlex + 25;
        	shots[shots_amount++].y = 515;
        }

    }

    public void keyReleased(KeyEvent e) {

    }

    
}
