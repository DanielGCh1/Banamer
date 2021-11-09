/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente;

import banamercliente.presentacion.cambiarClave.CambiarClaveModel;
import banamercliente.presentacion.login.LoginModel;
import banamercliente.presentacion.pantallaPrincipal.PantallaPrincipalModel;
import banamercliente.presentacion.retirarDinero.RetirarDineroModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author dgcha
 */
public class main extends Application {

    public static LoginModel loginModel;
    public static PantallaPrincipalModel principalModel;
    public static CambiarClaveModel claveModel;
    public static RetirarDineroModel retirarDineroModel;

    @Override
    public void start(Stage stage) throws Exception {
        loginModel = new LoginModel();
        principalModel = new PantallaPrincipalModel();
        claveModel = new CambiarClaveModel();
        retirarDineroModel = new RetirarDineroModel();
        
        Parent root = FXMLLoader.load(getClass().getResource("presentacion/login/Login.fxml"));

        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //sc.getStylesheets().add("ajedrez/vistas/EstilosPantallaJuego.css");
        stage.getIcons().add(new Image("/banamercliente/recursos/Login.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
