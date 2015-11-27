package personajes;

import java.awt.Rectangle;

import utils.Posicion;
import utils.Utils;
import utils.eventos.EventoOffScreen;

public class Pastel extends Chocable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2875871931449135087L;
	private int timer = Utils.tiempoPastel;
	private REQ PASTEL1 = new REQ(0, 2);
	private REQ PASTEL2 = new REQ(1, 2);

	public Pastel(Posicion pos) {
		super(pos, new Rectangle(20, 21));
		super.agregarImagen("res/img/pastel/pastel1.png");
		super.agregarImagen("res/img/pastel/pastel2.png");
	}

	@Override
	public void actualizar() throws EventoOffScreen {
		super.refresh();
		this.timer--;
		super.requests.add(PASTEL1);
		super.requests.add(PASTEL2);
		if (timer == 0) {
			super.requests.clear();
			throw new EventoOffScreen();
		}
	}

}
