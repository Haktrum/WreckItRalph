package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import utils.Direccion;
import utils.Utils;
import utils.eventos.EventoSeccionGanada;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import view.MainWindow;
import view.View;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;
import view.MenuItem.NombreBoton;
import juego.Modelo;

public class Controlador implements ActionListener {
	private MainWindow window;
	private boolean corriendo = false;
	private Modelo modelo;
	private ViewJuego panelJuego = null;
	private int nivelInicial = 1;
	private View view;
	private Timer timer;

	public Controlador(Modelo modelo, View view) {
		// this.irMenu();
		this.modelo = modelo;
		this.view = view;
		KeyListener kl = new MiKeyListener();
		/*window = new MainWindow();
		window.addKeyListener(kl);
		window.setTitulo("Wreck It Ralph");*/
		timer = new Timer(40, this);
		timer.start();
	}

	public void setCorriendo(boolean b) {
		corriendo = b;
	}

	public boolean isCorriendo() {
		return corriendo;
	}

	private void flechaIzq() {
		window.flechaIzq();
		if (modelo != null) {
			modelo.moverFelix(Direccion.IZQUIERDA);
		}
	}

	private void flechaDer() {
		window.flechaDer();
		if (modelo != null) {
			modelo.moverFelix(Direccion.DERECHA);
		}
	}

	private void flechaArriba() {
		window.flechaArriba();
		if (modelo != null) {
			modelo.moverFelix(Direccion.ARRIBA);
		}
	}

	private void flechaAbajo() {
		window.flechaAbajo();
		if (modelo != null) {
			modelo.moverFelix(Direccion.ABAJO);
		}
	}

	private void enter() {
		Object res = window.enter();
		if (res instanceof Integer) {
			this.nivelInicial = (int) res;
			window.setContentPane(new ViewMenu(modelo));
			// System.out.println(nivelInicial);
		} else if (res instanceof NombreBoton) {
			switch (((NombreBoton) res)) {
			case CONFIG:
				window.setContentPane(new ViewConfig(modelo));
				break;
			case JUGAR:
				window.setContentPane(new ViewJuego(modelo));
				break;
			case REGLAS:
				window.setContentPane(new ViewReglas(modelo));
				break;
			case TOP:
				window.setContentPane(new ViewTop(modelo));
				break;
			}
		}
	}

	private void escape() {
		window.escape();
		this.corriendo = false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			modelo.actualizar();
		} catch (EventoSeccionGanada e) {
			panelJuego.incOffset();
		} catch (EventoNivelGanado e) {
			panelJuego.reset();
			window.setTitulo("Wreck It Ralph - Nivel " + Utils.nivelActual);
		} catch (EventoJuegoTerminado e) {
			modelo = null;
			window.setContentPane(new ViewMenu(modelo));
		}
	}

	private void space() {
		modelo.martillar();
	}

	private class MiKeyListener implements KeyListener {
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
			case KeyEvent.VK_ENTER:
				enter();
				break;
			case KeyEvent.VK_ESCAPE:
				escape();
				break;
			case KeyEvent.VK_SPACE:
				space();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
}
