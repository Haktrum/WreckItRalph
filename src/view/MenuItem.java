package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import utils.Loader;

@SuppressWarnings("serial")
public class MenuItem extends JButton {
	private GridBagConstraints c;

	public MenuItem(String nombre, int gx, int gy) {
		super(nombre);
		setFont(Loader.getFont(25));
		setBackground(Color.BLACK);
		setForeground(Color.RED);
		setFocusPainted(false);
		c = new GridBagConstraints();
		c.gridx = gx;
		c.gridy = gy;
		c.gridwidth = 1;
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.YELLOW, 5),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		setMargin(new Insets(10, 10, 10, 10));
	}

	public void setWidth(int gwidth) {
		c.gridwidth = gwidth;
	}

	public GridBagConstraints getGBC() {
		return c;
	}
}
