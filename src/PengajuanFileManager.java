import java.io.*;
import java.util.ArrayList;

// PengajuanFileManager.java
public class PengajuanFileManager {
    private static final String PENGAJUAN_FILE = "pengajuan.txt";

    // Save pengajuan to file
    public static void savePengajuan(ArrayList<PengajuanClass> pengajuanList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PENGAJUAN_FILE))) {
            for (PengajuanClass pengajuan : pengajuanList) {
                writer.write(String.format("%d;%d;%s;%b",
                    pengajuan.getId_pengajuan(),
                    pengajuan.getId_beasiswa(),
                    pengajuan.getNim_mhs(),
                    pengajuan.isAccepted()));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Load pengajuan from file
    public static ArrayList<PengajuanClass> loadPengajuan() {
        ArrayList<PengajuanClass> pengajuanList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PENGAJUAN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int idPengajuan = Integer.parseInt(parts[0]);
                int idBeasiswa = Integer.parseInt(parts[1]);
                String nimMhs = parts[2];
                boolean accepted = Boolean.parseBoolean(parts[3]);
                pengajuanList.add(new PengajuanClass(idPengajuan, idBeasiswa, nimMhs, accepted));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return pengajuanList;
    }

    // Append a single pengajuan to the file
    public static void addPengajuan(PengajuanClass pengajuan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PENGAJUAN_FILE, true))) {
            writer.write(String.format("%d;%d;%s;%b",
                pengajuan.getId_pengajuan(),
                pengajuan.getId_beasiswa(),
                pengajuan.getNim_mhs(),
                pengajuan.isAccepted()));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Update pengajuan status by ID
    public static void updatePengajuanStatus(int idPengajuan, boolean newStatus) {
        ArrayList<PengajuanClass> pengajuanList = loadPengajuan();
        for (PengajuanClass pengajuan : pengajuanList) {
            if (pengajuan.getId_pengajuan() == idPengajuan) {
                pengajuan.setAccepted(newStatus);
                break;
            }
        }
        savePengajuan(pengajuanList);
    }
}
