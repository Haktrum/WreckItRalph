package utils;

import utils.eventos.EventoJuegoTerminado;
import utils.eventos.EventoNivelGanado;
import utils.eventos.EventoOffScreen;
import utils.eventos.EventoSeccionGanada;

public interface Actualizable {
	public abstract void actualizar() throws EventoOffScreen, EventoNivelGanado, EventoSeccionGanada, EventoJuegoTerminado;

}
