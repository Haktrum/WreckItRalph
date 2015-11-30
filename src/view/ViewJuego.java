package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ViewJuego extends View implements ActionListener {
	private Ventana[][][] mapas;
	private int offset = 0;
	private int visualOffset = 0;
	private BufferedImage edificio = Loader.getImage("res/img/edificio/edificio.png");

	public ViewJuego(ModeloJuego modelo) {
		super(modelo);
		modelo.init();
		actualizarMapas();
		this.setPreferredSize(new Dimension(Utils.juegoWidth, Utils.juegoHeight));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	
	public void actualizarMapas() {
		this.mapas = ((ModeloJuego) getModelo()).getMapas();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(edificio, 0, visualOffset - (edificio.getHeight()-Utils.juegoHeight), null);
		for (int k = 0; k < mapas.length; k++) {
			for (int j = 0; j < Utils.numPisos; j++) {
				for (int i = 0; i < Utils.numCols; i++) {
					Posicion aux = new Posicion(i, k * Utils.numPisos + j);
					BufferedImage imagen = mapas[k][j][i].getImage();
					int y = aux.inPx().getY() + visualOffset;
					g.drawImage(imagen, aux.inPx().getX(), y, null);
				}
			}
		}
		for (Chocable c : ((ModeloJuego) getModelo()).getChocables()) {
			if (c != null) {
				c.paintComponent(g);
			}
		}
		// puntos vidas y nivel		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Utils.juegoWidth, 50);
		g.setFont(Loader.getFont(12));
		g.setColor(Color.WHITE);
		g.drawString("vidas*"+ModeloJuego.getInstancia().getFelix().getVidas(), Utils.margenIzq, 30);
		g.drawString("puntos*"+ModeloJuego.getInstancia().getPuntaje(), Utils.margenIzq+200, 30);
	}

	public void incOffset() {
		offset += 252;
	}

	public void reset() {
		offset = 0;
		visualOffset = 0;
		this.actualizarMapas();
	}

	@Override
	public void actualizarVista() {
		this.repaint();
		if (this.offset > this.visualOffset) {
			this.visualOffset += 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.actualizarVista();
	}
}
