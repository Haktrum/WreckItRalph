package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import juego.Modelo;
import utils.Loader;

@SuppressWarnings("serial")
public class ViewConfig extends View {
	private JLabel lbNivel;
	private JLabel dec;
	private JLabel inc;
	
	public ViewConfig(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 330, 60, 80, 60, 330 };
		gridBagLayout.rowHeights = new int[] { 70, 50, 70, 190 };
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();

		JLabel titulo = new JLabel("NIVEL");
		titulo.setForeground(Color.RED);
		titulo.setFont(Loader.getFont());
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		this.add(titulo, c);

		c.gridy++;
		c.gridwidth = 1;

		dec = new JLabel(new ImageIcon(Loader.getImage("res/ui/shift_lt.png")));
		c.anchor = GridBagConstraints.SOUTH;
		this.add(dec, c);

		c.gridx++;
		lbNivel = new JLabel();
		lbNivel.setFont(Loader.getFont());
		lbNivel.setForeground(Color.ORANGE);
		this.add(lbNivel, c);

		c.gridx++;
		inc = new JLabel(new ImageIcon(Loader.getImage("res/ui/shift_rt.png")));
		this.add(inc, c);

		this.setPreferredSize(new Dimension(860, 400));
	}
	
	public void addDecListener(MouseListener l) {
		dec.addMouseListener(l);
	}
	
	public void addIncListener(MouseListener l) {
		inc.addMouseListener(l);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Loader.getFondo(), 0, 0, null);
	}

	@Override
	public void actualizarVista() {
		lbNivel.setText(String.valueOf(getModelo().getNivel()));
	}

}
