import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class homePageUserController {
    @FXML private Label lblUserName;
    @FXML private TableView<BeasiswaClass> tableBeasiswa;
    @FXML private Button btnDaftar;

    private AdminClass admin;
    
    private MahasiswaClass penerima;
    
    @FXML
    private void initialize() {
        // Initialize components
        
    }
    
    @FXML
    private void handleDaftarBeasiswa(ActionEvent event) {
        // penerima.daftar_beasiswa();
    }
    
    public void setPenerima(MahasiswaClass penerima) {
        this.penerima = penerima;
        updateUserInfo();
    }
    
    private void updateUserInfo() {
        // lblUserName.setText(penerima.nama);
    }
}