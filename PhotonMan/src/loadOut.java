import java.awt.Color;
import java.awt.Graphics;

public class loadOut {
	
	character curr;
	int selected;
	character[] loadouts;
	selection[] boxes;
	String[] colors = {"red", "blue", "green", "orange", "purple", "black"};
	object[] stats;
 	
	public loadOut(sprites sp, character current) {
		curr = current; //current character
		loadouts = new character[6]; //number of loadout
		boxes = new selection[6]; //boxes highlighting selected character
//		stats = new object[5]; 
		for(int i = 0; i < loadouts.length; i++) {
			loadouts[i] = new character(sp, 45 + (75 * i), 45, colors[i]);
			boxes[i] = new selection(40 + (75 * i), 40, false, colors[i]);
//			if(i < stats.length) modify with the new image when they come
//			 stats[i] = new object(null, 10, 50 + 40 * (i + 1), 40, 40);
			if(loadouts[i].getLoadOut().equals(curr.getLoadOut())) {
				selected = i;
				boxes[i].visible = true;
			}
		}
	}
	
	public void selectRight() { //selects the character to the right
		boxes[selected].visible = false;
		if(selected < 5)
			selected++;
		boxes[selected].visible = true;
	}
	
	public void selectLeft() { //selects the character to the left
		boxes[selected].visible = false;
		if(selected > 0)
			selected--;
		boxes[selected].visible = true;
	}
	
	public String select() { //returns the color of the selected box to be used in main
		return boxes[selected].getColor();
	}
	
	public void draw(Graphics g) { //draws the screen
		for(int i = 0; i < loadouts.length; i++)
			loadouts[i].draw(g); //draws the player models
			boxes[selected].draw(g); //draws the selected box
			drawStats(g); //draws the stats of the selected box
			g.setColor(Color.BLACK);
			g.drawRect(360, 150, 100, 125); //text box
	}

	public void drawStats(Graphics g) {
		int count = 0;
		character disp = loadouts[selected];
		Color[] levels = {new Color(250,180,50), new Color(100, 255, 0), new Color(0,200,50)}; //0 == orange // 1 == light green // 2 == dark green
		switch(count) {
			case 0: //speed
				g.setColor(Color.BLACK); //always set for text color
				g.drawString("SP", 10, 120); //speed
				if(disp.stats[count] == 1) //Changes Color Based on stat level
					g.setColor(levels[0]);
				else if(disp.stats[count] == 2)
					g.setColor(levels[1]);
				else
					g.setColor(levels[2]);
				g.fillRect(50, 105, disp.stats[count] * 100, 20); //highest is 300, lowest is 100, 200 middle
				count++;
			case 1: //damage
				g.setColor(Color.BLACK); //always set for text color
				g.drawString("DM", 10, 155);
				if(disp.stats[count] == 1) //Changes Color Based on stat level
					g.setColor(levels[0]);
				else if(disp.stats[count] == 2)
					g.setColor(levels[1]);
				else
					g.setColor(levels[2]);
				g.fillRect(50, 140, disp.stats[count] * 100, 20); //highest is 300, lowest is 100
				count++;
			case 2: //energy use
				g.setColor(Color.BLACK);  //always set for text color
				g.drawString("EU", 10, 190);
				if(disp.stats[count] == 2) {
				g.setColor(levels[2]);
				g.fillRect(50, 175, 300, 20); //highest is 300, lowest is 100
				}
				else {
				g.setColor(levels[1]);
				g.fillRect(50, 175, 200, 20); 
				}
				count++;
			case 3: //energy gain
				g.setColor(Color.BLACK);  //always set for text color
				g.drawString("EG", 10, 225);
				if(disp.stats[count] == 10) {
					g.setColor(levels[2]);
					g.fillRect(50, 210, 300, 20); //highest is 300, lowest is 100
					}
					else {
					g.setColor(levels[1]); 
					g.fillRect(50, 210, 200, 20); 
					}
				count++;
			case 4: //can be stunned
				g.setColor(Color.BLACK);  //always set for text color
				g.drawString("VB", 10, 260);
				if(disp.stats[count] != 0) {
					g.setColor(levels[2]);
					g.fillRect(50, 245 , 300, 20); //highest is 300, lowest is 100
					}
					else {
					g.setColor(levels[1]); 
					g.fillRect(50, 245, 200, 20); 
					}
				count++;
			case 5: //shield
				if(!disp.hasShield)
					g.setColor(Color.RED);
				else
					g.setColor(Color.GREEN);
				g.fillRect(400, 105, 40, 40);
				break;
		}
	}
	
	public void move() {
		for(int i = 0; i < loadouts.length; i++) {
			loadouts[i].move();
		}
		
	}
}
