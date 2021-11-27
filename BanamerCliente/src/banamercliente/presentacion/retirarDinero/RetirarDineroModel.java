/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.retirarDinero;

import banamerprotocolo.logica.Usuario;
import java.util.Observable;
import java.util.Observer;
import javafx.stage.Stage;

/**
 *
 * @author dgcha
 */
public class RetirarDineroModel extends Observable {

    private Stage stage;
    private Usuario usuario;
    private Integer montoRetirar;
    private String resultado;

    public RetirarDineroModel() {
        this.montoRetirar = 0;
        this.resultado = "";
    }

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

    public void setMontoRetirar(Integer montoRetirar) {
        this.montoRetirar = montoRetirar;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Integer getMontoRetirar() {
        return montoRetirar;
    }

    public String getResultado() {
        return resultado;
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
