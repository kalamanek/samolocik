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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class PlaneGame extends JComponent implements ActionListener, MouseMotionListener, KeyListener {

    Shooting sh = new Shooting();
    Time t1 = new Time();
    int shot_type = 1;
    int shots_speed = 5;
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
    public boolean gameOver;
    //gawel
    public boolean immortal;
    int iG = 1;
    private int shotsBonusFrequency = 5;
    private int shotsBonusCounter = 1;
    public Enemy e1 = new Enemy();
    public Bonus b1 = new Bonus();
    public Bonus shotBonus = new Bonus();
    
    public List enemyList;
    public List bonusList;
    public List shotsUpgrade;

    public BufferedImage image;
    public PlaneGame() throws IOException {
        
            System.out.println(System.getProperty("user.dir") + "\\spaceship.png");
        try{
            image = ImageIO.read( new File("spaceship.png" ));
        }catch(IOException e){
            throw e;
        }
        this.enemyList = new LinkedList<Enemy>();
        this.enemyList.add(new Enemy());
        this.enemyList.add(new Enemy());
        this.enemyList.add(new Enemy());
        for (int i = 0; i < 1000; i++) {
            sh.shots[i] = new CustomPair();
        }

        this.bonusList = new LinkedList<Bonus>();
        this.shotsUpgrade = new LinkedList<Bonus>();

        this.plane_x = 150;
        this.plane_y = 30;
        this.plane_xSpeed = 20;
        this.plane_ySpeed = 20;
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
//draw the paddle
        //g.setColor(Color.black);
        //g.fillRect(paddlex, 500, 100, 20);
        g.drawImage(image, paddlex, 500, this);
//draw the enemy
        g.setColor(Color.black);
        for (int j = 0; j < this.enemyList.size(); j++) {
            e1 = (Enemy) enemyList.get(j);
            g.fillOval(e1.getPositionX(), e1.getPositionY(), 40, 40);
        }
        //g.fillOval(e1.getPositionX(), e1.getPositionY(), 40, 40);
//draw shots
        for (int i = 0; i < sh.shots_amount; i++) {
            g.fillOval(sh.shots[i].x, sh.shots[i].y, 15, 30);
        }
//draw the ball
        //g.setColor(Color.RED);
        //g.fillOval(plane_x, plane_y, 30, 30);
//draw bonus
        g.setColor(Color.GREEN);
        for (int j = 0; j < this.bonusList.size(); j++) {
            b1 = (Bonus) bonusList.get(j);
            g.fillOval(b1.getPositionX(), b1.getPositionY(), 40, 40);
        }
        g.setColor(Color.BLUE);
        for (int j = 0; j < this.shotsUpgrade.size(); j++) {
        	shotBonus = (Bonus) shotsUpgrade.get(j);
            g.fillOval(shotBonus.getPositionX(), shotBonus.getPositionY(), 40, 40);
        }
        //draw the ball_1
if (score >= 5) {
g.setColor(Color.BLACK);

}
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
            this.t1.timerStop();
        }
    }

    public void actionPerformed(ActionEvent e) {
        //enemy v0.3
        for (int i = 0; i < sh.shots_amount; i++) {
            sh.shots[i].y -= shots_speed;
        }
        for (int j = 0; j < this.enemyList.size(); j++) {
            int p = r.nextInt(19) + 1;
            e1 = (Enemy) enemyList.get(j);
            for (int i = 0; i < sh.shots_amount; i++) {
                if (sh.shots[i].x >= e1.getPositionX() && sh.shots[i].x <= e1.getPositionX() + 40
                        && sh.shots[i].y <= e1.getPositionY() + 40 && sh.shots[i].y >= e1.getPositionY() ) {
                    sh.shots[i].y = -10;
                    score++;
                    e1.generateNewPosition(p);
                    e1.setPositionY(r.nextInt(10) - 500);
                    if (enemyList.size() < 50) {
                        this.enemyList.add(new Enemy());
                    }
                }
            }
        }
        //bonus
        for (int j = 0; j < this.bonusList.size(); j++) {
            b1 = (Bonus) bonusList.get(j);
            b1.setPositionY(b1.getPositionY() + 1);
        }
        for (int j = 0; j < this.shotsUpgrade.size(); j++) {
            shotBonus = (Bonus) shotsUpgrade.get(j);
            shotBonus.setPositionY(shotBonus.getPositionY() + 3);
        }

        //strzelanie v1.0
        sh.cleanShotArray();
        plane_x = plane_x + plane_xSpeed;
        plane_y = plane_y + plane_ySpeed;

        for (int j = 0; j < this.enemyList.size(); j++) {
            e1 = (Enemy) enemyList.get(j);
            e1.setPositionY(e1.getPositionY() + 1);
        }
        //~Kuba: do poprawienia, braklo czasu na zajeciach. PóŸniej zoptmalizuje
        /*for (int j = 0; j < this.enemyList.size(); j++) {
            e1 = (Enemy) enemyList.get(j);
            for (int i = 0; i < this.enemyList.size(); i++) {
                Enemy e2 = (Enemy) enemyList.get(i);
                if(e1.noCollision(e2))
                {
                    int p = r.nextInt(19) + 1;
                    e2.generateNewPosition(p);
                    e2.setPositionY(r.nextInt(10) - 100);
                }
            }
        }*/

        plane_x = plane_x + plane_xSpeed;
        plane_y = plane_y + plane_ySpeed;
        //rysowanie przeciwników
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
        if (score == iG * 10) {
            iG++;
            this.bonusList.add(new Bonus());
        }
        
        if (score == shotsBonusFrequency * shotsBonusCounter  && shot_type < 3) {
        	shotsBonusCounter++;
            this.shotsUpgrade.add(new Bonus());
        }
      
        //trafienie na bonusie
        for (int j = 0; j < this.bonusList.size(); j++) {
            b1 = (Bonus) bonusList.get(j);

            //zdobycie bonusu
            if (b1.getPositionX() >= paddlex && b1.getPositionX() <= paddlex + 100 && b1.getPositionY() >= 475 && b1.getPositionY() <= 500) {
                bonusList.clear();
                immortal = true;
            }
        }
        for (int j = 0; j < this.shotsUpgrade.size(); j++) {
            shotBonus = (Bonus) shotsUpgrade.get(j);

            //zdobycie bonusu
            if (shotBonus.getPositionX() >= paddlex && shotBonus.getPositionX() <= paddlex + 100 && shotBonus.getPositionY() >= 475 && shotBonus.getPositionY() <= 500) {
            	shotsUpgrade.clear();
            	shot_type++;
            }
        }
        for (int i = 0; i < this.enemyList.size(); i++) {
            e1 = (Enemy) enemyList.get(i);
            if (e1.getPositionX() >= paddlex && e1.getPositionX() <= paddlex + 100 && e1.getPositionY() >= 475 && e1.getPositionY() <= 500 && immortal == false) {
                score = 0;
                gameOver = true;
            }
            if (e1.getPositionX() >= paddlex && e1.getPositionX() <= paddlex + 100 && e1.getPositionY() >= 475 && e1.getPositionY() <= 500 && immortal == true) {
                e1.setPositionY(r.nextInt(10) - 100);
                score++;
                immortal = false;
            }

        }
        //rysowanie wroga jeœli nie zestrzelimy go
        for (int j = 0; j < this.enemyList.size(); j++) {
            e1 = (Enemy) enemyList.get(j);
            if (e1.getPositionY() >= 700) {
                int p = r.nextInt(19) + 1;
                e1.generateNewPosition(p);
                e1.setPositionY(r.nextInt(10) - 100);
            }
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

        if (t1.timer.isRunning()) {
            paddlex = e.getX() - 50;
            repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
//reset jest bez sensu bo powinien na nowo ³adowaæ poziom i generowaæ wszystko od nowa
        if (key == 'n') {
            if (!this.t1.timer.isRunning()) {
                gameOver = false;
                score = 0;
                enemyList.clear();
                sh.cleanShotArray();
                this.enemyList.add(new Enemy());
                this.enemyList.add(new Enemy());
                this.enemyList.add(new Enemy());
                this.enemyList.add(new Enemy());
                this.enemyList.add(new Enemy());
                this.enemyList.add(new Enemy());
                this.t1.timer.start();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.t1.timerPause();
        }
        if (key == KeyEvent.VK_SPACE) // strzelanie
        {
            switch (shot_type) {
                case 1:
                    sh.shots[sh.shots_amount].x = paddlex + 25;
                    sh.shots[sh.shots_amount++].y = 515;
                    break;
                case 2:
                    sh.shots[sh.shots_amount].x = paddlex + 10;
                    sh.shots[sh.shots_amount++].y = 515;
                    sh.shots[sh.shots_amount].x = paddlex + 40;
                    sh.shots[sh.shots_amount++].y = 515;
                    break;
                case 3:
                    sh.shots[sh.shots_amount].x = paddlex + 2;
                    sh.shots[sh.shots_amount++].y = 515;
                    sh.shots[sh.shots_amount].x = paddlex + 48;
                    sh.shots[sh.shots_amount++].y = 515;
                    sh.shots[sh.shots_amount].x = paddlex + 25;
                    sh.shots[sh.shots_amount++].y = 505;
                    break;
                default:
                    throw new NoSuchMethodError("niema takiego strzelania");
            }
        }
    }
    public void keyReleased(KeyEvent e) {

    }
}
