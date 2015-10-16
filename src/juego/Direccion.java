package juego;

public enum Direccion {
	ARRIBA(0, 1),
	ABAJO(0, -1),
	IZQUIERDA(-1, 0),
	DERECHA(1, 0);
	
	private final int x;
	private final int y;
	
	Direccion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direccion opuesta() {
		switch (this) {
		case ARRIBA:
			return ABAJO;
		case ABAJO:
			return ARRIBA;
		case DERECHA:
			return IZQUIERDA;
		case IZQUIERDA:
			return DERECHA;
		}
		return null;
	}
}
