package controller;

import juego.Modelo;
import view.ViewTop;

public class ControllerTop extends Controller {

	public ControllerTop(Modelo modelo, ViewTop view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void addListeners() {
		ViewTop viewTop = (ViewTop) getView();
		
		viewTop.addKeyListener(new BackKeyListener());
	}

}
