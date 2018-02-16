package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import modelo.DAOUsuario;
import modelo.Usuario;

/**
 *
 * @author Fabian
 */
public class RegistroControlador implements Initializable {

    @FXML
    private TextField fieldnombre, fieldapellido, fieldusuario, fieldpass;
    @FXML
    private Button btn_cerrar;
    @FXML
    private AnchorPane root;

    /**
     * Registra un usuario en la base de datos.
     *
     * @param MouseEvente e
     */
    @FXML
    private void registrar(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            String nombres = fieldnombre.getText();
            String apellidos = fieldapellido.getText();
            String usuario = fieldusuario.getText();
            String pass = fieldpass.getText();
            if (validarCampos(nombres, apellidos, usuario, pass)) {

                DAOUsuario dao_usuario = new DAOUsuario();
                Usuario user = new Usuario();

                user.setNombres(nombres);
                user.setApellidos(apellidos);
                user.setUsername(usuario);
                user.setPassword(pass);

                if (dao_usuario.insertar(user)) {
                    abrirVentanaLogin(e);
                }
            }
        }
    }

    /**
     * Abre una ventana nueva de login
     *
     * @param MouseEvent e
     */
    @FXML
    private void abrirVentanaLogin(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            try {
                hacerFadeOut();
            } catch (Exception r) {
                System.out.println("Error : " + r);
            }
        }
    }

    /**
     * Crea el efecto visual FadeOut y abre una nueva escena
     */
    private void hacerFadeOut() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(900));
        fade.setNode(root);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished((ActionEvent event) -> {
            try {
                Stage stage = (Stage) root.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/vista/Login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (Exception e) {
            }

        });
        fade.play();
    }

    /**
     * Valida los campos digitados por el usuario que no esten vacios
     *
     * @param String nombre
     * @param String apellido
     * @param String usuario
     * @param String pass
     * @return boolean
     */
    private boolean validarCampos(String nombre, String apellido, String usuario, String pass) {
        if (!nombre.replace(" ", "").isEmpty() && !apellido.replace(" ", "").isEmpty() && !usuario.replace(" ", "").isEmpty() && !pass.replace(" ", "").isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Cierra la ventana registro
     */
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) btn_cerrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Crea el efecto visual fadeIn que se ejecuta al abrir una nueva escena
     */
    private void hacerFadein() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(800));
        fade.setNode(root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setOpacity(0);
        hacerFadein();
    }

}
