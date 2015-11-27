package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

import juego.Modelo;
import utils.Loader;

public class ViewTop extends View {
	private static final long serialVersionUID = 6259571743885667200L;
	private JTextArea jTextArea = new JTextArea();
	
	public ViewTop(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		jTextArea.setEditable(false);
		jTextArea.setFont(Loader.getFont());
		jTextArea.setBackground(Color.BLACK);
		jTextArea.setForeground(Color.RED);
		this.add(jTextArea, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(860, 400));
	}
	
	@Override
	public synchronized void addKeyListener(KeyListener l) {
		super.addKeyListener(l);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Loader.getFondo(), 0, 0, null);
	}
	
	@Override
	public void actualizarVista() {
		jTextArea.setText(getModelo().getHighscore().toString());
	}
}