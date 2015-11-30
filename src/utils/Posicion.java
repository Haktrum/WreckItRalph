package utils;

/**
 * Modela una posici&oacute; dentro del juego
 *
 */
public class Posicion {
	private int x;
	private int y;
	private int subx;
	private int suby;

	public void go(Direccion dir) {
		this.x += dir.getX();
		this.y += dir.getY();
		this.subx = 0;
		this.suby = 0;
	}

	public void subGo(Direccion dir, int vel) {
		this.subx += dir.getX() * vel;
		if (subx >= Utils.cellWidth) {
			x += dir.getX();
			subx -= Utils.cellWidth;
		} else if (subx < 0) {
			x += dir.getX();
			subx += Utils.cellWidth;
		}
		this.suby += dir.getY() * vel;
		if (suby >= Utils.cellHeight) {
			y += dir.getY();
			suby -= Utils.cellHeight;
		} else if (suby < 0) {
			y += dir.getY();
			suby += Utils.cellHeight;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSubY() {
		return suby;
	}

	public int getSubX() {
		return subx;
	}

	public void setSubX(int x) {
		this.subx = x;
	}

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
		this.subx = 0;
		this.suby = 0;
	}

	public Posicion(Posicion pos) {
		this(pos.x, pos.y);
	}

	/**
	 * Da la posici&oacute;n resultante de moverse en una dada direcci&oacute;
	 * 
	 * @param dir
	 *            Direcci&oacute;on de movimiento
	 * @return Posici&oacute;n siguiente
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

	public Posicion inPx() {
		return new Posicion(25 + x * Utils.cellWidth + subx, 410 - (Utils.cellHeight * (y + 1) + suby));
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ") - (" + subx + "," + suby + ")";
	}
}
