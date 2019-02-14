
public class attack extends object {

	public attack(character c) {
		super(c.sp, c.x + c.w / 2 - 5, c.y + c.h / 2 - 5, 10, 10);
		// creates 10x10 object at the center of character
		currsp = sp.smallobj[c.type];
		// sets sprite based on the type
		if (c.isGood)
			dx = c.moveSpeed * 2;
		else
			dx = c.moveSpeed * -2;
		// default move speed

	}

}