package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import modelo.ModeloHighscore;
import modelo.ModeloHighscore.MalInput;
import view.ViewAgregarJugador;

public class ControladorAgregarJugador extends Controlador {

	public ControladorAgregarJugador(ModeloHighscore modelo, ViewAgregarJugador view) {
		super(modelo, view);
		addListeners();
	}
	
	public ControladorAgregarJugador(ModeloHighscore modelo,
			ViewAgregarJugador view, Integer puntaje) {
		this(modelo, view);
		((ModeloHighscore) getModelo()).setPuntaje(puntaje);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void addListeners() {
		final ViewAgregarJugador viewAgregarJugador = (ViewAgregarJugador) getView();
		viewAgregarJugador.addTextListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					try {
						ModeloHighscore modeloHighscore = (ModeloHighscore) getModelo();
						modeloHighscore.submit(viewAgregarJugador.getNombre());
						WreckItRalph.getInstancia().crearTop();
					} catch (MalInput e2) {
						((ViewAgregarJugador) getView()).setError(e2.getMessage());
					}
					break;
				case KeyEvent.VK_ESCAPE:
					WreckItRalph.getInstancia().crearTop();
				}
			}
		});
	}

}
