package Plane;
/**
 *
 * @author Damian
 */
public class Shooting  {
    
 int cleanShotArrayCounter = 0;
 int shots_amount = 0;
 CustomPair[] shots = new CustomPair[1000];
 CustomPair holder = new CustomPair();
    
    public void cleanShotArray() {
        cleanShotArrayCounter = 0;
        for (int i = 0; i < shots_amount - cleanShotArrayCounter; i++) {
            if (shots[i].y < -5) {
                holder = shots[i];
                shots[i] = shots[shots_amount - cleanShotArrayCounter - 1];
                shots[shots_amount - cleanShotArrayCounter - 1] = holder;
                System.out.println(i + " " + cleanShotArrayCounter + " " + shots_amount + " ");
                shots[i].print();
                cleanShotArrayCounter++;
                i--;
            }
        }
        shots_amount -= cleanShotArrayCounter;
     }
}
