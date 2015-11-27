package view;

import java.awt.Container;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MainWindow {
	private JFrame frame;
	private static MainWindow instancia;

	public JFrame getFrame() {
		return frame;
	}
	
	private MainWindow() {
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setResizable(true);
		this.frame.setFocusable(true);
		this.frame.requestFocusInWindow();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setContentPane(new ViewMenu());
	}

	public void setContentPane(Container c) {
		this.frame.getContentPane().removeAll();
		this.frame.setContentPane(c);
		this.frame.setPreferredSize(c.getPreferredSize());
		this.frame.setBounds(0, 0, c.getWidth(), c.getHeight());
		c.requestFocusInWindow();
		frame.requestFocusInWindow();
		this.frame.pack();
	}

	// tira ir menu o ir juego

	public void flechaIzq() {
		Container p = frame.getContentPane();
		if (p instanceof ViewMenu) {
			((ViewMenu) p).flechaIzq();
		} else if (p instanceof ViewConfig) {
			//((ViewConfig) p).cambiarNivel(-1);
		}
	}

	public void flechaDer() {
		Container p = frame.getContentPane();
		if (p instanceof ViewMenu) {
			((ViewMenu) p).flechaDer();
		} else if (p instanceof ViewConfig) {
			//((ViewConfig) p).cambiarNivel(1);
		}
	}

	public void flechaArriba() {
		Container p = frame.getContentPane();
		if (p instanceof ViewMenu) {
			((ViewMenu) p).flechaArriba();
		}
	}

	public void flechaAbajo() {
		Container p = frame.getContentPane();
		if (p instanceof ViewMenu) {
			((ViewMenu) p).flechaAbajo();
		}
	}

	public Object enter() {
		Container p = frame.getContentPane();
		if (p instanceof ViewConfig) {
			//return new Integer(((ViewConfig) p).getNivel());
		} else if (p instanceof ViewMenu) {
			return ((ViewMenu) p).getDestino();
		}
		return null;
	}

	public void escape() {
		//this.setContentPane(new ViewMenu());
	}

	public void setTitulo(String titulo) {
		frame.setTitle(titulo);
	}

	public static MainWindow getInstancia() {
		if (instancia == null) {
			instancia = new MainWindow();
		}
		return instancia;
	}

	public void setView(View view) {
		setContentPane(view);
	}
}
