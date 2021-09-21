
public class crate extends object {
	int type;

	public crate(sprites sp, int x, int y) {
		super(sp, x, y, 40, 40);
		// TODO Auto-generated constructor stub
		type = (int) (Math.random() * 2);
		currsp = sp.largeobj[type];
	}

	@Override
	public void move() {
		dx = 0;
		dy = 0;
		super.move();
	}
}
