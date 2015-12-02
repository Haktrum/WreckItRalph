package control;

import java.io.Serializable;

/**
 * Modela un jugador del juego
 *
 */
public class Jugador implements Comparable<Jugador>, Serializable {

	private static final long serialVersionUID = 4196508987432820781L;
	/**
	 * Nombre del jugador
	 */
	private String nombre;
	/**
	 * Puntaje obtenido
	 */
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
		if(this.equals(o)) 
			return 0;
		else
			if(o.puntaje-puntaje==0)
				return 1;
		return o.puntaje - puntaje;
	}

	@Override
	public String toString() {
		return nombre + "*" + puntaje;
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
	/**
	 * Dos jugadores son iguales cuando tienen el mismo nombre
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Jugador)
			return this.nombre.equals(((Jugador) o).getNombre());
		return false;
	}
}
