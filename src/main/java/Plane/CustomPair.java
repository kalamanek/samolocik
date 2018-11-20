/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plane;

/**
 *
 * @author Damian
 */
public class CustomPair {

        public int x;
        public int y;

        CustomPair() {
            this.x = 0;
            this.y = 0;
        }
        CustomPair(CustomPair a) {
        	this.x =a.x;
        	this.y =a.y;
        }
        public void print(){
			System.out.println(this.x + " " + this.y);
        }
    }
