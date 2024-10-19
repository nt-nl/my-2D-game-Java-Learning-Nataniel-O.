package main;

import java.awt.Dimension; //IMPORTS DIMENSION
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel; //IMPORTS JPANEL

import entity.Player;
import tile.tileManager;

public class GamePanel extends JPanel implements Runnable{
	
    //GAME SETTINGS
	final int originalTileSize = 16; //variable for 16x16 tile size
    final int scale = 3;

    public int tileSize = originalTileSize * scale; // 48x48 tile (public so that player can access it)
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60;
    
    // Creating objects in this part of the code
    tileManager tileM = new tileManager(this);
    KeyHandler keyH = new KeyHandler(); //Puts the KeyHandler into the game
    Thread gameThread; //Keeps game running until you stop it
    
    public CollisionCheck cCheck = new CollisionCheck(this);
    
    public Player player = new Player(this,keyH);
    
    
    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    //CONSTRUCTOR
    public GamePanel() {
    	
    	this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Sets the window size
    	this.setBackground(Color.black);  //sets background colour of the window to black
        this.setDoubleBuffered(true); //better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
    	System.out.println("Starting");
    	
    		gameThread = new Thread(this); //instantiates a thread
    	    gameThread.start();} //calls run method
 

	public void run() {
	    	
			double drawInterval = 1000000000/FPS; //you are dividing 1billion nanoseconds(1 second) by the FPS
			
			long timer = 0;  
			int drawCount = 0;
			double delta = 0;  
			long lastTime = System.nanoTime();
			long currentTime;
			
			
			
			                                                 // System.out.println("The game loop is running"); //Informs if the game loop is working
		    //TESTS/NOTES -->                                //long currentTime = System.nanoTime(); //checks the nanoseconds in the system time
		   
			while(gameThread != null) { //means it will repeat run as long as it isn't null
	currentTime = System.nanoTime();
	
	delta += (currentTime - lastTime) / drawInterval; 
	timer+= (currentTime - lastTime);
	lastTime = currentTime;
	
	if(delta >=1) { //WHENEVER DELTA REACHES THIS DRAW INTEVAL, WE PERFORM THE UPDATE AND REPAINT CYCLE, THEN RESET DELTA
		
		update(); 	// 1 UPDATE: update information such as character position
		repaint();  // 2 DRAW: Draw the screen with updated information from 1 
		delta --;
		drawCount ++; //USED FOR DISPLAYING FPS
	}
		if(timer >= 1000000000) {
			System.out.println("FPS:" + drawCount); //HOW MANY TIMES IT REPEATS IN 1Second
			drawCount = 0; //Resetting draw count
			timer = 0;
		}
		
		
			
		}
	}
	
	public void update() {
      player.update();
	
	}
	
	@Override
	public void paintComponent(Graphics g) { //BUILT IN METHOD TO JAVA
		
		super.paintComponent(g); //needs to be typed when using paint component. "super" refers to the superclass
	
	Graphics2D g2 = (Graphics2D)g;
	
	tileM.draw(g2); //tiles before player so that the player is on top of the layer
	player.draw(g2);
	
	
	g2.dispose(); //stops running this
	}
	
}
