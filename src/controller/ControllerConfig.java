package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import juego.Modelo;
import view.View;
import view.ViewConfig;

public class ControllerConfig extends Controller {

	public ControllerConfig(Modelo modelo, View view) {
		super(modelo, view);
		if (!(view instanceof ViewConfig)) {
			throw new IllegalArgumentException();
		}
		
		addListeners();
		
		view.actualizarVista();
	}

	private void addListeners() {
		final ViewConfig viewConfig = (ViewConfig) getView();
		
		viewConfig.addIncListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int nivel = getModelo().getNivel();
				if (nivel < 10) {
					getModelo().setNivel(nivel + 1);
					viewConfig.actualizarVista();
				}
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				viewConfig.setIncFocused();
			}
		});
		
		viewConfig.addDecListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int nivel = getModelo().getNivel();
				if (nivel > 0) {
					getModelo().setNivel(nivel - 1);
					viewConfig.actualizarVista();
				}
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				viewConfig.setDecFocused();
			}
		});
	}

}
