/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.retirarDinero;

import banamercliente.logica.ClaseObserver;
import banamercliente.logica.Service;
import banamercliente.main;
import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Respuesta;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dgcha
 */
public class RetirarDineroController extends ClaseObserver implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextField textFielSaldo;
    @FXML
    private TextField textFielMontoRetirar;
    @FXML
    private TextField textFielResultado;
    @FXML
    private Button btnConsultarSaldo;

    // ----------------MVC-----------------------------------------
    private RetirarDineroModel model;

    // ------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = main.retirarDineroModel;
        consultarSaldo();
        model.addObserver(this);
        model.commit();
    }

    @Override
    public void update(Observable o, Object arg) {
        textFielSaldo.setText(String.valueOf(model.getUsuario().getSaldo()));
        textFielMontoRetirar.setText(String.valueOf(model.getMontoRetirar()));
        textFielResultado.setText(model.getResultado());
    }

    @FXML
    private void onActionAceptar(ActionEvent event) {
        try {
            model.setMontoRetirar(Integer.valueOf(textFielMontoRetirar.getText()));
            if (model.getMontoRetirar() == null || model.getMontoRetirar() < 0) {
                model.setMontoRetirar(0);
            }
        } catch (Exception e) {
            model.setMontoRetirar(0);
        }
        
        Boolean datosCorrectos = true;
        
        if(model.getMontoRetirar() <= 0){
            datosCorrectos = false;
        }
        
        if(datosCorrectos){
            try {
                Respuesta respuesta = Service.instance().retiro(model.getMontoRetirar());
                if (respuesta.getAccion() == Protocolo.ok) {
                    JOptionPane.showMessageDialog(null, "Retire su dinero");
                    model.setResultado("Transaccion procesada, saldo retirado= " + model.getMontoRetirar());
                    model.getUsuario().setSaldo(respuesta.getSaldo());
                    model.commit();
                } else {
                    if (respuesta.getAccion() == Protocolo.saldoInsuficiente) {
                        model.setResultado("Dinero insuficiente");
                        model.setMontoRetirar(0);
                        consultarSaldo();
                        model.commit();
                    } else {
                        model.setResultado("Error al hacer el retiro");
                    }
                }
            } catch (Exception e) {
                
            }
        }
        else{
            model.setResultado("Error al ingresar el monto a retir");
            model.commit();
        }
        
    }

    @FXML
    private void onActionLimpiar(ActionEvent event) {
        consultarSaldo();
        model.setMontoRetirar(0);
        model.setResultado("");
        model.commit();
    }

    @FXML
    private void onActionRegresar(ActionEvent event) {
        Stage vistaPrestamos = (Stage) btnRegresar.getScene().getWindow();
        main.principalModel.setUsuario(model.getUsuario());
        vistaPrestamos.close();
        model.getStage().show();
    }

    private void consultarSaldo() {
        Respuesta respuesta = Service.instance().consultarSaldo(model.getUsuario());
        try {
            if (respuesta.getAccion() == Protocolo.ok) {
                model.getUsuario().setSaldo(respuesta.getSaldo());
            } else {
                JOptionPane.showMessageDialog(null, "Error a la hora de consultar el saldo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error a la hora de consultar el saldo " + e.getMessage());
        }
    }

    @FXML
    private void OnActionActualizarSaldo(ActionEvent event) {
        consultarSaldo();
        try {
            model.setMontoRetirar(Integer.valueOf(textFielMontoRetirar.getText()));
            if (model.getMontoRetirar() == null || model.getMontoRetirar() < 0) {
                model.setMontoRetirar(0);
            }
        } catch (Exception e) {
            model.setMontoRetirar(0);
        }
        try {
            model.setResultado(textFielResultado.getText());
            if (model.getResultado() == null || model.getResultado().isEmpty()) {
                model.setResultado("");
            }
        } catch (Exception e) {
            model.setResultado("");
        }
        model.commit();
    }

}
