package personajes;

import utils.Direccion;
import utils.Evento;
import utils.Evento.EventoID;
import utils.Posicion;
import utils.Utils;

/**
 * Modela al personaje F&eacute;lix del juego
 *
 */
public class Felix extends Chocable {
	/** Tiempo de invulnerabilidad restante */
	private int timerInvulnerable;
	/** Posici&oacute; de F&eacute;lix */
	/** Vidas restantes */
	private int vidas = Utils.vidasPorNivel;

	private final REQ FELIX = new REQ(0, 3);
	private final REQ FELIX_IZQ = new REQ(1, 3);
	private final REQ FELIX_DER = new REQ(2, 3);
	private final REQ FELIX_MARTILLA = new REQ(3, 3);
	private final REQ FELIX_GOLPEADO = new REQ(4, 3);
	private final REQ FELIX_MUERTO = new REQ(5, 10);
	private final REQ FELIX_COME = new REQ(6, 3);

	public Felix() {
		super(new Posicion(0, 0));
		super.agregarImagen("res/img/felix/felix.png");
		super.agregarImagen("res/img/felix/felix_izq.png");
		super.agregarImagen("res/img/felix/felix_der.png");
		super.agregarImagen("res/img/felix/felix_martilla.png");
		super.agregarImagen("res/img/felix/felix_golpeado.png");
		super.agregarImagen("res/img/felix/felix_muerto.png");
		super.agregarImagen("res/img/felix/felix_pastel.png");
	}

	/**
	 * Se mueve dentro de la secci&oacute;n si es posible
	 * 
	 * @param dir
	 *            Direcci&oacute; de movimiento
	 */
	@Override
	public void mover(Direccion dir) {
		if (dir == Direccion.IZQUIERDA || dir == Direccion.ABAJO) {
			super.requests.add(FELIX_IZQ);
		}
		if (dir == Direccion.DERECHA || dir == Direccion.ARRIBA) {
			super.requests.add(FELIX_DER);
		}
		super.pos.go(dir);
	}

	/**
	 * Actualiza el tiempo de invulnerabilidad
	 */
	@Override
	public void actualizar() {
		super.refresh();
		if (timerInvulnerable > 0) {
			timerInvulnerable--;
			if (timerInvulnerable == 0) {
				super.requests.clear();
				super.agregarImagen("res/img/felix/felix.png", 0);
			}
		}
	}

	/**
	 * Lo hace invulnerable
	 */
	private void hacerInvulnerable() {
		timerInvulnerable = Utils.tiempoInvulnerable;
		super.agregarImagen("res/img/felix/felix_invensible.png", 0);
	}

	/**
	 * Invulnerabilidad
	 * 
	 * @return True si es invulnerable, false en caso contrario
	 */
	private boolean esInvulnerable() {
		return timerInvulnerable > 0;
	}

	public void martillar() {
		super.requests.add(FELIX_MARTILLA);
		super.requests.add(FELIX);
	}

	/**
	 * Verifica el choque de dos elementos
	 * 
	 * @param felix
	 *            objeto a chocar
	 */
	public void chequearChoque(Chocable c) throws Evento {
		// boolean subx = Math.abs(c.getSubX()-Utils.cellWidth/2)<
		// c.getVelocidad();
		// boolean suby =
		// Math.abs(c.getSubY()-Utils.cellHeight/2)<c.getVelocidad();
		boolean subx = true;
		boolean suby = true;
		if (pos.equals(c.getPos()) && (subx || suby)) {
			this.chocar(c);
		}
	}

	/**
	 * Simula un choque con un objeto
	 * 
	 * @param c
	 *            Objeto a chocar
	 */
	private void chocar(Chocable c) throws Evento {
		if (!this.esInvulnerable()) {
			if (c.getClass() == Ladrillo.class) {
				vidas--;
				super.requests.add(FELIX_GOLPEADO);
				super.requests.add(FELIX);
				super.requests.add(FELIX_GOLPEADO);
				super.requests.add(FELIX);
				if (vidas < 1) {
					super.requests.add(FELIX_MUERTO);
					// throw new Evento(EventoID.TERMINAJUEGO);
				}
				this.setPos(new Posicion(0, 0));
				timerInvulnerable = (int) Utils.cellHeight / c.getVelocidad();
			}
			if (c.getClass() == Pajaro.class) {
				vidas--;
				super.requests.add(FELIX_GOLPEADO);
				super.requests.add(FELIX);
				super.requests.add(FELIX_GOLPEADO);
				super.requests.add(FELIX);
				if (vidas < 1) {
					super.requests.add(FELIX_MUERTO);
					// throw new Evento(EventoID.TERMINAJUEGO);
				}
				timerInvulnerable = (int) Utils.cellWidth / c.getVelocidad();
			}
		}
		if (c.getClass() == Pastel.class) {
			this.hacerInvulnerable();
			try {
				throw new Evento(EventoID.OFF_SCREEN, c);
			} finally {
				super.requests.add(FELIX_COME);
			}
		}
	}

}
