package view;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

import utils.Vista;

public class MainWindow {
	private JFrame frame;
	private static MainWindow instancia = null;

	public MainWindow() {
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setResizable(true);
		this.frame.setFocusable(false);
		this.frame.requestFocusInWindow();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				frame.getContentPane().requestFocusInWindow();
			}
		});
	}

	public void setContentPane(Vista vista) {
		this.frame.getContentPane().removeAll();
		this.frame.setContentPane(vista);
		this.frame.setBounds(0, 0, vista.getWidth(), vista.getHeight());
		vista.requestFocusInWindow();
		vista.actualizarVista();
	}

	public void setKeyListener(KeyListener nuevokl) {
		for (KeyListener kl : frame.getKeyListeners())
			this.frame.removeKeyListener(kl);
		frame.addKeyListener(nuevokl);
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
}
