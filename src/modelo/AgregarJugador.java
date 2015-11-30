package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import control.Highscore;
import utils.Modelo;
import view.MenuItem.NombreBoton;

public class AgregarJugador implements Modelo {

	private final int puntaje;
	private StringBuilder nombre;
	private NombreBoton dest = null;
	private boolean sobreEscribir = false;
	private String error = "";

	public AgregarJugador(int puntaje) {
		this.puntaje = puntaje;
		this.nombre = new StringBuilder();
	}

	private void submit() throws MalInput {
		if (nombre.length() < 2)
			throw new MalInput(ERR.CORTO);
		Highscore h = new Highscore();
		if (h.yaEsta(new Jugador(nombre.toString())) && !sobreEscribir) {
			sobreEscribir = true;
			throw new MalInput(ERR.YA_EXISTE);
		}
		if (nombre.length() > 20)
			nombre.substring(0, 19);
		dest = NombreBoton.TOP;
	}

	public Jugador getJugador() {
		return new Jugador(nombre.toString(), puntaje);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		error = "";
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			if (nombre.length() > 0)
				nombre.deleteCharAt(nombre.length() - 1);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			dest = NombreBoton.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				submit();
			} catch (MalInput ex) {
				error = ex.getMessage();
			}
		} else {
			sobreEscribir = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (Character.isLetter(e.getKeyChar()))
			nombre.append(e.getKeyChar());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public Object[] getInfo() {
		Object[] res = { nombre.toString(), error };
		return res;
	}

	@Override
	public NombreBoton getDestino() {
		return dest;
	}

	enum ERR {
		CORTO("Minimo dos caracteres"), YA_EXISTE("El nombre ya existe * ENTER para sobreescribir");
		public String msj;

		ERR(String msj) {
			this.msj = msj;
		}
	}

	@SuppressWarnings("serial")
	class MalInput extends Exception {
		private ERR id;

		public MalInput(ERR id) {
			super(id.msj);
			this.id = id;
		}

		public ERR getID() {
			return this.id;
		}
	}
}
