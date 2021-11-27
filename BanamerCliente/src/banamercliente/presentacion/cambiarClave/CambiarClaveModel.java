/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.cambiarClave;

import banamerprotocolo.logica.Usuario;
import java.util.Observable;
import java.util.Observer;
import javafx.stage.Stage;

/**
 *
 * @author dgcha
 */
public class CambiarClaveModel extends Observable {

    private Stage stage;
    private Usuario usuario;
    private String claveNueva;
    private String confirmarClave;
    private String claveActual;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit() {
        this.setChanged();
        this.notifyObservers();
    }
}
