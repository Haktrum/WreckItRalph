package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utils.Vista;
import view.MenuItem.NombreBoton;

@SuppressWarnings("serial")
public class ViewMenu extends JPanel implements Vista{
	private MenuItem[] items = new MenuItem[4];
	private int selected;

	public ViewMenu() {
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 260, 20, 260, 20, 260, 20 };
		gridBagLayout.rowHeights = new int[] { 20, 84, 152, 134 };
		this.setLayout(gridBagLayout);

		// Boton Config
		MenuItem config = new MenuItem(NombreBoton.CONFIG, 3, 1);
		this.add(config, config.getGBC());
		items[3] = config;

		// Boton reglas
		MenuItem reglas = new MenuItem(NombreBoton.REGLAS, 1, 2);
		this.add(reglas, reglas.getGBC());
		items[0] = reglas;

		// Boton Jugar
		MenuItem jugar = new MenuItem(NombreBoton.JUGAR, 3, 2);
		this.add(jugar, jugar.getGBC());
		jugar.setSelected(true);
		items[1] = jugar;

		// Boton top
		MenuItem top = new MenuItem(NombreBoton.TOP, 5, 2);
		this.add(top, top.getGBC());
		items[2] = top;

		// otra config
		this.setBounds(0, 0, 860, 400);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File archivo = new File("res/ui/fondo.png");
		try {
			Image fondo = ImageIO.read(archivo);
			g.drawImage(fondo, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void repintar() {
		for (int i = 0; i < items.length; i++)
			items[i].setSelected(i == this.selected);
	}

	@Override
	public void setInfo(Object[] args) {
		selected = (int) args[0];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repintar();
	}
}
