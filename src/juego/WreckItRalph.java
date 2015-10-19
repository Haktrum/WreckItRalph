package juego;

import personajes.Felix;
import personajes.Ladrillo;

public class WreckItRalph {
	private Highscore highscore = new Highscore();
	private Contexto contexto = new Contexto();
	private Jugador jugador;
	private static WreckItRalph wreckItRalph;
	
	public static void main(String[] args) {
		wreckItRalph = new WreckItRalph();
	}

	private WreckItRalph() {
		//jugador = new Jugador();
		contexto.empezarJuego();
		bucleConsola();
	}
	
	private void bucleConsola() {
		Direccion movimiento[] = {
				Direccion.DERECHA, Direccion.DERECHA, Direccion.DERECHA, Direccion.DERECHA,
				Direccion.ARRIBA,
				Direccion.IZQUIERDA, Direccion.IZQUIERDA, Direccion.IZQUIERDA, Direccion.IZQUIERDA,
				Direccion.ARRIBA,
				Direccion.DERECHA, Direccion.DERECHA, Direccion.DERECHA, Direccion.DERECHA
		};
		Felix felix = contexto.getFelix();
		for (Direccion d : movimiento) {
			contexto.actualizar();
			if (felix.getVidas() == 0) {
				break;
			}
			felix.mover(d);
			felix.arreglarVentana();
			felix.arreglarVentana();
			felix.arreglarVentana();
			felix.arreglarVentana();
		}
		new Ladrillo(felix.getPos());
		contexto.actualizar();
		System.out.println("Puntaje final: " + contexto.getPuntaje());
	}

}
