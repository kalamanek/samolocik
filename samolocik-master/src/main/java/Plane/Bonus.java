/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Student
 */
public class Bonus {
    private int xPositionBonus;
    private int yPositionBonus;
    
    public Random r = new Random();
    public Bonus(){
    this.yPositionBonus=30;
    this.xPositionBonus=r.nextInt(740) + 1;
    }

    
    
    public int getPositionX(){
        return this.xPositionBonus;
    }
    
    public void setPositionX(int x){
        this.xPositionBonus = x;
    }
    
    public int getPositionY(){
        return this.yPositionBonus;
    }
    
    public void setPositionY(int y){
        this.yPositionBonus = y;
    }
}
