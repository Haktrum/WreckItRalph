package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import juego.Modelo;
import view.MenuItem.NombreBoton;

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
		//config = new MenuItem(NombreBoton.CONFIG, 3, 1);
		config = new Boton("Config", 3, 1);
		this.add(config, config.getGBC());
		items[3] = config;

		// Boton reglas
		//reglas = new MenuItem(NombreBoton.REGLAS, 1, 2);
		reglas = new Boton("reglas", 1, 2);
		this.add(reglas, reglas.getGBC());
		items[0] = reglas;

		// Boton Jugar
		//jugar = new MenuItem(NombreBoton.JUGAR, 3, 2);
		jugar = new Boton("jugar", 3, 2);
		this.add(jugar, jugar.getGBC());
		items[1] = jugar;

		// Boton top
		//top = new MenuItem(NombreBoton.TOP, 5, 2);
		top = new Boton("top", 5, 2);
		this.add(top, top.getGBC());
		items[2] = top;

		// otra config
		this.setPreferredSize(new Dimension(860, 400));
	}

	public void addConfigListener(ActionListener l) {
		config.addActionListener(l);
	}
	
	public void addReglasListener(ActionListener l) {
		reglas.addActionListener(l);
	}
	
	public void addJugarListener(ActionListener l) {
		jugar.addActionListener(l);
		jugar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});
	}
	
	public void addTopListener(ActionListener l) {
		top.addActionListener(l);
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

	public NombreBoton getDestino() {
		return null;
		//return items[selected].getNombre();
	}

	private void repintar() {
		System.out.println("Repintando");
		actualizarVista();
	}

	public void flechaIzq() {
		if (this.selected > 0 && this.selected < 3)
			this.selected--;
		repintar();
	}

	public void flechaDer() {
		if (this.selected < 2)
			this.selected++;
		repintar();
	}

	public void flechaArriba() {
		this.selected = 3;
		repintar();
	}

	public void flechaAbajo() {
		if (this.selected == 3)
			this.selected = 1;
		repintar();
	}

	@Override
	public void actualizarVista() {
		items[selected].requestFocusInWindow();
		for (int i = 0; i < items.length; i++) {
			items[i].setBorderPainted(i == this.selected);
		}
	}
}
