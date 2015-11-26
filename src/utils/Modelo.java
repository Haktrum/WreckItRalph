package utils;

import graficos.MenuItem.NombreBoton;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface Modelo extends KeyListener,ActionListener{
	public Object[] getInfo();
	public NombreBoton getDestino();
}
