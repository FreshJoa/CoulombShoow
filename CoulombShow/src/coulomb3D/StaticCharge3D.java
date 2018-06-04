package coulomb3D;

import java.awt.Color;
import java.util.Random;

import coulomb.StaticCharge;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class StaticCharge3D extends Sphere {
	
	double xPos;
	double yPos;
	double zPos;
	double valueElectricCharge;
	javafx.scene.paint.Color color;
	
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
	public double getzPos() {
		return zPos;
	}
	public void setzPos(double zPos) {
		this.zPos = zPos;
	}
	public void setColor(javafx.scene.paint.Color color) {
		this.color = color;
	}
	public javafx.scene.paint.Color getColor() {
		return color;
	}
	public double getValueElectricCharge() {
		return valueElectricCharge;
	}
	public void setValueElectricCharge(double valueElectricCharge) {
		this.valueElectricCharge = valueElectricCharge;
	}

	public StaticCharge3D(double x, double y, double value) {
		Random rand=new Random();
		xPos=x;
		yPos=y;
		valueElectricCharge=value;
		System.out.println("valule constructor "+valueElectricCharge);
		setTranslateX(xPos);
		setTranslateY(yPos);
		zPos=(double)rand.nextInt(1000);
		setTranslateZ(zPos);
		setRadius(Math.abs(valueElectricCharge*5));
		if(valueElectricCharge>=0) {
			PhongMaterial whiteMaterial = new PhongMaterial();
	        whiteMaterial.setDiffuseColor(javafx.scene.paint.Color.RED);
	        whiteMaterial.setSpecularColor(javafx.scene.paint.Color.CRIMSON);
	        setMaterial(whiteMaterial);
		}
		else if(valueElectricCharge < 0){
			PhongMaterial whiteMaterial = new PhongMaterial();
	        whiteMaterial.setDiffuseColor(javafx.scene.paint.Color.BLUE);
	        whiteMaterial.setSpecularColor(javafx.scene.paint.Color.CORNFLOWERBLUE);
	        setMaterial(whiteMaterial);
			
		}
		
	}

	public StaticCharge3D(double radius) {
		super(radius);
		// TODO Auto-generated constructor stub
	}

	public StaticCharge3D(double radius, int divisions) {
		super(radius, divisions);
		// TODO Auto-generated constructor stub
	}

}
