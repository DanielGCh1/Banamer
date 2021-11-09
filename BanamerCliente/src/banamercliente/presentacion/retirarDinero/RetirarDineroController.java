/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.presentacion.retirarDinero;

import banamercliente.main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dgcha
 */
public class RetirarDineroController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnLimpiar;

    // ----------------MVC-----------------------------------------
    private RetirarDineroModel model;
    // ------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = main.retirarDineroModel;
    }    


    @FXML
    private void onActionAceptar(ActionEvent event) {
    }

    @FXML
    private void onActionLimpiar(ActionEvent event) {
    }

    @FXML
    private void onActionRegresar(ActionEvent event) {
        Stage vistaPrestamos = (Stage) btnRegresar.getScene().getWindow();
        vistaPrestamos.close();
        model.getStage().show();
    }
    
}
