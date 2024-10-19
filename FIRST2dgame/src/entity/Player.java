package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel; //Imports GamePanel and KeyHandler from Main
import main.KeyHandler;

public class Player extends entity{ //extends means it is a part of the entity package

	GamePanel gp; //Importing GamePanel and KeyHandler
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
	
		screenX = gp.screenWidth/2 - (gp.tileSize/2); //Positioning in the centre
		screenY = gp.screenHeight/2- (gp.tileSize/2);
		
		//Collision Rectangle
		solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefaultValues();
		getPlayerImage();
		
	}
		
	public void setDefaultValues() {	
		worldX = gp.tileSize * 25; //Starting Positions
		worldY = gp.tileSize * 25;
		speed = 4;
		direction = "down";
	
	}
	public void getPlayerImage() {
		try {
			
			//SETTING UP VARIABLES FOR IMAGES
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
		    right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Player Sprite Error!");
		}
	}
	
	
	public void update() { //UPDATED 60 TIMES EVERY SECOND (FPS)
		
	// MOVEMENT AND ANIMATION
		
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) { // "||" is used as an OR statement 
			
			if(keyH.upPressed == true) { //IF "W" IS PRESSED THEN...
				
				direction = "up";
				}
				
				else if(keyH.downPressed == true) {
				
				direction = "down";
			    }
				
				else if(keyH.leftPressed == true) {
					
					direction = "left";
				}
				
				else if(keyH.rightPressed == true) {
					
					direction ="right";
				}
				
			//CHECK TILE COLLSION
			
			collisionOn = false;
			gp.cCheck.checkTile(this);
			
			//IF COLLSION IS FLASE THEN THE PLAYER IS ALLOWED TO MOVE
			
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY = worldY - speed; //<---- MOVES PLAYERS Y CO-ORDINATE BY THE PLAYER SPEED
				    break;
				case "down":
					worldY += speed; //another way to say the same thing (adds playerY)
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
				//every 12 frames the image is changed to either 1 or 2
				
				spriteCounter++;
				if(spriteCounter > 12) {
					if(spriteNum == 1) {
						spriteNum = 2;
					}
					else if(spriteNum == 2) {
						spriteNum = 1;
					}
					spriteCounter = 0;
					
				}
		}
	}
	
	
	public void draw(Graphics2D g2) {
		//TEST MODEL
	//	g2.setColor(Color.white); 
	//	g2.fillRect(x, y, gp.tileSize, gp.tileSize); 
		
		BufferedImage image = null;
		
		switch(direction) { //based on the direction we use a different image
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}		        
				break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum ==2) {
			    image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
			
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
}
