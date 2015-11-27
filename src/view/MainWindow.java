package view;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;

public class MainWindow {
	private JFrame frame;
	private static MainWindow instancia;

	private MainWindow() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				frame.requestFocusInWindow();
			}
		});
	}
	
	public void setContentPane(View view) {
		frame.getContentPane().removeAll();
		frame.setContentPane(view);
		frame.setPreferredSize(view.getPreferredSize());
		view.actualizarVista();
		view.requestFocusInWindow();
		frame.pack();
	}

	public void setTitulo(String titulo) {
		frame.setTitle(titulo);
	}

	public static MainWindow getInstancia() {
		if (instancia == null) {
			instancia = new MainWindow();
		}
		return instancia;
	}
}
