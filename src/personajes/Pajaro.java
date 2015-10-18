package personajes;

import juego.Direccion;
import juego.Posicion;

public class Pajaro extends Chocable {
	public Pajaro(Posicion pos) {
		super(pos);
	}

	private Direccion dir;

	@Override
	public void actualizar() {
		Posicion pos = super.getPos().potencial(dir);
		int x = pos.getX();
		int y = pos.getY();
		if (x < -1 || x > 5 || y < -1 || y > 3) {
			dir = dir.opuesta();
		}
		super.mover(dir);
	}
}
