
public class laser {
	beam b;
	crate crateA;
	crate crateB;

	public laser(sprites sp, int x, int y) {
		crateA = new crate(sp, x, y);
		b = new beam(sp, x + 9, y + crateA.h + 2);
		crateB = new crate(sp, x, b.y + b.h + 2);
	}
}
