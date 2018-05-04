package coulomb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	OptionPanel optionPanel;
	SimulationPanel simulationPanel;
	MauseListener mauseListener;
	MovementClass movementClass;

	public MainWindow() throws HeadlessException {
		optionPanel=new OptionPanel(this);
		simulationPanel=new SimulationPanel(this);
		mauseListener=new MauseListener(this, simulationPanel);
		movementClass=new MovementClass(this);
		
		setLayout(new BorderLayout());
		add(optionPanel, BorderLayout.WEST);
		add(simulationPanel, BorderLayout.CENTER);
		
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
