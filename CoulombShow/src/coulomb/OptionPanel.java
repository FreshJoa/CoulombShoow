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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import com.sun.prism.paint.Stop;

import coulomb3D.Window3D;
import javafx.application.Application;
import jdk.nashorn.internal.scripts.JS;

public class OptionPanel extends JPanel implements ActionListener {
	MainWindow mainWindow;

	JButton startButton;
	JButton stopButton;
	JButton addStaticCharges;
	JButton addTestCharge;
	JButton newSimulationButton;
	JButton drawEquipotential;
	JButton simulation3DButton;

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
	JPanel simulation3DPanel;

	public static final String addStaticChargeString = "add static charge";
	public static final String addTestChargeString = "add test charge";
	String whichCharge = "";

	boolean startSimulationBoolean = false;
	boolean drawEquipotentialBoolean = false;
	boolean simulation3DBoolean=false;
	
	public OptionPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

		startButton = new JButton(mainWindow.resourceBundle.getString("Start"));
		stopButton = new JButton(mainWindow.resourceBundle.getString("Stop"));
		addStaticCharges = new JButton(mainWindow.resourceBundle.getString("Dodaj_ladunek_statyczny"));
		addTestCharge = new JButton(mainWindow.resourceBundle.getString("Dodaj_ladunek_probny"));
		newSimulationButton = new JButton(mainWindow.resourceBundle.getString("Nowa_symulacja"));
		drawEquipotential = new JButton(mainWindow.resourceBundle.getString("Rysuj_linie_ekwipotencjalne"));
		simulation3DButton=new JButton("Symulacja_3D");

		startButton.setPreferredSize(new Dimension(290, 30));
		stopButton.setPreferredSize(new Dimension(290, 30));
		addStaticCharges.setPreferredSize(new Dimension(290, 30));
		addTestCharge.setPreferredSize(new Dimension(290, 30));
		newSimulationButton.setPreferredSize(new Dimension(290, 30));
		drawEquipotential.setPreferredSize(new Dimension(290, 30));
		simulation3DButton.setPreferredSize(new Dimension(290, 30));

		valueStaticCharge = new JSlider(JSlider.HORIZONTAL, -10, 10, 1);
		valueStaticCharge.setMajorTickSpacing(2);
		valueStaticCharge.setFocusable(true);
		valueStaticCharge.setPaintLabels(true);
		valueStaticCharge.setPaintTicks(true);
		valueStaticCharge.setBorder(
				BorderFactory.createTitledBorder(mainWindow.resourceBundle.getString("Wartosc_ladunku_statycznego")));
		valueStaticCharge.setPreferredSize(new Dimension(320, 70));

		speedTestCharge = new JSlider(-10, 10, 0);
		speedTestCharge.setMajorTickSpacing(2);
		speedTestCharge.setPaintLabels(true);
		speedTestCharge.setPaintTicks(true);
		speedTestCharge.setPreferredSize(new Dimension(320, 70));
		speedTestCharge.setBorder(BorderFactory
				.createTitledBorder(mainWindow.resourceBundle.getString("Predkosc_poczatkowa_ladunku_probnego")));

		startPanel = new JPanel();
		stopPanel = new JPanel();
		newSimulationPanel = new JPanel();
		speedTestChargePanel = new JPanel();
		valueStaticChargePanel = new JPanel();
		addStaticChargesPanel = new JPanel();
		addTestChargePanel = new JPanel();
		drawEquipotentialPanel = new JPanel();
		simulation3DPanel=new JPanel();

		startPanel.add(startButton);
		stopPanel.add(stopButton);
		newSimulationPanel.add(newSimulationButton);
		speedTestChargePanel.add(speedTestCharge);
		valueStaticChargePanel.add(valueStaticCharge);
		addStaticChargesPanel.add(addStaticCharges);
		addTestChargePanel.add(addTestCharge);
		drawEquipotentialPanel.add(drawEquipotential);
		simulation3DPanel.add(simulation3DButton);

		setLayout(new GridLayout(9, 1));
		add(valueStaticChargePanel);
		add(addStaticChargesPanel);
		add(speedTestChargePanel);
		add(addTestChargePanel);
		add(startPanel);
		add(stopPanel);
		add(newSimulationPanel);
		add(drawEquipotentialPanel);
		add(simulation3DPanel);
		setPreferredSize(new Dimension(390, mainWindow.HEIGHT));

		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		newSimulationButton.addActionListener(this);
		addStaticCharges.addActionListener(this);
		addTestCharge.addActionListener(this);
		drawEquipotential.addActionListener(this);
		simulation3DButton.addActionListener(this);

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
			if(startSimulationBoolean==true && mainWindow.simulationPanel.testCharge !=null) {
				mainWindow.chartsPanel.seriesVx.clear();
				mainWindow.chartsPanel.seriesVy.clear();
				mainWindow.chartsPanel.seriesVt.clear();
				
				mainWindow.chartsPanel.vList.clear();
				mainWindow.chartsPanel.vyList.clear();
				mainWindow.chartsPanel.vxList.clear();
				mainWindow.chartsPanel.tList.clear();
				
				mainWindow.movementClass.startSimulation();
				
				
			}
			if (startSimulationBoolean==false && mainWindow.simulationPanel.testCharge != null) {
				startSimulationBoolean = true;
				mainWindow.movementClass.startSimulation();
			} 
			if(mainWindow.simulationPanel.testCharge== null){
				JOptionPane.showMessageDialog(null, mainWindow.resourceBundle.getString("Dodaj_ladunek_probny_do_symulacji"),
						"Error",
						JOptionPane.WARNING_MESSAGE);
			}

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
				drawEquipotentialBoolean = false;
				mainWindow.chartsPanel.vList.clear();
				mainWindow.chartsPanel.vyList.clear();
				mainWindow.chartsPanel.vxList.clear();
				mainWindow.chartsPanel.tList.clear();

			}
			startSimulationBoolean = false;
			drawEquipotentialBoolean=false;
			mainWindow.simulationPanel.testCharge = null;
			mainWindow.simulationPanel.staticChargeList.clear();
			mainWindow.drawEquipotential.pixelList.clear();
			mainWindow.simulationPanel.repaint();

		}
		if (e.getSource() == addStaticCharges) {
			whichCharge = addStaticChargeString;
			System.out.println("add static charge");

		}
		if (e.getSource() == addTestCharge) {
			whichCharge = addTestChargeString;

		}
		if (e.getSource() == drawEquipotential) {
			drawEquipotentialBoolean = true;
			ExecutorService exec = Executors.newFixedThreadPool(1);
			exec.execute(mainWindow.drawEquipotential);

		}
		if (e.getSource() == simulation3DButton) {
		//	Window3D window3d=new Window3D(mainWindow);
			Window3D.main("[Ljava.lang.String;@f2a0b8e");
			simulation3DBoolean=true;
		//	window3d.launch(null);
			

		}

	}

}
