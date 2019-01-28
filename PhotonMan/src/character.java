import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class character extends object {

	boolean id;
	// true is main character
	int atk;
	int def;
	int hp;
	// stats
	boolean hasJumped1;
	boolean hasJumped2;
	// checks if character has jumped or double jumped
	boolean isDead;
	// checks if the character is dead
	int type;
	// type of attack
	int range;
	// range of attack
	ArrayList<attack> attacks;
	// stores all attacks
	int count;
	// regulates the walk animation

	public character(sprites sp, int x, int y, boolean id) {
		super(sp, x, y, 40, 40);
		this.id = id;
		// creates 40x40 object at the given location
		hasJumped1 = false;
		hasJumped2 = false;
		// character hasn't jumped prior to creation
		isDead = false;
		// character is alive
		attacks = new ArrayList<attack>();
		// instantiates attacks
		count = 0;
		// instantiates count
	}

	public void redLoadOut() {
		// gun suit
		mysp = sp.red;
		// sets personal sprites to red
		atk = 10;
		def = 5;
		hp = 10;
		// sets stats (average)
		type = 0;
		range = 300;
		// sets type and range (average)
	}

	public void blueLoadOut() {
		// sword suit
		mysp = sp.blue;
		// sets personal sprites to blue
		atk = 12;
		def = 3;
		hp = 10;
		// sets stats (high atk, low def)
		type = 1;
		range = 100;
		// sets type and range (low)
	}

	public void greenLoadOut() {
		// shield suit
		mysp = sp.green;
		// sets personal sprites to green
		atk = 8;
		def = 6;
		hp = 11;
		// sets stats (low atk, high def and hp)
		type = 2;
		range = 500;
		// sets type and range (high)
	}

	public void grayLoadOut() {
		// dummy suit
		mysp = sp.gray;
		// sets personal sprites to gray
		atk = 5;
		def = 5;
		hp = 5;
		// sets stats (all low)
		dx = -2;
		range = -100;
		type = 0;
		// sets movement speed (walk speed) and range (nonexistent)
	}

	public void draw(Graphics g) {
		for (attack a : attacks)
			a.draw(g);
		// draws all attacks
		super.draw(g);
		// draws self
	}

	public void move() {
		if (isDead) {
			x += dx;
			return;
		}
		// if dead, continue to move but nothing else
		count++;
		if (count <= 10)
			currsp = mysp[1];
		if (count > 10)
			currsp = mysp[2];
		if (count > 20)
			currsp = mysp[3];
		if (count > 30)
			currsp = mysp[2];
		if (count > 40) {
			currsp = mysp[1];
			count = 0;
		}
		// increment count and adjust walk animation
		check();
		// check any out of range attacks
		super.move();
		// moves self
		for (attack a : attacks)
			a.move();
		// moves attacks
	}

	public void attack() {
		attacks.add(new attack(this, type));
		// adds a new attack
	}

	public boolean checkHits(character c) {
		if (c.isDead)
			return false;
		// doesn't check hits if dead
		Rectangle hb = c.getHitBox();
		// creates enemy hitbox
		boolean check = false;
		// will automatically return false (no hit)
		for (int x = 0; x != attacks.size(); x++) {
			attack a = attacks.get(x);
			// gets an attack
			if (hb.intersects(a.getHitBox())) {
				// checks if attack hit enemy
				check = true;
				// will return true (hit confirmed)
				int dmg = a.atk - c.def;
				// calculates damage based on personal atk and enemy def
				if (dmg < 0)
					dmg = 0;
				// damage minimum is 0
				c.hp -= dmg;
				// adjust enemy health
				attacks.remove(x);
				// removes attack from attacks
				x--;
				// decrements
			}
		}
		return check;
		// returns success of attack
	}

	public void check() {
		for (int x = 0; x != attacks.size(); x++) {
			if (!attacks.get(x).visible) {
				// checks if attack is out of range
				attacks.remove(x);
				x--;
				// removes attack from attacks and decrements
			}
		}
	}
}
