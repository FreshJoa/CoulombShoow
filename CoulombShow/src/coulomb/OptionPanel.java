package coulomb;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import com.sun.prism.paint.Stop;

import jdk.nashorn.internal.scripts.JS;

public class OptionPanel extends JPanel implements ActionListener {
	MainWindow mainWindow;

	JButton startButton;
	JButton stopButton;
	JButton addStaticCharges;
	JButton addTestCharge;
	JButton newSimulationButton;
	JButton drawEquipotential;

	JSlider valueStaticCharge;
	JSlider speedTestCharge;

	JPanel startPanel;
	JPanel stopPanel;
	JPanel addStaticChargesPanel;
	JPanel addTestChargePanel;
	JPanel newSimulationPanel;
	JPanel valueStaticChargePanel;
	JPanel speedTestChargePanel;
	JPanel drawEquipotentialPanel;

	public static final String addStaticChargeString = "add static charge";
	public static final String addTestChargeString = "add test charge";
	String whichCharge = "";

	boolean startSimulationBoolean = false;
	boolean drawEquipotentialBoolean=false;

	public OptionPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		/*startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		addStaticCharges = new JButton("Dodaj ładunek statyczny");
		addTestCharge = new JButton("Dodaj łądunek próbny");
		newSimulationButton = new JButton("Nowa symulacja");
		drawEquipotential = new JButton("Rysuj linie ekwipotencjalne");*/
		startButton = new JButton(mainWindow.resourceBundle.getString("Start"));
		stopButton = new JButton(mainWindow.resourceBundle.getString("Stop"));
		addStaticCharges = new JButton(mainWindow.resourceBundle.getString("Dodaj_ladunek_statyczny"));
		addTestCharge = new JButton(mainWindow.resourceBundle.getString("Dodaj_ladunek_probny"));
		newSimulationButton = new JButton(mainWindow.resourceBundle.getString("Nowa_symulacja"));
		drawEquipotential = new JButton(mainWindow.resourceBundle.getString("Rysuj_linie_ekwipotencjalne"));
		
		startButton.setPreferredSize(new Dimension(290, 30));
		stopButton.setPreferredSize(new Dimension(290, 30));
		addStaticCharges.setPreferredSize(new Dimension(290, 30));
		addTestCharge.setPreferredSize(new Dimension(290, 30));
		newSimulationButton.setPreferredSize(new Dimension(290, 30));
		drawEquipotential.setPreferredSize(new Dimension(290, 30));

		valueStaticCharge = new JSlider(JSlider.HORIZONTAL, -10, 10, 1);
		valueStaticCharge.setMajorTickSpacing(2);
		//valueStaticCharge.setName("Name");
	//	valueStaticCharge.setToolTipText("vVV");
		valueStaticCharge.setFocusable(true);

		// valueStaticCharge.setVisible(true);

		valueStaticCharge.setPaintLabels(true);
		valueStaticCharge.setPaintTicks(true);
		valueStaticCharge.setBorder(BorderFactory.createTitledBorder(mainWindow.resourceBundle.getString("Wartosc_ladunku_statycznego")));
		//valueStaticCharge.setBorder(BorderFactory.createTitledBorder("Wartość ładunku statycznego "));
		valueStaticCharge.setPreferredSize(new Dimension(320, 70));

		speedTestCharge = new JSlider(-10, 10, 0);
		speedTestCharge.setMajorTickSpacing(2);

		speedTestCharge.setPaintLabels(true);
		speedTestCharge.setPaintTicks(true);
		speedTestCharge.setPreferredSize(new Dimension(320, 70));
		speedTestCharge.setBorder(BorderFactory.createTitledBorder(mainWindow.resourceBundle.getString("Predkosc_poczatkowa_ladunku_probnego")));
		//speedTestCharge.setBorder(BorderFactory.createTitledBorder("Prędkość początkowa ładunku próbnego  "));

		startPanel = new JPanel();
		stopPanel = new JPanel();
		newSimulationPanel = new JPanel();
		speedTestChargePanel = new JPanel();
		valueStaticChargePanel = new JPanel();
		addStaticChargesPanel = new JPanel();
		addTestChargePanel = new JPanel();
		drawEquipotentialPanel = new JPanel();

		startPanel.add(startButton);
		stopPanel.add(stopButton);
		newSimulationPanel.add(newSimulationButton);
		speedTestChargePanel.add(speedTestCharge);
		valueStaticChargePanel.add(valueStaticCharge);
		addStaticChargesPanel.add(addStaticCharges);
		addTestChargePanel.add(addTestCharge);
		drawEquipotentialPanel.add(drawEquipotential);

		setLayout(new GridLayout(8, 1));
		add(valueStaticChargePanel);
		add(addStaticChargesPanel);
		add(speedTestChargePanel);
		add(addTestChargePanel);
		add(startPanel);
		add(stopPanel);
		add(newSimulationPanel);
		add(drawEquipotentialPanel);
		setPreferredSize(new Dimension(390, mainWindow.HEIGHT));

		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		newSimulationButton.addActionListener(this);
		addStaticCharges.addActionListener(this);
		addTestCharge.addActionListener(this);
		drawEquipotential.addActionListener(this);

	}

	public OptionPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public OptionPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public OptionPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startSimulationBoolean = true;
			mainWindow.movementClass.startSimulation();

		}
		if (e.getSource() == stopButton) {
			mainWindow.movementClass.stopSimulation();

		}
		if (e.getSource() == newSimulationButton) {
			if (startSimulationBoolean == true) {
				mainWindow.movementClass.stopSimulation();
				mainWindow.simulationPanel.testCharge = null;
				mainWindow.simulationPanel.staticChargeList.clear();
				mainWindow.drawEquipotential.pixelList.clear();
				mainWindow.simulationPanel.repaint();
				mainWindow.chartsPanel.seriesVx.clear();
				mainWindow.chartsPanel.seriesVy.clear();
				mainWindow.chartsPanel.seriesVt.clear();
				startSimulationBoolean = false;
				drawEquipotentialBoolean=false;

			}
			startSimulationBoolean = false;
			mainWindow.simulationPanel.testCharge = null;
			mainWindow.simulationPanel.staticChargeList.clear();
			mainWindow.drawEquipotential.pixelList.clear();
			mainWindow.simulationPanel.repaint();

		}
		if (e.getSource() == addStaticCharges) {
			whichCharge = addStaticChargeString;

		}
		if (e.getSource() == addTestCharge) {
			whichCharge = addTestChargeString;

		}
		if (e.getSource() == drawEquipotential) {
			drawEquipotentialBoolean=true;
			ExecutorService exec = Executors.newFixedThreadPool(1);
			exec.execute(mainWindow.drawEquipotential);
			
			// mainWindow.drawEquipotential.restart();

		}

	}

}
