package graficos;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import juego.Ventana;

import personajes.Chocable;

import utils.Actualizable;
import utils.Posicion;
import utils.Utils;

public class ContainerJuego extends JPanel implements Actualizable{
	private ArrayList<Chocable> lista;
	private Ventana[][] mapa;
	public ContainerJuego(ArrayList<Chocable> lista,Ventana[][] mapa){
		super();
		pasarInfo(lista,mapa);
		this.setBounds(0,0,800,340);
	}
	public void pasarInfo(ArrayList<Chocable> lista,Ventana[][] mapa){
		this.lista = lista;
		this.mapa = mapa;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/img/edificio/edificio_150_seccion1.png");
		try {
			Image fondo = ImageIO.read(archivo);
			g.drawImage(fondo, 242, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int j = 0;j<Utils.numPisos;j++){
			for(int i = 0;i<Utils.numCols;i++){
				Posicion aux = new Posicion(i,j);
				if(mapa[j][i]==null){ System.out.println("null"); continue;}
				g.drawImage(mapa[j][i].getImage(), inPx(aux).getX(), inPx(aux).getY(), null);
			}
		}
		for(Chocable c: lista){
			if(c!=null){
				//System.out.println(c.toString() +" = "+ inPx(c.getPos()).getX() +","+ inPx(c.getPos()).getY());
				//System.out.println(c.getImage());
				g.drawImage(c.getImage(), inPx(c.getPos()).getX(), inPx(c.getPos()).getY(), null);
			}
		}
	}
	
	private Posicion inPx(Posicion pos){
		int x = pos.getX();
		int y = pos.getY();
		return new Posicion(242+30+50*x,340-78-100*y);
	}
	@Override
	public void actualizar() {
		this.repaint();
	}
}
