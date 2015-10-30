package personajes;

import juego.Direccion;
import juego.Nivel;
import juego.Posicion;
import juego.Utils;
/**
 * Modela un p&aacute;jaro del juego
 *
 */
public class Pajaro extends Chocable {
	
	public Pajaro(Posicion pos) {
		super(pos,Utils.vPajaro);
	}
	/** Direcci&oacute; en la que se mueve */
	private Direccion dir;
	
	/**
	 * Actualiza su posici&oacute;n
	 */
	@Override
	public void actualizar() {
		Posicion pos = super.getPos().potencial(dir);
		int x = pos.getX();
		int y = pos.getY();
		if (x < -1 || x > Utils.numCols || y < -1 || y > Utils.numPisos) {
			dir = dir.opuesta();
		}
		super.mover(dir);
	}
}
