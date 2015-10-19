package personajes;

import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;
import juego.Posicion;

public abstract class Chocable implements Actualizable {
	private Posicion pos;
	public Chocable(Posicion pos) {
		this.pos = pos;
		Contexto.ctx.agregarActualizable(this);
	}
	public void chequearChoque(Felix felix) {
		if (pos.equals(felix.getPos())) {
			felix.chocar(this);
		}
	}
	protected void mover(Direccion dir) {
		pos.go(dir);
		if (dir == Direccion.ABAJO && pos.getY() < 0) {
			Contexto.ctx.eliminarActualizable(this);
		}
	}
	public Posicion getPos() {
		return pos;
	}
}
