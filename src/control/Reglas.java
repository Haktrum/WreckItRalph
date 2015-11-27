package control;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import utils.Evento;
import utils.Evento.EventoID;
import utils.Modelo;
import view.MenuItem.NombreBoton;

public class Reglas implements Modelo{

	private NombreBoton dest = null;
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			dest= NombreBoton.MENU;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public Object[] getInfo() {
		return null;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public NombreBoton getDestino() {
		return dest;
	}

}
