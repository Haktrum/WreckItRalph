package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import utils.Modelo;
import view.ViewMenu;

public class ControladorMenu extends Controlador {

	public ControladorMenu(Modelo modelo, ViewMenu view) {
		super(modelo, view);
		addListeners();
	}

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
		
		viewMenu.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				viewMenu.seleccionar(e.getComponent());
			}
		});
	}

	private class MiKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			ViewMenu viewMenu = (ViewMenu) getView();
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				viewMenu.flechaArriba();
				break;
			case KeyEvent.VK_DOWN:
				viewMenu.flechaAbajo();
				break;
			case KeyEvent.VK_LEFT:
				viewMenu.flechaIzq();
				break;
			case KeyEvent.VK_RIGHT:
				viewMenu.flechaDer();
				break;
			case KeyEvent.VK_ENTER:
				viewMenu.enter();
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}