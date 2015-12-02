package utils;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Vista extends JPanel {
	private final Modelo modelo;
	public Vista(Modelo modelo) {
		this.modelo = modelo;
		setFocusable(true);
	}
	public abstract void actualizarVista();
	public Modelo getModelo() {
		return modelo;
	}
}
