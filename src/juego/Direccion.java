package juego;

	/**
	 * Modela las direcciones
	 * de movimiento de los personajes
	 *
	 */
public enum Direccion {
	ARRIBA("arriba", 0, 1),
	ABAJO("abajo", 0, -1),
	IZQUIERDA("izquierda", -1, 0),
	DERECHA("derecha", 1, 0);
	
	/** Direcci&oacute;n en x */
	private final int x;
	/** Direcci&oacute;n en x */
	private final int y;
	/** Nombre de la direcci&oacute;n */
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
	/**
	 * Devuelve la direcci&oacute;n opuesta
	 * a esta direcci&oacute;
	 * @return direcci&oacute; opuesta
	 */
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
