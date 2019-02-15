import java.awt.Graphics;
import java.awt.Rectangle;
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
	int[] stats = { moveSpeed, damage, energyUse, energyGain, stunCount };
	// array if the current stats health not included because it doesn't change
	ArrayList<attack> attacks;
	// stores all attacks
	int walkCount;
	// regulates the walk animation
	boolean up, down, left, right = false;

	public character(sprites sp, int x, int y, String loadout) {
		super(sp, x, y, 40, 40);
		// creates 40x40 object at the given location
		setLoadOut(loadout);
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
	}

	public void setLoadOut(String s) {
		defaultLoadOut();
		if (s.equals("red"))
			mysp = sp.red;
		if (s.equals("blue")) {
			mysp = sp.blue;
			damage = 2;
			energyUse = 2;
			type = 1;
		}
		if (s.equals("green")) {
			mysp = sp.green;
			stunCount = -1000000;
			moveSpeed = 1;
			type = 2;
		}
		if (s.equals("purple")) {
			mysp = sp.purple;
			energyGain = 10;
		}
		if (s.equals("orange")) {
			mysp = sp.orange;
			moveSpeed = 3;
		}
		if (s.equals("black")) {
			mysp = sp.black;
			damage = 3;
			hasShield = false;
			type = 2;
		}
		if (s.equals("scientist")) {
			mysp = sp.scientist;
			health = 1;
			isGood = false;
			dx = -1;
		}
		if (s.equals("soldier")) {
			mysp = sp.soldier;
			health = 2;
			isGood = false;
			dx = -1;
		}
		updateStats();
	}

	// returns loadouts
	public String getLoadOut() {
		if (mysp.equals(sp.red))
			return "red";
		if (mysp.equals(sp.blue))
			return "blue";
		if (mysp.equals(sp.green))
			return "green";
		if (mysp.equals(sp.orange))
			return "orange";
		if (mysp.equals(sp.purple))
			return "purple";
		if (mysp.equals(sp.black))
			return "black";
		if (mysp.equals(sp.scientist))
			return "scientist";
		return "soldier";
	}

	public void updateStats() {
		stats[0] = moveSpeed;
		stats[1] = damage;
		stats[2] = energyUse;
		stats[3] = energyGain;
		stats[4] = stunCount;
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
		if (isGood) {
			if (up || down) {
				if (up && down) {
					dy = 0;
				} else if (up) {
					dy = -2;
				} else if (down) {
					dy = 2;
				}
			} else {
				dy = 0;
			}
			if (left || right) {
				if (left && right) {
					dx = 2;
				} else if (left) {
					dx = 0;
				} else if (right) {
					dx = 4;
				}
			} else {
				dx = 2;
			}
		}
		if (isGood) {
			if (x + dx > 480 - w)
				dx = -2;
			if (y + dy < 0)
				dy = 2;
			if (y + dy > 240 - h)
				dy = -2;
		}
		if (isDead) {
			x += -2;
			// body keeps moving
		} else {
			walkAnimation(dx, dy, isGood);
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

	public void walkAnimation(int xSpeed, int ySpeed, boolean isGood) {
		if (isGood) {
			if (Math.abs(xSpeed) > 2) {
				walkCount += 3;
			} else if (Math.abs(xSpeed) > 0 || ySpeed != 0) {
				walkCount += 2;
			}

			if (xSpeed == 0 && ySpeed == 0) {
				currsp = mysp[0];
			} else {
				if (walkCount <= 20)
					currsp = mysp[1];
				if (walkCount > 20)
					currsp = mysp[2];
				if (walkCount > 40)
					currsp = mysp[3];
				if (walkCount > 60)
					currsp = mysp[2];
				if (walkCount > 80) {
					currsp = mysp[1];
					walkCount = 0;
				}
			}
		} else {
			if (Math.abs(xSpeed) == 1 || ySpeed != 0) {
				walkCount++;
			}
			if (xSpeed == 0 && ySpeed == 0) {
				currsp = mysp[0];
			} else {
				if (walkCount <= 20)
					currsp = mysp[1];
				if (walkCount > 20)
					currsp = mysp[2];
				if (walkCount > 40)
					currsp = mysp[3];
				if (walkCount > 60)
					currsp = mysp[2];
				if (walkCount > 80) {
					currsp = mysp[1];
					walkCount = 0;
				}
			}
		}
	}

	public void attack() {
		if (energy < energyUse)
			return;
		if (!isDead) {
			attacks.add(new attack(this));
		}
		if (isGood)
			energy -= energyUse;
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
		if (energy <= 0) {
			energy = 0;
			health = 1;
		}
	}
}
