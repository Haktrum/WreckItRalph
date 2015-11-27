package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import juego.Modelo;

@SuppressWarnings("serial")
public class ViewReglas extends View {

	public ViewReglas(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		this.setBounds(0, 0, 570, 421);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/ui/instrucciones.png");
		Image fondo = null;
		try {
			fondo = ImageIO.read(archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(fondo, 0, 0, null);
	}

	@Override
	public void actualizarVista() {
		// TODO Auto-generated method stub
		
	}
}
