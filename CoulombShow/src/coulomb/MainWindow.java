package coulomb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {
	OptionPanel optionPanel;
	SimulationPanel simulationPanel;
	MauseListener mauseListener;
	MovementClass movementClass;
	ChartsPanel chartsPanel;
	JPanel firstPanel; // panel z simulationPanel i OptionsPanel( JTabbedPane)
	JTabbedPane tabbedPane;

	public MainWindow() throws HeadlessException {
		optionPanel=new OptionPanel(this);
		simulationPanel=new SimulationPanel(this);
		mauseListener=new MauseListener(this, simulationPanel);
		movementClass=new MovementClass(this);
		chartsPanel=new ChartsPanel(this);
		firstPanel=new JPanel();
		tabbedPane=new JTabbedPane();
		
		
		
		firstPanel.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
		firstPanel.setLayout(new BorderLayout());
		firstPanel.add(optionPanel, BorderLayout.WEST);
		firstPanel.add(simulationPanel, BorderLayout.CENTER);
		
		tabbedPane.addTab("Symulacja", firstPanel);
		tabbedPane.addTab("Wykresy", chartsPanel);
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
