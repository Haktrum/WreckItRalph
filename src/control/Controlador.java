package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import utils.Direccion;
import utils.Evento;

import graficos.ContainerConfig;
import graficos.ContainerJuego;
import graficos.ContainerMenu;
import graficos.ContainerReglas;
import graficos.ContainerTop;
import graficos.MainWindow;
import graficos.MenuItem.NombreBoton;

import juego.Contexto;

public class Controlador implements ActionListener{
	private MainWindow window =null;
	private boolean corriendo = false;
	private Contexto ctx = null;
	private int nivelInicial = 1;
	public Controlador(){
		//this.irMenu();
		KeyListener kl = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()){
					case KeyEvent.VK_LEFT:
						flechaIzq();
						break;
					case KeyEvent.VK_RIGHT:
						flechaDer();
						break;
					case KeyEvent.VK_UP:
						flechaArriba();
						break;
					case KeyEvent.VK_DOWN:
						flechaAbajo();
						break;
					case KeyEvent.VK_ENTER:
						enter();
						break;
					case KeyEvent.VK_ESCAPE:
						escape();
						break;
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			
			@Override
			public void keyTyped(KeyEvent arg0) {}
		};
		window = new MainWindow(kl);
	}
	public void setCorriendo(boolean b){
		corriendo = b;
	}
	public boolean isCorriendo(){
		return corriendo;
	}
	
	private void flechaIzq(){
		window.flechaIzq();
		if(ctx!=null){
			ctx.moverFelix(Direccion.IZQUIERDA);
		}
	}
	private void flechaDer(){
		window.flechaDer();
		if(ctx!=null){
			ctx.moverFelix(Direccion.DERECHA);
		}
	}
	private void flechaArriba(){
		window.flechaArriba();
		if(ctx!=null){
			ctx.moverFelix(Direccion.ARRIBA);
		}
	}
	private void flechaAbajo(){
		window.flechaAbajo();
		if(ctx!=null){
			ctx.moverFelix(Direccion.ABAJO);
		}
	}
	private void enter(){
		Object res = window.enter();
		if(res instanceof Integer){
			this.nivelInicial = (int) res;
			window.setContentPane(new ContainerMenu());
			//System.out.println(nivelInicial);
		}else if(res instanceof NombreBoton){
			switch(((NombreBoton) res)){
			case CONFIG:
				window.setContentPane(new ContainerConfig());
				break;
			case JUGAR:
				ctx = new Contexto(nivelInicial);
				window.setContentPane(new ContainerJuego(ctx.getChocables(),ctx.getMapa()));
				break;
			case REGLAS:
				window.setContentPane(new ContainerReglas());
				break;
			case TOP:
				window.setContentPane(new ContainerTop());
				break;
			}
		}
	}
	private void escape(){
		window.escape();
		ctx = null;
		this.corriendo = false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		window.actualizar();
		if(ctx!=null){
			try {
				ctx.actualizar();
				window.pasarInfo(ctx.getChocables(),ctx.getMapa());
			} catch (Evento excep) {
				//e.printStackTrace();
			}
		}
	}
	
}
