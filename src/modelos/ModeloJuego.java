package modelos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import personajes.Chocable;
import personajes.Felix;
import personajes.Ladrillo;
import personajes.Pajaro;
import personajes.Pastel;
import personajes.Ralph;
import utils.Direccion;
import utils.Posicion;
import utils.Utils;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import utils.eventos.EventoOffScreen;
import utils.eventos.EventoRalphSalta;
import utils.eventos.EventoSeccionGanada;

/**
 * Modela el conjunto de circustancias que rodean a los personajes
 * 
 */
public class ModeloJuego extends Modelo implements ActionListener{

	private static ModeloJuego instancia;
	/** Personaje principal */
	private Felix felix;
	/** Villano */
	private Ralph ralph;
	/** Puntaje inicial */
	private int puntaje = 0;
	/** Lista de objetos de car&acute;cter actualizable */
	private Set<Chocable> chocables = new HashSet<>();
	//private ArrayList<Chocable> chocables = new ArrayList<Chocable>();

	/** Nivel */
	private Nivel nivel = null;
	
	private ModeloJuego() {
		nivel = new Nivel(1);
		this.init();
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
	
	@Override
	public void actualizar() throws EventoNivelGanado, EventoSeccionGanada, EventoJuegoTerminado {
		try {
			nivel.actualizar();
		} catch (EventoNivelGanado e) {
			if (this.nivel.getNro() < 10) {
				this.nivel = new Nivel(nivel.getNro() + 1);
				this.reiniciar();
				throw e;
			} else {
				throw new EventoJuegoTerminado(puntaje);
			}
		} catch (EventoSeccionGanada e) {
			felix.setPos(new Posicion(felix.getPos().getX(), 0));
			for (Iterator<Chocable> iterator = chocables.iterator(); iterator.hasNext();) {
				Chocable chocable = iterator.next();
				if (chocable instanceof Pajaro || chocable instanceof Pastel) {
					iterator.remove();
				}
			}
			throw e;
		}
		Set<Chocable> paraAgregar = new HashSet<Chocable>();
		Iterator<Chocable> iterator = chocables.iterator();
		while(iterator.hasNext()) {
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
			} catch (EventoRalphSalta e) {
				Integer ladrillos = (Integer) e.getParam();
				while (ladrillos-- > 0) {
					paraAgregar.add(new Ladrillo(new Posicion(ralph.getPos().getX(), 2)));
				}
			}
		}
		chocables.addAll(paraAgregar);
		if (Utils.randomBoolean(Utils.probPastel) && nivel.getSeccion().puedoPastel()) {
			int x = Utils.RANDOM.nextInt(Utils.numCols);
			int y = Utils.RANDOM.nextInt(Utils.numPisos);
			chocables.add(new Pastel(new Posicion(x, y)));
			nivel.getSeccion().nuevoPastel();
		}
		if (Utils.randomBoolean(Utils.probPajaro) && !nivel.getSeccion().hayPajaro()) {
			int y = Utils.RANDOM.nextInt(Utils.numPisos - 1) + 1;
			chocables.add(new Pajaro(new Posicion(Utils.numCols+1, y)));
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
		this.nivel = new Nivel(n);
	}
	
	public Ventana[][][] getMapas() {
		return nivel.getMapas();
	}

	public static ModeloJuego getInstancia() {
		if (instancia == null) {
			instancia = new ModeloJuego();
		}
		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.actualizar();
	}
	
}
