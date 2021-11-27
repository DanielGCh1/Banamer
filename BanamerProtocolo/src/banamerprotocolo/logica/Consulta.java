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
public class Consulta implements Serializable{
    private int accion;
    private Usuario usuario;
    private int saldo;
    private String clave;
    private String claveNueva;

    public Consulta(int accion, String clave, String claveNueva) {
        this.accion = accion;
        this.clave = clave;
        this.claveNueva = claveNueva;
    }

    public Consulta(int accion, Usuario usuario) {
        this.accion = accion;
        this.usuario = usuario;
    }

    public Consulta(int accion, int saldo) {
        this.accion = accion;
        this.saldo = saldo;
    }

    public Consulta(int accion, String clave) {
        this.accion = accion;
        this.clave = clave;
    }

    public Consulta(int accion) {
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }
    
}
