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
	boolean blockedSide = false;
	boolean blockedUp = false;
	boolean blockedDown = false;
	boolean collideable = true;
	// collision

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
		int mx = dx - 2;
		if (!blockedSide) {
			x += mx;
		}

		if (dy > 0 && !blockedDown) {
			y += dy;
		} else if (dy < 0 && !blockedUp) {
			y += dy;
		}
		// moves self according to movement speed and collision variables
	}

	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
		// returns rectangle according to location and dimensions
	}
}