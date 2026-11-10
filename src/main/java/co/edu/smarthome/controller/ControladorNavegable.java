package co.edu.smarthome.controller;

import co.edu.smarthome.service.ContextoAplicacion;
import javafx.fxml.FXML;

/** Funciones de navegacion compartidas por las vistas */
public abstract class ControladorNavegable {
    protected final ContextoAplicacion contexto;
    protected final NavegadorHogar navegador;

    protected ControladorNavegable(ContextoAplicacion contexto, NavegadorHogar navegador){
        this.contexto = contexto;
        this.navegador = navegador;
    }

    @FXML
    public final void irAInicio() {
        navegador.mostrarInicio();
    }

    @FXML
    public final void irADispositivos() {
        navegador.mostrarDispositivos();
    }

    @FXML
    public final void irAAutomatizaciones() {
        navegador.mostrarAutomatizaciones();
    }

    @FXML
    public final void irAHistorial() {
        navegador.mostrarHistorial();
    }

}
