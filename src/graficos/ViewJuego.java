package graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import juego.Ventana;

import personajes.Chocable;
import utils.Actualizable;
import utils.Posicion;
import utils.Utils;
import utils.Vista;

@SuppressWarnings("serial")
public class ViewJuego extends JPanel implements Vista{
	private ArrayList<Chocable> lista;
	private Ventana[][][] mapas;
	private int visualOffset = 0;

	public ViewJuego() {
		this.setBounds(100, 20, 360, 430);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	public void pasarInfo(ArrayList<Chocable> lista, Ventana[][][] mapas) {
		this.lista = lista;
		this.mapas = mapas;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/img/edificio/edificio.png");
		try {
			BufferedImage fondo = ImageIO.read(archivo);
			int y = visualOffset - 498;
			g.drawImage(fondo, 0, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		for (Chocable c : lista) {
			if (c != null) {
				int y = c.getPos().inPx().getY() + c.getMargenes().top;
				int x = c.getPos().inPx().getX() + c.getMargenes().left;
				g.drawImage(c.getImage(), x, y, null);
			}
		}
		// puntos vidas y nivel
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 360, 50);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setInfo(Object[] args) {
		lista = (ArrayList<Chocable>) args[0];
		mapas = (Ventana[][][]) args[1];
		visualOffset = (int) args[2];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
}
