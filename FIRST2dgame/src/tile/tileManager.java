package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class tileManager {

	GamePanel gp;
	public tile[] tile;
	
	public int mapTileNum[][]; // new variable
	
	
	
	public tileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //instantiating object to store map info
		
		
		getTileImage();
		loadMap("/maps/map01.txt"); //calling the loadMap method
	}
	
	
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass01.png"));
			
			tile[1] = new tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			tile[1].collision = true; //Creating collision
			
			tile[2] = new tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water00.png"));
			tile[2].collision = true;
			
			tile[3] = new tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/floor01.png"));
			
			tile[4] = new tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));

			tile[5] = new tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			tile[5].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error getting Tile Objects!");
		}
	}
	
	
	//READING THE MAP FILE AND DISPLAYING THE MAP
	public void loadMap(String filepath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {		
				
			//File Handling
			String line = br.readLine(); 
			
			while(col < gp.maxWorldCol)  {
				String numbers[] = line.split(" "); //going through the map file. Each Space indicates a new part to be read separately 
				
				int num = Integer.parseInt(numbers[col]); //variable is created to store the 
			
			    mapTileNum[col][row] = num;
			    col++; //increases by 1
			
			}
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}	
		br.close();
			
	}catch(Exception e) {
			
		}
	}
	
	
	//Drawing tiles
	public void draw(Graphics2D g2) {
		
		int worldcol = 0;
		int worldrow = 0;
		
		
		while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) { // && is AND operator
			
			int tileNum = mapTileNum[worldcol][worldrow]; //everything is stored in the mapTileNum array
			
			int worldX = worldcol * gp.tileSize;
			int worldY = worldrow * gp.tileSize;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			
			//RENDERING OPTIMISATION (DISPLAYING ONLY NEEDED TILES)
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // + and - used to remove black borders
			    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			    worldY - gp.tileSize <gp.player.worldY + gp.player.screenY) {
				
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
			}
			
			worldcol++;
			
			if(worldcol == gp.maxWorldCol ) {
				worldcol = 0;
			
				worldrow++;
				
				
			}
		}
	}
}
