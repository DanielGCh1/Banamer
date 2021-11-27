/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascci;

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
public class Ascci extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        String clave = "HJ";
        char letra = clave.charAt(0);
        Integer c = Integer.valueOf(letra);
        
        System.out.println(c); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
