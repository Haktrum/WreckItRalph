package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import utils.Direccion;
import utils.Utils;
import utils.eventos.EventoSeccionGanada;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import view.MainWindow;
import view.ViewJuego;
import view.ViewMenu;
import juego.Modelo;

public class ControllerJuego extends Controller implements ActionListener {
	private MainWindow window;
	private boolean corriendo = false;
	private ViewJuego panelJuego = null;
	private Timer timer;

	public ControllerJuego(Modelo modelo, ViewJuego view) {
		super(modelo, view);
		addListeners();
		timer = new Timer(40, this);
		timer.start();
	}
	
	@Override
	public void addListeners() {
		KeyListener l = new MiKeyListener();
		((ViewJuego) getView()).addKeyListener(l); 
	}
	
	public void setCorriendo(boolean b) {
		corriendo = b;
	}

	public boolean isCorriendo() {
		return corriendo;
	}

	private void flechaIzq() {
		getModelo().moverFelix(Direccion.IZQUIERDA);
	}

	private void flechaDer() {
		getModelo().moverFelix(Direccion.DERECHA);
	}

	private void flechaArriba() {
		getModelo().moverFelix(Direccion.ARRIBA);
	}

	private void flechaAbajo() {
		getModelo().moverFelix(Direccion.ABAJO);
	}

	private void escape() {
		this.corriendo = false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			getModelo().actualizar();
		} catch (EventoSeccionGanada e) {
			panelJuego.incOffset();
		} catch (EventoNivelGanado e) {
			panelJuego.reset();
			window.setTitulo("Wreck It Ralph - Nivel " + Utils.nivelActual);
		} catch (EventoJuegoTerminado e) {
			window.setContentPane(new ViewMenu(getModelo()));
		}
	}

	private void space() {
		getModelo().martillar();
	}

	private class MiKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent arg0) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				flechaIzq();
				break;
			case KeyEvent.VK_RIGHT:
				flechaDer();
				break;
			case KeyEvent.VK_UP:
				flechaArriba();
				break;
			case KeyEvent.VK_DOWN:
				flechaAbajo();
				break;
			case KeyEvent.VK_ESCAPE:
				escape();
				break;
			case KeyEvent.VK_SPACE:
				space();
				break;
			}
		}
	}
}
