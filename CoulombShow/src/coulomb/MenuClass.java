package coulomb;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;



public class MenuClass extends JMenuBar implements ActionListener {
	MainWindow mainWindow;
	JMenu saveMenu;
	JMenuItem saveDrawingEquipotential;
	JMenuItem saveCharts;
	
	JMenu helpMenu;
	JMenuItem helpItem;

	public MenuClass(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		saveMenu = new JMenu("Zapisz");
		saveDrawingEquipotential = new JMenuItem("Zapisz_linie_ekwipotencjalne");
		saveCharts = new JMenuItem("Zapisz_wykresy");
		
		helpMenu=new JMenu("Pomoc");
		helpItem=new JMenuItem("Pomoc");
		
		saveDrawingEquipotential.addActionListener(this);
		saveCharts.addActionListener(this);
		helpItem.addActionListener(this);

		saveMenu.add(saveDrawingEquipotential);
		saveMenu.add(saveCharts);
		helpMenu.add(helpItem);
		
		this.add(saveMenu);
		this.add(helpMenu);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == saveDrawingEquipotential) {
			saveDrawingEquipotential();

		}
		if (object == saveCharts) {
			saveCharts();

		}
		if (object == helpItem) {
			HelpFrame help=new HelpFrame(mainWindow);
			

		}

	}

	void saveDrawingEquipotential() {
		if (mainWindow.optionPanel.drawEquipotentialBoolean == true) {
			JFileChooser chooser = new JFileChooser();

			int returnVal = chooser.showDialog(null, "Wybierz");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				BufferedImage image = new BufferedImage(mainWindow.simulationPanel.getWidth(),
						mainWindow.simulationPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2im = image.createGraphics();
				mainWindow.simulationPanel.paintComponent(g2im);
				try {
					ImageIO.write(image, "jpeg", chooser.getSelectedFile());

				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}

			}

		}
	}

	void saveCharts() {
		if (mainWindow.optionPanel.startSimulationBoolean == true) {
			JFileChooser chooser = new JFileChooser();

			int returnVal = chooser.showDialog(null, "Wybierz");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				PDFDocument pdfDoc = new PDFDocument();
				pdfDoc.setTitle("Wykresy");

				Page page1 = pdfDoc.createPage(new Rectangle(612, 468));
				PDFGraphics2D g21 = page1.getGraphics2D();
				g21.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
				mainWindow.chartsPanel.chartVx.draw(g21, new Rectangle(0, 0, 612, 468));
				
				Page page2 = pdfDoc.createPage(new Rectangle(612, 468));
				PDFGraphics2D g22 = page2.getGraphics2D();
				g22.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
				mainWindow.chartsPanel.chartVy.draw(g22, new Rectangle(0, 0, 612, 468));
				
				Page page3 = pdfDoc.createPage(new Rectangle(612, 468));
				PDFGraphics2D g23 = page3.getGraphics2D();
				g23.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
				mainWindow.chartsPanel.chartVt.draw(g23, new Rectangle(0, 0, 612, 468));

				pdfDoc.writeToFile(chooser.getSelectedFile());

			}

		}

	}

}
