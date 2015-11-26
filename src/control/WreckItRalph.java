package control;

import javax.swing.Timer;

public class WreckItRalph {

	public static void main(String[] args) {
		Controlador c = new Controlador();
		Timer timer = new Timer(40, c);
		timer.setInitialDelay(0);
		timer.start();
	}

}
