import java.awt.Color;
import java.awt.Graphics;

public class selection extends object {
	String color;

	public selection(int x, int y, boolean selected, String c) {
		super(null, x, y, 50, 50);
		color = c;
		if (selected)
			visible = true;
		else
			visible = false;
	}

	public String getColor() {
		return color;
	}

	@Override
	public void draw(Graphics g) {
		if (visible) {
			g.setColor(new Color(255, 255, 0, 180));
			g.fillRect(x, y, w, h);
			g.setColor(Color.black);
			g.drawRect(x, y, w, h);
		}
	}
}
