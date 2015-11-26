package personajes;

import java.awt.Insets;

import utils.Direccion;
import utils.Evento;
import utils.Posicion;
import utils.Utils;

/**
 * Modela un ladrillo del juego
 *
 */
public class Ladrillo extends Chocable {
	private REQ LADRILLO1 = new REQ(0, 3);
	private REQ LADRILLO2 = new REQ(1, 3);

	public Ladrillo(Posicion pos) {
		super(pos, Utils.dificultar(Utils.vLadrillo, true));
		super.agregarImagen("res/img/rocas/ladrillo1.png");
		super.agregarImagen("res/img/rocas/ladrillo2.png");
		super.pos.setSubX(Utils.RANDOM.nextInt(21) - 10);
	}

	/**
	 * El ladrillo cae
	 */
	@Override
	public void actualizar() throws Evento {
		super.refresh();
		super.requests.add(LADRILLO1);
		super.requests.add(LADRILLO2);
		super.mover(Direccion.ABAJO);
	}
	@Override
	public Insets getMargenes(){
		return new Insets(0,0,0,0);
	}
}
