package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Vista;

@SuppressWarnings("serial")
public class ViewConfig extends JPanel implements Vista{
	private int nivel = 1;
	private JLabel lbNivel;

	public ViewConfig() {
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 330, 60, 80, 60, 330 };
		gridBagLayout.rowHeights = new int[] { 70, 50, 70, 190 };
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();

		JLabel titulo = new JLabel("NIVEL");
		Font fuente = null;
		try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File("res/ui/8-bit.ttf")).deriveFont(Font.PLAIN, 40);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
		setearLb();
		lbNivel.setForeground(Color.ORANGE);
		if (fuente != null)
			lbNivel.setFont(fuente);
		this.add(lbNivel, c);

		c.gridx++;
		JLabel inc = new JLabel(new ImageIcon("res/ui/shift_rt.png"));
		this.add(inc, c);

		this.setBounds(0, 0, 860, 400);
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

	private void setearLb() {
		lbNivel.setText(String.valueOf(nivel));
	}
	@Override
	public void setInfo(Object[] args) {
		nivel = (int) args[0];
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setearLb();
	}
}
