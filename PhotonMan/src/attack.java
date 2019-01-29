import java.awt.Graphics;

public class attack extends object {

	int atk;
	int end;
	character Char;
	// damage to be dealt by attack

	public attack(character c, int type) {
		super(c.sp, c.x + c.w / 2 - 5, c.y + c.h / 2 - 5, 10, 10);
		// creates 10x10 object at the center of character
		Char = c;
		end = x + c.range;
		currsp = sp.smallobj[type];
		// sets sprite based on the type
		if (c.id)
			dx = 4;
		else
			dx = -4;
		// default move speed

		atk = c.atk;
		// sets damage to character's attack stat
	}

	@Override
	public void draw(Graphics g) {
		if (Char.id) {
			if (x >= end) {
				visible = false;
			}
		} else {
			if (x <= end) {
				visible = false;
			}
		}
		super.draw(g);
		// draws self

	}
}
