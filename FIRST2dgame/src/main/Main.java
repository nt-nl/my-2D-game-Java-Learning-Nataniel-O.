package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
	
	public static void main(String[] args) {
		try {
	
			//CREATING THE WINDOW
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.setTitle("Nataniel's Java Game!");
	

	//DISPLAYING THE WINDOW
	
	GamePanel gamePanel = new GamePanel();

	window.add(gamePanel);
	window.pack();
	
	window.setLocationRelativeTo(null);
	window.setVisible(true);
	
	
	gamePanel.startGameThread();
		}catch (Exception e) {
			System.out.println("Exception in main method:");
			e.printStackTrace();
		}
	}
}