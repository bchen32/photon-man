import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class sprites {
	
	// 0 rest 1-3 run cycle 4 death
	BufferedImage[] red = new BufferedImage[5];
	BufferedImage[] blue = new BufferedImage[5];
	BufferedImage[] green = new BufferedImage[5];
	BufferedImage[] gray = new BufferedImage[5];
	// 0 bullet 1 slice 2 beam 3 yellow orb 4 purple orb
	BufferedImage[] smallobj = new BufferedImage[5];
	// 0 red 1 blue 2 green 3 purple 4 orange
	BufferedImage[] icons = new BufferedImage[5];
	// 0 metal box 1 wood box 2 laser
	BufferedImage[] largeobj = new BufferedImage[3];
	
	public sprites() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("/images/photon man ss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// temp image is linked to the sprite sheet
		
		for(int x = 0; x != 5; x++) {
			red[x] = img.getSubimage(40*x, 0, 40, 40);
			blue[x] = img.getSubimage(40*x, 40, 40, 40);
			green[x] = img.getSubimage(40*x, 80, 40, 40);
			gray[x] = img.getSubimage(40*x, 120, 40, 40);
			smallobj[x] = img.getSubimage(20*x, 160, 20, 20);
			icons[x] = img.getSubimage(20*x, 180, 20, 20);
		}
		largeobj[0] = img.getSubimage(100, 160, 40, 40);
		largeobj[1] = img.getSubimage(140, 160, 40, 40);
		largeobj[2] = img.getSubimage(180, 160, 20, 40);
		// chops up the sprite sheet into individual sprites then organizes them
	}
	
	

}
