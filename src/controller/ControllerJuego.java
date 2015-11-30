package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import modelos.ModeloJuego;

import control.Highscore;
import control.WreckItRalph;
import utils.Direccion;
import utils.Utils;
import utils.eventos.EventoOffScreen;
import utils.eventos.EventoRalphSalta;
import utils.eventos.EventoSeccionGanada;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import view.MainWindow;
import view.ViewJuego;

public class ControllerJuego extends Controller implements ActionListener{
	private boolean corriendo = false;
	private Timer timerModelo;
	private Timer timerView;

	
	public ControllerJuego(ModeloJuego modelo, ViewJuego view) {
		super(modelo, view);
		addListeners();
		timerModelo = new Timer(40, this);
		timerView = new Timer(40, view);
		timerView.start();
//		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(){
//			@Override
//			public void uncaughtException(Thread arg0, Throwable arg1) {
//				excepciones((Evento) arg1);
//			}
//		});
		timerModelo.start();
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
		((ModeloJuego) getModelo()).moverFelix(d);
	}
	
	private void terminarJuego() {
		timerModelo.stop();
		timerView.stop();
		Highscore h = new Highscore();
		int puntos = ModeloJuego.getInstancia().getPuntaje();
		if(h.hayLugar(puntos) && puntos>0)
			WreckItRalph.getInstancia().crearAgregarJugador(puntos);
		else
			WreckItRalph.getInstancia().crearMenu();
	}

	private void space() {
		((ModeloJuego) getModelo()).martillar();
	}

//	private void excepciones(Evento ex) {
//		ViewJuego viewJuego = (ViewJuego) getView();
//		try {
//			throw ex;
//		} catch (EventoSeccionGanada e) {
//			viewJuego.incOffset();
//		} catch (EventoNivelGanado e) {
//			viewJuego.reset();
//			MainWindow.getInstancia().setTitulo("Wreck It Ralph - Nivel " + Utils.nivelActual);
//		} catch (EventoJuegoTerminado e) {
//			this.terminarJuego();
//		} catch (EventoOffScreen e) {
//			e.printStackTrace();
//		} catch (EventoRalphSalta e) {
//			e.printStackTrace();
//		}
//	}

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
				terminarJuego();
				break;
			case KeyEvent.VK_SPACE:
				space();
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ViewJuego viewJuego = (ViewJuego) getView();
		try {
			((ModeloJuego) getModelo()).actualizar();
		} catch (EventoSeccionGanada e) {
			viewJuego.incOffset();
		} catch (EventoNivelGanado e) {
			viewJuego.reset();
			MainWindow.getInstancia().setTitulo("Wreck It Ralph - Nivel " + Utils.nivelActual);
		} catch (EventoJuegoTerminado e) {
			this.terminarJuego();
		} catch (EventoOffScreen e) {
			e.printStackTrace();
		} catch (EventoRalphSalta e) {
			e.printStackTrace();
		}
		
	}
}
