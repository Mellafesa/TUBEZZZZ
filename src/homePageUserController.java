import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class homePageUserController {

    @FXML
    private Button btnPengajuan;

    @FXML private TableView<BeasiswaClass> tableBeasiswa;

    @FXML
    private TableColumn<BeasiswaClass, String> colNamaBeasiswa;
    @FXML
    private TableColumn<BeasiswaClass, Float> colJumlahDana;

    @FXML
    private TableColumn<BeasiswaClass, String> colDeskripsi;

    @FXML
    private TableColumn<BeasiswaClass, String> colJenisBeasiswa;


    private ObservableList<BeasiswaClass> beasiswaList;

    @FXML
    private void initialize() {
        
        ArrayList<BeasiswaClass> loadedBeasiswa = BeasiswaFileManager.loadBeasiswa();
        beasiswaList = FXCollections.observableArrayList(loadedBeasiswa);
        tableBeasiswa.setItems(beasiswaList);

        // Inisialisasi tabel beasiswa
        colNamaBeasiswa.setCellValueFactory(new PropertyValueFactory<>("nama_beasiswa"));
        colJumlahDana.setCellValueFactory(new PropertyValueFactory<>("jumlah_dana"));
        colJenisBeasiswa.setCellValueFactory(new PropertyValueFactory<>("jenis_beasiswa"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        
        refreshTables();
    }

    //FUngsi untuk mengajukan beasiswa (syntax ini ada di file homePageUserController.java)
    @FXML
    void addPengajuan(ActionEvent event) {
        try{
            BeasiswaClass selected = tableBeasiswa.getSelectionModel().getSelectedItem();
            if(selected != null) {
                PengajuanClass pengajuan = new PengajuanClass(0, selected.getId_beasiswa(),  String.valueOf(1020223300), false);
                PengajuanFileManager.addPengajuan(pengajuan);
                

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pengajuan beasiswa");
                alert.setContentText("Beasiswa berhasil diajukan!");
                alert.showAndWait();
                tableBeasiswa.refresh();
            }
            
            
        }catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Kesalahan input data");
            alert.setContentText("Ulangi lagi");
            alert.showAndWait();
        }
        
    }
    //Fungsi untuk melihat data beasiswa yang diambil dari database txt (syntax ini ada di file homePageUserController.java)
    private void refreshTables() {
        ArrayList<BeasiswaClass> loadedBeasiswa = BeasiswaFileManager.loadBeasiswa();
        beasiswaList = FXCollections.observableArrayList(loadedBeasiswa);
        tableBeasiswa.setItems(beasiswaList);
    }

}
