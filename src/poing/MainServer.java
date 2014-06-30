package poing;

import java.awt.EventQueue;

public class MainServer {
	public static void main(String[] args) {
			
	    EventQueue.invokeLater(new Runnable() {
	      public void run() {
	    	  FrameServer frameserver = new FrameServer();
	      }
	    });
	  }
}
