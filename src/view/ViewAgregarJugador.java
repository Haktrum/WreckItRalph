package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import utils.Loader;
import utils.Modelo;
import utils.Vista;

@SuppressWarnings("serial")
public class ViewAgregarJugador extends Vista {
	private JLabel input;
	private JLabel error;

	public ViewAgregarJugador(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 300, 150 };
		gridBagLayout.rowHeights = new int[] { 100, 300, 100 };
		this.setLayout(gridBagLayout);

		JLabel title = new JLabel("Ingresa tu nombre");
		title.setFont(Loader.getFont(30));
		title.setForeground(Color.YELLOW);

		input = new JLabel();
		input.setMinimumSize(new Dimension(300, 50));
		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
		input.setFont(Loader.getFont(20));
		input.setForeground(Color.RED);
		input.requestFocusInWindow();

		error = new JLabel();
		error.setFont(Loader.getFont(10));
		error.setForeground(Color.RED);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		this.add(title, c);
		c.gridy++;
		this.add(input, c);
		c.gridy++;
		this.add(error, c);
		this.setBounds(50, 100, 600, 500);
	}
	
	public void setError(String error) {
		input.setText(error);
	}

	public synchronized void addTextListener(KeyListener l) {
		input.addKeyListener(l);
	}

	@Override
	public void actualizarVista() {
	}

	public String getNombre() {
		return input.getText();
	}
}
