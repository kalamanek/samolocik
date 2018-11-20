
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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class PlaneGame extends JComponent implements ActionListener, MouseMotionListener, KeyListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[] tablica = new int[10];
    CustomPair[] shots = new CustomPair[1000];
    CustomPair holder = new CustomPair();

    int shots_amount = 0;
    int shot_type = 3;
    int shots_speed = 3;
    int cleanShotArrayCounter =0;
    public String log;
    private int plane_x;
    private int plane_y;

    private int paddlex;
    private int plane_ySpeed;
    private int plane_xSpeed;
    public Random r = new Random();
    public int score;
    public int score1;
    private int scorefinal;
    public int bestscore;
    public int bestscore1;
    public boolean gameOver, started;
    public Timer timer;

    /**
     *
     */
    public List<Enemy> enemyList;

    public PlaneGame() {
        this.enemyList = new LinkedList<Enemy>();
        this.enemyList.add(new Enemy());
        this.enemyList.add(new Enemy());
        this.enemyList.add(new Enemy());
        for (int i = 0; i < 1000; i++) {
            shots[i] = new CustomPair();
        }
        this.plane_x = 150;
        this.plane_y = 30;
        this.plane_xSpeed = 20;
        this.plane_ySpeed = 20;
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

//testuje mape, potem wyrzuce ~Kuba
        g.setColor(Color.yellow);
        //panele boczne
        g.fillRect(0, 0, 20, 600);
        g.fillRect(780, 0, 20, 600);
        // "tor" przeciwnika
        g.setColor(Color.pink);
        g.fillRect(20, 0, 40, 600);
        g.fillRect(100, 0, 40, 600);
        g.fillRect(180, 0, 40, 600);
        g.fillRect(180, 0, 40, 600);
        g.fillRect(260, 0, 40, 600);
        g.fillRect(340, 0, 40, 600);
        g.fillRect(420, 0, 40, 600);
        g.fillRect(500, 0, 40, 600);
        g.fillRect(580, 0, 40, 600);
        g.fillRect(660, 0, 40, 600);
        g.fillRect(740, 0, 40, 600);

//koniec do usuniecia
//draw the paddel
        g.setColor(Color.black);
        g.fillRect(paddlex, 500, 100, 20);

//draw the enemy
        g.setColor(Color.black);
        for (int j = 0; j < this.enemyList.size(); j++) {
            e1 = (Enemy) enemyList.get(j);
            g.fillOval(e1.getPositionX(), e1.getPositionY(), 40, 40);
        }
        //g.fillOval(e1.getPositionX(), e1.getPositionY(), 40, 40);
//draw shots
        for (int i = 0; i < shots_amount; i++) {
            g.fillOval(shots[i].x, shots[i].y, 15, 30);
        }
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
    Enemy e1 = new Enemy();

    public void actionPerformed(ActionEvent e) {
        for (int j = 0; j < this.enemyList.size(); j++) {
            int p = r.nextInt(19) + 1;
            e1 = (Enemy) enemyList.get(j);
            for (int i = 0; i < shots_amount; i++) {
                shots[i].y -= shots_speed;
                if (shots[i].x >= e1.getPositionX() && shots[i].x+15 <= e1.getPositionX() + 40
                        && shots[i].y <= e1.getPositionY() && shots[i].y+30 <=e1.getPositionY() +40) {
                	shots[i].y = -10;
                    e1.generateNewPosition(p);
                    e1.setPositionY(10);
                }
            }
        }
        cleanShotArray();
        plane_x = plane_x + plane_xSpeed;
        plane_y = plane_y + plane_ySpeed;
        for (int j = 0; j < this.enemyList.size(); j++) {
            e1 = (Enemy) enemyList.get(j);
            e1.setPositionY(e1.getPositionY() + 1);
        }

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
        if (e1.getPositionY() >= 700) {
            e1.setPositionX(r.nextInt(700) + 30);
            e1.setPositionY(30);
        }

        if (e1.getPositionX() >= paddlex && e1.getPositionX() <= paddlex + 100 && e1.getPositionY() >= 475 && e1.getPositionY() <= 500) {

            score = 0;
            e1.setPositionY(30);
            gameOver = true;

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

        	bestscore = scorefinal;

        } else {

            scorefinal = bestscore1;
            scorefinal = score + score1;
        }

        repaint();
    }
    
     
    private void cleanShotArray() {
    	cleanShotArrayCounter = 0;
    	for(int i = 0 ; i < shots_amount - cleanShotArrayCounter ; i++ ){
    		if(shots[i].y < -5){
    			holder = shots[i];
    			shots[i] = shots[shots_amount - cleanShotArrayCounter-1] ;
    			shots[shots_amount - cleanShotArrayCounter-1] = holder;
    			System.out.println(i + " " + cleanShotArrayCounter + " " +shots_amount + " ");
        		shots[i].print();
    			cleanShotArrayCounter++;
    			i--;
    		}
    	}
    	shots_amount -=cleanShotArrayCounter;
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
        char key = e.getKeyChar();
//reset jest bez sensu bo powinien na nowo ³adowaæ poziom i generowaæ wszystko od nowa
        if (key == 'n') {
            if (!this.timer.isRunning()) {
                this.timer.start();
                gameOver = false;
                score = 0;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.timerPause();
        }
        if (key == KeyEvent.VK_SPACE) // strzelanie
        {
        	switch (shot_type) {
            case 1:  
                shots[shots_amount].x = paddlex + 25;
                shots[shots_amount++].y = 515;
                     break;
            case 2:  
                shots[shots_amount].x = paddlex + 10;
                shots[shots_amount++].y = 515;
                shots[shots_amount].x = paddlex + 40;
                shots[shots_amount++].y = 515;
                     break;
            case 3:
            	shots[shots_amount].x = paddlex + 2;
	            shots[shots_amount++].y = 515;
	            shots[shots_amount].x = paddlex + 48;
	            shots[shots_amount++].y = 515;
	            shots[shots_amount].x = paddlex + 25;
	            shots[shots_amount++].y = 505;
                     break;
            	default: 
            		throw new NoSuchMethodError("niema takiego strzelania");
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

}
