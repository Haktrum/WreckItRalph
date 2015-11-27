package control;

import javax.swing.Timer;

import controller.Controller;
import controller.ControllerMenu;
import juego.Modelo;
import view.View;
import view.ViewMenu;

public class WreckItRalph {

	public static void main(String[] args) {
		// wreckItRalph = new WreckItRalph();
		// Controlador.getCtrl().init();
		Modelo modelo = new Modelo();
		View view = new ViewMenu(modelo);
		Controller controlador = new ControllerMenu(modelo, view);
		/*Timer timer = new Timer(40, controlador);
		timer.setInitialDelay(0);
		timer.start();*/
		// Highscore h = new Highscore();
		// h.agregarJugador(new Jugador("nuevo",300));
		// ListHeap<Jugador> lista = h.getJugadores();
		// while(!lista.isEmpty()){
		// System.out.println(lista.extract().toString());
		// }
	}

}
