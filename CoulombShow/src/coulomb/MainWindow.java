package coulomb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.sound.midi.Soundbank;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {
	OptionPanel optionPanel;
	SimulationPanel simulationPanel;
	MauseListener mauseListener;
	MovementClass movementClass;
	ChartsPanel chartsPanel;
	DrawEquipotential drawEquipotential;
	MenuClass menuClass;
	
	
	JPanel firstPanel; // panel z simulationPanel i OptionsPanel( JTabbedPane)
	JTabbedPane tabbedPane;
	ResourceBundle resourceBundle;
	

	public MainWindow(ResourceBundle reBundle) throws HeadlessException {
		this.resourceBundle=reBundle;
		optionPanel=new OptionPanel(this);
		simulationPanel=new SimulationPanel(this);
		mauseListener=new MauseListener(this, simulationPanel);
		movementClass=new MovementClass(this);
		chartsPanel=new ChartsPanel(this);
		drawEquipotential=new DrawEquipotential(this);
		menuClass=new MenuClass(this);
		
		
		this.setJMenuBar(menuClass);
		
		firstPanel=new JPanel();
		tabbedPane=new JTabbedPane();
		
		firstPanel.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
		firstPanel.setLayout(new BorderLayout());
		firstPanel.add(optionPanel, BorderLayout.WEST);
		firstPanel.add(simulationPanel, BorderLayout.CENTER);
		
		//tabbedPane.addTab("Symulacja", firstPanel);
		//tabbedPane.addTab("Wykresy", chartsPanel);
		tabbedPane.addTab(resourceBundle.getString("Symulacja"), firstPanel);
		tabbedPane.addTab(resourceBundle.getString("Wykresy"), chartsPanel);
		this.add(tabbedPane);
		
		setSize(1200, 900);
		this.setLocationRelativeTo(null);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		MainWindow mainFrame = null;
		@SuppressWarnings("unused")
		LanguageFrame startFrame = new LanguageFrame(mainFrame);

	}

	
	
	

}
