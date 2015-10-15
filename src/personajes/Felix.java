package personajes;

import juego.Actualizable;
import juego.Contexto.Direccion;
import juego.Posicion;
import juego.Contexto;

public class Felix implements Actualizable{
	private Contexto ctx;
	private int timer;
	private Posicion pos;
	private int vidas;
	
	public Felix(Contexto juego){
		this.ctx = juego;
		this.pos = new Posicion(0,0);
	}
	
	public void mover(Direccion dir){
		Posicion nueva = pos;
		nueva.go(dir);
		if(ctx.getSeccion_actual().puedoIr(pos,nueva)){
			ctx.getSeccion_actual().VentanaEn(pos).felixesta=false;
			pos.go(dir);
		}
		ctx.getSeccion_actual().VentanaEn(pos).felixesta=true;
	}
	
	public void arreglarVentana(){
		int p = ctx.getSeccion_actual().arreglarVentana(pos);
		ctx.agregarPuntos(p);
	}
	@Override
	public void actualizar() {
		
	}
}
