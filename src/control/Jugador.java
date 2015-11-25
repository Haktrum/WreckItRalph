package control;

import java.io.Serializable;

/**
 * Modela un jugador del juego
 *
 */
public class Jugador implements Comparable<Jugador>, Serializable {
	private static final long serialVersionUID = 4196508987432820781L;
	private String nombre;
	private int puntaje;

	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public Jugador(String nombre, int puntaje) {
		this(nombre);
		this.puntaje = puntaje;
	}

	/**
	 * Compara dos jugadores por su puntaje
	 */
	@Override
	public int compareTo(Jugador o) {
		return o.puntaje - puntaje;
	}

	@Override
	public String toString() {
		return nombre + " - " + puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public String getNombre() {
		return this.nombre;
	}
}
