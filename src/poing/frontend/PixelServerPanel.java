package poing.frontend;


import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;

import poing.Constants;
import poing.backend.Ball;
import poing.backend.ClientObject;
import poing.backend.Paddle;
import poing.backend.ServerObject;

public class PixelServerPanel extends JPanel {
	private Socket socket;
	public PixelServerPanel(final Ball ball,final Paddle paddle1,final Paddle paddle2){
		
		
			ServerSocket ss;
			try {
				ss = new ServerSocket(Constants.port);
				socket = ss.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		
		
		setPreferredSize(new Dimension(Constants.width, Constants.height));
		KeyListener listener = new KeyListener(){
    		public void keyPressed(KeyEvent e) {

    	        int key = e.getKeyCode();

    	        if (key == KeyEvent.VK_UP) {
    	            paddle1.dy = -1;
    	        }

    	        if (key == KeyEvent.VK_DOWN) {
    	        	paddle1.dy = 1;
    	        }
    	    }

    	    public void keyReleased(KeyEvent e) {
    	        int key = e.getKeyCode();
    	        if (key == KeyEvent.VK_UP) {
    	        	paddle1.dy = 0;
    	        }

    	        if (key == KeyEvent.VK_DOWN) {
    	        	paddle1.dy = 0;
    	        }
    	    }

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
    	};
		this.addKeyListener(listener);
		this.setFocusable(true);
		this.requestFocus();
		final MouseWheelListener wheel = new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
			      paddle2.dy=e.getWheelRotation();
			}
    	};
    	this.addMouseWheelListener(wheel);
    	this.setSize(Constants.width, Constants.height);
    	this.setMaximumSize( this.getPreferredSize() );
    	draw(ball,paddle1,paddle2);
	}
	public void draw(final Ball ball,final Paddle paddle1,final Paddle paddle2) {
	      final PixelBall pixelBall = new PixelBall();
	      final PixelPaddle pixelPaddle1 = new PixelPaddle();
	      final PixelPaddle pixelPaddle2 = new PixelPaddle();
	      this.add(pixelBall);
	      this.add(pixelPaddle1,pixelPaddle2);
	      final PixelServerPanel comp = this;
	      //comp.setDoubleBuffered(true);
	      pixelPaddle1.x=Constants.paddle_to_border;
	      pixelPaddle2.x=Constants.width-Constants.paddle_to_border;
	     
	      
	      Thread pixelBall_thread = new Thread( new Runnable(){
	            public void run(){
	            	InputStream is ;
	            	ObjectInputStream ois;
	            	OutputStream os; 
	    			ObjectOutputStream oos; 
	    			ClientObject co;
	            	ServerObject so =  new ServerObject();
	            	 
        			
						while(true){
		               		//We receive the object
		            		 try {
		            			 
								 is = socket.getInputStream();
		            			 ois = new ObjectInputStream(is);  
			        			 co = (ClientObject)ois.readObject(); 
			        			 //We prepare the sending object
			        			 
			            		 so.setBall_x(ball.x);
			            		 so.setBall_y(ball.y);
			        			 so.setPaddle_y(paddle1.y);
			        			 so.setScore1(ball.scoreP1);
			        			 so.setScore2(ball.scoreP2);
			        			 //We send the object
			        			 os =  socket.getOutputStream(); 
			        			 oos= new ObjectOutputStream(os);
			        			 oos.writeObject(so);  
			        			 pixelPaddle2.y=co.getPos_y();
			        			 paddle2.y=co.getPos_y();
							} catch (IOException e1) {
								
								e1.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
		        			 
		        			 
		        			 
		            		pixelBall.x=ball.x;
		            		pixelBall.y=ball.y;
		            		pixelBall.scoreP1=ball.scoreP1;
		            		pixelBall.scoreP2=ball.scoreP2;
		            		pixelPaddle1.y=paddle1.y;
		            		
		            	
		            		comp.repaint();
		            		try {
		        				Thread.sleep(Constants.refresh_screen);
		        			} catch (InterruptedException e) {
		        				e.printStackTrace();
		        			}
		            	}
					
	            	
	               	
	            }
	    	});
	    	pixelBall_thread.start();
	  }
    public void add(PixelBall b) {
      ball=b;
    }

    public void add(PixelPaddle pixelPaddle1,PixelPaddle pixelPaddle2) {
		paddle1=pixelPaddle1;
		paddle2=pixelPaddle2;
	}

	public void paintComponent(Graphics g) {
      super.paintComponent(g); // erase background
      Graphics2D g2 = (Graphics2D) g;
      Font font = new Font("SansSerif", Font.PLAIN, 42);
      g2.setFont(font);
      Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
      g2.setStroke(dashed);
      g2.drawLine(Constants.width/2, 0, Constants.width/2, Constants.height);
        g2.fill(ball.getShape());
        g2.drawString(Integer.toString(ball.scoreP1), Constants.width/2-Constants.paddle_to_border, Constants.height/4);
  		  g2.drawString(Integer.toString(ball.scoreP2), Constants.width/2+Constants.paddle_to_border, Constants.height/4);
        
	        g2.fill(paddle1.getShape());
	        g2.fill(paddle2.getShape());
    }

    private PixelBall ball = new PixelBall();
    private PixelPaddle paddle1 = new PixelPaddle();
    private PixelPaddle paddle2 = new PixelPaddle();
  }

  

