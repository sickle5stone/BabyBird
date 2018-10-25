package aloysius.lim.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Aloysius Lim
 * Created: 16 Oct 2018
 * Modified: 23 Oct 2018
 */

public class FlightPanel extends JPanel {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	private static final int SEPERATION = 40;
	
	/**** Variable ****/
	private BabyBird babyBird;
	private Bird bird = new Bird(HEIGHT);
	private Timer timer;
	private ArrayList<Wall> walls = new ArrayList<>();
	private int count = 0;
	
	public FlightPanel(BabyBird babyBird) {
		this.babyBird = babyBird;
		setFocusable(true);
		requestFocusInWindow(true);
		
		//Listeners
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				Character key = e.getKeyChar();
				if (key == ' ') {
					bird.startFlapping();
//					bird.move();
//					repaint();
				}
			}
		});
		
		Wall wall = new Wall();
		walls.add(wall);
		
		//Timer flapping 
		timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timedAction();
			}
		});
		timer.start();
	}
	
	private void timedAction() {
		//moving the bird
		bird.move();
		
		//moving walls
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).move();
			if (walls.get(i).isPastWindowEdge()) {
				walls.remove(i);
			}
		}
		
		//checking collision
		Wall firstWall = walls.get(0);
		Rectangle birdBounds = bird.getBounds();
		Rectangle topWallBounds = firstWall.getTopBounds();
		Rectangle bottomWallBounds = firstWall.getBottomBounds();
		if (birdBounds.intersects(topWallBounds) || birdBounds.intersects(bottomWallBounds)) {
			timer.stop();
		}
		
		//check for additional walls to be added
		count += 1;
		if (count>SEPERATION) {
			Wall wall = new Wall();
			walls.add(wall);
			count = 0;
		}
		
		//repainting
		repaint();
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH,HEIGHT);
	};
	
	public void paintComponent(Graphics g) {
		//Set background to blue
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//paint the bird
		bird.draw(g);
		
		//paint the walls
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).draw(g);
		}
	}
	
}
