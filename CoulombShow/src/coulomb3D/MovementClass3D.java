package coulomb3D;

import java.util.Timer;
import java.util.TimerTask;

public class MovementClass3D {

	Window3D window3d;
	int dt = 500;
	Timer timer;
	TimerTask task;

	public MovementClass3D(Window3D window3d) {
		this.window3d = window3d;
		// TODO Auto-generated constructor stub
	}

	/*double R(int i) {
		double R = Math.sqrt((window3d.simulationPane.staticCharge3Dlist.get(i).getxPos()
				- window3d.simulationPane.testCharge.getX())
				* (window3d.simulationPane.staticCharge3Dlist.get(i).getxPos()
						- window3d.simulationPane.testCharge.getX())
				+ (window3d.simulationPane.staticCharge3Dlist.get(i).getyPos()
						- window3d.simulationPane.testCharge.getY())
						* (window3d.simulationPane.staticCharge3Dlist.get(i).getyPos()
								- window3d.simulationPane.testCharge.getY())
				+ (window3d.simulationPane.staticCharge3Dlist.get(i).getzPos()
						- window3d.simulationPane.testCharge.getZ())
						* ((window3d.simulationPane.staticCharge3Dlist.get(i).getzPos()
								- window3d.simulationPane.testCharge.getZ())));
		return R;
	}

	double CosPhi(int i) {
		return (window3d.simulationPane.testCharge.getX()
				- window3d.simulationPane.staticCharge3Dlist.get(i).getxPos()) / R(i);
	}

	double SinPhi(int i) {
		return (window3d.simulationPane.testCharge.getY()
				- window3d.simulationPane.staticCharge3Dlist.get(i).getyPos()) / R(i);
	}
	double CosTeta(int i) {
		return (window3d.simulationPane.testCharge.getZ()
				- window3d.simulationPane.staticCharge3Dlist.get(i).getzPos()) / R(i);
	}
	double SinTeta(int i) {
		double Rxy= Math.sqrt((window3d.simulationPane.staticCharge3Dlist.get(i).getxPos()
				- window3d.simulationPane.testCharge.getX())
				* (window3d.simulationPane.staticCharge3Dlist.get(i).getxPos()
						- window3d.simulationPane.testCharge.getX())
				+ (window3d.simulationPane.staticCharge3Dlist.get(i).getyPos()
						- window3d.simulationPane.testCharge.getY())
						* (window3d.simulationPane.staticCharge3Dlist.get(i).getyPos()
								- window3d.simulationPane.testCharge.getY()));
		return (Rxy/ R(i));
	}
	
	
	

	void Ax() {
		double ax = 0;
		for (int i = 0; i <window3d.simulationPane.staticCharge3Dlist.size(); i++) {
			ax += window3d.simulationPane.staticCharge3Dlist.get(i).valueElectricCharge / (R(i) * R(i)) * CosPhi(i)*SinTeta(i);
		}
		window3d.simulationPane.testCharge.setAx(ax * 0.000001);

		window3d.simulationPane.testCharge.setVx(
				window3d.simulationPane.testCharge.getVx() + window3d.simulationPane.testCharge.getAx() * dt);
		window3d.simulationPane.testCharge.setX(window3d.simulationPane.testCharge.getVx() * dt
				+ window3d.simulationPane.testCharge.getAx() * dt * dt / 2.0
				+ window3d.simulationPane.testCharge.getX());

		
		dt += 500;

		

	}

	void Ay() {
		double ay = 0;
		for (int i = 0; i <window3d.simulationPane.staticCharge3Dlist.size(); i++) {
			ay += window3d.simulationPane.staticCharge3Dlist.get(i).valueElectricCharge / (R(i) * R(i)) * SinPhi(i)*SinTeta(i);
		}
		window3d.simulationPane.testCharge.setAy(ay * 0.000001);

		window3d.simulationPane.testCharge.setVy(
				window3d.simulationPane.testCharge.getVy() + window3d.simulationPane.testCharge.getAy() * dt);
		window3d.simulationPane.testCharge.setY(window3d.simulationPane.testCharge.getVy() * dt
				+ window3d.simulationPane.testCharge.getAy() * dt * dt / 2.0
				+ window3d.simulationPane.testCharge.getY());

		
		dt += 500;

		

	}
	
	void Az() {
		double az = 0;
		for (int i = 0; i <window3d.simulationPane.staticCharge3Dlist.size(); i++) {
			az += window3d.simulationPane.staticCharge3Dlist.get(i).valueElectricCharge / (R(i) * R(i)) *CosTeta(i);
		}
		window3d.simulationPane.testCharge.setAz(az * 0.000001);

		window3d.simulationPane.testCharge.setVz(
				window3d.simulationPane.testCharge.getVz() + window3d.simulationPane.testCharge.getAz() * dt);
		window3d.simulationPane.testCharge.setZ(window3d.simulationPane.testCharge.getVz() * dt
				+ window3d.simulationPane.testCharge.getAz() * dt * dt / 2.0
				+ window3d.simulationPane.testCharge.getZ());

		System.out.println("nowy x "+window3d.simulationPane.testCharge.getX());
		
		dt += 500;

		

	}
	
	class MyTask extends TimerTask {

		@Override
		public void run() {
			
			Ax();
			Ay();
			Az();
			System.out.println("run");
		window3d.simulationPane.getChildren().remove(window3d.simulationPane.testCharge);
			window3d.simulationPane.getChildren().add(new TestCharge3D(100, 100));
		
			System.out.println("run");
		}

	}

	public void startSimulation() {
		dt = 500;

		task = new MyTask();

		timer = new Timer(true);

		timer.scheduleAtFixedRate(task, 100, 50);
		System.out.println("Task run");
	}

	public void stopSimulation() {

		timer.cancel();
		System.out.println("cancel");
	}
*/
}
