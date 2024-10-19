package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed; // Creates a boolean variable
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) { //Controls
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) { //W literally refers to the key pressed being "W"
			upPressed = true;  //USES THE VARIABLES DECLARED
		}
        if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
        if (code == KeyEvent.VK_A) {
	        leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
	        rightPressed = true;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) { //same thing as keyPressed but now marks if the key was let go
			upPressed = false; //sets to false to stop movement
		}
        if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
        if (code == KeyEvent.VK_A) {
	        leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
	        rightPressed = false;
        }
	}

}
