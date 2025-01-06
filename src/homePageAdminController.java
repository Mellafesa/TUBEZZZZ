import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class homePageAdminController {
    @FXML private TableView<BeasiswaClass> tableBeasiswa;
    @FXML private Button btnTambah;
    @FXML private Button btnUpdate;
    @FXML private Button btnHapus;
    @FXML private Button btnVerifikasi;

    @FXML
    private TableColumn<BeasiswaClass, String> colIDBeasiswa;
    @FXML
    private TableColumn<BeasiswaClass, String> colNamaBeasiswa;
    @FXML
    private TableColumn<BeasiswaClass, Float> colJumlahDana;
    @FXML
    private ComboBox<String> jenisBeasiswaBox;
    @FXML
    private TableColumn<BeasiswaClass, String> colDeskripsi;
    @FXML
    private TextArea txtDeskripsi;
    @FXML
    private TextField txtIDBeasiswa;
    @FXML
    private TextField txtJumlahDana;
    @FXML
    private TextField txtNamaBeasiswa;

    @FXML private TableView<PengajuanClass> tablePengajuan;
    @FXML private TableColumn<PengajuanClass, Integer> colIDPengajuan;
    @FXML private TableColumn<PengajuanClass, String> colNIM;
    @FXML private TableColumn<PengajuanClass, String> colStatus;

    private ObservableList<BeasiswaClass> beasiswaList;

    @FXML
    private void initialize() {
        ArrayList<BeasiswaClass> loadedBeasiswa = BeasiswaFileManager.loadBeasiswa();
        beasiswaList = FXCollections.observableArrayList();
        tableBeasiswa.setItems(beasiswaList);

        // Inisialisasi tabel beasiswa
        colIDBeasiswa.setCellValueFactory(new PropertyValueFactory<>("id_beasiswa"));
        colNamaBeasiswa.setCellValueFactory(new PropertyValueFactory<>("nama_beasiswa"));
        colJumlahDana.setCellValueFactory(new PropertyValueFactory<>("jumlah_dana"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        jenisBeasiswaBox.setItems(FXCollections.observableArrayList("Akademik", "Non-Akademik"));

        // Inisialisasi tabel pengajuan
        colIDPengajuan.setCellValueFactory(new PropertyValueFactory<>("id_pengajuan"));
        colNIM.setCellValueFactory(new PropertyValueFactory<>("nim_mhs"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("accepted"));

        
        refreshTables();
    }

    @FXML
    private void addBeasiswa(ActionEvent event) {
        try {
            // Validasi input
            if (txtIDBeasiswa.getText().isEmpty()) throw new MissingFieldException("ID tidak boleh kosong!");
            if (txtNamaBeasiswa.getText().isEmpty()) throw new MissingFieldException("Nama Beasiswa tidak boleh kosong!");
            if (txtJumlahDana.getText().isEmpty()) throw new MissingFieldException("Jumlah Dana tidak boleh kosong!");
            if (jenisBeasiswaBox.getValue() == null) throw new MissingFieldException("Jenis Beasiswa harus dipilih!");
            if (txtDeskripsi.getText().isEmpty()) throw new MissingFieldException("Deskripsi tidak boleh kosong!");

            // Konversi jumlah dana
            float jumlahDana = Float.parseFloat(txtJumlahDana.getText());

            // Buat objek beasiswa berdasarkan jenis beasiswa
            BeasiswaClass beasiswa;
            if (jenisBeasiswaBox.getValue().equals("Akademik")) {
                beasiswa = new BeasiswaAkademikClass(
                    Integer.parseInt(txtIDBeasiswa.getText()),
                    txtNamaBeasiswa.getText(),
                    jumlahDana,
                    txtDeskripsi.getText(),
                    3.0f // Contoh nilai IPK minimal
                );
            } else {
                beasiswa = new BeasiswaNonAkademikClass(
                    Integer.parseInt(txtIDBeasiswa.getText()),
                    txtNamaBeasiswa.getText(),
                    jumlahDana,
                    txtDeskripsi.getText(),
                    "Prestasi Non-Akademik"
                );
            }

            // Tambahkan ke daftar dan simpan
            beasiswaList.add(beasiswa);
            tableBeasiswa.refresh();
            clearBeasiswa();
            BeasiswaFileManager.saveBeasiswa(new ArrayList<>(beasiswaList));

        } catch (MissingFieldException | NumberFormatException e) {
            showError(e.getMessage());
        }
    }

    private void clearBeasiswa() {
        txtIDBeasiswa.clear();
        txtNamaBeasiswa.clear();
        txtJumlahDana.clear();
        jenisBeasiswaBox.getSelectionModel().clearSelection();
        txtDeskripsi.clear();
    }

    @FXML
    private void updateBeasiswa(ActionEvent event) {
        // TODO: Implementasi update data beasiswa
    }

    @FXML
    private void deleteBeasiswa(ActionEvent event) {
        // TODO: Implementasi hapus data beasiswa
    }

    private void refreshTables() {
        ArrayList<BeasiswaClass> loadedBeasiswa = BeasiswaFileManager.loadBeasiswa();
        beasiswaList.setAll(loadedBeasiswa);
        // Refresh tabel beasiswa
        tableBeasiswa.getItems().clear();
        tableBeasiswa.getItems().addAll(beasiswaList);

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error!");
        alert.showAndWait();
    }

    public static class MissingFieldException extends Exception {
        public MissingFieldException(String message) {
            super(message);
        }
    }

    public static class TaskNotSelectedException extends Exception {
        public TaskNotSelectedException(String message) {
            super(message);
        }
    }

    public static class InvalidDateException extends Exception {
        public InvalidDateException(String message) {
            super(message);
        }
    }

    public static class MissingDateException extends Exception {
        public MissingDateException(String message) {
            super(message);
        }
    }
}
