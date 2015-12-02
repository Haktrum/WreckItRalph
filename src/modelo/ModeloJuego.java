package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import personajes.Chocable;
import personajes.Felix;
import personajes.Ladrillo;
import personajes.Pajaro;
import personajes.Pastel;
import personajes.Ralph;
import utils.Direccion;
import utils.Evento;
import utils.Modelo;
import utils.Posicion;
import utils.Utils;
import utils.Evento.EventoID;

/**
 * Modela el conjunto de circustancias que rodean a los personajes
 * 
 */
public class ModeloJuego implements Modelo {

	public enum Estado {
		NOEMPEZADO, PAUSADO, JUGANDO, TERMINADO
	}

	/** Personaje principal */
	private Felix felix;
	/** Villano */
	private Ralph ralph;
	/** Puntaje inicial */
	private int puntaje = 0;
	/** Lista de objetos de car&acute;cter actualizable */
	private List<Chocable> chocables = new ArrayList<Chocable>(Utils.maxLista);

	/** Nivel */
	private Nivel nivel = null;

	private Estado estado = Estado.NOEMPEZADO;

	public ModeloJuego() {
		nivel = new Nivel(Utils.nivelActual);
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
	 * Finaliza el juego
	 * 
	 * @throws Evento
	 */
	public void terminarJuego() {
		estado = Estado.TERMINADO;
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

	public List<Chocable> getChocables() {
		return chocables;
	}
	
	public Ventana[][][] getVentanas() {
		return nivel.getMapas();
	}
	
	public int getVidas() {
		return felix.getVidas();
	}
	
	public int getPuntaje() {
		return puntaje;
	}

	public void run() {
		if (estado == Estado.PAUSADO) {
			return;
		}
		try {
			nivel.actualizar();
		} catch (Evento e) {
			if (e.getId() == EventoID.TERMINAJUEGO) {
				terminarJuego();
			} else if (e.getId() == EventoID.GANANIVEL) {
				if (this.nivel.getNro() < 10) {
					this.nivel = new Nivel(nivel.getNro() + 1);
					this.felix.setPos(new Posicion(0, 0));
				} else {
					terminarJuego();
				}
				this.reiniciar();
			} else if (e.getId() == EventoID.GANASECCION) {
				felix.setPos(new Posicion(felix.getPos().getX(), 0));
				for (Iterator<Chocable> iterator = chocables.iterator(); iterator.hasNext();) {
					Chocable chocable = (Chocable) iterator.next();
					if (chocable instanceof Pajaro || chocable instanceof Ladrillo
							|| chocable instanceof Pastel) {
						iterator.remove();
					}
					
				}
			}
		}
		List<Chocable> agregar = new ArrayList<>(); 
		for (Iterator<Chocable> iterator = chocables.iterator(); iterator.hasNext();) {
			Chocable chocable = iterator.next();
			try {
				if (chocable != null) {
					chocable.actualizar();
					if (!(chocable instanceof Felix)) {
						felix.chequearChoque(chocable);
					}
				}
			} catch (Evento e) {
				switch (e.getId()) {
				case TERMINAJUEGO:
					terminarJuego();
					break;
				case OFF_SCREEN:
					iterator.remove();
					break;
				case SALTA:
					Integer ladrillos = (Integer) e.getParam();
					while (ladrillos-- > 0) {
						agregar.add(new Ladrillo(new Posicion(ralph.getPos().getX(), 2)));
					}
					break;
				default:
					break;
				}
			}
		}
		chocables.addAll(agregar);
		if (Utils.randomBoolean(Utils.dificultar(Utils.probPastel, false))) {
			Pastel pastel = nivel.getSeccion().crearPastel();
			if (pastel != null) {
				chocables.add(pastel);
			}
		}
		if (Utils.randomBoolean(Utils.dificultar(Utils.probPajaro, false)) 
				&& !nivel.getSeccion().hayPajaro() && nivel.getSeccion().getNro() > 0) {
			Pajaro pajaro = nivel.getSeccion().crearPajaro();
			chocables.add(pajaro);
		}
	}

	public boolean isCorriendo() {
		return estado == Estado.JUGANDO;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public int getSeccion() {
		return nivel.getSeccion().getNro();
	}

	public void pausar() {
		if (estado == Estado.PAUSADO) {
			estado = Estado.JUGANDO;
		} else if (estado == Estado.JUGANDO) {
			estado = Estado.PAUSADO;
		}
	}

}
