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

	public int getY() {
		return y;
	}

	public Posicion(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Posicion(Posicion pos) {
		this(pos.x, pos.y);
	}
	
	public Posicion potencial(Direccion dir) {
		return new Posicion(x + dir.getX(), y + dir.getY());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Posicion) {
			Posicion p = (Posicion) o;
			return x == p.x && y == p.y;
		}
		return false;
	}

}
