package personajes;

import juego.Posicion;
import juego.Contexto;

public class Ralph {
	
	private Contexto ctx;
	private Posicion pos;
	public Ralph(Contexto juego){
		this.ctx = juego;
	}
	public void romperVentanas(){
		ctx.getSeccion_actual().romperTodas();
	}
}
