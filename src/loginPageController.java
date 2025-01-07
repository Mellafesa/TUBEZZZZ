import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class loginPageController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void LoginFunction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        // Validasi input kosong
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Login Gagal", "Username dan password tidak boleh kosong!");
            return;
        }
        
        // Cek login sebagai admin
        if (username.equals("admin") && password.equals("admin123")) {
            openDashboard("Admin", "homePageAdmin.fxml");
            return;
        }
        
        // Cek login sebagai user
        if (username.equals("user") && password.equals("user123")) {
            openDashboard("User", "homePageUser.fxml");
            return;
        }
        
        // Jika login gagal
        showAlert("Error", "Login Gagal", "Username atau password salah!");
    }
    
    private void openDashboard(String userType, String fxmlFile) {
        try {
            // Load file FXML dashboard
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            System.err.println(loader);
            Parent root = loader.load();
            System.err.println("TES FLAG2 ==================");
            
            // Buat scene baru
            Stage stage = new Stage();
            stage.setTitle(userType + " Dashboard");
            stage.setScene(new Scene(root));
            
            // Tutup window login
            Stage loginStage = (Stage) txtUsername.getScene().getWindow();
            loginStage.close();
            // Tampilkan dashboard
            stage.show();
            
        } catch (IOException e) {
            showAlert("Error", "System Error", "Gagal membuka dashboard: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}