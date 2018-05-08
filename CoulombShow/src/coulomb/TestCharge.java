package coulomb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import com.sun.prism.paint.Paint;

public class TestCharge {
	double x;
	double y;
	double vx;
	double vy;
	double ax;
	double ay;
	
	
	public double getX() {
		return x;
	}
	 
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	public double getY() {
		return y;
	}
	public double getVx() {
		return vx;
	}
	public void setVx(double vx) {
		this.vx = vx;
	}
	
	public void setVy(double vy) {
		this.vy = vy;
	}
	
	public double getVy() {
		return vy;
	}
	public double getAx() {
		return ax;
	}
	public void setAx(double ax) {
		this.ax = ax;
	}
	public double getAy() {
		return ay;
	}
	public void setAy(double ay) {
		this.ay = ay;
	}
	

	public TestCharge(double xx, double yy) {
		x=xx;
		y=yy;
		vx=0;
		vy=0;
		ax=0;
		ay=0;
		
		
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.yellow);
		g2d.fillOval((int)x, (int)y, 10, 10);

	}
	
	
}
