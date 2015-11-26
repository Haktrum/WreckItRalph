package juego;

import graficos.MenuItem.NombreBoton;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import personajes.Chocable;
import personajes.Felix;
import personajes.Ladrillo;
import personajes.Pajaro;
import personajes.Pastel;
import personajes.Ralph;
import utils.Actualizable;
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
public class Contexto implements Modelo{

	/** Personaje principal */
	private Felix felix;
	/** Villano */
	private Ralph ralph;
	/** Puntaje inicial */
	private int puntaje = 0;
	/** Lista de objetos de car&acute;cter actualizable */
	private ArrayList<Chocable> chocables = new ArrayList<Chocable>(Utils.maxLista);

	/** Nivel */
	private Nivel nivel = null;

	private int offset = 0;
	private int visualOffset = 0;
	private NombreBoton dest = null;
	public Contexto(int lvl) {
		nivel = new Nivel(lvl);
		this.reiniciar();
	}

	private void reiniciar() {
		chocables.clear();
		felix = new Felix();
		ralph = new Ralph();
		chocables.add(felix);
		chocables.add(ralph);
		offset = 0;
		visualOffset = 0;
	}

	/**
	 * Finaliza el juego
	 * 
	 * @throws Evento
	 */
	private void terminarJuego()  {
		dest = NombreBoton.MENU;
	}
	private void ganaSeccion(){
		offset += 252;
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
	private void martillar() {
		felix.martillar();
		this.agregarPuntos(this.nivel.getSeccion().arreglarVentana(felix.getPos()));
	}

	private void moverFelix(Direccion dir) {
		if (nivel.getSeccion().puedoIr(felix.getPos(), dir)) {
			nivel.getSeccion().ventanaEn(felix.getPos()).felixEsta(false);
			felix.mover(dir);
		}
		nivel.getSeccion().ventanaEn(felix.getPos()).felixEsta(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			this.moverFelix(Direccion.IZQUIERDA);
			break;
		case KeyEvent.VK_RIGHT:
			this.moverFelix(Direccion.DERECHA);
			break;
		case KeyEvent.VK_UP:
			this.moverFelix(Direccion.ARRIBA);
			break;
		case KeyEvent.VK_DOWN:
			this.moverFelix(Direccion.ABAJO);
			break;
		case KeyEvent.VK_ESCAPE:
			this.terminarJuego();
		case KeyEvent.VK_SPACE:
			this.martillar();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public Object[] getInfo() {
		Object[] res = {chocables,nivel.getMapas(),visualOffset};
		return res;
	}

	@Override
	public void actionPerformed(ActionEvent actEv) {
		try {
			nivel.actualizar();
		} catch (Evento e) {
			if (e.getId() == EventoID.TERMINAJUEGO) {
				this.terminarJuego();
			} else if (e.getId() == EventoID.GANANIVEL) {
				if (this.nivel.getNro() < 10) {
					this.nivel = new Nivel(nivel.getNro() + 1);
					this.felix.setPos(new Posicion(0, 0));
				} else {
					this.terminarJuego();
				}
				this.reiniciar();
			} else if (e.getId() == EventoID.GANASECCION) {
				felix.setPos(new Posicion(felix.getPos().getX(), 0));
				for (int i = 0; i < chocables.size(); i++) {
					if (chocables.get(i) instanceof Pajaro) {
						chocables.remove(i);
						break;
					}
				}
				this.ganaSeccion();
			}
		}
		ArrayList<Chocable> paraAgregar = new ArrayList<Chocable>();
		ArrayList<Chocable> paraEliminar = new ArrayList<Chocable>();
		for (Chocable chocable : chocables) {
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
					this.terminarJuego();
					break;
				case OFF_SCREEN:
					paraEliminar.add((Chocable) e.getParam());
					break;
				case SALTA:
					Integer ladrillos = (Integer) e.getParam();
					while (ladrillos-- > 0) {
						paraAgregar.add(new Ladrillo(new Posicion(ralph.getPos().getX(), 2)));
					}
					break;
				default:
					break;
				}
			}
		}
		for (Chocable nuevo : paraAgregar) {
			chocables.add(nuevo);
		}
		for (Chocable nuevo : paraEliminar) {
			chocables.remove(nuevo);
		}
		if (Utils.randomBoolean(Utils.dificultar(Utils.probPastel,false)) && nivel.getSeccion().puedoPastel()) {
			int x = Utils.RANDOM.nextInt(Utils.numCols);
			int y = Utils.RANDOM.nextInt(Utils.numPisos);
			chocables.add(new Pastel(new Posicion(x, y)));
			nivel.getSeccion().nuevoPastel();
		}
		if (Utils.randomBoolean(Utils.dificultar(Utils.probPajaro,false)) && !nivel.getSeccion().hayPajaro()) {
			int y = Utils.RANDOM.nextInt(Utils.numPisos - 1) + 1;
			chocables.add(new Pajaro(new Posicion(Utils.numCols - 1, y)));
			nivel.getSeccion().hayPajaro(true);
		}
		if(offset>visualOffset){
			visualOffset+=10;
		}
	}

	@Override
	public NombreBoton getDestino() {
		return dest;
	}
}
