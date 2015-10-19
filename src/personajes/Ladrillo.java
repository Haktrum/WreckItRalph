package personajes;

import juego.Direccion;
import juego.Posicion;

public class Ladrillo extends Chocable {
	
	public Ladrillo(Posicion pos) {
		super(pos);
	}

	@Override
	public void actualizar() {
		super.mover(Direccion.ABAJO);
	}
}
