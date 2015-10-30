package personajes;

import juego.Direccion;
import juego.Nivel;
import juego.Posicion;
import juego.Utils;
/**
 * Modela un ladrillo del juego
 *
 */
public class Ladrillo extends Chocable {
	
	public Ladrillo(Posicion pos) {
		super(pos,Utils.vLadrillo);
	}
	/**
	 * El ladrillo cae
	 */
	@Override
	public void actualizar() {
		super.mover(Direccion.ABAJO);
	}
}
