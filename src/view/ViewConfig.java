package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import juego.Modelo;

@SuppressWarnings("serial")
public class ViewConfig extends View {
	private JLabel lbNivel;
	private Font font = new Font("8BIT WONDER", Font.BOLD, 25);
	private File file = new File("res/ui/fondo.png");
	private Image fondo;
	private JLabel dec;
	private JLabel inc;
	private Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.YELLOW, 5),
				BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	public ViewConfig(Modelo modelo) {
		super(modelo);
		try {
			fondo = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 330, 60, 80, 60, 330 };
		gridBagLayout.rowHeights = new int[] { 70, 50, 70, 190 };
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();

		JLabel titulo = new JLabel("NIVEL");
		titulo.setForeground(Color.RED);
		titulo.setFont(font);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		this.add(titulo, c);

		c.gridy++;
		c.gridwidth = 1;

		dec = new JLabel(new ImageIcon("res/ui/shift_lt.png"));
		c.anchor = GridBagConstraints.SOUTH;
		this.add(dec, c);

		c.gridx++;
		lbNivel = new JLabel();
		lbNivel.setForeground(Color.ORANGE);
		this.add(lbNivel, c);

		c.gridx++;
		inc = new JLabel(new ImageIcon("res/ui/shift_rt.png"));
		this.add(inc, c);

		this.setPreferredSize(new Dimension(860, 400));
		setIncFocused();
	}
	
	public void addDecListener(MouseListener l) {
		dec.addMouseListener(l);
	}
	
	public void addIncListener(MouseListener l) {
		inc.addMouseListener(l);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, null);
	}

	@Override
	public void actualizarVista() {
		lbNivel.setText(String.valueOf(getModelo().getNivel()));
	}

	public void setIncFocused() {
		inc.setBorder(border);
		dec.setBorder(null);
	}
	
	public void setDecFocused() {
		inc.setBorder(null);
		dec.setBorder(border);
	}

}
