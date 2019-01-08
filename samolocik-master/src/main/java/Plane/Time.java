
package Plane;

import javax.swing.Timer;

/**
 *
 * @author Damian
 */
public class Time {
    
     public Timer timer;
    
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
    
}
