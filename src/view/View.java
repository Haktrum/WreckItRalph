package view;


import javax.swing.JPanel;
import modelos.Modelo;


@SuppressWarnings("serial")
public abstract class View extends JPanel{
	private final Modelo modelo;
	public View(Modelo modelo) {
		this.modelo = modelo;
		setFocusable(true);
	}
	public abstract void actualizarVista();
	
	public Modelo getModelo() {
		return modelo;
	}
}
