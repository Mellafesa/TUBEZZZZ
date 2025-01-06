import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class homePageAdminController {
    @FXML private TableView<BeasiswaClass> tableBeasiswa;
    @FXML private Button btnInsert;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnVerifikasi;

    @FXML
    private TableColumn<BeasiswaClass, String> colIDBeasiswa;
    @FXML
    private TableColumn<BeasiswaClass, String> colNamaBeasiswa;
    @FXML
    private TableColumn<BeasiswaClass, Integer> colJumlahDana;
    @FXML
    private TableColumn<BeasiswaClass, String> colDeskripsi;

    
    private AdminClass admin;
    
    @FXML
    private void initialize() {
        // Initialize components
        colIDBeasiswa.setCellValueFactory(new PropertyValueFactory<>("idBeasiswa"));
        colNamaBeasiswa.setCellValueFactory(new PropertyValueFactory<>("namaBeasiswa"));
        colJumlahDana.setCellValueFactory(new PropertyValueFactory<>("jumlahDana"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
    }
    
    @FXML
    private void handleInsertData(ActionEvent event) {
        admin.insert_data();
    }
    
    @FXML
    private void handleUpdateData(ActionEvent event) {
        admin.update_data();
    }
    
    @FXML
    private void handleDeleteData(ActionEvent event) {
        admin.delete_data();
    }
    
    @FXML
    private void handleVerifikasi(ActionEvent event) {
        admin.verifikasi_mahasiswa();
    }
}