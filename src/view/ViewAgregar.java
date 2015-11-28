package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import modelos.Modelo;
import modelos.ModeloHighscore;


import utils.Loader;
import utils.Utils;

public class ViewAgregar extends View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8230641981464246760L;
	private JTextField input;
	private JLabel error;
	
	
	public ViewAgregar(ModeloHighscore modelo){
		super(modelo);
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150,300,150 };
		gridBagLayout.rowHeights = new int[] { 100, 300,100};
		this.setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Ingresa tu nombre");
		title.setFont(Loader.getFont(30));
		title.setForeground(Color.YELLOW);
		
		input = new JTextField();
		input.setBackground(Color.BLACK);
		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
		input.setFont(Loader.getFont(30));
		input.setForeground(Color.RED);
		input.setFocusable(true);
		input.setPreferredSize(new Dimension(300,40));
		
		error = new JLabel();
		error.setFont(Loader.getFont(14));
		error.setForeground(Color.RED);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		this.add(title,c);
		c.gridy++;
		this.add(input,c);
		c.gridy++;
		this.add(error,c);
		this.setBounds(50, 100, 600, 500);
		SwingUtilities.invokeLater( new Runnable() { 
			public void run() { 
				input.requestFocus(); 
			} 
		});
	}

	public String getInputText(){
		return this.input.getText();
	}
	public void setError(String err){
		error.setText(err);
		input.requestFocus();
	}
	public JTextField getInput(){
		return input;
	}
	@Override
	public void actualizarVista() {
		
	}

}

