package controlador;

import core.Cliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Fabian
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextArea area_mensaje;
    @FXML
    private AnchorPane pane_mensaje;
    @FXML
    private Label lbl_user;

    static String usuario;

    @FXML
    private void enviarMensaje(MouseEvent e) {
        String strmensaje = area_mensaje.getText();
        if (e.getButton() == MouseButton.PRIMARY && !strmensaje.isEmpty()) {
            TextArea mensaje = new TextArea(strmensaje);
            pane_mensaje.getChildren().add(mensaje);
            Cliente cliente = new Cliente();
            cliente.enviarTexto(strmensaje);
            area_mensaje.setText(strmensaje);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("Texarea = " + area_mensaje);
        lbl_user.setText(usuario);
    }
}
