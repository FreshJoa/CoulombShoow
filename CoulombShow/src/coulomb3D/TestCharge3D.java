package coulomb3D;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class TestCharge3D extends Sphere {

	double x;
	double y;
	double z;
	double vx;
	double vy;
	double vz;
	double ax;
	double ay;
	double az;
	Color color;

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
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
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
	public void setVz(double vz) {
		this.vz = vz;
	}
	public double getVz() {
		return vz;
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
	public double getAz() {
		return az;
	}
	public void setAz(double az) {
		this.az = az;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public TestCharge3D(double xx, double yy) {
		x = xx;
		y = yy;
		Random rand=new Random();
		
		setTranslateX(x);
		setTranslateY(y);
		z=(double)rand.nextInt(1000);
		setTranslateZ(z);
		vx=0;
		vy=0;
		vz=0;
		setRadius(25.0);
		PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.GOLD);
        whiteMaterial.setSpecularColor(Color.LIGHTGOLDENRODYELLOW);
        setMaterial(whiteMaterial);
		
			
		
	}

}
