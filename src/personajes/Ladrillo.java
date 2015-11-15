package personajes;

import juego.Nivel;
import utils.Direccion;
import utils.Evento;
import utils.Posicion;
import utils.Utils;
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
	public void actualizar() throws Evento{
		super.mover(Direccion.ABAJO);
	}
}
