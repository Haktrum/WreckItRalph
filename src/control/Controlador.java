package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utils.Direccion;
import utils.Evento;
import utils.Utils;
import utils.Evento.EventoID;
import view.MainWindow;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;
import view.MenuItem.NombreBoton;
import juego.Contexto;

public class Controlador implements ActionListener {
	private MainWindow window = null;
	private boolean corriendo = false;
	private Contexto ctx = null;
	private ViewJuego panelJuego = null;
	private int nivelInicial = 1;

	public Controlador() {
		// this.irMenu();
		KeyListener kl = new MiKeyListener();
		window = new MainWindow(kl);
		window.setTitulo("Wreck It Ralph");
	}

	public void setCorriendo(boolean b) {
		corriendo = b;
	}

	public boolean isCorriendo() {
		return corriendo;
	}

	private void flechaIzq() {
		window.flechaIzq();
		if (ctx != null) {
			ctx.moverFelix(Direccion.IZQUIERDA);
		}
	}

	private void flechaDer() {
		window.flechaDer();
		if (ctx != null) {
			ctx.moverFelix(Direccion.DERECHA);
		}
	}

	private void flechaArriba() {
		window.flechaArriba();
		if (ctx != null) {
			ctx.moverFelix(Direccion.ARRIBA);
		}
	}

	private void flechaAbajo() {
		window.flechaAbajo();
		if (ctx != null) {
			ctx.moverFelix(Direccion.ABAJO);
		}
	}

	private void enter() {
		Object res = window.enter();
		if (res instanceof Integer) {
			this.nivelInicial = (int) res;
			window.setContentPane(new ViewMenu());
			// System.out.println(nivelInicial);
		} else if (res instanceof NombreBoton) {
			switch (((NombreBoton) res)) {
			case CONFIG:
				window.setContentPane(new ViewConfig());
				break;
			case JUGAR:
				ctx = new Contexto(nivelInicial);
				panelJuego = new ViewJuego(ctx.getChocables(), ctx.getMapas());
				window.setContentPane(panelJuego);
				break;
			case REGLAS:
				window.setContentPane(new ViewReglas());
				break;
			case TOP:
				window.setContentPane(new ViewTop());
				break;
			}
		}
	}

	private void escape() {
		window.escape();
		ctx = null;
		this.corriendo = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (panelJuego != null) {
			panelJuego.actualizar();
		}
		if (ctx != null) {
			try {
				ctx.actualizar();
				panelJuego.pasarInfo(ctx.getChocables(), ctx.getMapas());
			} catch (Evento ex) {
				if (ex.getId() == EventoID.GANASECCION) {
					panelJuego.incOffset();
				} else if (ex.getId() == EventoID.GANANIVEL) {
					panelJuego.reset();
					window.setTitulo("Wreck It Ralph - Nivel " + Utils.nivelActual);
				} else if (ex.getId() == EventoID.TERMINAJUEGO) {
					ctx = null;
					window.setContentPane(new ViewMenu());
				}
			}
		}
	}

	private void space() {
		ctx.martillar();
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
