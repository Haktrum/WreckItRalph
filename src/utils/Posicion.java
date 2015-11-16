package utils;

/**
 * Modela una posici&oacute; dentro del juego
 *
 */
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
	/**
	 * Da la posici&oacute;n resultante de moverse
	 * en una dada direcci&oacute;
	 * @param dir Direcci&oacute;on de movimiento
	 * @return	Posici&oacute;n siguiente
	 */
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
	
	public Posicion inPx(){
		return new Posicion(x*20,340-y*20);
	}

}
