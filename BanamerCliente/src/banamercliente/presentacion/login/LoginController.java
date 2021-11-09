/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.login;

import banamercliente.main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dgcha
 */
public class LoginController implements Initializable {

    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldContrase;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnEntrar;
    
    // ----------------MVC-----------------------------------------
    private LoginModel model;
    // ------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionSalir(ActionEvent event) {
    }

    @FXML
    private void onActionEntrar(ActionEvent event) {
        if(true){
            entrar();
        }        
    }
    public void entrar(){
        try {
            Stage ventanaPrincipal = (Stage) btnEntrar.getScene().getWindow();

            main.principalModel.setStage(ventanaPrincipal);
            //main.loginModel.setCliente(model.getCliente());

            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/banamercliente/presentacion/pantallaPrincipal/PantallaPrincipal.fxml"));
            Parent root = fxmloader.load();
            Stage stage = new Stage();
            stage.setTitle(textFieldUsuario.getText()/*model.getCliente().getNombre() + " Ced: " + model.getCliente().getCedula()*/);
            Scene sc = new Scene(root);
            stage.setScene(sc);
            //sc.getStylesheets().add("ajedrez/vistas/EstilosPantallaJuego.css");
            stage.getIcons().add(new Image("/banamercliente/recursos/Banco.png"));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();           //Consumar el evento
                }
            });
            stage.show();
            ventanaPrincipal.hide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
