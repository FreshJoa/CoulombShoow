package coulomb;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpFrame extends JFrame {
	MainWindow mainWindow;
	JTextArea areaText;
	

	public HelpFrame(MainWindow mainWindow) throws HeadlessException {
		this.mainWindow = mainWindow;

		JPanel helpPanel = new JPanel();
		areaText = new JTextArea(40, 30);
		JScrollPane scrollPaneForLog = new JScrollPane(areaText);
		this.add(scrollPaneForLog);
		this.add(areaText);
		setText();
		setPreferredSize(new Dimension(400, 300));
		setSize(400, 300);
		setTitle(mainWindow.resourceBundle.getString("Pomoc"));
		setLocationRelativeTo(null);
		setVisible(true);

	}

	void setText() {
		areaText.append(mainWindow.resourceBundle.getString("Pomoc_opis"));
	}

	
	

}
