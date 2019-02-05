import java.awt.Color;
import java.awt.Graphics;

public class selection extends object{
	String color;
	public selection(int x, int y, boolean selected, String c) {
		super(null, x, y, 50, 50);
		color = c;
		if(selected)
			visible = true;
		else
			visible = false;
	}
	
	public String getColor() {
		return color;
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255, 250, 117, 100));
		if(visible)
		g.fillRect(x, y, w, h);
	}
}
