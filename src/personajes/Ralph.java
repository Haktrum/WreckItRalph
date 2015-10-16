package personajes;

import juego.Posicion;

import java.util.Random;

import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;

public class Ralph implements Actualizable {
	
	private Contexto ctx;
	private Posicion pos;
	private int ladrillosRestantes = 40;
	public Ralph(Contexto juego){
		this.ctx = juego;
		this.ctx.agregarActualizable(this);
	}
	public void romperVentanas(){
		ctx.getSeccionActual().romperTodas();
	}
	@Override
	public void actualizar() {
		if (Contexto.randomBoolean(30)) {
			mover();
		}
		if (Contexto.randomBoolean(30)) {
			saltar();
		}
	}
	private void saltar() {
		Random rand = new Random();
		if (ladrillosRestantes > 0) {
			int ladrillos = rand.nextInt(ladrillosRestantes + 1);
			while (ladrillos-- > 0) {
				ctx.agregarActualizable(new Ladrillo(pos, ctx));
			}
		}
	}
	private void mover() {
		Random random = new Random();
		Direccion dir;
		if (random.nextBoolean()) {
			dir = Direccion.IZQUIERDA;
		} else {
			dir = Direccion.DERECHA;
		}
		Posicion nueva = pos.potencial(dir);
		int x = nueva.getX();
		int y = nueva.getY();
		if (x >= 0 && x <= 4 && y >= 0 && y <= 2) {
			pos.go(dir);
		}
	}
}
