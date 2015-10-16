package juego;

import personajes.Felix;

public abstract class Chocable implements Actualizable {
	private Posicion pos;
	Contexto ctx;
	public Chocable(Posicion pos, Contexto ctx) {
		this.pos = pos;
		this.ctx = ctx;
		ctx.agregarActualizable(this);
	}
	public void chequearChoque(Felix felix) {
		if (pos.equals(felix.getPos())) {
			felix.chocar(this);
		}
	}
	protected void mover(Direccion dir) {
		pos.go(dir);
		if (dir == Direccion.ABAJO && pos.getY() < 0) {
			ctx.eliminarActualizable(this);
		}
	}
	public Posicion getPos() {
		return pos;
	}
}
