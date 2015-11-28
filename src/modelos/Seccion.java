package modelos;

import modelos.Ventana.Tipo;
import utils.Direccion;
import utils.Posicion;
import utils.Utils;
import utils.eventos.Evento;
import utils.eventos.EventoNivelGanado;
import utils.eventos.EventoSeccionGanada;

/**
 * Modela una secci&oacute;n del juego
 *
 */
public class Seccion {

	/** N&uacute;mero de ventanas en un piso */
	private final int COLS = Utils.numCols;

	/** N&uacute;mero de pisos en una seccion */
	private final int ROWS = Utils.numPisos;

	/** Cantidad de ventanas rotas en la secci&oacute;n */
	private int ventRotas = 0;

	/** Mapa de las ventanas en la secci&oacute;n. (Piso,Ventana) */
	private Ventana[][] mapa;

	private boolean hayPajaro = false;

	private int proximoPastel = Utils.dificultar(Utils.proximoPastel, false);
	/**
	 * Crea una secci&oacute;n de ROWS pisos y COLS ventanas por piso
	 */

	private int nroSeccion;

	public Seccion(int nro) {
		mapa = new Ventana[ROWS][COLS];
		this.nroSeccion = nro;
		for (int j = 0; j < ROWS; j++) {
			for (int i = 0; i < COLS; i++) {
				if (Utils.randomBoolean(Utils.probDosHojas)) {
					mapa[j][i] = new Ventana(Tipo.DOSHOJAS);
				} else {
					mapa[j][i] = new Ventana(Tipo.COMUN);
				}
			}
		}
	}

	/**
	 * Crea ventanas semicirculares en​ en la entrada de planta baja y ​ del
	 * primer piso
	 */
	public void puertaYBalcon() {
		int medio = (int) ((COLS - 1) / 2);
		mapa[0][medio] = new Ventana(Tipo.PUERTA);
		mapa[1][medio] = new Ventana(Tipo.SEMICIRCULAR);
	}

	public void imprimir() {
		for (int j = ROWS - 1; j >= 0; j--) {
			System.out.println("---------------------------------------------");
			for (int i = COLS - 1; i >= 0; i--) {
				System.out.print(ventanaEn(i, j).toString());
			}
			System.out.println();
		}
	}

	/**
	 * Martilla la ventana para arreglar sus paneles.
	 *
	 * @param pos
	 *            posici&oacute;n de la ventana
	 * @return puntaje obtenido por martillar la ventana
	 */
	public int arreglarVentana(Posicion pos) {
		Ventana v = ventanaEn(pos);
		int puntos = v.arreglar();
		v.actualizar();
		if (puntos == Utils.puntajeArreglar) {
			ventRotas--;
		}
		return puntos;
	}

	/**
	 * Devuelve la ventana en la Posicion pos
	 * 
	 * @param pos
	 *            Posicion v&aacute;lida dentro de la secci&oacute;n
	 * @return La ventana ubicada en pos
	 */
	public Ventana ventanaEn(Posicion pos) {
		return ventanaEn(pos.getX(), pos.getY());
	}

	/**
	 * Devuelve la ventana en x en el piso y
	 * 
	 * @param x
	 *            Punto x v&aacute;lido dentro de la secci&oacute;n
	 * @param y
	 *            Punto y v&aacute;lida dentro de la secci&oacute;n
	 * @return La ventana ubicada en el punto(x,y)
	 */
	private Ventana ventanaEn(int x, int y) {
		return mapa[y][x];
	}

	/**
	 * Determina si se puede mover en una direcci&oacute;n dada desde una
	 * posici&oacute;n dada
	 * 
	 * @param pos
	 *            Posici&oacute;n actual en la que se encuentra
	 * @param dir
	 *            Direcci&oacute;n de movimiento
	 * @return True si se puede mover, false en caso contrario
	 */
	public boolean puedoIr(Posicion pos, Direccion dir) {
		Posicion nueva = pos.potencial(dir);
		Direccion opuesta = dir.opuesta();
		if (nueva.getX() < 0 || nueva.getX() >= COLS)
			return false;
		if (nueva.getY() < 0 || nueva.getY() >= ROWS)
			return false;
		return ventanaEn(pos).puedoMoverHacia(dir) && ventanaEn(nueva).puedoMoverHacia(opuesta);
	}

	/**
	 * Rompe o no, los paneles de cada ventana de la secci&oacute;n
	 */
	public void romperTodas() {
		for (int j = 0; j < ROWS; j++) {
			for (int i = 0; i < COLS; i++) {
				ventRotas += ventanaEn(i, j).romper();
			}
		}
	}

	/**
	 * Devuelve la cantidad de ventanas rotas en la secci&oacute;n
	 * 
	 * @return cantidad de ventanas rotas en la secci&oacute;n
	 */
	public int getVentanasRotas() {
		return ventRotas;
	}

	public void ganarSeccion() throws Evento {
		if (this.nroSeccion == Utils.maxSeccion) {
			throw new EventoNivelGanado();
		} else {
			this.nroSeccion++;
			throw new EventoSeccionGanada();
		}
	}

	public int getNro() {
		return this.nroSeccion;
	}

	public Ventana[][] getMapa() {
		return this.mapa;
	}

	public boolean hayPajaro() {
		return this.hayPajaro;
	}

	public void hayPajaro(boolean b) {
		this.hayPajaro = b;
	}

	public boolean puedoPastel() {
		return this.proximoPastel == 0;
	}

	public void nuevoPastel() {
		this.proximoPastel = Utils.dificultar(Utils.proximoPastel, false);
	}

	protected void decProximoPastel() {
		if (this.proximoPastel > 0) {
			this.proximoPastel--;
		}
	}
}
