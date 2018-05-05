package coulomb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel {
	MainWindow mainWindow;
	ArrayList<StaticCharge> staticChargeList=new ArrayList<>();
	TestCharge testCharge;
	BufferedImage image;

	public SimulationPanel(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
		setPreferredSize(new Dimension(mainWindow.WIDTH-200, mainWindow.HEIGHT));
		setBackground(Color.pink);
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		

		
			int w=this.getWidth();
			int h=this.getHeight();
			image = (BufferedImage) this.createImage(w, h);
			Graphics2D gc = image.createGraphics();
			gc.setColor(Color.white);
			
			gc.fillRect(0,0, w, h);
		

		
		

		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < staticChargeList.size(); i++) {
			staticChargeList.get(i).draw(g2d);
		}
		if (testCharge != null) {
			testCharge.draw(g2d);
		}
		if(mainWindow.drawEquipotential.pixelList !=null) {
			for(int i=0; i<mainWindow.drawEquipotential.pixelList.size(); i++) {
				mainWindow.drawEquipotential.pixelList.get(i).draw(g2d);
			}
		}
	}

}
