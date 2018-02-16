package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.DAOUsuario;
import modelo.Usuario;

/**
 *
 * @author Fabian
 */
public class LoginControlador implements Initializable {

    @FXML
    private Button btn_cerrar;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField field_usuario;
    @FXML 
    private PasswordField field_password;

    /**
     * Cierra la ventana login
     *
     */
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) btn_cerrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Abre una ventana nueva de registro
     */
    @FXML
    private void abrirVentanaRegistro(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            try {
                hacerFadeOut();

            } catch (Exception ex) {
            }
        }
    }

    /**
     * Crea el efecto visual fadeIn que se ejecuta al abrir una nueva escena
     */
    private void hacerFadein() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    /**
     * Crea el efecto visual FadeOut y abre una nueva escena
     */
    private void hacerFadeOut() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(root);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished((ActionEvent event) -> {
            try {
                Stage stage = (Stage) root.getScene().getWindow();
                Parent parent = FXMLLoader.load(getClass().getResource("/vista/Registro.fxml"));
                Scene scene = new Scene(parent);
                stage.setScene(scene);
            } catch (Exception e) {
            }

        });
        fade.play();
    }

    @FXML
    private void ingresar(MouseEvent e) {

        String username = field_usuario.getText();
        String password = field_password.getText();
        if (e.getButton() == MouseButton.PRIMARY) {
            if (verificarField(username, password)) {
                DAOUsuario dao_usuario = new DAOUsuario();
                Usuario user = new Usuario();

                user.setUsername(username);
                user.setPassword(password);

                dao_usuario.seleccionar(user);

                if (user.getNombres() != null && user.getApellidos() != null) {
                    abrirVentanaChat();
                }
            }
        }
    }

    private void abrirVentanaChat() {
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("/vista/FXMLDocument.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException ex) {
           
        }
    }

    private boolean verificarField(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setOpacity(0);
        hacerFadein();
    }

}
