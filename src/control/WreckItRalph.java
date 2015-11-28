package control;

import modelos.Modelo;
import modelos.ModeloHighscore;
import modelos.ModeloJuego;
import controller.Controller;
import controller.ControllerAgregar;
import controller.ControllerConfig;
import controller.ControllerJuego;
import controller.ControllerMenu;
import controller.ControllerReglas;
import controller.ControllerTop;
import view.View;
import view.ViewAgregar;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;

public class WreckItRalph {
	private Modelo modelo;
	private View view;
	private Controller controller;
	private static WreckItRalph wreckItRalph = WreckItRalph.getInstancia();

	public static void main(String[] args) {
		wreckItRalph.crearMenu();
	}
	
	public static WreckItRalph getInstancia() {
		if (wreckItRalph == null) {
			wreckItRalph = new WreckItRalph();
		}
		return wreckItRalph;
	}
	
	public void crearMenu() {
		modelo = ModeloJuego.getInstancia();
		view = new ViewMenu((ModeloJuego) modelo);
		controller = new ControllerMenu((ModeloJuego) modelo, (ViewMenu) view);
	}
	
	public void crearJuego() {
		modelo = ModeloJuego.getInstancia();
		view = new ViewJuego((ModeloJuego) modelo);
		controller = new ControllerJuego((ModeloJuego) modelo, (ViewJuego) view);
	}
	
	public void crearConfig() {
		modelo = ModeloJuego.getInstancia();
		view = new ViewConfig((ModeloJuego) modelo);
		controller = new ControllerConfig((ModeloJuego) modelo, (ViewConfig) view);
	}
	
	public void crearReglas() {
		view = new ViewReglas((ModeloJuego) modelo);
		controller = new ControllerReglas((ModeloJuego) modelo, (ViewReglas) view);
	}
	
	public void crearTop() {
		modelo = ModeloHighscore.getInstancia();
		view = new ViewTop((ModeloHighscore) modelo);
		controller = new ControllerTop((ModeloHighscore) modelo, (ViewTop) view);
	}
	public void crearAgregarJugador(int puntos){
		modelo = ModeloHighscore.getInstancia();
		view = new ViewAgregar((ModeloHighscore) modelo);
		controller = new ControllerAgregar((ModeloHighscore) modelo,(ViewAgregar) view,puntos);
	}

	public Modelo getModelo() {
		return modelo;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
