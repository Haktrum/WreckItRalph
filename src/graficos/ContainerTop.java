package graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utils.Vista;

import control.Highscore;

public class ContainerTop extends JPanel implements Vista{
	public ContainerTop() {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		JTextArea jTextArea = new JTextArea(Highscore.leer().toString());
		jTextArea.setEditable(false);
		jTextArea.setBorder(BorderFactory.createDashedBorder(Color.RED));
		// jTextArea.setFont();
		this.add(jTextArea, BorderLayout.CENTER);

	}

	@Override
	public void setInfo(Object[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}