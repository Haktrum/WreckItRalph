package personajes;

import java.awt.image.BufferedImage;
import java.util.Random;

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
		super(pos,Utils.dificultar(Utils.vLadrillo,true));
		super.agregarImagen("res/img/rocas/ladrillo1.png");
		super.agregarImagen("res/img/rocas/ladrillo2.png");
		super.subPosX = Utils.RANDOM.nextInt(21)-10;
	}
	/**
	 * El ladrillo cae
	 */
	@Override
	public void actualizar() throws Evento{
		super.refresh();
		super.toggleREQ(new REQ(0,5),new REQ(1,5));
		super.mover(Direccion.ABAJO);
	}
}
