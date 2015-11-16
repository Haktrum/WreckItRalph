package graficos;

import java.awt.Container;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import utils.Actualizable;

import javax.swing.JFrame;

import juego.Ventana;

import personajes.Chocable;



public class MainWindow implements Actualizable{
	private JFrame frame;
	
	public MainWindow(KeyListener kl) {
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setResizable(false);
		this.frame.setFocusable(true);
		this.frame.requestFocusInWindow();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new ContainerMenu());
		this.frame.addKeyListener(kl);
	}
	public void setContentPane(Container c){
		this.frame.getContentPane().removeAll();
		this.frame.setContentPane(c);
		this.frame.setBounds(0,0,c.getWidth(), c.getHeight());
	}
	
	//tira ir menu o ir juego
	
	@Override
	public void actualizar(){
		Container p = frame.getContentPane();
		if(p instanceof ContainerJuego)
			((ContainerJuego) p).actualizar();
	}
	public void flechaIzq(){
		Container p = frame.getContentPane();
		if(p instanceof ContainerMenu){
			((ContainerMenu) p).flechaIzq();
		}else if(p instanceof ContainerConfig){
			((ContainerConfig) p).cambiarNivel(-1);
		}
	}
	public void flechaDer(){
		Container p = frame.getContentPane();
		if(p instanceof ContainerMenu){
			((ContainerMenu) p).flechaDer();
		}else if(p instanceof ContainerConfig){
			((ContainerConfig) p).cambiarNivel(1);
		}
	}
	public void flechaArriba(){
		Container p = frame.getContentPane();
		if(p instanceof ContainerMenu){
			((ContainerMenu) p).flechaArriba();
		}
	}
	public void flechaAbajo(){
		Container p = frame.getContentPane();
		if(p instanceof ContainerMenu){
			((ContainerMenu) p).flechaAbajo();
		}
	}
	public Object enter(){
		Container p = frame.getContentPane();
		if(p instanceof ContainerConfig){
			return new Integer(((ContainerConfig) p).getNivel());
		}else if(p instanceof ContainerMenu){
			return ((ContainerMenu) p).getDestino();
		}
		return null;
	}
	public void escape(){
		this.setContentPane(new ContainerMenu());
	}
	public void pasarInfo(ArrayList<Chocable> l,Ventana[][] mapa){
		Container p = frame.getContentPane();
		((ContainerJuego) p).pasarInfo(l,mapa);
	}

}
