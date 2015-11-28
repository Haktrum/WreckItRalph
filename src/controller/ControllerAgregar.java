package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import control.WreckItRalph;

import modelos.ModeloHighscore;
import modelos.ModeloJuego;

import utils.exceptions.BadInput;
import utils.exceptions.NombreConEspacios;
import utils.exceptions.NombreCorto;
import utils.exceptions.NombreYaExiste;
import view.View;
import view.ViewAgregar;

public class ControllerAgregar extends Controller{
	
	private String error = "";
	private ViewAgregar view = (ViewAgregar) super.getView();
	private ModeloHighscore modelo = (ModeloHighscore) super.getModelo();
	private final int puntaje;
	
	public ControllerAgregar(ModeloHighscore modelo, ViewAgregar view,int puntos) {
		super(modelo, view);
		addListeners();
		this.puntaje = puntos;
	}

	@Override
	public void addListeners() {
		view.getInput().addKeyListener(new MiKey());
	}
	private class MiKey extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			view.setError("");
			switch(arg0.getKeyCode()){
				case KeyEvent.VK_ENTER:
					try {
						modelo.submit(view.getInputText().toUpperCase(),puntaje);
						WreckItRalph.getInstancia().crearTop();
					}catch (BadInput e) {
						view.setError(e.getMessage());
					}
					break;
				default:
					modelo.noSobreescribir();
					break;
			}
		}
	}
	public String getMsj(){
		return error;
	}
}