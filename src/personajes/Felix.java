package personajes;

import juego.Actualizable;
import juego.Chocable;
import juego.Direccion;
import juego.Posicion;
import juego.Contexto;

public class Felix implements Actualizable {
	private Contexto ctx;
	private int timerInvulnerable;
	private Posicion pos;
	private int vidas = 3;
	
	public Felix(Contexto juego){
		this.ctx = juego;
		this.pos = new Posicion(0,0);
	}
	
	public void mover(Direccion dir){
		if(ctx.getSeccionActual().puedoIr(pos, dir)){
			ctx.getSeccionActual().VentanaEn(pos).felixesta=false;
			pos.go(dir);
		}
		ctx.getSeccionActual().VentanaEn(pos).felixesta=true;
	}
	
	public void arreglarVentana(){
		int p = ctx.getSeccionActual().arreglarVentana(pos);
		ctx.agregarPuntos(p);
	}
	@Override
	public void actualizar() {
		if (timerInvulnerable > 0) {
			timerInvulnerable--;
		}
		if (ctx.getSeccionActual().VentanaEn(pos).comerPastel()) {
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
			ctx.reiniciarNivel();
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
