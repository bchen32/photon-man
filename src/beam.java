
public class beam extends object {
	public beam(sprites sp, int x, int y) {
		super(sp, x, y, 20, 60);
		// TODO Auto-generated constructor stub
		currsp = sp.largeobj[3];
	}
	
	@Override
	public void move() {
		dx = 0;
		dy = 0;
		super.move();
	}
}
