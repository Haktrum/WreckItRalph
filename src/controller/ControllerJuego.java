package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import control.WreckItRalph;
import utils.Direccion;
import utils.Utils;
import utils.eventos.EventoSeccionGanada;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import view.MainWindow;
import view.ViewJuego;
import juego.Modelo;

public class ControllerJuego extends Controller implements ActionListener {
	private boolean corriendo = false;
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
		getView().addKeyListener(l); 
	}
	
	public void setCorriendo(boolean b) {
		corriendo = b;
	}

	public boolean isCorriendo() {
		return corriendo;
	}

	private void moverFelix(Direccion d) {
		getModelo().moverFelix(d);
	}
	
	private void escape() {
		this.corriendo = false;
	}

	private void space() {
		getModelo().martillar();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		ViewJuego viewJuego = (ViewJuego) getView();
		try {
			getModelo().actualizar();
			viewJuego.actualizarVista();
		} catch (EventoSeccionGanada e) {
			viewJuego.incOffset();
		} catch (EventoNivelGanado e) {
			viewJuego.reset();
			MainWindow.getInstancia().setTitulo("Wreck It Ralph - Nivel " + Utils.nivelActual);
		} catch (EventoJuegoTerminado e) {
			WreckItRalph.getInstancia().crearMenu();
		}
	}

	private class MiKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent arg0) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				moverFelix(Direccion.IZQUIERDA);
				break;
			case KeyEvent.VK_RIGHT:
				moverFelix(Direccion.DERECHA);
				break;
			case KeyEvent.VK_UP:
				moverFelix(Direccion.ARRIBA);
				break;
			case KeyEvent.VK_DOWN:
				moverFelix(Direccion.ABAJO);
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
