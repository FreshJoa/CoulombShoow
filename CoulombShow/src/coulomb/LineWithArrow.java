package coulomb;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class LineWithArrow {
	double phi;
	int barb;
	ArrayList<Point> pointList;
	
	void addPoint(java.awt.Point p1 ){
		pointList.add(p1);
		
	}

	public LineWithArrow() {
		phi = Math.toRadians(40);
		barb = 20;
		pointList=new ArrayList<>();
	}

	 void drawArrowLine(Graphics2D g2) {
		g2.setPaint(Color.YELLOW);
		double dy = pointList.get(1).getY() -pointList.get(0).getY();
		double dx = pointList.get(1).getX() - pointList.get(0).getX();
		double theta = Math.atan2(dy, dx);
		// System.out.println("theta = " + Math.toDegrees(theta));
		double x, y, rho = theta + phi;
		for (int j = 0; j < 2; j++) {
			x = pointList.get(1).getX() - barb * Math.cos(rho);
			y = pointList.get(1).getY() - barb * Math.sin(rho);
			g2.draw(new Line2D.Double(pointList.get(1).getX(), pointList.get(1).getY(), x, y));
			rho = theta - phi;
		}
		g2.draw(new Line2D.Double(pointList.get(0), pointList.get(1)));
	}

}
