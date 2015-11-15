package control;

import graficos.MainWindow;
import personajes.Felix;
import personajes.Ladrillo;

public class WreckItRalph {
	private Highscore highscore = new Highscore();
	private Jugador jugador;
	private static WreckItRalph wreckItRalph;
	
	public static void main(String[] args) {
		//wreckItRalph = new WreckItRalph();
		//Controlador.getCtrl().init();
		Controlador c = new Controlador();
		c.setCorriendo(true);
		new Thread(c).start();
	}
	
	public static void guardarPuntaje(int p){
		wreckItRalph.jugador = new Jugador();
		wreckItRalph.jugador.setPuntaje(p);
		wreckItRalph.highscore.agregarJugador(wreckItRalph.jugador);
	}
}
