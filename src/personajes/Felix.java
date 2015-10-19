package personajes;

import juego.Actualizable;
import juego.Direccion;
import juego.Posicion;
import juego.Contexto;

public class Felix implements Actualizable {
	private int timerInvulnerable;
	private Posicion pos;
	private int vidas = 3;
	
	public Felix(){
		this.pos = new Posicion(0,0);
	}
	
	public void mover(Direccion dir){
		if(Contexto.ctx.getSeccionActual().puedoIr(pos, dir)){
			Contexto.ctx.getSeccionActual().VentanaEn(pos).felixesta=false;
			pos.go(dir);
			System.out.println("Felix se mueve hacia " + dir.getNombre());
		} else {
			System.out.println("Camino bloqueado hacia " + dir.getNombre());
		}
		Contexto.ctx.getSeccionActual().VentanaEn(pos).felixesta=true;
	}
	
	public void arreglarVentana(){
		System.out.println("Felix martilla la ventana");
		int p = Contexto.ctx.getSeccionActual().arreglarVentana(pos);
		Contexto.ctx.agregarPuntos(p);
	}
	@Override
	public void actualizar() {
		if (timerInvulnerable > 0) {
			timerInvulnerable--;
		}
		if (Contexto.ctx.getSeccionActual().VentanaEn(pos).comerPastel()) {
			hacerInvulnerable();
		}
	}
	public void hacerInvulnerable() {
		timerInvulnerable = 100;
	}
	public boolean esInvulnerable() {
		return timerInvulnerable > 0;
	}
	
	public void chocar(Chocable c) {
		if (c.getClass() == Ladrillo.class) {
			vidas--;
			Contexto.ctx.reiniciarNivel();
			System.out.println("A Felix le cae un ladrillo");
		}
		pos = new Posicion(0, 0);
	}

	public Posicion getPos() {
		return pos;
	}
	public int getVidas() {
		return vidas;
	}
}
