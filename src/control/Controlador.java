package control;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import utils.Modelo;
import utils.Vista;
import view.MainWindow;

public abstract class Controlador implements ActionListener {
	private final Vista view;
	private final Modelo modelo;
	
	public Controlador(Modelo modelo, Vista view) {
		this.modelo = modelo;
		this.view = view;
		MainWindow.getInstancia().setContentPane(view);
	}
	
	public abstract void addListeners();

	protected Vista getView() {
		return view;
	}
	
	protected Modelo getModelo() {
		return modelo;
	}

	class BackKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				WreckItRalph.getInstancia().crearMenu();
				break;
			}
		}
	}
}
