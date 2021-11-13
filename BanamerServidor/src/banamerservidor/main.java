/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerservidor;

import banamerprotocolo.logica.Usuario;
import banamerservidor.data.UsuarioDao;
import banamerservidor.logica.Service;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dgcha
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola2");
        try {
            /*List<Usuario> resultado = Service.instance().findAll();
            for (int i = 0; i < resultado.size(); i++) {
                System.out.println("Usuario: "+ resultado.get(i).toString());
            }*/
            Usuario usuario = new Usuario(1, "5Mu$kr24");
            //usuario = Service.instance().login(usuario);
            UsuarioDao ud = new UsuarioDao();
            usuario = ud.login(usuario);
            System.out.println("Usuario: "+ usuario.toString());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
