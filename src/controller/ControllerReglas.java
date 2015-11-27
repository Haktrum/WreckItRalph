package controller;

import juego.Modelo;
import view.ViewReglas;

public class ControllerReglas extends Controller {

	public ControllerReglas(Modelo modelo, ViewReglas view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void addListeners() {
		ViewReglas viewReglas = (ViewReglas) getView();
		
		viewReglas.addKeyListener(new BackKeyListener());
	}

}
