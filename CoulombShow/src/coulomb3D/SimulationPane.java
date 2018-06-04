package coulomb3D;



import java.util.Collection;
import java.util.Random;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.collections.ListChangeListener.Change;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

public class SimulationPane extends Pane  {
	Window3D window3d;
	double x = 0;
	double y = 0;
	double value = 0;
	int i=0;

	ObservableList<StaticCharge3D> staticCharge3Dlist =  FXCollections.observableArrayList();
	ObservableList<TestCharge3D> testCharge=FXCollections.observableArrayList();

	public SimulationPane(Window3D window3d)  {

		
		this.window3d = window3d;
	getTransforms().add(window3d.shear);
		setDepthTest(DepthTest.ENABLE);
		System.out.println("cerate simulationpane");
		

		testCharge.addListener(new ListChangeListener<TestCharge3D>() {

			@Override
			public void onChanged(Change<? extends TestCharge3D> change) {
				while (change.next()) {
                    for (TestCharge3D t : change.getAddedSubList()) {
                       testCharge.add(t);
                    }
                    for (TestCharge3D t : change.getRemoved()) {
                        testCharge.remove(t);
                     }
                }
				
				
			}
			
		});
		
		 
	 this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if(event.getClickCount()==1 && window3d.optionsPane.whichCharge==window3d.optionsPane.addStaticChargeString) {
				staticCharge3Dlist.add(new StaticCharge3D(event.getX(), event.getY(), window3d.optionsPane.valueStaticCharge.getValue()));
				
			}
			
		}
		 
	 });	
		 
		
		setScaleX(2.5);
		setScaleY(2.5);
		setScaleZ(2.5);
	}
	
	
	




}
