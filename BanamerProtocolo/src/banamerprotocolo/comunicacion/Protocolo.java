/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerprotocolo.comunicacion;

/**
 *
 * @author dgcha
 */
public class Protocolo {
    
    public static final String SERVER = "localhost";
    public static final int PORT = 1234;
    
    public static final int login = 1;
    public static final int cambiarClave= 2;
    public static final int  retirarSaldo= 3;
    public static final int  consultarSaldo= 4;
    public static final int  salir = 5;
    
    public static final int ok= 6;
    
    
    public static final int error =0;
    
    public static final int claveIncorrecta= 1;
    public static final int saldoInsuficiente = 2;
    public static final int usuarioNoExiste = 3;
}
