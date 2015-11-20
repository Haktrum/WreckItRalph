package control;

import javax.swing.Timer;

import utils.ListHeap;

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
//		Highscore h = new Highscore();
//		h.agregarJugador(new Jugador("nuevo",300));
//		ListHeap<Jugador> lista = h.getJugadores();
//		while(!lista.isEmpty()){
//			System.out.println(lista.extract().toString());
//		}
	}
	
	public static void guardarPuntaje(int p){
		
	}
}
