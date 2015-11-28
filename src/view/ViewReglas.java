package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelos.ModeloJuego;

@SuppressWarnings("serial")
public class ViewReglas extends View {

	public ViewReglas(ModeloJuego modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(570, 421));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/ui/instrucciones.png");
		Image fondo = null;
		try {
			fondo = ImageIO.read(archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(fondo, 0, 0, null);
	}

	@Override
	public void actualizarVista() {
	}
}
