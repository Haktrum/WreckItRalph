package personajes;

import java.awt.image.BufferedImage;

import utils.Direccion;
import utils.Evento;
import utils.Posicion;
import utils.Utils;
import utils.Evento.EventoID;
/**
 * Modela un ladrillo del juego
 *
 */
public class Ladrillo extends Chocable {

	public Ladrillo(Posicion pos) {
		super(pos,Utils.vLadrillo);
		super.setBaseImage("res/img/rocas/ladrillo1.png");
		super.setAuxImage("res/img/rocas/ladrillo2.png");
	}
	/**
	 * El ladrillo cae
	 */
	@Override
	public void actualizar() throws Evento{
		super.refresh();
		super.mover(Direccion.ABAJO);
		
	}
}
