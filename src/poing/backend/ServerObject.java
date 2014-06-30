package poing.backend;

import java.io.Serializable;

public class ServerObject implements Serializable {

	private double ball_x;
	private double ball_y;
	private double paddle_y;
	private int score1;
	private int score2;
	
	public ServerObject(){
		ball_x=0;
		ball_y=0;
		paddle_y=0;
		
		
	}
	
	public ServerObject( double ball_x,  double ball_y, 
			  double paddle_y, int score1, int score2){
		
		this.ball_x=ball_x;
		this.ball_y=ball_y;
		this.paddle_y=paddle_y;
		this.score1=score1;
		this.score2=score2;
	}
	
	
	public double getBall_x() {
		return ball_x;
	}
	public void setBall_x(double ball_x) {
		this.ball_x = ball_x;
	}
	public double getBall_y() {
		return ball_y;
	}
	public void setBall_y(double ball_y) {
		this.ball_y = ball_y;
	}

	public double getPaddle_y() {
		return paddle_y;
	}
	public void setPaddle_y(double paddle_y) {
		this.paddle_y = paddle_y;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}
	
	
	
	
	
}
