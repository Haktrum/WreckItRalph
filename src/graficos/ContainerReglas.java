package graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utils.Vista;

@SuppressWarnings("serial")
public class ContainerReglas extends JPanel implements Vista{
	public ContainerReglas() {
		this.setBackground(Color.BLACK);
		this.setBounds(0, 0, 570, 421);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/ui/instrucciones.png");
		try {
			Image fondo = ImageIO.read(archivo);
			g.drawImage(fondo, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setInfo(Object[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
