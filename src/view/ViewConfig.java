package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modelo.ModeloConfig;
import utils.Loader;
import utils.Modelo;
import utils.Vista;

@SuppressWarnings("serial")
public class ViewConfig extends Vista {
	private JLabel lbNivel;
	private Image fondo;

	public ViewConfig(Modelo modelo) {
		super(modelo);
		fondo = Loader.getFondo();
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 330, 60, 80, 60, 330 };
		gridBagLayout.rowHeights = new int[] { 70, 50, 70, 190 };
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();

		JLabel titulo = new JLabel("NIVEL");
		Font fuente = Loader.getFont(40);
		titulo.setForeground(Color.WHITE);
		if (fuente != null)
			titulo.setFont(fuente);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		this.add(titulo, c);

		c.gridy++;
		c.gridwidth = 1;

		JLabel dec = new JLabel(new ImageIcon("res/ui/shift_lt.png"));
		c.anchor = GridBagConstraints.SOUTH;
		this.add(dec, c);

		c.gridx++;
		lbNivel = new JLabel();
		lbNivel.setForeground(Color.ORANGE);
		if (fuente != null)
			lbNivel.setFont(fuente);
		this.add(lbNivel, c);

		c.gridx++;
		JLabel inc = new JLabel(new ImageIcon("res/ui/shift_rt.png"));
		this.add(inc, c);

		this.setBounds(0, 0, 860, 400);
		actualizarVista();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, null);
	}

	@Override
	public void actualizarVista() {
		lbNivel.setText(String.valueOf(((ModeloConfig) getModelo()).getNivel()));
	}
}
