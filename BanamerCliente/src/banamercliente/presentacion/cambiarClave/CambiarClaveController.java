/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.cambiarClave;

import banamercliente.logica.ClaseObserver;
import banamercliente.logica.Service;
import banamercliente.main;
import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dgcha
 */
public class CambiarClaveController extends ClaseObserver implements Initializable {

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnRegresar;

    // ----------------MVC-----------------------------------------
    private CambiarClaveModel model;
    // ------------------------------------------------------------
    @FXML
    private PasswordField passFieldActual;
    @FXML
    private PasswordField passFieldNuevaClave;
    @FXML
    private PasswordField passFieldConfirmarClave;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = main.claveModel;
        model.addObserver(this);
        model.commit();
    }

    @Override
    public void update(Observable o, Object arg) {
        passFieldActual.setText("");
        passFieldNuevaClave.setText(model.getClaveNueva());
        passFieldConfirmarClave.setText(model.getConfirmarClave());
    }

    public Boolean claveSegura(String clave) {
        Boolean esSegura = false;
        Boolean tieneMayus = false;
        Boolean tieneMinus = false;
        Boolean tieneNume = false;
        Boolean tieneCaracEspeci = false;
        Boolean tieneEspacios = false;

        for (int i = 0; i < clave.length(); i++) {
            char letra = clave.charAt(i);
            if (esMayuscula(letra)) {
                tieneMayus = true;
            } else {
                if (esMinuscula(letra)) {
                    tieneMinus = true;
                } else {
                    if (esNumero(letra)) {
                        tieneNume = true;
                    } else {
                        if (esCaracterEspecial(letra)) {
                            tieneCaracEspeci = true;
                        } else {
                            if (tieneUnEspacio(letra)) {
                                tieneEspacios = true;
                            }
                        }
                    }
                }
            }
        }

        if (tieneMayus && tieneMinus && tieneNume && tieneCaracEspeci && (clave.length() >= 8) && !tieneEspacios) {
            esSegura = true;
        }
        return esSegura;
    }

    public Boolean esMayuscula(char letra) {
        Boolean esMayu = false;
        Integer i = Integer.valueOf(letra);

        if ((i >= 65 && i <= 90) || (i >= 142 && i <= 144) || (i >= 153 && i <= 154) || (i == 165) || (i >= 181 && i <= 183) || (i == 128)) {
            esMayu = true;
        }
        if ((i == 199) || (i >= 209 && i <= 212) || (i == 224) || (i >= 226 && i <= 227) || (i == 229) || (i >= 234 && i <= 235) || (i == 237)) {
            esMayu = true;
        }

        return esMayu;
    }

    public Boolean esMinuscula(char letra) {
        Boolean esMinus = false;
        Integer i = Integer.valueOf(letra);

        if ((i >= 97 && i <= 122) || (i >= 129 && i <= 141) || (i >= 147 && i <= 152) || (i >= 160 && i <= 164) || (i == 198)) {
            esMinus = true;
        }
        if ((i == 228) || (i == 236)) {
            esMinus = true;
        }

        return esMinus;
    }

    public Boolean esNumero(char letra) {
        Boolean esMinus = false;
        Integer i = Integer.valueOf(letra);

        if ((i >= 48 && i <= 57)) {
            esMinus = true;
        }

        return esMinus;
    }

    public Boolean esCaracterEspecial(char letra) {
        Boolean esCaracEspecial = true;
        Integer i = Integer.valueOf(letra);

        if ((i >= 0 && i <= 32) || (i == 127)) {
            esCaracEspecial = false;
        }

        return esCaracEspecial;
    }

    public Boolean tieneUnEspacio(char letra) {
        Boolean tieneEspacio = false;
        Integer i = Integer.valueOf(letra);

        if ((i == 127)) {
            tieneEspacio = true;
        }

        return tieneEspacio;
    }

    @FXML
    private void onActionAceptar(ActionEvent event) {
        try {
            model.setClaveActual(passFieldActual.getText());
            if (model.getClaveActual() == null || model.getClaveActual().isEmpty()) {
                model.setClaveActual("");
            }
        } catch (Exception e) {
            model.setClaveActual("");
        }
        try {
            model.setClaveNueva(passFieldNuevaClave.getText());
            if (model.getClaveNueva() == null || model.getClaveNueva().isEmpty()) {
                model.setClaveNueva("");
            }
        } catch (Exception e) {
            model.setClaveNueva("");
        }
        try {
            model.setConfirmarClave(passFieldConfirmarClave.getText());
            if (model.getConfirmarClave() == null || model.getConfirmarClave().isEmpty()) {
                model.setConfirmarClave("");
            }
        } catch (Exception e) {
            model.setConfirmarClave("");
        }
        try {
            System.out.println("Clave actual: " + model.getClaveActual());
            System.out.println("Clave nueva: " + model.getClaveNueva());
            System.out.println("Clave confirmacion: " + model.getConfirmarClave());

            if (model.getClaveNueva().equals(model.getConfirmarClave())) {
                if (!model.getClaveActual().equals("") && model.getClaveActual() != null) {
                    if (claveSegura(model.getClaveNueva())) {
                        Respuesta respuesta = Service.instance().cambiarClave(model.getClaveActual(), model.getClaveNueva());
                        if (respuesta.getAccion() == Protocolo.ok) {

                            JOptionPane.showMessageDialog(null, "Clave modificada exitosamente");
                            model.setClaveNueva("");
                            model.setConfirmarClave("");

                            // resgreso a loguin
                            respuesta = Service.instance().salir();
                            
                            if (respuesta.getAccion() == Protocolo.ok) {
                                JOptionPane.showMessageDialog(null, "El usuario " + model.getUsuario().getNombre() + " salio con exito.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al salir");
                            }
                            
                            Stage vistaActual = (Stage) btnRegresar.getScene().getWindow();
                            main.loginModel.setUsuario(new Usuario());
                            vistaActual.close();
                            model.getStage().close();
                            main.principalModel.getStage().show();
                            main.loginModel.commit();
                            // ----------------------------------------------

                        } else {
                            JOptionPane.showMessageDialog(null, "La contraseña actual es incorrecta");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La nueva clave no es segura");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la contraseña actual");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La clave nueva y la confirmación no son iguales");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + e.getMessage());
        }
    }

    @FXML
    private void onActionLimpiar(ActionEvent event) {
        model.setClaveNueva("");
        model.setConfirmarClave("");
        model.commit();
    }

    @FXML
    private void onActionRegresar(ActionEvent event) {
        Stage vistaPrestamos = (Stage) btnRegresar.getScene().getWindow();
        vistaPrestamos.close();
        model.getStage().show();
    }
}
