package view;

import javax.swing.JPanel;

import juego.Modelo;

@SuppressWarnings("serial")
public abstract class View extends JPanel {
	private final Modelo modelo;
	public View(Modelo modelo) {
		this.modelo = modelo;
	}
	public abstract void actualizarVista();
	public Modelo getModelo() {
		return modelo;
	}
}
