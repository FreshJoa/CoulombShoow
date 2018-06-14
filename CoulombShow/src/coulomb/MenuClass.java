package coulomb;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	JMenuItem saveToExcel;

	JMenu helpMenu;
	JMenuItem helpItem;

	public MenuClass(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

		saveMenu = new JMenu(mainWindow.resourceBundle.getString("Zapisz"));
		saveDrawingEquipotential = new JMenuItem(mainWindow.resourceBundle.getString("Zapisz_linie_ekwipotencjalne"));
		saveCharts = new JMenuItem(mainWindow.resourceBundle.getString("Zapisz_wykresy"));
		saveToExcel = new JMenuItem(mainWindow.resourceBundle.getString("Zapisz_dane_do_exela"));

		helpMenu = new JMenu(mainWindow.resourceBundle.getString("Pomoc"));
		helpItem = new JMenuItem(mainWindow.resourceBundle.getString("Pomoc"));

		saveDrawingEquipotential.addActionListener(this);
		saveCharts.addActionListener(this);
		helpItem.addActionListener(this);
		saveToExcel.addActionListener(this);

		saveMenu.add(saveDrawingEquipotential);
		saveMenu.add(saveCharts);
		saveMenu.add(saveToExcel);
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
			HelpFrame help = new HelpFrame(mainWindow);

		}
		if (object == saveToExcel) {
			if(mainWindow.chartsPanel.tList.isEmpty()) {
				JOptionPane.showMessageDialog(null, mainWindow.resourceBundle.getString("Uruchom_symulacje"),
						"Error",
						JOptionPane.WARNING_MESSAGE);
			}
			else {
			try {
				saveDataToExcel();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}

		}

	}

	void saveDrawingEquipotential() {
		if (mainWindow.optionPanel.drawEquipotentialBoolean == true && mainWindow.drawEquipotential.pixelList != null) {
			JFileChooser chooser = new JFileChooser();

			int returnVal = chooser.showDialog(null, mainWindow.resourceBundle.getString("Wybierz"));
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

		} else {
			JOptionPane.showMessageDialog(null, mainWindow.resourceBundle.getString("Nie_narysowano_linii"),
					mainWindow.resourceBundle.getString("Narysuj_linie__ekwipotencjalne"), JOptionPane.WARNING_MESSAGE);
		}
	}

	void saveCharts() {
		if (mainWindow.optionPanel.startSimulationBoolean == true) {
			JFileChooser chooser = new JFileChooser();

			int returnVal = chooser.showDialog(null, mainWindow.resourceBundle.getString("Wybierz"));
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				PDFDocument pdfDoc = new PDFDocument();
				pdfDoc.setTitle(mainWindow.resourceBundle.getString("Wykresy"));

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

		} else {
			JOptionPane.showMessageDialog(null, mainWindow.resourceBundle.getString("Brak_wykresów"),
					mainWindow.resourceBundle.getString("Uruchom_symulacje"), JOptionPane.WARNING_MESSAGE);
		}

	}

	void saveDataToExcel() throws IOException {
		//Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook();
	      
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet(mainWindow.resourceBundle.getString("Zapisz_dane_do_exela"));

	      //Create row object
	      XSSFRow row;

		
		if (mainWindow.optionPanel.startSimulationBoolean == true) {
			
		    Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
		    empinfo.put( "1", new Object[] {
		    	       "t", "Vx", "Vy", "V"  });
		    for(int i=0; i<mainWindow.chartsPanel.tList.size(); i++) {
		    	
		    
		    empinfo.put( Integer.toString(i+10), new Object[] {
		    		String.format("%.1f",  mainWindow.chartsPanel.tList.get(i)),  String.format( "%.3f", mainWindow.chartsPanel.vxList.get(i)),  String.format( "%.3f", mainWindow.chartsPanel.vyList.get(i)),   String.format( "%.3f", mainWindow.chartsPanel.vList.get(i)) });
		  System.out.println(
			       mainWindow.chartsPanel.tList.get(i)+" "+mainWindow.chartsPanel.vxList.get(i)+" "+ mainWindow.chartsPanel.vyList.get(i)+" "+ mainWindow.chartsPanel.vList.get(i));
		    }
		    //Iterate over data and write to sheet
		    Set < String > keyid = empinfo.keySet();
		      int rowid = 0;
		      System.out.println(keyid.size());
		    
		    for (String key : keyid) {
		         row = spreadsheet.createRow(rowid++);
		         Object [] objectArr = empinfo.get(key);
		         int cellid = 0;
		         
		         for (Object obj : objectArr){
		            Cell cell = row.createCell(cellid++);
		            cell.setCellValue((String)obj);
		         }
		    }
		    String fileDictName = "";

		    File file;

		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Open the file"); //name for chooser
		        FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
		        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
		        fileChooser.addChoosableFileFilter(filter);
		        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
		        fileChooser.setVisible(true);
		        int result = fileChooser.showOpenDialog(fileChooser);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
		            if(!fileDictName.endsWith(".xlsx") ) {
		                file = new File(fileDictName + ".xlsx");
		            }
		            else {

		         file = new File(fileDictName);
		            }
		        
	                    //Write the workbook in file system
	                    FileOutputStream out = new FileOutputStream(file);
	                workbook.write(out);
	            
	        
	            // Sheet already exists
	            System.out.println("File already exist");
	        }
		}
		
	}

}
