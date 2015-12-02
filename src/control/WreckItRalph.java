package control;

import modelo.ModeloConfig;
import modelo.ModeloHighscore;
import modelo.ModeloJuego;
import utils.Modelo;
import utils.Vista;
import view.ViewAgregarJugador;
import view.ViewConfig;
import view.ViewHighscore;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;

public class WreckItRalph {
	private Modelo modelo;
	private Vista view;
	private Controlador controller;
	private static WreckItRalph wreckItRalph = null;

	private WreckItRalph() {
		
	}
	
	public static void main(String[] args) {
		getInstancia().crearMenu();
	}

	static WreckItRalph getInstancia() {
		if (wreckItRalph == null) {
			wreckItRalph = new WreckItRalph();
		}
		return wreckItRalph;
	}

	public void crearMenu() {
		view = new ViewMenu(modelo);
		controller = new ControladorMenu(null, (ViewMenu) view);
	}
	
	public void crearJuego() {
		modelo = new ModeloJuego();
		view = new ViewJuego(modelo);
		controller = new ControladorJuego((ModeloJuego) modelo, (ViewJuego) view);
	}
	
	public void crearConfig() {
		modelo = new ModeloConfig();
		view = new ViewConfig(modelo);
		controller = new ControladorConfig((ModeloConfig) modelo, (ViewConfig) view);
	}
	
	public void crearReglas() {
		modelo = null;
		view = new ViewReglas(modelo);
		controller = new ControladorReglas(modelo, (ViewReglas) view);
	}
	
	public void crearTop() {
		modelo = new ModeloHighscore();
		view = new ViewHighscore(modelo);
		controller = new ControladorHighscore((ModeloHighscore) modelo,
				(ViewHighscore) view);
	}
	
	public void crearAgregarJugador(Integer puntaje) {
		modelo = new ModeloHighscore();
		view = new ViewAgregarJugador(modelo);
		controller = new ControladorAgregarJugador((ModeloHighscore) modelo,
				(ViewAgregarJugador) view, puntaje);
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public Vista getView() {
		return view;
	}
	
	public Controlador getControlador() {
		return controller;
	}

}
