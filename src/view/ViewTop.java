package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.Highscore;

public class ViewTop extends JPanel {
	private JTextArea jTextArea = new JTextArea();
	public ViewTop() {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		actualizar();
		jTextArea.setEditable(false);
		jTextArea.setFont(new Font("Comic Sans", Font.BOLD, 22));
		jTextArea.setBackground(Color.BLACK);
		jTextArea.setForeground(Color.RED);
		this.add(jTextArea, BorderLayout.CENTER);
		this.setPreferredSize(jTextArea.getSize());
	}
	
	public void actualizar() {
		jTextArea.setText(Highscore.leer().toString());
	}
}