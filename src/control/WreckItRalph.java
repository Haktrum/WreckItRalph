package control;

import javax.swing.Timer;

public class WreckItRalph {
	private Highscore highscore = new Highscore();
	private Jugador jugador;
	private static WreckItRalph wreckItRalph;
	
	public static void main(String[] args) {
		//wreckItRalph = new WreckItRalph();
		//Controlador.getCtrl().init();
		Controlador c = new Controlador();
		Timer timer = new Timer(40, c);
		timer.setInitialDelay(0);
		timer.start(); 
	}
	
	public static void guardarPuntaje(int p){
		wreckItRalph.jugador = new Jugador();
		wreckItRalph.jugador.setPuntaje(p);
		wreckItRalph.highscore.agregarJugador(wreckItRalph.jugador);
	}
}
