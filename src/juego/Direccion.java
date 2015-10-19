package juego;

public enum Direccion {
	ARRIBA("arriba", 0, 1),
	ABAJO("abajo", 0, -1),
	IZQUIERDA("izquierda", -1, 0),
	DERECHA("derecha", 1, 0);
	
	private final int x;
	private final int y;
	private final String nombre;
	
	Direccion(String nombre, int x, int y) {
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getNombre() {
		return nombre;
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
