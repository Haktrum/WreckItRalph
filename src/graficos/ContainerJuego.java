package graficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import juego.Ventana;

import personajes.Chocable;
import personajes.Felix;
import personajes.Ladrillo;
import personajes.Ralph;

import utils.Actualizable;
import utils.Posicion;
import utils.Utils;

public class ContainerJuego extends JPanel implements Actualizable{
	private ArrayList<Chocable> lista;
	private Ventana[][][] mapas;
	private int offset = 0;
	private int visualOffset = 0;
	public ContainerJuego(ArrayList<Chocable> lista,Ventana[][][] mapas){
		super();
		pasarInfo(lista,mapas);		
		this.setBounds(100,20,360,380);
		this.setBorder(new EmptyBorder(0,0,0,0));
	}
	public void pasarInfo(ArrayList<Chocable> lista,Ventana[][][] mapas){
		this.lista = lista;
		this.mapas = mapas;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/img/edificio/edificio.png");
		try {
			BufferedImage fondo = ImageIO.read(archivo);
			int y = visualOffset-548;
			g.drawImage(fondo, 0, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int k = 0;k<mapas.length;k++){
			for(int j = 0;j<Utils.numPisos;j++){
				for(int i = 0;i<Utils.numCols;i++){
					Posicion aux = new Posicion(i,k*Utils.numPisos+j);
					BufferedImage imagen = mapas[k][j][i].getImage();
					int y = aux.inPx().getY()+visualOffset;
					//if(k==0 && i==2 && j!=2)
						//y+=15;
					g.drawImage(imagen,aux.inPx().getX(),y, null);
				}
			}
		}
		for(Chocable c: lista){
			if(c!=null){
				int y = c.getPos().inPx().getY()+c.getSubY();
				int x = c.getPos().inPx().getX()+c.getSubX();
				g.drawImage(c.getImage(),x, y, null);
			}
		}
	}
	
	private Posicion inPx(Posicion pos,int oWidth, int oHeight){
		int x = pos.getX();
		int y = pos.getY();
		return new Posicion(242+30+50*x,640-78-100*y);
	}
	
	@Override
	public void actualizar() {
		this.repaint();
		if(this.offset>this.visualOffset){
			this.visualOffset+=10;
		}
	}
	public void incOffset(){
		offset+=252;
	}
	public void reset(){
		offset=0;
		visualOffset=0;
	}
}
