/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerprotocolo.logica;

import java.util.Objects;

/**
 *
 * @author dgcha
 */
public class Usuario {
    Integer id;
    String nombre;
    String clave;
    Integer saldo;

    public Usuario(Integer id, String clave) {
        this.id = id;
        this.clave = clave;
    }

    public Usuario(Integer id, String nombre, String clave, Integer saldo) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.saldo = saldo;
    }

    public Usuario() {
        this.id=0;
        this.clave="";
    }

    public Integer getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }     

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", saldo=" + saldo + '}';
    }
    
}
