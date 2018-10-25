package aloysius.lim.babybird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import aloysius.lim.mycommonmethods.FileIO;

/**
 * 
 * @author Aloysius Lim
 * Created: 16 Oct 2018
 * Modified: 16 Oct 2018
 */

public class Bird {

	private static final String BIRD_FLAP_UP = "babyBirdFlapUp.gif";
	private static final String BIRD_GLIDE = "babyBirdGlide.gif";
	private static final String BIRD_FLAP_DOWN = "babyBirdFlapDown.gif";

	private final int FLAP_UP = 0;
	private final int FLAP_GLIDE = 1;
	private final int FLAP_DOWN = 2;
	
	private static final float GRAVITY = .5f;
	private static final int FLAP_FORCE = -8;
	
	/**** VARIABLE ****/
	
	private BufferedImage[] birds = new BufferedImage[3];
	private int width;
	private int height;
	private int x = 10;
	private int y = 10;
	private int flap = FLAP_GLIDE;
	private boolean flapping = false;
	private float changeY = 0;
	private int panelHeight;
	
	
	public Bird(int panelHeight) {
		birds[FLAP_UP] = FileIO.readImageFile(this, BIRD_FLAP_UP);
		birds[FLAP_GLIDE] = FileIO.readImageFile(this, BIRD_GLIDE);
		birds[FLAP_DOWN] = FileIO.readImageFile(this, BIRD_FLAP_DOWN);
		width = birds[0].getWidth();
		height = birds[0].getHeight();
		this.panelHeight = panelHeight;
	}
	
	public void draw(Graphics g) {
		g.drawImage(birds[flap], x, y, null);
	}

	public void startFlapping() {
		flapping = true;
		changeY = FLAP_FORCE;
	}
	
	public void move() {
		
		int changeYInt = Math.round(changeY);
		int distanceFromTop = y + height + changeYInt;
		if (distanceFromTop > panelHeight) {
			y = panelHeight - height;
			changeY = 0;
		}else {			
			y += changeYInt;
			changeY += GRAVITY;
			if (changeY > 0) {
				flapping = false;
			}
		}
		
		if (flapping) {
			flap = (flap+1)%3;
		}else {
			flap = FLAP_GLIDE;
		}
	}
	
	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(x,y,width,height);
		return bounds;
	}
	
	public BufferedImage getImage() {
		return birds[FLAP_GLIDE];
	}
}
