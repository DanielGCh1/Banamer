/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerprotocolo.logica;

import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class Respuesta implements Serializable{
    private int accion;
    private Usuario usuario;
    private int saldo;

    public Respuesta(int accion, Usuario usuario) {
        this.accion = accion;
        this.usuario = usuario;
    }

    public Respuesta(int accion, int saldo) {
        this.accion = accion;
        this.saldo = saldo;
    }

    public Respuesta(int accion) {
        this.accion = accion;
    }

    public int getAccion() {
        return accion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
