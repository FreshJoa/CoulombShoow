package coulomb;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pixel {
	int x;
	int y;
	Color color;
	double valueEquipotential;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	
	public double getValueEquipotential() {
		return valueEquipotential;
	}
	public void setValueEquipotential(double valueEquipotential) {
		this.valueEquipotential = valueEquipotential;
	}
	
	
	

	public Pixel(int xx, int yy) {
		x=xx;
		y=yy;
		//color=Color.black;
	}
	
public void draw(Graphics2D g2d) {
		
		//g2d.setColor(color);
	if(color==Color.green) {
		g2d.fillRect(x, y, 1, 1);
	}

	}

}
