package coulomb;

import java.awt.Color;
import java.util.ArrayList;

public class DrawEquipotential implements Runnable {
	MainWindow mainWindow;
	ArrayList<Pixel> pixelList = new ArrayList<>();
	// int iterator = 0;
	Thread drawEquipotential;

	public DrawEquipotential(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	double R(int i, int j) {
		double R = Math.sqrt((mainWindow.simulationPanel.staticChargeList.get(i).getxPos() - pixelList.get(j).getX())
				* (mainWindow.simulationPanel.staticChargeList.get(i).getxPos() - pixelList.get(j).getX())
				+ ((mainWindow.simulationPanel.staticChargeList.get(i).getyPos() - pixelList.get(j).getY())
						* (mainWindow.simulationPanel.staticChargeList.get(i).getyPos() - pixelList.get(j).getY())));
		return R;
	}

	void calculateEquipotential() {

		int iterator = 0;
		int numberOfPixels = mainWindow.simulationPanel.getWidth() * mainWindow.simulationPanel.getHeight();
		System.out.println(numberOfPixels + " number of pixels");
		for (int a = 0; a <= mainWindow.simulationPanel.getWidth(); a++) {
			for (int b = 0; b <= mainWindow.simulationPanel.getHeight(); b++) {

				double equipotential = 0;
				pixelList.add(new Pixel(a, b));
				for (int i = 0; i < mainWindow.simulationPanel.staticChargeList.size(); i++) {
					equipotential += mainWindow.simulationPanel.staticChargeList.get(i).getValueElectricCharge()
							/ (R(i, iterator) * R(i, iterator));
				}
				pixelList.get(iterator).setValueEquipotential(equipotential * 100);
				iterator++;
				// System.out.println(iterator);
			}
		}

	}

	double maxValueEquipotential() {
		double inf = Double.POSITIVE_INFINITY;
		double max = pixelList.get(0).valueEquipotential;
		for (int i = 0; i < pixelList.size(); i++) {
			if (pixelList.get(i).getValueEquipotential() > max && (pixelList.get(i).getValueEquipotential() != inf)) {
				max = pixelList.get(i).getValueEquipotential();
			}
		}

		return max;
	}

	double minValueEquipotential() {
		double inf = Double.NEGATIVE_INFINITY;
		double min = pixelList.get(0).valueEquipotential;
		for (int i = 0; i < pixelList.size(); i++) {
			if (pixelList.get(i).getValueEquipotential() < min && (pixelList.get(i).getValueEquipotential() != inf)) {
				min = pixelList.get(i).getValueEquipotential();
			}
		}

		return min;
	}

	void drawEquipotential() {
		double max = maxValueEquipotential();
		double min = minValueEquipotential();
		double step = (max - min) / 10000.0;
		System.out.println(min + " min " + max + " max");
		System.out.println(step + " step");

		for (double currentEquipotential = min; currentEquipotential < max; currentEquipotential += step) {
			for (int i = 0; i < pixelList.size(); i++) {
				if (Math.abs(pixelList.get(i).valueEquipotential) <= Math.abs(1.07 * currentEquipotential)
						&& Math.abs(pixelList.get(i).valueEquipotential) >= Math.abs(0.97 * currentEquipotential)) {
					pixelList.get(i).setColor(Color.green);
				}

			}

		}

	}

	@Override
	public void run() {
		calculateEquipotential();
		drawEquipotential();
		mainWindow.simulationPanel.repaint();
		System.out.println("koniec");

	}

	

}
