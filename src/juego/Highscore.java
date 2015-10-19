package juego;

public class Highscore {
	Jugador jugadores[] = new Jugador[10];
	int cantJugadores = 0;

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
