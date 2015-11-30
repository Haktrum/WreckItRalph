package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utils.Utils;
import utils.Vista;

public class AgregarView extends JPanel implements Vista {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel input;
	private JLabel error;

	public AgregarView() {
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 300, 150 };
		gridBagLayout.rowHeights = new int[] { 100, 300, 100 };
		this.setLayout(gridBagLayout);

		JLabel title = new JLabel("Ingresa tu nombre");
		title.setFont(Utils.getFont(30));
		title.setForeground(Color.YELLOW);

		input = new JLabel();
		input.setMinimumSize(new Dimension(300, 50));
		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
		input.setFont(Utils.getFont(20));
		input.setForeground(Color.RED);

		error = new JLabel();
		error.setFont(Utils.getFont(10));
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void setInfo(Object[] args) {
		input.setText((String) args[0]);
		error.setText((String) args[1]);
	}

}
