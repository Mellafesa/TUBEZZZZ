import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TableView<BeasiswaClass> tableBeasiswa;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnHapus;
    @FXML
    private Button btnVerifikasi;

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
    private TableColumn<BeasiswaClass, String> colJenisBeasiswa;
    @FXML
    private TextArea txtDeskripsi;
    @FXML
    private TextField txtIDBeasiswa;
    @FXML
    private TextField txtJumlahDana;
    @FXML
    private TextField txtNamaBeasiswa;

    private ObservableList<BeasiswaClass> beasiswaList;

    // TABEL PENGAJUAN:
    @FXML
    private TableView<PengajuanClass> tablePengajuan;
    @FXML
    private TableColumn<PengajuanClass, Integer> colIDPengajuan;
    @FXML
    private TableColumn<PengajuanClass, Integer> colIDBeasiswaDipilih;
    @FXML
    private TableColumn<PengajuanClass, String> colNIM;
    @FXML
    private TableColumn<PengajuanClass, String> colStatus;

    private ObservableList<PengajuanClass> pengajuanList;

    @FXML
    private void initialize() {
        initializeBeasiswa();
        initializePengajuan();
    }

    private void initializeBeasiswa() {
        ArrayList<BeasiswaClass> loadedBeasiswa = BeasiswaFileManager.loadBeasiswa();
        beasiswaList = FXCollections.observableArrayList(loadedBeasiswa);
        tableBeasiswa.setItems(beasiswaList);

        // Inisialisasi tabel beasiswa
        colIDBeasiswa.setCellValueFactory(new PropertyValueFactory<>("id_beasiswa"));
        colNamaBeasiswa.setCellValueFactory(new PropertyValueFactory<>("nama_beasiswa"));
        colJumlahDana.setCellValueFactory(new PropertyValueFactory<>("jumlah_dana"));
        colJenisBeasiswa.setCellValueFactory(new PropertyValueFactory<>("jenis_beasiswa"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        jenisBeasiswaBox.setItems(FXCollections.observableArrayList("Akademik", "Non-Akademik"));
        refreshTables();
    }

    private void initializePengajuan() {
        ArrayList<PengajuanClass> loadedPengajuan = PengajuanFileManager.loadPengajuan();
        pengajuanList = FXCollections.observableArrayList(loadedPengajuan);
        tablePengajuan.setItems(pengajuanList);

        // Inisialisasi tabel pengajuan
        colIDPengajuan.setCellValueFactory(new PropertyValueFactory<>("id_pengajuan"));
        colIDBeasiswaDipilih.setCellValueFactory(new PropertyValueFactory<>("id_beasiswa"));
        colNIM.setCellValueFactory(new PropertyValueFactory<>("nim_mhs"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("accepted"));
    }

    @FXML
    private void addBeasiswa(ActionEvent event) {
        try {
            // Validasi input
            if (txtIDBeasiswa.getText().isEmpty())
                throw new MissingFieldException("ID tidak boleh kosong!");
            if (txtNamaBeasiswa.getText().isEmpty())
                throw new MissingFieldException("Nama Beasiswa tidak boleh kosong!");
            if (txtJumlahDana.getText().isEmpty())
                throw new MissingFieldException("Jumlah Dana tidak boleh kosong!");
            if (jenisBeasiswaBox.getValue() == null)
                throw new MissingFieldException("Jenis Beasiswa harus dipilih!");
            if (txtDeskripsi.getText().isEmpty())
                throw new MissingFieldException("Deskripsi tidak boleh kosong!");

            float jumlahDana = Float.parseFloat(txtJumlahDana.getText());

            // Buat objek beasiswa berdasarkan jenis beasiswa
            BeasiswaClass beasiswa;
            if (jenisBeasiswaBox.getValue().equals("Akademik")) {
                beasiswa = new BeasiswaAkademikClass(
                        Integer.parseInt(txtIDBeasiswa.getText()),
                        txtNamaBeasiswa.getText(),
                        jumlahDana,
                        jenisBeasiswaBox.getValue(),
                        txtDeskripsi.getText(),
                        3.0f // Contoh nilai IPK minimal
                );
            } else {
                beasiswa = new BeasiswaNonAkademikClass(
                        Integer.parseInt(txtIDBeasiswa.getText()),
                        txtNamaBeasiswa.getText(),
                        jumlahDana,
                        jenisBeasiswaBox.getValue(),
                        txtDeskripsi.getText(),
                        "Prestasi Non-Akademik");
            }

            // Tambahkan ke daftar dan simpan
            beasiswaList.add(beasiswa);
            BeasiswaFileManager.saveBeasiswa(new ArrayList<>(beasiswaList));
            tableBeasiswa.refresh();
            clearBeasiswa();

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
        try {

            BeasiswaClass selected = tableBeasiswa.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new TaskNotSelectedException("Message Error");
            }
            // Validasi input
            if (txtIDBeasiswa.getText().isEmpty())
                throw new MissingFieldException("ID tidak boleh kosong!");
            if (txtNamaBeasiswa.getText().isEmpty())
                throw new MissingFieldException("Nama Beasiswa tidak boleh kosong!");
            if (txtJumlahDana.getText().isEmpty())
                throw new MissingFieldException("Jumlah Dana tidak boleh kosong!");
            if (jenisBeasiswaBox.getValue() == null)
                throw new MissingFieldException("Jenis Beasiswa harus dipilih!");
            if (txtDeskripsi.getText().isEmpty())
                throw new MissingFieldException("Deskripsi tidak boleh kosong!");

            // Perbarui nilai dari item yang dipilih
            selected.setId_beasiswa(Integer.parseInt(txtIDBeasiswa.getText()));
            selected.setNama_beasiswa(txtNamaBeasiswa.getText());
            selected.setJumlah_dana(Float.parseFloat(txtJumlahDana.getText()));
            selected.setJenis_beasiswa(jenisBeasiswaBox.getValue());
            selected.setDeskripsi(txtDeskripsi.getText());

            // Refresh tabel agar perubahan langsung terlihat
            tableBeasiswa.refresh();

            // Simpan perubahan ke file
            BeasiswaFileManager.saveBeasiswa(new ArrayList<>(beasiswaList));

            // Bersihkan input form setelah update
            clearBeasiswa();
        } catch (MissingFieldException | TaskNotSelectedException | NumberFormatException e) {
            showError(e.getMessage());
        }

    }

    @FXML
    private void deleteBeasiswa(ActionEvent event) {
        try {

            BeasiswaClass selected = tableBeasiswa.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new TaskNotSelectedException("Message Error");
            }
            tableBeasiswa.getItems().remove(selected);
            BeasiswaFileManager.saveBeasiswa(new ArrayList<>(tableBeasiswa.getItems()));
        } catch (TaskNotSelectedException e) {
            showError(e.getMessage());
        }

    }

    @FXML
    void showBeasiswa(MouseEvent event) {
        BeasiswaClass selected = tableBeasiswa.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtIDBeasiswa.setText(String.valueOf(selected.getId_beasiswa()));
            txtNamaBeasiswa.setText(selected.getNama_beasiswa());
            txtJumlahDana.setText(String.valueOf(selected.getJumlah_dana()));
            jenisBeasiswaBox.setValue(selected.getJenis_beasiswa());
            txtDeskripsi.setText(selected.getDeskripsi());
        }
    }

    private void refreshTables() {
        ArrayList<BeasiswaClass> loadedBeasiswa = BeasiswaFileManager.loadBeasiswa();
        beasiswaList = FXCollections.observableArrayList(loadedBeasiswa);
        tableBeasiswa.setItems(beasiswaList);
    }

    @FXML
    void verifikasiPengajuan(ActionEvent event) {
        try {
            PengajuanClass selected = tablePengajuan.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new TaskNotSelectedException("Message Error");
            }
            selected.setAccepted(true);

            // Refresh tabel agar perubahan langsung terlihat
            tablePengajuan.refresh();
            // Simpan perubahan ke file
            PengajuanFileManager.savePengajuan(new ArrayList<>(pengajuanList));

            // TODOS: 
            // 1. TRACK STATUS APAKAH BEASISWA UDAH ADA YANG AMBIL ATAU BELUM
            // 2. SHOW NAMA MAHASISWA YANG AMBIL DARI GABUNGIN 2 TABEL


        } catch (TaskNotSelectedException e) {
            showError(e.getMessage());
        }
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
