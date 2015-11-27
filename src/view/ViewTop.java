package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import juego.Modelo;

public class ViewTop extends View {
	private static final long serialVersionUID = 6259571743885667200L;
	private JTextArea jTextArea = new JTextArea();
	
	public ViewTop(Modelo modelo) {
		super(modelo);
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
		jTextArea.setText(getModelo().getHighscore().toString());
	}

	@Override
	public void actualizarVista() {
		// TODO Auto-generated method stub
		
	}
}