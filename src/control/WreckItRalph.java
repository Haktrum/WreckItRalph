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
import utils.Loader;
import view.View;
import view.ViewAgregar;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;

public class WreckItRalph {
	/** Modelo */
	private Modelo modelo;
	/** Vista */
	private View view;
	/** Controlador */
	private Controller controller;
	/** Instancia de clase */
	private static WreckItRalph wreckItRalph = WreckItRalph.getInstancia();
	

	public static void main(String[] args) {
		wreckItRalph.crearMenu();
	}
	
	private WreckItRalph(){
	}
	public static WreckItRalph getInstancia() {
		if (wreckItRalph == null) {
			wreckItRalph = new WreckItRalph();
		}
		return wreckItRalph;
	}
	/**
	 * Ir al menu principal
	 */
	public void crearMenu() {
		Loader.playBgSonido("juego/menu_bg.wav").start();
		modelo = ModeloJuego.getInstancia();
		view = new ViewMenu((ModeloJuego) modelo);
		controller = new ControllerMenu((ModeloJuego) modelo, (ViewMenu) view);
	}
	/**
	 * Ir al juego
	 */
	public void crearJuego() {
		Loader.playBgSonido("juego/juego_bg.wav").start();
		modelo = ModeloJuego.getInstancia();
		view = new ViewJuego((ModeloJuego) modelo);
		controller = new ControllerJuego((ModeloJuego) modelo, (ViewJuego) view);
	}
	/**
	 * Ir a la configuraci&oacute;n de nivel
	 */
	public void crearConfig() {
		modelo = ModeloJuego.getInstancia();
		view = new ViewConfig((ModeloJuego) modelo);
		controller = new ControllerConfig((ModeloJuego) modelo, (ViewConfig) view);
	}
	/**
	 * Ir a reglas
	 */
	public void crearReglas() {
		view = new ViewReglas((ModeloJuego) modelo);
		controller = new ControllerReglas((ModeloJuego) modelo, (ViewReglas) view);
	}
	/**
	 * Ir a los highscores
	 */
	public void crearTop() {
		modelo = ModeloHighscore.getInstancia();
		view = new ViewTop((ModeloHighscore) modelo);
		controller = new ControllerTop((ModeloHighscore) modelo, (ViewTop) view);
	}
	/**
	 * Ir a agregar jugador
	 * @param puntos
	 */
	public void crearAgregarJugador(int puntos){
		Loader.playBgSonido("juego/menu_bg.wav");
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
