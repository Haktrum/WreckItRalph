package personajes;

import juego.Nivel;
import utils.Direccion;
import utils.Evento;
import utils.Posicion;
import utils.Utils;
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
	 * @throws Evento 
	 */
	@Override
	public void actualizar() throws Evento {
		Posicion pos = super.getPos().potencial(dir);
		int x = pos.getX();
		int y = pos.getY();
		if (x < -1 || x > Utils.numCols || y < -1 || y > Utils.numPisos) {
			dir = dir.opuesta();
		}
		super.mover(dir);
	}
}
