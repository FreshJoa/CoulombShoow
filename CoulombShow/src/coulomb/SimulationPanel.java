package coulomb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javafx.scene.image.Image;

public class SimulationPanel extends JPanel {
	MainWindow mainWindow;
	ArrayList<StaticCharge> staticChargeList = new ArrayList<>();
	TestCharge testCharge;
	BufferedImage image;
	LineWithArrow lineWithArrow;

	
	public ArrayList<StaticCharge> getStaticChargeList() {
		return staticChargeList;
	}

	public SimulationPanel(MainWindow mainWindow) {
		super();

		URL resource = getClass().getResource("milky-way.jpg");
		try {
			image = ImageIO.read(resource);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(mainWindow.WIDTH - 200, mainWindow.HEIGHT));

		this.mainWindow = mainWindow;

	}

	@Override
	protected void paintComponent(Graphics g) {

		int x = (this.getWidth() - image.getWidth(null)) / 2;
		int y = (this.getHeight() - image.getHeight(null)) / 2;

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(image, x, y, this);

		for (int i = 0; i < staticChargeList.size(); i++) {
			staticChargeList.get(i).draw(g2d);
		}
		if (testCharge != null) {
			testCharge.draw(g2d);
			
		}
		if (lineWithArrow != null) {
			
			lineWithArrow.drawArrowLine(g2d);
		}
		if (mainWindow.drawEquipotential.pixelList != null) {
			for (int i = 0; i < mainWindow.drawEquipotential.pixelList.size(); i++) {
				mainWindow.drawEquipotential.pixelList.get(i).draw(g2d);
			}
		}
	}

}
