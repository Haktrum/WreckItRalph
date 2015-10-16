package juego;

import juego.Direccion;

public class Posicion {
	private int x;
	private int y;
	
	public void go(Direccion dir){
		this.x += dir.getX();
		this.y += dir.getY();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Posicion(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Posicion potencial(Direccion dir) {
		return new Posicion(x + dir.getX(), y + dir.getY());
	}
}
