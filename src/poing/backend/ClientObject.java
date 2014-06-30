package poing.backend;

import java.io.Serializable;

public class ClientObject implements Serializable{

	private double pos_y;
	
	public ClientObject(){
		
		pos_y=0;
	}
	
	public double getPos_y(){
		
		
		return pos_y;
	}
	
	public void setPos_y(double pos_y){
		
		
		this.pos_y=pos_y;
	}
	
	
}
