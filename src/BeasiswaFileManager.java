import java.io.*;
import java.util.ArrayList;


// BeasiswaFileManager.java
public class BeasiswaFileManager {
    private static final String AKADEMIK_FILE = "beasiswa_akademik.txt";
    private static final String NONAKADEMIK_FILE = "beasiswa_nonakademik.txt";
 
    public static void saveBeasiswa(ArrayList<BeasiswaClass> beasiswa) {
        ArrayList<BeasiswaAkademikClass> akademik = new ArrayList<>();
        ArrayList<BeasiswaNonAkademikClass> nonAkademik = new ArrayList<>();
        
        // Pisahkan berdasarkan tipe class
        for(BeasiswaClass b : beasiswa) {
            if(b instanceof BeasiswaAkademikClass) {
                akademik.add((BeasiswaAkademikClass)b);
            } else if(b instanceof BeasiswaNonAkademikClass) {
                nonAkademik.add((BeasiswaNonAkademikClass)b);
            }
        }
 
        // Save ke file terpisah
        saveBeasiswa(akademik, AKADEMIK_FILE);
        saveBeasiswa(nonAkademik, NONAKADEMIK_FILE);
    }
 
    private static void saveBeasiswa(ArrayList<?> beasiswa, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Object b : beasiswa) {
                if(b instanceof BeasiswaAkademikClass) {
                    BeasiswaAkademikClass bea = (BeasiswaAkademikClass)b;
                    writer.write(String.format("%d;%s;%.2f;%s;%.2f",
                        bea.getnama_beasiswa(),
                        bea.getjumlah_dana(),
                        bea.getdeskripsi(),
                        bea.getMin_IPK()));
                } else if(b instanceof BeasiswaNonAkademikClass) {
                    BeasiswaNonAkademikClass bea = (BeasiswaNonAkademikClass)b;
                    writer.write(String.format("%d;%s;%.2f;%s;%s",
                        bea.getnama_beasiswa(),
                        bea.getjumlah_dana(), 
                        bea.getdeskripsi(),
                        bea.getBidang_beasiswa()));
                }
                writer.newLine();
            }
            writer.flush(); 
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
 
    public static ArrayList<BeasiswaClass> loadBeasiswa() {
        ArrayList<BeasiswaClass> beasiswa = new ArrayList<>();
        beasiswa.addAll(loadFromFile(AKADEMIK_FILE, true));
        beasiswa.addAll(loadFromFile(NONAKADEMIK_FILE, false));
        return beasiswa;
    }
 
    private static ArrayList<BeasiswaClass> loadFromFile(String filename, boolean isAkademik) {
        ArrayList<BeasiswaClass> beasiswa = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if(isAkademik) {
                    beasiswa.add(new BeasiswaAkademikClass(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Float.parseFloat(parts[2]),
                        parts[3],
                        Float.parseFloat(parts[4])));
                } else {
                    beasiswa.add(new BeasiswaNonAkademikClass(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Float.parseFloat(parts[2]),
                        parts[3],
                        parts[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return beasiswa;
    }
 }