package coulomb;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.Soundbank;


public class MovementClass {
	MainWindow mainWindow;
	int dt = 500;
	Timer timer;
	TimerTask task;

	public MovementClass(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

	}

	double R(int i) {
		double R = Math.sqrt((mainWindow.simulationPanel.staticChargeList.get(i).getxPos()
				- mainWindow.simulationPanel.testCharge.getX())
				* (mainWindow.simulationPanel.staticChargeList.get(i).getxPos()
						- mainWindow.simulationPanel.testCharge.getX())
				+ ((mainWindow.simulationPanel.staticChargeList.get(i).getyPos()
						- mainWindow.simulationPanel.testCharge.getY())
						* (mainWindow.simulationPanel.staticChargeList.get(i).getyPos()
								- mainWindow.simulationPanel.testCharge.getY())));
		return R;
	}

	double Cos(int i) {
		return (mainWindow.simulationPanel.testCharge.getX()
				- mainWindow.simulationPanel.staticChargeList.get(i).getxPos()) / R(i);
	}

	double Sin(int i) {
		return (mainWindow.simulationPanel.testCharge.getY()
				- mainWindow.simulationPanel.staticChargeList.get(i).getyPos()) / R(i);
	}

	void Ax() {
		double ax = 0;
		for (int i = 0; i < mainWindow.simulationPanel.staticChargeList.size(); i++) {
			ax += mainWindow.simulationPanel.staticChargeList.get(i).valueElectricCharge / (R(i) * R(i)) * Cos(i);

		}
		mainWindow.simulationPanel.testCharge.setAx(ax);
		
		mainWindow.simulationPanel.testCharge.setVx(mainWindow.simulationPanel.testCharge.getVx() + mainWindow.simulationPanel.testCharge.getAx() * dt);
		mainWindow.simulationPanel.testCharge.setX(mainWindow.simulationPanel.testCharge.getVx() * dt
				+ mainWindow.simulationPanel.testCharge.getAx() * dt * dt
				+ mainWindow.simulationPanel.testCharge.getX());

		mainWindow.simulationPanel.repaint();
		
		if(mainWindow.simulationPanel.testCharge.getX()<(-100) || mainWindow.simulationPanel.testCharge.getX()>(mainWindow.simulationPanel.getWidth()+100)){
			stopSimulation();
		}

	}

	void Ay() {
		double ay = 0;
		for (int i = 0; i < mainWindow.simulationPanel.staticChargeList.size(); i++) {
			ay += mainWindow.simulationPanel.staticChargeList.get(i).valueElectricCharge / (R(i) * R(i)) * Sin(i);

		}
		mainWindow.simulationPanel.testCharge.setAy(ay);
		
		mainWindow.simulationPanel.testCharge.setVy(mainWindow.simulationPanel.testCharge.getVy() + mainWindow.simulationPanel.testCharge.getAy() * dt);
		mainWindow.simulationPanel.testCharge.setY(mainWindow.simulationPanel.testCharge.getVy() * dt
				+ mainWindow.simulationPanel.testCharge.getAy() * dt * dt
				+ mainWindow.simulationPanel.testCharge.getY());

		mainWindow.simulationPanel.repaint();
		
		if(mainWindow.simulationPanel.testCharge.getY()<(-100) || mainWindow.simulationPanel.testCharge.getY()>(mainWindow.simulationPanel.getHeight()+100)){
			stopSimulation();
		}

	}

	class MyTask extends TimerTask {

		@Override
		public void run() {
			Ax();
			Ay();

		}

	}

	public void startSimulation() {

		task = new MyTask();

		timer = new Timer(true);

		timer.scheduleAtFixedRate(task, 100, 200);
		System.out.println("Task run");
	}

	public void stopSimulation(){
		
		timer.cancel();
		System.out.println("cancel");
	}

}
