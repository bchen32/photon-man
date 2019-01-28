# PhotonManPHOTON MAN
Joey Ilagan, Arik Baron, Ben Chen, Ben Guan

OVERVIEW
Photon Man is a hack and slash infinite runner. Players run as the titular Photon Man, a soldier wearing a Photon Suit with interchangeable abilities.

STORY
In the world of Photon Man, Photon Suits are under development in secret bases. Photon Man breaks into these bases to destroy them and obtain the Photon Suits.

GAMEPLAY
Photon Man moves in an auto scrolling map where he can freely move to shoot enemies and avoid obstacles. Photon Man has energy storage that powers his attacks and shield. Upon draining his energy, Photon Man loses his shield and is susceptible to any attack. Gameplay ends when 1) Photon Man is pushed off the map or 2) is hit without a shield.

Attacks briefly stun Photon Man, slowing his movement and pushing him to the edge of the map. The lower his energy, the longer the stun duration. Energy can be obtained by grabbing capsules scattered on the map. Additionally, Photon Man can expend a large amount of energy to change suits, altering his abilities to fit his needs.

These suits are obtained via occasional boss battles against other Photon Suit users. Enemies have varying health (each deciding the number of hits they can absorb) with the bosses having the most. Bosses aren’t stunned like normal enemies.

LEVEL DESIGN
Enemies, obstacles, and capsules are procedurally loaded into the game and are moved to the left as Photon Man runs. All objects have similar properties and collision detection. These object chunks are defined in a text file and object chunks vary depending on the current level.

GAME CONTROLS
WASD to move, SPACE to select/shoot, E to view suit menu








IMPORTANT VARIABLES
x and y: location
dx and dy: current movement
w and h: dimensions
currsp: current sprite
visible: whether or not it should be visible
attacks: stores all attacks



MAIN CHARACTER
Red Suit: normal suit
Blue Suit: attacks have double strength but double energy use (damage and energyUse)
Green Suit: stun is disabled but attacks have half strength (isStunned and damage)
Purple Suit: capsules have double energy (energyGain)
Orange Suit: run speed is 50% higher (runSpeed)
Black Suit: attacks have triple strength but shield is permanently off (damage and hasShield)

ENEMIES
Scientist: 1HP, can’t attack
Soldier: 2HP, can attack
Super Soldier: 10HP, can attack, has Photon Suit

MECHANICS
Movement: Objects are repeatedly moved around the screen according to dx and dy
Rendering: Objects are repeatedly drawn on the screen according to x, y, and currsp
Collision: Objects contain hitboxes which are repeatedly compared within the handlers
Removal: Objects are erased from use according to visible (if !visible)
Attacks: Characters stores all attacks in attacks
Energy: Photon Man attacks and defends according to energy and energyUse (can attack and won’t die from attacks as long as more energy than energyUse is available)
Suits: Photon Man has different suits that alter his instance variables, changing requires energy
Damage: Enemies have health that is lowered when hit according to the attacker’s damage
Push Back: Photon Man is pushed back when in contact with a solid obstacle
Run Speed: Photon move around the screen according to moveSpeed
Stun: Photon Man has an added negative dx movement according to isStunned
Energy Gain: Photon Man regains energy according to energyGain
Game End: Photon Man loses according to x (if out of bounds) or hasShield (if he is attacked and !hasShield)

LAYOUT
application (JFrame that runs Photon Man), contains…
main (JPanel that moves/draws everything and stores all handlers), contains several…
all game modes (handler that moves/draws specific contents), contains several…
object (game component with sprites, location, dimensions, movement speed, and visibility)

object is the parent class of
character (abstract class for Photon Man and his enemies), implemented by
photonMan (Photon Man character) and
enemy (character with special health values and attack methods)

character objects use
attack (object with definitive move speed and visibility detection)


 


