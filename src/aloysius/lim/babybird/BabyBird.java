package aloysius.lim.babybird;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import aloysius.lim.mycomponents.ScorePanel;
import aloysius.lim.mycomponents.TitleLabel;

/**
 * 
 * @author Aloysius Lim
 * Created: 16 Oct 2018
 * Modified: 16 Oct 2018
 */

public class BabyBird extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ScorePanel scorePanel = new ScorePanel(0,Color.GREEN);
	private FlightPanel flightPanel = new FlightPanel(this);

	public BabyBird() {
		initGUI();
		
		setTitle("Baby Bird");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}catch(Exception e) {
			
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					new BabyBird();
			}//end run
			// end runnable
		});
	} // end main
	
	public void initGUI() {
		//Title
		TitleLabel titleLabel = new TitleLabel("Baby Bird");
		add(titleLabel,BorderLayout.PAGE_START);
		
		//main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.GREEN);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		//score panel
		mainPanel.add(scorePanel);
		
		//flight panel
		mainPanel.add(flightPanel);
		
		//bottom panel
		
		//bird nest panel
		
	} // end initGUI
	
}
