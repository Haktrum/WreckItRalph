package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

import utils.Vista;

public class MainWindow {
	private JFrame frame;

	public MainWindow() {
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setResizable(true);
		this.frame.setFocusable(true);
		this.frame.requestFocusInWindow();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new ViewMenu());
	}

	public void setContentPane(Vista vista) {
		Container c = (Container) vista;
		this.frame.getContentPane().removeAll();
		this.frame.setContentPane(c);
		this.frame.setBounds(0, 0, c.getWidth(), c.getHeight());
	}
	public void setKeyListener(KeyListener nuevokl){
		for(KeyListener kl : frame.getKeyListeners())
			this.frame.removeKeyListener(kl);
		frame.addKeyListener(nuevokl);
	}
	public void escape() {
		this.setContentPane(new ViewMenu());
	}

	public void setTitulo(String titulo) {
		frame.setTitle(titulo);
	}
}
