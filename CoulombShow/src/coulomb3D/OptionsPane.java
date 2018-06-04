package coulomb3D;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.StrokeBorder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OptionsPane extends GridPane {
	Window3D window3d;
	Button addStaticCharge;
	Button addTestCharge;
	Button startSimulation;
	Button stopSimulation;
	Label valueStaticChargeLabel;
	BorderPane valueStatichChargePane;
	
	boolean startBoolean=false;
	boolean stopBoolean=false;
	public static final String addStaticChargeString = "add static charge";
	public static final String addTestChargeString = "add test charge";
	String whichCharge = "";

	Slider valueStaticCharge;

	public OptionsPane(Window3D window) {
		this.window3d = window;
		this.setAlignment(Pos.CENTER);
		this.prefHeight(900);
		this.prefWidth(300);

		addStaticCharge = new Button("Dodaj_ladunek_statyczny");
		addTestCharge = new Button("Dodaj_ladunek_probny");
		startSimulation = new Button("Start");
		stopSimulation = new Button("Stop");
		valueStaticChargeLabel = new Label("Wartosc_ladunku_statycznego");
		valueStatichChargePane = new BorderPane();

		addStaticCharge.setPrefSize(200, 30);
		addTestCharge.setPrefSize(200, 30);
		stopSimulation.setPrefSize(200, 30);
		startSimulation.setPrefSize(200, 30);
		valueStaticChargeLabel.setPrefSize(300, 30);

		valueStaticCharge = new Slider(-10, 10, 1);
		valueStaticCharge.setPrefSize(200, 50);
		valueStaticCharge.setShowTickMarks(true);
		valueStaticCharge.setShowTickLabels(true);
		valueStaticCharge.setMajorTickUnit(2);

		valueStatichChargePane.setCenter(valueStaticChargeLabel);
		valueStatichChargePane.setBottom(valueStaticCharge);

		addStaticCharge.setOnAction(event);
		addTestCharge.setOnAction(event);
		startSimulation.setOnAction(event);
		stopSimulation.setOnAction(event);
		
		this.add(valueStatichChargePane, 1, 1);
		this.add(addStaticCharge, 1, 2);
		this.add(addTestCharge, 1, 3);
		this.add(startSimulation, 1, 4);
		this.add(stopSimulation, 1, 5);

		this.setVgap(30.0);
		this.setHgap(15);

	}
	
	EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			if(event.getSource()==addStaticCharge) {
				whichCharge=addStaticChargeString;
				System.out.println(whichCharge);
			}
			if(event.getSource()==addTestCharge) {
				whichCharge=addTestChargeString;
				System.out.println(whichCharge);
			}
			if(event.getSource()==startSimulation) {
				startBoolean=true;
				
				stopBoolean=false;
		//		window3d.movementClass3D.startSimulation();
			}
			if(event.getSource()==stopSimulation) {
				stopBoolean=true;
				
			}
			
		}
	};

	
}
