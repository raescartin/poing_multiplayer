package poing.frontend;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import poing.Constants;

class PixelBall {
	double x;
   double y;
   int scoreP1;
   int scoreP2;
   public Ellipse2D getShape() {
     return new Ellipse2D.Double(this.x, this.y, Constants.ball_size, Constants.ball_size);
   }
 }