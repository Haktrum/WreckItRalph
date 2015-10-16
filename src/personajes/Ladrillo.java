package personajes;

import juego.Chocable;
import juego.Contexto;
import juego.Direccion;
import juego.Posicion;

public class Ladrillo extends Chocable {
	
	public Ladrillo(Posicion pos, Contexto ctx) {
		super(pos, ctx);
	}

	@Override
	public void actualizar() {
		super.mover(Direccion.ABAJO);
	}
}
