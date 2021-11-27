/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.login;

import banamercliente.logica.ClaseObserver;
import banamercliente.logica.Service;
import banamercliente.main;
import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Consulta;
import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.text.PlainView;

/**
 * FXML Controller class
 *
 * @author dgcha
 */
public class LoginController extends ClaseObserver implements Initializable {

    @FXML
    private TextField textFieldUsuario;
    @FXML
    private PasswordField passFieldContrase;

    @FXML
    private Button btnSalir;
    @FXML
    private Button btnEntrar;

    // ----------------MVC-----------------------------------------
    private LoginModel model;
    // ------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = main.loginModel;
        model.setUsuario(new Usuario());
        model.addObserver(this);
        model.commit();
        /*Platform.runLater(
                    new Runnable() {         // nuevo evento, para que el hilo principal modifique la interfaz grafica
                public void run() {  // y no un hilo secundario
                    //controller.animate();
                }
            }
        );*/
    }

    @Override
    public void update(Observable o, Object arg) {
        Usuario usuario = model.getUsuario();
        textFieldUsuario.setText(usuario.getClave());
        passFieldContrase.setText(usuario.getClave());
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
        Stage vistaActual = (Stage) btnSalir.getScene().getWindow();
        vistaActual.close();
    }

    @FXML
    private void onActionEntrar(ActionEvent event) {
        try {
            Boolean datosCorrectos = true;
            Usuario u = new Usuario();

            u.setId(Integer.valueOf(textFieldUsuario.getText()));
            u.setClave(passFieldContrase.getText());

            if (u.getId() == null || textFieldUsuario.getText().isEmpty()) {
                datosCorrectos = false;
            }
            if (u.getClave() == null || passFieldContrase.getText().isEmpty()) {
                datosCorrectos = false;
            }
            if (datosCorrectos) {
                System.out.println("Id usuario: " + u.getId());
                System.out.println("Clave usuario: " + u.getClave());
                
                
                Respuesta respuesta = Service.instance().login(u);
                if (respuesta.getAccion() == Protocolo.ok) {
                    main.principalModel.setUsuario(respuesta.getUsuario());
                    entrar();
                } else {
                    if (respuesta.getAccion() == Protocolo.usuarioNoExiste) {
                        JOptionPane.showMessageDialog(null, "El usuario no existe");
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error en los campos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error ingresando los datos " + e.getMessage());
        }
    }

    public void entrar() {
        try {
            Stage ventanaPrincipal = (Stage) btnEntrar.getScene().getWindow();

            main.principalModel.setStage(ventanaPrincipal);

            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/banamercliente/presentacion/pantallaPrincipal/PantallaPrincipal.fxml"));
            Parent root = fxmloader.load();
            Stage stage = new Stage();
            stage.setTitle(main.principalModel.getUsuario().getNombre() + " Ced: " + main.principalModel.getUsuario().getId());
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
