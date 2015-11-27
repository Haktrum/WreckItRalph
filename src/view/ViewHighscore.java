package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import utils.Utils;
import utils.Vista;

import control.Highscore;
import control.Jugador;

public class ViewHighscore extends JPanel implements Vista{
	
	private String texto = "";
	private JTextArea jTextArea;
	
	public ViewHighscore() {
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150,300,150 };
		gridBagLayout.rowHeights = new int[] { 100, 300};
		this.setLayout(gridBagLayout);
		
		jTextArea = new JTextArea();
		jTextArea.setFont(Utils.getFont(20));
		jTextArea.setBackground(Color.BLACK);
		jTextArea.setForeground(Color.RED);
		jTextArea.setEditable(false);
		jTextArea.setFocusable(false);
		
		
		JLabel title = new JLabel("Top 5 puntajes");
		title.setFont(Utils.getFont(40));
		title.setForeground(Color.YELLOW);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		this.add(title,c);
		c.gridy++;
		c.fill = GridBagConstraints.VERTICAL;
		this.add(jTextArea, c);
		this.setBounds(100,50,600,400);
	}

	@Override
	public void setInfo(Object[] args) {
		String texto = (String) args[0];
		jTextArea.setText(texto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}