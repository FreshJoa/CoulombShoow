package coulomb;

import java.awt.GridLayout;
import java.awt.LayoutManager;
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




public class ChartsPanel extends JPanel  {
	MainWindow mainWindow;
	JFreeChart chartVx;
	JFreeChart chartVy=null;
	JFreeChart chartVt=null;
	XYSeries seriesVx=new XYSeries("Vx(x)");
	XYSeries seriesVy=new XYSeries("Vy(y)");
	XYSeries seriesVt=new XYSeries("V(t)");
	ChartPanel chartPanelVx=null;
	ChartPanel chartPanelVy=null;
	ChartPanel chartPanelVt=null;
	

	public ChartsPanel(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
		
	}
	
	void createCharts() {
		//Tworzę wykres Vx(x)
		seriesVx.add(mainWindow.simulationPanel.testCharge.getX(), mainWindow.simulationPanel.testCharge.getVx());
		XYSeriesCollection datasetVx = new XYSeriesCollection();
		datasetVx.addSeries(seriesVx);
		NumberAxis xAxis = new NumberAxis("x");
		xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		NumberAxis vxAxis = new NumberAxis("Vx");
		vxAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		xAxis.setAutoRangeIncludesZero(false);
		vxAxis.setAutoRangeIncludesZero(false);
		XYPlot plotVx = new XYPlot(new XYSeriesCollection(seriesVx), xAxis, vxAxis, new XYLineAndShapeRenderer(true, false));
		chartVx = new JFreeChart("Wykres zależności Vx(x)", JFreeChart.DEFAULT_TITLE_FONT, plotVx, false);
		chartPanelVx = new ChartPanel(chartVx);
		
		//Tworzę wykres Vy(y)
		seriesVy.add(mainWindow.simulationPanel.testCharge.getY(), mainWindow.simulationPanel.testCharge.getVy());
		XYSeriesCollection datasetVy = new XYSeriesCollection();
		datasetVy.addSeries(seriesVy);
		NumberAxis yAxis = new NumberAxis("y");
		xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		NumberAxis vyAxis = new NumberAxis("Vy");
		vyAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		yAxis.setAutoRangeIncludesZero(false);
		vyAxis.setAutoRangeIncludesZero(false);
		XYPlot plotVy = new XYPlot(new XYSeriesCollection(seriesVy), yAxis, vyAxis, new XYLineAndShapeRenderer(true, false));
		chartVy = new JFreeChart("Wykres zależności Vy(y)", JFreeChart.DEFAULT_TITLE_FONT, plotVy, false);
		chartPanelVy = new ChartPanel(chartVy);
		
		//Tworzę wykres V(t)
		seriesVt.add(Math.sqrt(mainWindow.simulationPanel.testCharge.getVx()*mainWindow.simulationPanel.testCharge.getVx()+mainWindow.simulationPanel.testCharge.getVy()*mainWindow.simulationPanel.testCharge.getVy()), mainWindow.movementClass.dt);
		XYSeriesCollection datasetVt = new XYSeriesCollection();
		datasetVt.addSeries(seriesVt);
		NumberAxis tAxis = new NumberAxis("t");
		tAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		NumberAxis vAxis = new NumberAxis("V");
		vAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		tAxis.setAutoRangeIncludesZero(false);
		vAxis.setAutoRangeIncludesZero(false);
		XYPlot plotVt = new XYPlot(new XYSeriesCollection(seriesVt), tAxis, vAxis, new XYLineAndShapeRenderer(true, false));
		chartVt = new JFreeChart("Wykres zależności V(t)", JFreeChart.DEFAULT_TITLE_FONT, plotVt, false);
		chartPanelVt = new ChartPanel(chartVt);
		this.setLayout(new GridLayout(3, 1));
		this.removeAll();
		this.add(chartPanelVx);
		this.add(chartPanelVy);
		this.add(chartPanelVt);
		this.revalidate();
		//System.out.println(mainWindow.simulationPanel.testCharge.getX()+" "+ mainWindow.simulationPanel.testCharge.getVx());
		
	}

	
}
