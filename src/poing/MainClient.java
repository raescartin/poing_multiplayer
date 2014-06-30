package poing;

import java.awt.EventQueue;

public class MainClient {
	public static void main(String[] args) {
			
	    EventQueue.invokeLater(new Runnable() {
	      public void run() {
	    	  FrameClient frameclient = new FrameClient();
	      }
	    });
	  }
}
