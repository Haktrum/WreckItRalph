package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Boton extends JButton {
	
	private GridBagConstraints gConstraints;
	private Font font = new Font("8BIT WONDER", Font.BOLD, 25);
	
	public Boton(String text, int gx, int gy) {
		super(text);
		setFont(font);
		//setEnabled(false);
		setBackground(Color.BLACK);
		setForeground(Color.RED);
		setFocusPainted(false);
		gConstraints = new GridBagConstraints();
		gConstraints.gridx = gx;
		gConstraints.gridy = gy;
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.YELLOW, 5),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		setBorderPainted(false);
		this.setMargin(new Insets(10, 10, 10, 10));
	}
	
	public GridBagConstraints getGBC() {
		return gConstraints;
	}
	
	@Override
	public void setBorderPainted(boolean b) {
		super.setBorderPainted(b);
	}
}
