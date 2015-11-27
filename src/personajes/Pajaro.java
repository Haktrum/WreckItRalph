package personajes;

import utils.Direccion;
import utils.Evento;
import utils.Posicion;
import utils.Utils;

/**
 * Modela un p&aacute;jaro del juego
 *
 */
public class Pajaro extends Chocable {

	private REQ PAJARO_IZQ1 = new REQ(0, 3);
	private REQ PAJARO_IZQ2 = new REQ(1, 3);
	private REQ PAJARO_DER1 = new REQ(2, 3);
	private REQ PAJARO_DER2 = new REQ(3, 3);

	/** Direcci&oacute; en la que se mueve */
	private Direccion dir;

	public Pajaro(Posicion pos) {
		super(pos, Utils.vPajaro);
		super.agregarImagen("res/img/pajaro/pajaro_izq1.png");
		super.agregarImagen("res/img/pajaro/pajaro_izq2.png");
		super.agregarImagen("res/img/pajaro/pajaro_der1.png");
		super.agregarImagen("res/img/pajaro/pajaro_der2.png");
		dir = Direccion.IZQUIERDA;
	}

	/**
	 * Actualiza su posici&oacute;n
	 * 
	 * @throws Evento
	 */
	@Override
	public void actualizar() throws Evento {
		super.refresh();
		Posicion pos = super.getPos().potencial(dir);
		int x = pos.getX();
		int y = pos.getY();
		if (x < -1 || x > Utils.numCols || y < -1 || y > Utils.numPisos) {
			dir = dir.opuesta();
			super.requests.clear();
		}
		if (dir == Direccion.DERECHA) {
			super.requests.add(PAJARO_DER1);
			super.requests.add(PAJARO_DER2);
		} else {
			super.requests.add(PAJARO_IZQ1);
			super.requests.add(PAJARO_IZQ2);

		}
		super.mover(dir);
	}
}
