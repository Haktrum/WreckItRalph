package view;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MenuItem extends JButton {
	private GridBagConstraints c;
	private final NombreBoton nombre;

	public MenuItem(NombreBoton nombre, int gx, int gy) {
		super(new ImageIcon("res/ui/" + nombre.url + "_hover.png"));
		// this.setSelectedIcon(new
		// ImageIcon("res/ui/"+nombre.url+"_hover.png"));
		//this.setEnabled(false);
		this.setDisabledIcon(new ImageIcon("res/ui/" + nombre.url + ".png"));
		this.setDisabledSelectedIcon(new ImageIcon("res/ui/" + nombre.url + "_hover.png"));
		// this.setRolloverIcon(new ImageIcon("res/"+nombre.url+"_hover.png"));
		c = new GridBagConstraints();
		c.gridx = gx;
		c.gridy = gy;
		c.gridwidth = 1;
		this.nombre = nombre;
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);

	}

	public void setWidth(int gwidth) {
		c.gridwidth = gwidth;
	}

	public GridBagConstraints getGBC() {
		return c;
	}

	public enum NombreBoton {
		CONFIG("bt_conf"), REGLAS("bt_reglas"), JUGAR("bt_jugar"), TOP("bt_top");
		private String url;

		NombreBoton(String url) {
			this.url = url;
		}
	}

	public NombreBoton getNombre() {
		return nombre;
	}
}
