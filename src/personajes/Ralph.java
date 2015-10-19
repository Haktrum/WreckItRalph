package personajes;

import juego.Posicion;

import java.util.Random;

import juego.Actualizable;
import juego.Contexto;
import juego.Direccion;

public class Ralph implements Actualizable {
	
	private Posicion pos;
	private int ladrillosRestantes = 40;
	public Ralph(){
		Contexto.ctx.agregarActualizable(this);
		empezar();
	}
	private void empezar() {
		pos = new Posicion(0, 3);
		romperVentanas();
	}
	public void romperVentanas(){
		Contexto.ctx.getSeccionActual().romperTodas();
	}
	@Override
	public void actualizar() {
		if (Contexto.randomBoolean(30)) {
			//mover();
		}
		if (Contexto.randomBoolean(30)) {
			saltar();
		}
	}
	private void saltar() {
		System.out.print("Ralph salta... ");
		Random rand = new Random();
		if (ladrillosRestantes > 0) {
			int ladrillos = rand.nextInt(ladrillosRestantes + 1);
			ladrillosRestantes -= ladrillos;
			System.out.println("y tira " + ladrillos + " ladrillos");
			while (ladrillos-- > 0) {
				new Ladrillo(pos);
			}
		} else {
			System.out.println("pero no tiene mas ladrillos");
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
