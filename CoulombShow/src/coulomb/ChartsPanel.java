package coulomb;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.Soundbank;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartsPanel extends JPanel {
	MainWindow mainWindow;
	JFreeChart chartVx;
	JFreeChart chartVy = null;
	JFreeChart chartVt = null;
	XYSeries seriesVx = new XYSeries("Vx(x)");
	XYSeries seriesVy = new XYSeries("Vy(y)");
	XYSeries seriesVt = new XYSeries("V(t)");
	ChartPanel chartPanelVx = null;
	ChartPanel chartPanelVy = null;
	ChartPanel chartPanelVt = null;
	ArrayList<Double> vxList=new ArrayList<>();
	ArrayList<Double> vyList=new ArrayList<>();
	ArrayList<Double> vList=new ArrayList<>();
	ArrayList<Double> tList=new ArrayList<>();

	public ChartsPanel(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;

	}

	void createCharts() {
		// Tworzę wykres Vx(x)
		seriesVx.add(mainWindow.movementClass.dt/1000, mainWindow.simulationPanel.testCharge.getVx()*10000);
		vxList.add(mainWindow.simulationPanel.testCharge.getVx()*10000);
		tList.add((double)( mainWindow.movementClass.dt/1000.0));
		XYSeriesCollection datasetVx = new XYSeriesCollection();
		datasetVx.addSeries(seriesVx);
		NumberAxis xAxis = new NumberAxis("t");
		// xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		NumberAxis vxAxis = new NumberAxis("Vx");
		// vxAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		xAxis.setAutoRangeIncludesZero(false);
		vxAxis.setAutoRangeIncludesZero(false);
		XYPlot plotVx = new XYPlot(new XYSeriesCollection(seriesVx), xAxis, vxAxis,
				new XYLineAndShapeRenderer(true, false));
		chartVx = new JFreeChart(mainWindow.resourceBundle.getString("Wykres_zaleznosci_Vx(t)"),
				JFreeChart.DEFAULT_TITLE_FONT, plotVx, false);
		chartPanelVx = new ChartPanel(chartVx);

		// Tworzę wykres Vy(y)
		seriesVy.add(mainWindow.movementClass.dt/1000.0, mainWindow.simulationPanel.testCharge.getVy()*10000);
		vyList.add(mainWindow.simulationPanel.testCharge.getVy()*10000);
		
		XYSeriesCollection datasetVy = new XYSeriesCollection();
		datasetVy.addSeries(seriesVy);
		NumberAxis yAxis = new NumberAxis("t");
		// xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		NumberAxis vyAxis = new NumberAxis("Vy");
		// vyAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		yAxis.setAutoRangeIncludesZero(false);
		vyAxis.setAutoRangeIncludesZero(false);
		XYPlot plotVy = new XYPlot(new XYSeriesCollection(seriesVy), yAxis, vyAxis,
				new XYLineAndShapeRenderer(true, false));
		chartVy = new JFreeChart(mainWindow.resourceBundle.getString("Wykres_zaleznosci_Vy(t)"),
				JFreeChart.DEFAULT_TITLE_FONT, plotVy, false);
		chartPanelVy = new ChartPanel(chartVy);

		// Tworzę wykres V(t)

		seriesVt.add(mainWindow.movementClass.dt,
				Math.sqrt(mainWindow.simulationPanel.testCharge.getVx()*100000000 * mainWindow.simulationPanel.testCharge.getVx()
						+ mainWindow.simulationPanel.testCharge.getVy()*100000000
								* mainWindow.simulationPanel.testCharge.getVy()));
		
		vList.add(Math.sqrt(mainWindow.simulationPanel.testCharge.getVx() *100000000* mainWindow.simulationPanel.testCharge.getVx()
						+ mainWindow.simulationPanel.testCharge.getVy()*100000000
								* mainWindow.simulationPanel.testCharge.getVy()));
		XYSeriesCollection datasetVt = new XYSeriesCollection();
		datasetVt.addSeries(seriesVt);
		NumberAxis tAxis = new NumberAxis("t");
		// tAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		NumberAxis vAxis = new NumberAxis("V");
		// vAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		tAxis.setAutoRangeIncludesZero(false);
		vAxis.setAutoRangeIncludesZero(false);
		XYPlot plotVt = new XYPlot(new XYSeriesCollection(seriesVt), tAxis, vAxis,
				new XYLineAndShapeRenderer(true, false));
		chartVt = new JFreeChart(mainWindow.resourceBundle.getString("Wykres_zaleznosci_V(t)"),
				JFreeChart.DEFAULT_TITLE_FONT, plotVt, false);
		chartPanelVt = new ChartPanel(chartVt);
		this.setLayout(new GridLayout(3, 1));
		this.removeAll();// Usuwa wszystkie składniki z tego kontenera
		this.add(chartPanelVx);
		this.add(chartPanelVy);
		this.add(chartPanelVt);
		this.revalidate();// metoda zostanie automatycznie wywołana na tym komponencie, gdy wartość
							// właściwości zmieni się tak, że wpłynie to na rozmiar, lokalizację lub układ
							// wewnętrzny tego komponentu.

	}

}
