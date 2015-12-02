package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.ModeloHighscore;
import view.ViewHighscore;

/**
 * Maneja los 10 puntajes m&aacute;s altos obtenidos en el juego
 */
public class ControladorHighscore extends Controlador {
	public ControladorHighscore(ModeloHighscore modelo, ViewHighscore view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void addListeners() {
		getView().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_ESCAPE:
					WreckItRalph.getInstancia().crearMenu();
				}
			}
		});
	}

}
