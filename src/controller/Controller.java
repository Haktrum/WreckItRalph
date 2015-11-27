package controller;

import juego.Modelo;
import view.View;

public abstract class Controller {
	private final View view;
	private final Modelo modelo;

	public Controller(Modelo modelo, View view) {
		this.modelo = modelo;
		this.view = view;
	}
	
	protected View getView() {
		return view;
	}
	
	protected Modelo getModelo() {
		return modelo;
	}
}
