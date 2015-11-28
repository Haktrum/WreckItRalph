package controller;

import modelos.ModeloHighscore;
import modelos.ModeloJuego;
import view.ViewTop;

public class ControllerTop extends Controller {

	public ControllerTop(ModeloHighscore modelo, ViewTop view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void addListeners() {
		ViewTop viewTop = (ViewTop) getView();
		
		viewTop.addKeyListener(new BackKeyListener());
	}

}
