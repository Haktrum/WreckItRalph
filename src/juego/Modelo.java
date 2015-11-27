package juego;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import control.Highscore;
import personajes.Chocable;
import personajes.Felix;
import personajes.Pajaro;
import personajes.Pastel;
import personajes.Ralph;
import utils.Actualizable;
import utils.Direccion;
import utils.Posicion;
import utils.Utils;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import utils.eventos.EventoOffScreen;
import utils.eventos.EventoSeccionGanada;

/**
 * Modela el conjunto de circustancias que rodean a los personajes
 * 
 */
public class Modelo implements Actualizable {

	/** Personaje principal */
	private Felix felix;
	/** Villano */
	private Ralph ralph;
	/** Puntaje inicial */
	private int puntaje = 0;
	/** Lista de objetos de car&acute;cter actualizable */
	private Set<Chocable> chocables = new HashSet<>();

	/** Nivel */
	private Nivel nivel = null;
	private Highscore highscore;
	
	public Modelo() {
		highscore = new Highscore();
		nivel = new Nivel(0);
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/ui/8-bit.ttf"));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (FontFormatException e) {
		} catch (IOException e) {
		}
	}
	
	public void init() {
		this.reiniciar();
	}

	private void reiniciar() {
		chocables.clear();
		felix = new Felix();
		ralph = new Ralph();
		chocables.add(felix);
		chocables.add(ralph);
	}

	/**
	 * Suma puntos al puntaje inicial
	 * 
	 * @param puntaje
	 *            Cantidad de puntos a sumar
	 */
	public void agregarPuntos(int puntaje) {
		this.puntaje += puntaje;
	}

	/**
	 * Devuelve a F&eacute;lix
	 */
	public Felix getFelix() {
		return felix;
	}

	/**
	 * Devuelve a Ralph;
	 */
	public Ralph getRalph() {
		return ralph;
	}

	/**
	 * Devuelve el puntaje actual;
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * Recorre la lista de Actualizables y los actualiza. Revisa tambi&eacute;
	 * los posibles choques de F&eacute;lix
	 */
	public void martillar() {
		felix.martillar();
		this.agregarPuntos(this.nivel.getSeccion().arreglarVentana(felix.getPos()));
	}

	public void moverFelix(Direccion dir) {
		if (nivel.getSeccion().puedoIr(felix.getPos(), dir)) {
			nivel.getSeccion().ventanaEn(felix.getPos()).felixEsta(false);
			felix.mover(dir);
		}
		nivel.getSeccion().ventanaEn(felix.getPos()).felixEsta(true);
	}

	// tira termina juego
	@Override
	public void actualizar() throws EventoNivelGanado, EventoSeccionGanada, EventoJuegoTerminado {
		try {
			nivel.actualizar();
		} catch (EventoNivelGanado e) {
			if (this.nivel.getNro() < 10) {
				this.nivel = new Nivel(nivel.getNro() + 1);
				this.felix.setPos(new Posicion(0, 0));
				this.reiniciar();
				throw e;
			} else {
				throw new EventoJuegoTerminado(puntaje);
			}
		} catch (EventoSeccionGanada e) {
			felix.setPos(new Posicion(felix.getPos().getX(), 0));
			for (Iterator<Chocable> iterator = chocables.iterator(); iterator.hasNext();) {
				Chocable chocable = iterator.next();
				if (chocable instanceof Pajaro) {
					iterator.remove();
				}
			}
			throw e;
		}
		ArrayList<Chocable> paraAgregar = new ArrayList<Chocable>();
		ArrayList<Chocable> paraEliminar = new ArrayList<Chocable>();
		for (Iterator<Chocable> iterator = chocables.iterator(); iterator.hasNext();) {
			Chocable chocable = iterator.next();
			try {
				if (chocable != null) {
					chocable.actualizar();
					if (!(chocable instanceof Felix)) {
						felix.chequearChoque(chocable);
					}
				}
			} catch (EventoJuegoTerminado e) {
				throw e;
			} catch (EventoOffScreen e) {
				iterator.remove();
			}
		}
		for (Chocable nuevo : paraAgregar) {
			chocables.add(nuevo);
		}
		for (Chocable nuevo : paraEliminar) {
			chocables.remove(nuevo);
		}
		if (Utils.randomBoolean(Utils.probPastel) && nivel.getSeccion().puedoPastel()) {
			int x = Utils.RANDOM.nextInt(Utils.numCols);
			int y = Utils.RANDOM.nextInt(Utils.numPisos);
			chocables.add(new Pastel(new Posicion(x, y)));
			nivel.getSeccion().nuevoPastel();
		}
		if (Utils.randomBoolean(Utils.probPajaro) && !nivel.getSeccion().hayPajaro()) {
			int y = Utils.RANDOM.nextInt(Utils.numPisos - 1) + 1;
			chocables.add(new Pajaro(new Posicion(Utils.numCols - 1, y)));
			nivel.getSeccion().hayPajaro(true);
		}
	}

	public Set<Chocable> getChocables() {
		return chocables;
	}

	public int getNivel() {
		return nivel.getNro();
	}
	
	public void setNivel(int n) {
		this.nivel.setNivel(n);
	}

	public Highscore getHighscore() {
		return highscore;
	}
	
	public Ventana[][][] getMapas() {
		return nivel.getMapas();
	}

}
