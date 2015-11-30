package utils;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import view.MenuItem.NombreBoton;

public interface Modelo extends KeyListener, ActionListener {
	public Object[] getInfo();

	public NombreBoton getDestino();
}
