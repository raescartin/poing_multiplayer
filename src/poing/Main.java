package poing;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
			
	    EventQueue.invokeLater(new Runnable() {
	      public void run() {
	    	  MainFrame mainFrame = new MainFrame();
	      }
	    });
	  }
}
