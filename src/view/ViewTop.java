package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import control.Highscore;

import modelos.ModeloHighscore;

import utils.Loader;

public class ViewTop extends View {
	private static final long serialVersionUID = 6259571743885667200L;
private JTextArea jTextArea;
	
	public ViewTop(ModeloHighscore modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150,300,150 };
		gridBagLayout.rowHeights = new int[] { 100, 300};
		this.setLayout(gridBagLayout);
		
		jTextArea = new JTextArea();
		jTextArea.setFont(Loader.getFont(15));
		jTextArea.setBackground(Color.BLACK);
		jTextArea.setForeground(Color.RED);
		jTextArea.setEditable(false);
		jTextArea.setFocusable(false);
		
		
		JLabel title = new JLabel("Top 5 puntajes");
		title.setFont(Loader.getFont(40));
		title.setForeground(Color.YELLOW);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		this.add(title,c);
		c.gridy++;
		c.fill = GridBagConstraints.VERTICAL;
		this.add(jTextArea, c);
		this.setBounds(100,50,600,400);
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
		Highscore h = new Highscore();
		jTextArea.setText(h.toString());
	}
}