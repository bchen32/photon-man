import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class object {

	sprites sp;
	BufferedImage[] mysp;
	BufferedImage currsp;
	// access to the main spritesheet, own sprites, and the current sprite
	int x;
	int y;
	// location
	int w;
	int h;
	// dimensions
	int dx;
	int dy;
	// movement speed
	boolean visible;
	// visibility
	boolean stuckRight = false, stuckLeft = false, stuckUp = false;
	//collision variables
	
	public object(sprites sp, int x, int y, int w, int h) {

		this.sp = sp;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		dx = 0;
		dy = 0;
		visible = true;
		// default movement speed is 0 in both directions
	}

	public void draw(Graphics g) {
		if (visible)
			g.drawImage(currsp, x, y, null);
		// draws self
	}

	public void move() {
		if(!stuckRight && dx > 0)
			x += dx; 
		else if (dx < 0 && !stuckLeft)
			x += dx;
		
		if(!stuckUp)
			y += dy;
		// moves self according to movement speed and collision variables
	}

	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
		// returns rectangle according to location and dimensions
	}
}
