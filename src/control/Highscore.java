package control;

import utils.Utils;

	/**
	 * Maneja los 10 puntajes m&aacute;s
	 * altos obtenidos en el juego
	 */
public class Highscore {
	/** Arreglo de jugadores con puntaje */
	private Jugador jugadores[] = new Jugador[Utils.maxJugadores];
	/** Cantidad de jugadores inicial*/	
	private int cantJugadores = 0;
	
	/** Agrega un jugador al arreglo, ordenado
	 * por su puntaje
	 * @param jugador Jugador con alg&uacute;n puntaje
	 */
	public void agregarJugador(Jugador jugador) {
		int i = 0;
		while (i < cantJugadores) {
			if (jugadores[i].compareTo(jugador) > 0) {
				break;
			}
		}	
		for (int j = cantJugadores; j >= i; j--) {
			if (j != jugadores.length) {
				jugadores[j + 1] = jugadores[j];
			}
		}
		jugadores[i] = jugador;
		if(cantJugadores<jugadores.length){
			cantJugadores++;
		}
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Puntajes");
		for (int i = 0; i < jugadores.length; i++) {
			stringBuilder.append(i + 1);
			if (jugadores[i] == null) {
				stringBuilder.append(" -----");
			} else {
				stringBuilder.append(" - " + jugadores[i]);
			}
		}
		return stringBuilder.toString();
	}
}
