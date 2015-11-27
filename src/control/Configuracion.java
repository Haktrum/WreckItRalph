package control;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import utils.Modelo;
import view.MenuItem.NombreBoton;

public class Configuracion implements Modelo{

	private int nivel = 1;
	private NombreBoton dest = null;
	
	@Override
	public Object[] getInfo() {
		Object[] res = {nivel};
		return res;
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(nivel>1)
				nivel--;
			break;
		case KeyEvent.VK_RIGHT:
			if(nivel<10)
				nivel++;
			break;
		case KeyEvent.VK_ENTER:
			dest = NombreBoton.MENU;
			break;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public NombreBoton getDestino() {
		return dest;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public int getNivel(){
		return this.nivel;
	}
	
}
