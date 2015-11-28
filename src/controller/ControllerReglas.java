package controller;

import modelos.ModeloJuego;
import view.ViewReglas;

public class ControllerReglas extends Controller {

	public ControllerReglas(ModeloJuego modelo, ViewReglas view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void addListeners() {
		ViewReglas viewReglas = (ViewReglas) getView();
		
		viewReglas.addKeyListener(new BackKeyListener());
	}

}
