package juego;

import personajes.Felix;
import personajes.Ladrillo;

public class WreckItRalph {
	private Highscore highscore = new Highscore();
	private Contexto contexto = Contexto.getContexto();
	private Jugador jugador;
	private static WreckItRalph wreckItRalph;
	
	public static void main(String[] args) {
		wreckItRalph = new WreckItRalph();
	}

	private WreckItRalph() {
		contexto.empezarJuego();
	}
	
	public static void guardarPuntaje(int p){
		wreckItRalph.jugador = new Jugador();
		wreckItRalph.jugador.setPuntaje(p);
		wreckItRalph.highscore.agregarJugador(wreckItRalph.jugador);
	}
}
