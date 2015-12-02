package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.border.EmptyBorder;

import modelo.ModeloJuego;
import modelo.Ventana;
import personajes.Chocable;
import utils.Loader;
import utils.Modelo;
import utils.Posicion;
import utils.Utils;
import utils.Vista;

@SuppressWarnings("serial")
public class ViewJuego extends Vista implements ActionListener {
	private List<Chocable> chocables;
	private Ventana[][][] ventanas;
	private int vidas = 0;
	private int puntaje = 0;
	private int offset = 0;
	private int visualOffset = 0;
	private Image edificio;

	public ViewJuego(Modelo modelo) {
		super(modelo);
		edificio = Loader.getImage("res/img/edificio/edificio.png");
		this.setBounds(100, 20, 360, 430);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	public void pasarInfo(List<Chocable> lista, Ventana[][][] ventanas) {
		this.chocables = lista;
		this.ventanas = ventanas;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(edificio, 0, visualOffset - 498, null);
		for (int k = 0; k < ventanas.length; k++) {
			for (int j = 0; j < Utils.numPisos; j++) {
				for (int i = 0; i < Utils.numCols; i++) {
					Posicion aux = new Posicion(i, k * Utils.numPisos + j);
					BufferedImage imagen = ventanas[k][j][i].getImage();
					int y = aux.inPx().getY() + visualOffset;
					// if(k==0 && i==2 && j!=2)
					// y+=15;
					g.drawImage(imagen, aux.inPx().getX(), y, null);
				}
			}
		}
		for (Chocable c : chocables) {
			if (c != null) {
				int y = c.getPos().inPx().getY() + c.getMargenes().top;
				int x = c.getPos().inPx().getX() + c.getMargenes().left;
				g.drawImage(c.getImage(), x, y, null);
			}
		}
		// puntos vidas y nivel
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 360, 50);
		g.setFont(Loader.getFont(12));
		g.setColor(Color.WHITE);
		g.drawString("vidas*" + vidas, 10, 30);
		g.drawString("puntos*" + puntaje, 200, 30);
	}

	@Override
	public void actualizarVista() {
		ModeloJuego modelo = (ModeloJuego) getModelo();
		offset = (modelo.getSeccion() - 1) * 252;
		if (offset > visualOffset) {
			visualOffset += 10;
		} else if (offset < visualOffset) {
			visualOffset = (modelo.getSeccion() - 1) * 252;
		}
		chocables = modelo.getChocables();
		ventanas = modelo.getVentanas();
		vidas = modelo.getVidas();
		puntaje = modelo.getPuntaje();
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		actualizarVista();
	}
}
