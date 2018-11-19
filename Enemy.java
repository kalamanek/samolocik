/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plane;

import java.util.Random;


/**
 *
 * @author bakus_000
 */

     
public class Enemy {
    private int enemy_x;
    private int enemy_y;
    public Random r = new Random();
    public Enemy(){
        this.enemy_y = 30;
        this.enemy_x = r.nextInt(19) + 1;
    }
    
    public int getPositionX(){
        return this.enemy_x;
    }
    
    public void setPositionX(int enemy_x){
        this.enemy_x = enemy_x;
    }
    
    public int getPositionY(){
        return this.enemy_y;
    }
    
    public void setPositionY(int enemy_y){
        this.enemy_y = enemy_y;
    }
    
    public void generateNewPosition(int random)
    {
        int x=0;
        switch(random) {
            case 1 : x=20; break;
            case 2 : x=60; break;
            case 3 : x=100; break;
            case 4 : x=140; break;
            case 5 : x=180; break;
            case 6 : x=220; break;
            case 7 : x=260; break;
            case 8 : x=300; break;
            case 9 : x=340; break;
            case 10 : x=380; break;
            case 11 : x=420; break;
            case 12 : x=460; break;
            case 13 : x=500; break;
            case 14 : x=540; break;
            case 15 : x=580; break;
            case 16 : x=620; break;
            case 17 : x=660; break;
            case 18 : x=700; break;
            case 19 : x=740; break;      
        }
        this.setPositionX(x);
    }
}
