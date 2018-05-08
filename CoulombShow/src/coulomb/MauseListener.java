package coulomb;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MauseListener implements MouseListener , MouseMotionListener {
	MainWindow mainWindow;
	SimulationPanel simulationPanel;

	public MauseListener(MainWindow mainWindow, SimulationPanel simulationPanel) {
		this.mainWindow=mainWindow;
		this.simulationPanel=simulationPanel;
		simulationPanel.addMouseListener(this);
		simulationPanel.addMouseMotionListener(this);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		
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
		if(mainWindow.optionPanel.whichCharge==mainWindow.optionPanel.addTestChargeString && mainWindow.optionPanel.speedTestCharge != null) {
			simulationPanel.testCharge=new TestCharge(e.getX(), e.getY());
			simulationPanel.repaint();
			
			}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mainWindow.optionPanel.whichCharge==mainWindow.optionPanel.addStaticChargeString) {
			simulationPanel.staticChargeList.add(new StaticCharge(e.getX(), e.getY(), mainWindow.optionPanel.valueStaticCharge.getValue()));
			simulationPanel.repaint();
			}
		
		if(mainWindow.optionPanel.whichCharge==mainWindow.optionPanel.addTestChargeString && mainWindow.optionPanel.speedTestCharge == null) {
			simulationPanel.testCharge=new TestCharge(e.getX(), e.getY());
			simulationPanel.repaint();
			
			}
		if(mainWindow.optionPanel.whichCharge==mainWindow.optionPanel.addTestChargeString && mainWindow.optionPanel.speedTestCharge != null) {
			simulationPanel.testCharge.setVx((e.getX()-simulationPanel.testCharge.getX())*0.000001*mainWindow.optionPanel.speedTestCharge.getValue());
			simulationPanel.testCharge.setVy((e.getY()-simulationPanel.testCharge.getY())*0.000001*mainWindow.optionPanel.speedTestCharge.getValue());
			System.out.println("Vx "+simulationPanel.testCharge.getVx());
			System.out.println("Vy "+simulationPanel.testCharge.getVy());
			
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
