package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import utils.Loader;
import utils.Modelo;
import utils.Vista;

@SuppressWarnings("serial")
public class ViewMenu extends Vista {
	private MenuItem[] items = new MenuItem[4];
	MenuItem config = null;
	MenuItem reglas = null;
	MenuItem jugar = null;
	MenuItem top = null;
	private int selected;

	public ViewMenu(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 260, 20, 260, 20, 260, 20 };
		gridBagLayout.rowHeights = new int[] { 20, 84, 152, 134 };
		this.setLayout(gridBagLayout);

		// Boton Config
		config = new MenuItem("Config", 3, 1);
		this.add(config, config.getGBC());
		items[3] = config;

		// Boton reglas
		reglas = new MenuItem("Reglas", 1, 2);
		this.add(reglas, reglas.getGBC());
		items[0] = reglas;

		// Boton Jugar
		jugar = new MenuItem("Jugar", 3, 2);
		this.add(jugar, jugar.getGBC());
		jugar.setSelected(true);
		items[1] = jugar;

		// Boton top
		top = new MenuItem("Top", 5, 2);
		this.add(top, top.getGBC());
		items[2] = top;

		// otra config
		this.setBounds(0, 0, 860, 400);
	}

	public void agregarListenerKeys(KeyListener l) {
		config.addKeyListener(l);
		reglas.addKeyListener(l);
		jugar.addKeyListener(l);
		top.addKeyListener(l);
	}
	
	public void agregarListenerConfig(ActionListener l) {
		config.addActionListener(l);
	}
	
	public void agregarListenerReglas(ActionListener l) {
		reglas.addActionListener(l);
	}
	
	public void agregarListenerJugar(ActionListener l) {
		jugar.addActionListener(l);
	}
	
	public void agregarListenerTop(ActionListener l) {
		top.addActionListener(l);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Loader.getFondo(), 0, 0, null);
	}

	@Override
	public void actualizarVista() {
		for (int i = 0; i < items.length; i++) {
			items[i].setSelected(i == this.selected);
		}
	}
}
