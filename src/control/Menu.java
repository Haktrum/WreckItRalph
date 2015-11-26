package control;

import graficos.MenuItem.NombreBoton;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import utils.Evento;
import utils.Evento.EventoID;
import utils.Modelo;

public class Menu implements Modelo{
	
	private int selected = 1;
	private NombreBoton dest = null;

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(selected<3 && selected >0)
				selected--;
			break;
		case KeyEvent.VK_RIGHT:
			if(selected<2)
				selected++;
			break;
		case KeyEvent.VK_UP:
			selected = 3;
			break;
		case KeyEvent.VK_DOWN:
			if(selected==3)
				selected=2;
			break;
		case KeyEvent.VK_ENTER:
			if(selected==0){
				dest= NombreBoton.REGLAS;
			}else if(selected==1){
				dest= NombreBoton.JUGAR;
			}else if(selected==2){
				dest= NombreBoton.TOP;
			}else if(selected==3){
				dest= NombreBoton.CONFIG;
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public Object[] getInfo() {
		Object[] res = {selected};
		return res;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public NombreBoton getDestino() {
		// TODO Auto-generated method stub
		return dest;
	}

}
