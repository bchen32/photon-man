import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class loadOut {
	
	character curr;
	int selected;
	character[] loadouts;
	selection[] boxes;
	String[] colors = {"red", "blue", "green", "orange", "purple","black"};
	
	public loadOut(sprites sp, character current) {
		curr = current;
		loadouts = new character[6]; //number of loadouts
		boxes = new selection[6];
		for(int i = 0; i < loadouts.length; i++) {
			loadouts[i] = new character(sp, 25 + (75 * i), 50, colors[i]);
			boxes[i] = new selection(20 + (75 * i), 45, false, colors[i]);
			if(loadouts[i].getLoadOut().equals(curr.getLoadOut())) {
				selected = i;
				boxes[i].visible = true;
			}
		}
	}
	
	public void selectRight() {
		boxes[selected].visible = false;
		if(selected < 5)
			selected++;
		boxes[selected].visible = true;
	}
	
	public void selectLeft() {
		boxes[selected].visible = false;
		if(selected > 0)
			selected--;
		boxes[selected].visible = true;
	}
	
	public String select() {
		return boxes[selected].getColor();
	}
	
	
	public void draw(Graphics g) {
		for(int i = 0; i < loadouts.length; i++)
			loadouts[i].draw(g);
		
			boxes[selected].draw(g);	
	}
	
	public void move() {
		for(int i = 0; i < loadouts.length; i++) {
			loadouts[i].move();
			
		}
		
	}
	
	

}
