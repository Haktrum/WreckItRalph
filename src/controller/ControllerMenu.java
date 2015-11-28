package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import modelos.ModeloJuego;
import control.WreckItRalph;
import view.ViewMenu;

public class ControllerMenu extends Controller {

	public ControllerMenu(ModeloJuego modelo, ViewMenu view) {
		super(modelo, view);
		addListeners();
	}
	
	public void addListeners() {
		final ViewMenu viewMenu = (ViewMenu) getView();
		
		viewMenu.addKeyListener(new MiKeyAdapter(viewMenu));
		
		viewMenu.addConfigListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearConfig();
			}
		});
		
		viewMenu.addJugarListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearJuego();
			}
		});
		
		viewMenu.addReglasListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearReglas();
			}
		});
		
		viewMenu.addTopListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WreckItRalph.getInstancia().crearTop();
			}
		});
		
//		viewMenu.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				viewMenu.seleccionar(e.getComponent());
//			}
//		});
	}

	class MiKeyAdapter extends KeyAdapter {

		private ViewMenu viewMenu;

		public MiKeyAdapter(ViewMenu viewMenu) {
			this.viewMenu = viewMenu;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
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
}
