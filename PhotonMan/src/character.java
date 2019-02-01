import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class character extends object {

	int health;
	// health
	int damage;
	// strength of attacks
	int moveSpeed;
	// move speed when holding a key
	int energy;
	// current energy
	int energyGain;
	// energy gained via capsules
	int energyUse;
	// energy lost when shooting
	int stunCount;
	// current stun (greater than zero means stunned)
	boolean isGood;
	// checks if character is Photon Man
	boolean hasShield;
	// checks if character has shield active
	boolean isDead;
	// checks if the character is dead
	int type;
	// type of attack
	ArrayList<attack> attacks;
	// stores all attacks
	int walkCount;
	// regulates the walk animation
	BufferedImage profile;
	// image for profile

	public character(sprites sp, int x, int y) {
		super(sp, x, y, 40, 40);
		// creates 40x40 object at the given location
		defaultLoadOut();
	}

	public void defaultLoadOut() {
		health = 100000;
		damage = 1;
		moveSpeed = 2;
		energy = 100;
		energyGain = 5;
		energyUse = 10;
		stunCount = 0;
		isGood = true;
		hasShield = true;
		isDead = false;
		type = 0;
		attacks = new ArrayList<attack>();
		walkCount = 0;
		profile = null;
	}

	public void setLoadOut(String s) {
		defaultLoadOut();
		if (s.equals("red")) {
			mysp = sp.red;
			profile = sp.icons[0];
		}
		if (s.equals("blue")) {
			mysp = sp.blue;
			damage = 2;
			energyUse = 2;
			type = 1;
			profile = sp.icons[1];
		}
		if (s.equals("green")) {
			mysp = sp.green;
			stunCount = -1000000;
			moveSpeed = 1;
			type = 2;
			profile = sp.icons[2];
		}
		if (s.equals("purple")) {
			mysp = sp.purple;
			energyGain = 10;
			profile = sp.icons[3];
		}
		if (s.equals("orange")) {
			mysp = sp.orange;
			moveSpeed = 3;
			profile = sp.icons[4];
		}
		if (s.equals("black")) {
			mysp = sp.black;
			damage = 3;
			hasShield = false;
			type = 2;
			profile = sp.icons[5];
		}
		if (s.equals("scientist")) {
			mysp = sp.scientist;
			health = 1;
			isGood = false;
			dx = -2;
		}
		if (s.equals("soldier")) {
			mysp = sp.soldier;
			health = 2;
			isGood = false;
			dx = -2;
		}
	}

	@Override
	public void draw(Graphics g) {
		for (attack a : attacks)
			a.draw(g);
		// draws all attacks
		super.draw(g);
		// draws self
	}

	@Override
	public void move() {
		if(isGood) {
			if(x + dx < 0 || x + dx > 480 - w) dx = 0;
			if(y + dy < 0 || y + dy > 240 - h) dy = 0;
		}
		if (isDead) {
			x += dx;
			// body keeps moving
		} else {
			walkAnimation();
			// adjust walk animation
			super.move();
			// moves self
		}
		check();
		// removes invisible attacks regardless of death
		for (attack a : attacks)
			a.move();
		// moves attacks regardless of death
	}

	public void walkAnimation() {
		walkCount++;
		if (walkCount <= 10)
			currsp = mysp[1];
		if (walkCount > 10)
			currsp = mysp[2];
		if (walkCount > 20)
			currsp = mysp[3];
		if (walkCount > 30)
			currsp = mysp[2];
		if (walkCount > 40) {
			currsp = mysp[1];
			walkCount = 0;
		}
	}

	public void attack() {
		if(energy < energyUse) return;
		if (!isDead) {
			attacks.add(new attack(this));
		}
		if(isGood) energy -= energyUse;
		// adds a new attack if alive
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
				c.health -= damage;
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
