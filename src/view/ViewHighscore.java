package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import modelo.ModeloHighscore;
import utils.Loader;
import utils.Modelo;
import utils.Vista;

@SuppressWarnings("serial")
public class ViewHighscore extends Vista {
	private JTextArea jTextArea;

	public ViewHighscore(Modelo modelo) {
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 300, 150 };
		gridBagLayout.rowHeights = new int[] { 100, 300 };
		this.setLayout(gridBagLayout);

		jTextArea = new JTextArea();
		jTextArea.setFont(Loader.getFont(20));
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
		this.add(title, c);
		c.gridy++;
		c.fill = GridBagConstraints.VERTICAL;
		this.add(jTextArea, c);
		this.setBounds(100, 50, 600, 400);
	}
	
	@Override
	public void actualizarVista() {
		jTextArea.setText(((ModeloHighscore) getModelo()).toString());
	}
}