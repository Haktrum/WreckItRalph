package personajes;

import java.awt.Insets;
import java.awt.Rectangle;

import utils.Direccion;
import utils.Loader;
import utils.Posicion;
import utils.Utils;
import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoOffScreen;

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
		super(new Posicion(0, 0), new Rectangle(24, 51));
		agregarImagen("res/img/felix/felix.png");
		agregarImagen("res/img/felix/felix_izq.png");
		agregarImagen("res/img/felix/felix_der.png");
		agregarImagen("res/img/felix/felix_martilla.png");
		agregarImagen("res/img/felix/felix_golpeado.png");
		agregarImagen("res/img/felix/felix_muerto.png");
		agregarImagen("res/img/felix/felix_pastel.png");
	}

	/**
	 * Se mueve dentro de la secci&oacute;n 
	 * 
	 * @param dir
	 *            Direcci&oacute; de movimiento
	 */
	@Override
	public void mover(Direccion dir) {
		if (dir == Direccion.IZQUIERDA || dir == Direccion.ABAJO) {
			requests.add(FELIX_IZQ);
		}
		if (dir == Direccion.DERECHA || dir == Direccion.ARRIBA) {
			requests.add(FELIX_DER);
		}
		pos.go(dir);
	}

	@Override
	public Insets getMargenes(){
		if(super.imagenActual==3) return new Insets(-5,15,0,0);
		return super.getMargenes();
	}

	/**
	 * Actualiza el tiempo de invulnerabilidad
	 * @throws EventoJuegoTerminado 
	 */
	@Override
	public void actualizar() throws EventoJuegoTerminado {
		super.refresh();
		if (timerInvulnerable > 0) {
			timerInvulnerable--;
		} else {
			requests.clear();
			agregarImagen("res/img/felix/felix.png", 0);
		}
		if(vidas<1 && !super.requests.contains(FELIX_MUERTO)){
			throw new EventoJuegoTerminado();
		}
	}

	/**
	 * Lo hace invulnerable
	 */
	private void hacerInvulnerable() {
		timerInvulnerable = Utils.tiempoInvulnerable;
		agregarImagen("res/img/felix/felix_invensible.png", 0);
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
		requests.add(FELIX_MARTILLA);
		requests.add(FELIX);
		Loader.playSonido("felix/felix_arregla.wav").start();
	}

	/**
	 * Verifica el choque de dos elementos
	 * 
	 * @param felix
	 *            objeto a chocar
	 */
	public void chequearChoque(Chocable c) throws EventoOffScreen {
		if (estaChocando(c)) {
			chocar(c);
		}
	}

	/**
	 * Simula un choque con un objeto
	 * 
	 * @param c
	 *            Objeto a chocar
	 */
	private void chocar(Chocable c) throws EventoOffScreen {
		if (!this.esInvulnerable()) {
			if (c.getClass() == Ladrillo.class) {
				vidas--;
				requests.add(FELIX_GOLPEADO);
				requests.add(FELIX);
				requests.add(FELIX_GOLPEADO);
				requests.add(FELIX);
				Loader.playSonido("felix/choque.wav").start();
				if (vidas < 1) {
					requests.add(FELIX_MUERTO);
					Loader.playSonido("felix/felix_muere.wav").start();
				}
				this.setPos(new Posicion(0, 0));
				timerInvulnerable = (int) Utils.cellHeight / c.getVelocidad();
			}
			if (c.getClass() == Pajaro.class) {
				vidas--;
				requests.add(FELIX_GOLPEADO);
				requests.add(FELIX);
				requests.add(FELIX_GOLPEADO);
				requests.add(FELIX);
				Loader.playSonido("felix/choque.wav").start();
				if (vidas < 1) {
					requests.add(FELIX_MUERTO);
					Loader.playSonido("felix/felix_muere.wav").start();
				}
				timerInvulnerable = (int) Utils.cellWidth / c.getVelocidad();
			}
		}
		if (c.getClass() == Pastel.class) {
			this.hacerInvulnerable();
			try {
				throw new EventoOffScreen();
			} finally {
				requests.add(FELIX_COME);
				Loader.playSonido("felix/come_pastel.wav").start();
			}
		}
	}
	public int getVidas(){
		return vidas;
	}

}
