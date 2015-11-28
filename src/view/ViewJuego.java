package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

import modelos.ModeloJuego;
import modelos.Ventana;


import personajes.Chocable;
import utils.Actualizable;
import utils.Loader;
import utils.Posicion;
import utils.Utils;

@SuppressWarnings("serial")
public class ViewJuego extends View implements Actualizable {
	private Ventana[][][] mapas;
	private int offset = 0;
	private int visualOffset = 0;
	private BufferedImage edificio = Loader.getImage("res/img/edificio/edificio.png");

	public ViewJuego(ModeloJuego modelo) {
		super(modelo);
		modelo.init();
		actualizarMapas();
		this.setPreferredSize(new Dimension(460, 450));
		this.setBounds(100, 20, 360, 430);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	
	public void actualizarMapas() {
		this.mapas = ((ModeloJuego) getModelo()).getMapas();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(edificio, 0, visualOffset - 498, null);
		for (int k = 0; k < mapas.length; k++) {
			for (int j = 0; j < Utils.numPisos; j++) {
				for (int i = 0; i < Utils.numCols; i++) {
					Posicion aux = new Posicion(i, k * Utils.numPisos + j);
					BufferedImage imagen = mapas[k][j][i].getImage();
					int y = aux.inPx().getY() + visualOffset;
					// if(k==0 && i==2 && j!=2)
					// y+=15;
					g.drawImage(imagen, aux.inPx().getX(), y, null);
				}
			}
		}
		for (Chocable c : ((ModeloJuego) getModelo()).getChocables()) {
			if (c != null) {
				c.paintComponent(g);
				/*int y = c.getPos().inPx().getY() + c.getSubY();
				int x = c.getPos().inPx().getX() + c.getSubX();
				g.drawImage(c.getImage(), x, y, null);*/
			}
		}
		// puntos vidas y nivel		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 360, 50);
		g.setFont(Loader.getFont(12));
		g.setColor(Color.WHITE);
		g.drawString("vidas*"+ModeloJuego.getInstancia().getFelix().getVidas(), 10, 30);
		g.drawString("puntos*"+ModeloJuego.getInstancia().getPuntaje(), 200, 30);
	}

	@Override
	public void actualizar() {
		this.repaint();
		if (this.offset > this.visualOffset) {
			this.visualOffset += 10;
		}
	}

	public void incOffset() {
		offset += 252;
	}

	public void reset() {
		offset = 0;
		visualOffset = 0;
	}

	@Override
	public void actualizarVista() {
		actualizar();
	}
}
