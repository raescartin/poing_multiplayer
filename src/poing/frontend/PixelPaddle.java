package poing.frontend;

import java.awt.geom.Rectangle2D;

import poing.Constants;

class PixelPaddle{
	  double x;
	  double y;
	  public Rectangle2D getShape() {
		  return new Rectangle2D.Double(this.x, this.y, Constants.paddle_width, Constants.paddle_height);
	  }
}