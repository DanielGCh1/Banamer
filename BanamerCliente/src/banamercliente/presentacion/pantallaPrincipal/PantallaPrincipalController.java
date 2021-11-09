/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.pantallaPrincipal;

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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dgcha
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private Button btnRetirarDinero;
    @FXML
    private Button btnEditarContrasenna;
    @FXML
    private Button btnRegresar;

    // ----------------MVC-----------------------------------------
    private PantallaPrincipalModel model;
    // ------------------------------------------------------------
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = main.principalModel;
    }    

    @FXML
    private void onActionRetirarDinero(ActionEvent event) {
        try {
            Stage ventanaPrincipal = (Stage) btnRegresar.getScene().getWindow();

            main.retirarDineroModel.setStage(ventanaPrincipal);
            //main.loginModel.setCliente(model.getCliente());

            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/banamercliente/presentacion/retirarDinero/RetirarDinero.fxml"));
            Parent root = fxmloader.load();
            Stage stage = new Stage();
            //stage.setTitle(textFieldUsuario.getText()/*model.getCliente().getNombre() + " Ced: " + model.getCliente().getCedula()*/);
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

    @FXML
    private void onActionEditarContrasenna(ActionEvent event) {
        try {
            Stage ventanaPrincipal = (Stage) btnRegresar.getScene().getWindow();

            main.claveModel.setStage(ventanaPrincipal);
            //main.loginModel.setCliente(model.getCliente());

            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/banamercliente/presentacion/cambiarClave/CambiarClave.fxml"));
            Parent root = fxmloader.load();
            Stage stage = new Stage();
            //stage.setTitle(textFieldUsuario.getText()/*model.getCliente().getNombre() + " Ced: " + model.getCliente().getCedula()*/);
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

    @FXML
    private void onActionRegresar(ActionEvent event) {
        Stage vistaPrestamos = (Stage) btnRegresar.getScene().getWindow();
        vistaPrestamos.close();
        model.getStage().show();
    }
    
}
