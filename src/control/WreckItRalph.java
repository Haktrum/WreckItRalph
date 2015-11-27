package control;

import controller.Controller;
import controller.ControllerConfig;
import controller.ControllerJuego;
import controller.ControllerMenu;
import controller.ControllerReglas;
import controller.ControllerTop;
import juego.Modelo;
import view.View;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;

public class WreckItRalph {
	private Modelo modelo = Modelo.getInstancia();
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
		view = new ViewMenu(modelo);
		controller = new ControllerMenu(modelo, (ViewMenu) view);
	}
	
	public void crearJuego() {
		view = new ViewJuego(modelo);
		controller = new ControllerJuego(modelo, (ViewJuego) view);
	}
	
	public void crearConfig() {
		view = new ViewConfig(modelo);
		controller = new ControllerConfig(modelo, (ViewConfig) view);
	}
	
	public void crearReglas() {
		view = new ViewReglas(modelo);
		controller = new ControllerReglas(modelo, (ViewReglas) view);
	}
	
	public void crearTop() {
		view = new ViewTop(modelo);
		controller = new ControllerTop(modelo, (ViewTop) view);
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
