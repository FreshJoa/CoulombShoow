package coulomb3D;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import coulomb.MainWindow;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Window3D extends Application {
	MainWindow mainWindow;
	SimulationPane simulationPane;
	OptionsPane optionsPane;
	MovementClass3D movementClass3D;

	BorderPane borderPane;
	final Cam camOffset = new Cam();
	final Cam cam = new Cam();
	double mousePosX;
	double mousePosY;
	double mouseOldX;
	double mouseOldY;
	double mouseDeltaX;
	double mouseDeltaY;
	Scene scene;

	final Shear shear = new Shear();

	public Window3D() {
		borderPane = new BorderPane();
	simulationPane=new SimulationPane(this);
		optionsPane = new OptionsPane(this);
		//movementClass3D=new MovementClass3D(this);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Coulomb Show 3D");

		Group group = new Group();

		camOffset.getChildren().add(cam);
		resetCam();
		cam.getChildren().add(simulationPane);

		camOffset.prefHeight(900);
		camOffset.prefWidth(800);
		group.getChildren().add(camOffset);
		group.getChildren().add(optionsPane);
		// borderPane.setCenter(camOffset);
		// borderPane.setLeft(optionsPane);

		scene = new Scene(group, 1300, 900, true);
		scene.setFill(new RadialGradient(225, 0.85, 300, 300, 500, false, // (setFill) Ustawia wartość wypełnienia.
				CycleMethod.NO_CYCLE, new Stop[]// RadialGradient -sposób wypełnienia kształt o kołowym promienistym
												// gradient kolorów
				{ new Stop(0, Color.CRIMSON), // RadialGradient(double focusAngle, double focusDistance, double
						new Stop(1, Color.BLUEVIOLET) })); // centerX, double centerY, double radius, boolean
															// proportional,
		// CycleMethod cycleMethod, Stop... stops)
		// CycleMethod -oznacza jedną z następujących metod wykorzystania podczas
		// malowania poza granice gradientu
		// Definiuje jeden element rampy kolorów do użycia na
		// gradiencie
		scene.setCamera(new PerspectiveCamera());
//
		final SwingNode swingNode = new SwingNode();// Ta klasa służy do osadzania treści Swing w aplikacji JavaFX

		// createSwingContent(swingNode);

		// StackPane pane = new StackPane();
		// pane.getChildren().add(swingNode);

		// camOffset.getChildren().add(pane);
		

		frameCam( scene);

		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mouseOldX = mousePosX;
				mouseOldY = mousePosY;
				mousePosX = me.getX();
				mousePosY = me.getY();
				mouseDeltaX = mousePosX - mouseOldX;
				mouseDeltaY = mousePosY - mouseOldY;
				// obrót w okół osi x (ctrl+Alt+primaryButtonDown - we wszystkie strony)
				if (me.isAltDown() && me.isShiftDown() && me.isPrimaryButtonDown()) {
					double rzAngle = cam.rz.getAngle();
					cam.rz.setAngle(rzAngle - mouseDeltaX);
				}
				// przenoszenie okna
				else if (me.isAltDown() && me.isPrimaryButtonDown()) {
					double ryAngle = cam.ry.getAngle();
					cam.ry.setAngle(ryAngle - mouseDeltaX);
					double rxAngle = cam.rx.getAngle();
					cam.rx.setAngle(rxAngle + mouseDeltaY);
				}
				// Ścinanie
				else if (me.isShiftDown() && me.isPrimaryButtonDown()) {
					double yShear = shear.getY();
					shear.setY(yShear + mouseDeltaY / 1000.0);
					double xShear = shear.getX();
					shear.setX(xShear + mouseDeltaX / 1000.0);
				}
				// Skalowanie
				else if (me.isAltDown() && me.isSecondaryButtonDown()) {
					double scale = cam.s.getX();
					double newScale = scale + mouseDeltaX * 0.01;
					cam.s.setX(newScale);
					cam.s.setY(newScale);
					cam.s.setZ(newScale);
				} else if (me.isAltDown() && me.isMiddleButtonDown()) {
					double tx = cam.t.getX();
					double ty = cam.t.getY();
					cam.t.setX(tx + mouseDeltaX);
					cam.t.setY(ty + mouseDeltaY);
				}

			}
		});
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (KeyCode.A.equals(ke.getCode())) {
					resetCam();
					shear.setX(0.0);
					shear.setY(0.0);
				}
				if (KeyCode.F.equals(ke.getCode())) {
					frameCam( scene);
					shear.setX(0.0);
					shear.setY(0.0);
				}
				if (KeyCode.SPACE.equals(ke.getCode())) {
					if (primaryStage.isFullScreen()) {
						primaryStage.setFullScreen(false);
						frameCam( scene);
					} else {
						primaryStage.setFullScreen(true);
						frameCam( scene);
					}
				}
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String string) {
		System.out.println(string);

		Application.launch(string);
	}

	public void frameCam( final Scene scene) {//ustawienia początkowe 
		setCamOffset(camOffset, scene);
		// cam.resetTSP();
		setCamPivot(cam);
		setCamTranslate(cam);
		setCamScale(cam, scene);
	}

	public void setCamOffset(final Cam camOffset, final Scene scene) { // ustawia na środku sceny elementy camOffset
		double width = scene.getWidth();
		double height = scene.getHeight();
		camOffset.t.setX(width / 2.0);
		camOffset.t.setY(height / 2.0);
	}

	public void setCamScale(final Cam cam, final Scene scene) {// ustawia skalę względem wymiarów sceny a cam.getBoundsInLocal
		final Bounds bounds = cam.getBoundsInLocal();
		final double pivotX = bounds.getMinX() + bounds.getWidth() / 2;
		final double pivotY = bounds.getMinY() + bounds.getHeight() / 2;
		final double pivotZ = bounds.getMinZ() + bounds.getDepth() / 2;

		double width = scene.getWidth();
		double height = scene.getHeight();

		double scaleFactor = 0;
		double scaleFactorY = 0;
		double scaleFactorX = 0;
		if (bounds.getWidth() > 0.0001) {
			scaleFactorX = width / bounds.getWidth(); // / 2.0;
			System.out.println("wigtd scene "+width+" "+bounds.getWidth()+" bunds wigth");
		}
		if (bounds.getHeight() > 0.0001) {
			scaleFactorY = height / bounds.getHeight(); // / 1.5;
		}
		if (scaleFactorX > scaleFactorY) {
			scaleFactor = scaleFactorY;
		} else {
			scaleFactor = scaleFactorX;
		}
		cam.s.setX(scaleFactor);
		cam.s.setY(scaleFactor);
		cam.s.setZ(scaleFactor);
	}

	public void setCamPivot(final Cam cam) { // ustawia oś obrotu
		// Bounds -używane do opisania granic węzła lub innego obiektu wykresu sceny.
		// Jedną z interesujących cech obiektu Bounds jest to, że może mieć ujemną
		// szerokość, wysokość lub głębokość. Wartość ujemna dowolnego z nich oznacza,
		// że ​​wartości graniczne są "puste".

		final Bounds bounds = cam.getBoundsInLocal();// Prostokątne granice tego w nietransformowanej lokalnej
														// przestrzeni współrzędnych węzła.
		final double pivotX = bounds.getMinX() + bounds.getWidth() / 2;//współrzędną X punktu obrotu obrotu.
		final double pivotY = bounds.getMinY() + bounds.getHeight() / 2;
		final double pivotZ = bounds.getMinZ() + bounds.getDepth() / 2;
		cam.p.setX(pivotX);
		cam.p.setY(pivotY);
		cam.p.setZ(pivotZ);
		cam.ip.setX(-pivotX);
		cam.ip.setY(-pivotY);
		cam.ip.setZ(-pivotZ);
	}

	public void setCamTranslate(final Cam cam) {
		final Bounds bounds = cam.getBoundsInLocal();
		final double pivotX = bounds.getMinX() + bounds.getWidth() / 2;
		final double pivotY = bounds.getMinY() + bounds.getHeight() / 2;
		System.out.println(pivotX+" "+pivotY+" pivot");
		cam.t.setX(-pivotX);//Określa odległość, o jaką współrzędne są przekształcane w kierunku osi X.
		cam.t.setY(-pivotY);
		System.out.println(cam.t.getX());
		System.out.println(bounds.getMinX());
		System.out.println(bounds.getWidth());
	}

	public void resetCam() {
		cam.t.setX(0.0);
		cam.t.setY(0.0);
		cam.t.setZ(0.0);
		cam.rx.setAngle(45.0);// kat obrotu w stopnich
		cam.ry.setAngle(20.0);
		cam.rz.setAngle(10.0);
		cam.s.setX(1.25);
		cam.s.setY(1.25);
		cam.s.setZ(1.25);

		/*cam.p.setX(0.0);
		cam.p.setY(0.0);
		cam.p.setZ(0.0);

		cam.ip.setX(0.0);
		cam.ip.setY(0.0);
		cam.ip.setZ(0.0);*/

	}

	public static void main(String[] args) {
        launch(args);
    }

}
