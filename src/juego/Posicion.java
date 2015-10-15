package juego;

import juego.Contexto.Direccion;

public class Posicion {
	private int x;
	private int y;
	
	public void go(Direccion dir){
		switch(dir){
			case UP:
				y++;
			break;
			case LEFT:
				x--;
			break;
			case DOWN:
				y--;
			break;
			case RIGHT:
				x++;
			break;
		}
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
}
