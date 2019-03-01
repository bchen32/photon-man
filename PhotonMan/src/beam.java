
public class beam extends object {
	public beam(sprites sp, int x, int y) {
		super(sp, x, y, 18, 18);
		// TODO Auto-generated constructor stub
		currsp = sp.largeobj[2];
	}

	@Override
	public void move() {
		dx = 0;
		dy = 0;
		super.move();
	}
}
