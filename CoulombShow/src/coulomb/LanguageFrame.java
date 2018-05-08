package coulomb;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LanguageFrame extends JFrame {

	MainWindow window;//referencja do głównej klasy
	JPanel labelPanel = new JPanel();
	JLabel labelChoose = new JLabel("Choose the prefer language");
	JPanel languageMenuPanel = new JPanel();
	String[] language = { "Polish", "English", "German", };
	JComboBox comboBox = new JComboBox(language);
	JButton choose = new JButton("Choose");
	int languageValue = 0;
	ResourceBundle resource = null;

	ItemListener ChooseLanguageValue = new ItemListener() {//wybór języka

		@Override
		public void itemStateChanged(ItemEvent e) {//Wywoływane, gdy element został zaznaczony lub odznaczony przez użytkownika.
			System.out.println(e.getItem());
			if (e.getItem() == "Polish" ) {
				languageValue = 0;
				System.out.println("pol");
			} 
			else if (e.getItem() == "English") {
				languageValue = 1;
				System.out.println("ang");
			} 
			else
			{
				languageValue = 2;
				System.out.println("germ");
			}
		}
	};
	ResourceBundle resourceBundle() {
	
		
	
		
		if(languageValue==0) {
			resource=ResourceBundle.getBundle("LabelsBundle", Locale.FRENCH);
		}
		if(languageValue==1) {
			resource=ResourceBundle.getBundle("LabelsBundle", Locale.ENGLISH);
		}
		if(languageValue==2) {
			resource=ResourceBundle.getBundle("LabelsBundle", Locale.GERMAN);
		}
		
		
		return resource;
	}
	

	ActionListener ChooseLanguage = new ActionListener() {//otworzenie głównego okna z odpowiednim językiem

		@Override
		public void actionPerformed(ActionEvent e) {
			resource=resourceBundle();
			window = new MainWindow(resource);
			setVisible(false);
			
		}

	};

	
	
	
	public LanguageFrame(MainWindow WINDOW) throws HeadlessException {

		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(labelPanel, BorderLayout.PAGE_START);
		add(languageMenuPanel, BorderLayout.CENTER);
		labelPanel.setBackground(Color.WHITE);
		languageMenuPanel.setBackground(Color.WHITE);
		labelPanel.add(labelChoose);
		languageMenuPanel.add(comboBox);
		languageMenuPanel.add(choose);

		comboBox.addItemListener(ChooseLanguageValue);
		choose.addActionListener(ChooseLanguage);//otwiera główne okno
		

		window=WINDOW;	

		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Coulomb Show");
	}



	
	

	public LanguageFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	

	public LanguageFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
