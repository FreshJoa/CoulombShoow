package coulomb3D;

import java.util.Collection;


import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Cam extends BorderPane {

	  Translate t  = new Translate();
      Translate p  = new Translate();
      Translate ip = new Translate();
      Rotate rx = new Rotate();
      { rx.setAxis(Rotate.X_AXIS); } //(setAxis) Określa oś X jako oś obrotu.
      Rotate ry = new Rotate();
      { ry.setAxis(Rotate.Y_AXIS); }
      Rotate rz = new Rotate();
      { rz.setAxis(Rotate.Z_AXIS); }
     
      Scale s = new Scale();
      public Cam() { 
      	super(); 
      	getTransforms().addAll(t, p, rx, rz, ry, s, ip); 
      	System.out.println("border pane ");
      	}
	
/*
	public Cam(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

	public Cam(Collection<Node> children) {
		super(children);
		// TODO Auto-generated constructor stub
	}*/
	

}
