package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import utils.Modelo;
import view.MainWindow;
import view.ViewMenu;

public class ControladorMenu extends Controlador {

	public ControladorMenu(Modelo modelo, ViewMenu view) {
		super(modelo, view);
		addListeners();
	}

	private int selected = 1;

	public void addListeners() {
		final ViewMenu viewMenu = (ViewMenu) getView();
		
		viewMenu.addKeyListener(new MiKeyAdapter());
		
		viewMenu.agregarListenerConfig(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearConfig();
			}
		});
		
		viewMenu.agregarListenerJugar(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearJuego();
			}
		});
		
		viewMenu.agregarListenerReglas(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearReglas();
			}
		});
		
		viewMenu.agregarListenerTop(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearTop();
			}
		});
		
		/*viewMenu.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				viewMenu.seleccionar(e.getComponent());
			}
		});*/
	}

	private class MiKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (selected < 3 && selected > 0)
					selected--;
				break;
			case KeyEvent.VK_RIGHT:
				if (selected < 2)
					selected++;
				break;
			case KeyEvent.VK_UP:
				selected = 3;
				break;
			case KeyEvent.VK_DOWN:
				if (selected == 3)
					selected = 2;
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}