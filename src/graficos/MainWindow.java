package graficos;

import java.awt.Container;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MainWindow {
	private JFrame frame;

	public MainWindow(KeyListener kl) {
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setResizable(true);
		this.frame.setFocusable(true);
		this.frame.requestFocusInWindow();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new ContainerMenu());
		this.frame.addKeyListener(kl);
	}

	public void setContentPane(Container c) {
		this.frame.getContentPane().removeAll();
		this.frame.setContentPane(c);
		this.frame.setBounds(0, 0, c.getWidth(), c.getHeight());
	}

	// tira ir menu o ir juego

	public void flechaIzq() {
		Container p = frame.getContentPane();
		if (p instanceof ContainerMenu) {
			((ContainerMenu) p).flechaIzq();
		} else if (p instanceof ContainerConfig) {
			((ContainerConfig) p).cambiarNivel(-1);
		}
	}

	public void flechaDer() {
		Container p = frame.getContentPane();
		if (p instanceof ContainerMenu) {
			((ContainerMenu) p).flechaDer();
		} else if (p instanceof ContainerConfig) {
			((ContainerConfig) p).cambiarNivel(1);
		}
	}

	public void flechaArriba() {
		Container p = frame.getContentPane();
		if (p instanceof ContainerMenu) {
			((ContainerMenu) p).flechaArriba();
		}
	}

	public void flechaAbajo() {
		Container p = frame.getContentPane();
		if (p instanceof ContainerMenu) {
			((ContainerMenu) p).flechaAbajo();
		}
	}

	public Object enter() {
		Container p = frame.getContentPane();
		if (p instanceof ContainerConfig) {
			return new Integer(((ContainerConfig) p).getNivel());
		} else if (p instanceof ContainerMenu) {
			return ((ContainerMenu) p).getDestino();
		}
		return null;
	}

	public void escape() {
		this.setContentPane(new ContainerMenu());
	}

	public void setTitulo(String titulo) {
		frame.setTitle(titulo);
	}
}
