package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import juego.Modelo;

@SuppressWarnings("serial")
public class ViewMenu extends View {
	private int selected = 1;
	private Boton[] items = new Boton[4];
	private Boton config;
	private Boton reglas;
	private Boton jugar;
	private Boton top;

	public ViewMenu(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 260, 20, 260, 20, 260, 20 };
		gridBagLayout.rowHeights = new int[] { 20, 84, 152, 134 };
		this.setLayout(gridBagLayout);
		this.selected = 1;

		// Boton Config
		config = new Boton("Config", 3, 1);
		this.add(config, config.getGBC());
		items[3] = config;

		// Boton reglas
		reglas = new Boton("reglas", 1, 2);
		this.add(reglas, reglas.getGBC());
		items[0] = reglas;

		// Boton Jugar
		jugar = new Boton("jugar", 3, 2);
		this.add(jugar, jugar.getGBC());
		items[1] = jugar;

		// Boton top
		top = new Boton("top", 5, 2);
		this.add(top, top.getGBC());
		items[2] = top;

		// otra config
		this.setPreferredSize(new Dimension(860, 400));
	}
	
	@Override
	public boolean requestFocusInWindow() {
		return jugar.requestFocusInWindow();
	}
	
	public void addConfigListener(ActionListener l) {
		config.addActionListener(l);
	}
	
	public void addReglasListener(ActionListener l) {
		reglas.addActionListener(l);
	}
	
	public void addJugarListener(ActionListener l) {
		jugar.addActionListener(l);
	}
	
	public void addTopListener(ActionListener l) {
		top.addActionListener(l);
	}
	
	@Override
	public synchronized void addKeyListener(KeyListener l) {
		config.addKeyListener(l);
		reglas.addKeyListener(l);
		jugar.addKeyListener(l);
		top.addKeyListener(l);
	}
	
	@Override
	public synchronized void addMouseMotionListener(MouseMotionListener l) {
		config.addMouseMotionListener(l);
		reglas.addMouseMotionListener(l);
		jugar.addMouseMotionListener(l);
		top.addMouseMotionListener(l);
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

	public void flechaIzq() {
		if (this.selected > 0 && this.selected < 3) {
			this.selected--;
			actualizarVista();
		}
	}

	public void flechaDer() {
		if (this.selected < 2) {
			this.selected++;
			actualizarVista();
		}
	}

	public void flechaArriba() {
		if (this.selected != 3) {
			this.selected = 3;
			actualizarVista();
		}
	}

	public void flechaAbajo() {
		if (this.selected == 3) {
			this.selected = 1;
			actualizarVista();
		}
	}

	@Override
	public void actualizarVista() {
		items[selected].requestFocusInWindow();
		for (int i = 0; i < items.length; i++) {
			items[i].setBorderPainted(i == this.selected);
		}
	}

	public void enter() {
		items[this.selected].doClick();
	}

	public void seleccionar(Component component) {
		this.selected = Arrays.asList(items).indexOf(component);
		actualizarVista();
	}
}
