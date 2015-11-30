package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import modelos.Modelo;

import control.WreckItRalph;
import view.MainWindow;
import view.View;

public abstract class Controller {
	private final View view;
	private final Modelo modelo;

	public Controller(Modelo modelo, View view) {
		this.modelo = modelo;
		this.view = view;
		MainWindow.getInstancia().setContentPane(view);
	}
	
	public abstract void addListeners();
	
	protected View getView() {
		return view;
	}
	
	protected Modelo getModelo() {
		return modelo;
	}
	
	protected class BackKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_ENTER:
				WreckItRalph.getInstancia().crearMenu();
				break;
			}
		}
	}
}
