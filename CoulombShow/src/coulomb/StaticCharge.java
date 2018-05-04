package coulomb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class StaticCharge {
	double xPos;
	double yPos;
	int valueElectricCharge;
	Color color;
	
	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public int getValueElectricCharge() {
		return valueElectricCharge;
	}
	public void setValueElectricCharge(int valueElectricCharge) {
		this.valueElectricCharge = valueElectricCharge;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	
	

	public StaticCharge(double x, double y, int value) {
		xPos=x;
		yPos=y;
		valueElectricCharge=value;
		Random rand=new Random();
		color=new Color(rand.nextInt(256),rand.nextInt(256), rand.nextInt(256));
		
		
	}
	
public void draw(Graphics2D g2d) {
		
		g2d.setColor(color);
		g2d.fillOval((int)xPos, (int)yPos, 10*Math.abs(valueElectricCharge), 10*Math.abs(valueElectricCharge));

	}

}
