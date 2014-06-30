package poing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import poing.Constants.Globals;
import poing.backend.Ball;
import poing.backend.Game;
import poing.backend.Paddle;
import poing.frontend.PixelPanel;
import poing.frontend.PixelServerPanel;

public class FrameServer extends JFrame{

	    CardLayout cardLayout;
	    JPanel mainPanel;
	    SplashPanel menu;
	    GamePanel game;
	    final Ball ball = new Ball();
		final Paddle paddle1 = new Paddle(Constants.paddle_to_border+Constants.paddle_width/2);
		final Paddle paddle2 = new Paddle(Constants.width-Constants.paddle_to_border+Constants.paddle_width/2);
		
	    
	    public FrameServer() {
	    	
	        this.setTitle("Poing");
		    final JPanel game = new PixelServerPanel(ball, paddle1,paddle2);
		    this.add(game, BorderLayout.CENTER);
		    this.pack();
		    this.setVisible(true);
		    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    
	        cardLayout = new CardLayout();
	        mainPanel = new JPanel(cardLayout);
	        menu = new SplashPanel();
	        mainPanel.add(menu, "menu");
	        mainPanel.add(game, "game");
		    
		    
		  


	        add(mainPanel);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        pack();
	        setVisible(true);
	        serverGameStart();
			   game.setFocusable(true);
			   game.requestFocus();
			          
	    }
	    class SplashPanel extends JPanel {

	        public SplashPanel() {
	        	setBackground(Color.BLACK);
	        	try {
	        		BufferedImage myPicture = ImageIO.read(this.getClass().getResource("/images/logo.png"));
		        	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		        	add(picLabel);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }
	    class GamePanel extends JPanel {
	    	
	    }

	public void fullGameStart(){
		try {
			Globals.end=true;
			Thread.sleep(Constants.refresh_screen);
			Globals.end=false;
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Globals.update_ball=Constants.update_ball;
		Game game = new Game(ball,paddle1,paddle2);
		cardLayout.show(mainPanel, "game");
	}
	public void serverGameStart(){
		Game game = new Game(ball,paddle1,paddle2);
		cardLayout.show(mainPanel, "game");
	}
	public void clientGameStart(){
		
	}
	public void settings(){
		
	}
}
