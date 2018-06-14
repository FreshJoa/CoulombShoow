package coulomb;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javafx.scene.effect.Light.Point;

public class MauseListener implements MouseListener, MouseMotionListener {
	MainWindow mainWindow;
	SimulationPanel simulationPanel;
	java.awt.Point p1;
	java.awt.Point p2;
	double R=0;
	
	
	
	public MauseListener(MainWindow mainWindow, SimulationPanel simulationPanel) {
		this.mainWindow = mainWindow;
		this.simulationPanel = simulationPanel;
		simulationPanel.addMouseListener(this);
		simulationPanel.addMouseMotionListener(this);

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (mainWindow.optionPanel.whichCharge == mainWindow.optionPanel.addTestChargeString
				&& mainWindow.optionPanel.speedTestCharge.getValue() !=0) {
			simulationPanel.lineWithArrow.pointList.add(1, new java.awt.Point(e.getX(), e.getY()));
			simulationPanel.repaint();
			
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mainWindow.optionPanel.whichCharge == mainWindow.optionPanel.addTestChargeString
				&& mainWindow.optionPanel.speedTestCharge.getValue() !=0) {
			simulationPanel.lineWithArrow=new LineWithArrow();
			simulationPanel.lineWithArrow.addPoint(new java.awt.Point(e.getX(), e.getY()));
			simulationPanel.testCharge = new TestCharge(e.getX(), e.getY());
			
			//simulationPanel.repaint();

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mainWindow.optionPanel.whichCharge == mainWindow.optionPanel.addStaticChargeString) {
			
			simulationPanel.staticChargeList
					.add(new StaticCharge(e.getX(), e.getY(), mainWindow.optionPanel.valueStaticCharge.getValue()));
			simulationPanel.repaint();
		}

		if (mainWindow.optionPanel.whichCharge == mainWindow.optionPanel.addTestChargeString
				&& mainWindow.optionPanel.speedTestCharge.getValue() == 0) {
			simulationPanel.testCharge = new TestCharge(e.getX(), e.getY());
			simulationPanel.repaint();

		}
		 if (mainWindow.optionPanel.whichCharge == mainWindow.optionPanel.addTestChargeString
				&& mainWindow.optionPanel.speedTestCharge.getValue() !=0) {
			R=Math.sqrt((e.getX() - simulationPanel.testCharge.getX())*(e.getX() - simulationPanel.testCharge.getX())+(e.getY() - simulationPanel.testCharge.getY())*(e.getY() - simulationPanel.testCharge.getY()));
			
			simulationPanel.testCharge.setVx((e.getX() - simulationPanel.testCharge.getX())/R * 0.0001
					* mainWindow.optionPanel.speedTestCharge.getValue());
			simulationPanel.testCharge.setVy((e.getY() - simulationPanel.testCharge.getY())/R * 0.0001
					* mainWindow.optionPanel.speedTestCharge.getValue());
			simulationPanel.lineWithArrow=null;
			System.out.println("Vx " + simulationPanel.testCharge.getVx());
			System.out.println("Vy " + simulationPanel.testCharge.getVy());
			simulationPanel.repaint();

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
